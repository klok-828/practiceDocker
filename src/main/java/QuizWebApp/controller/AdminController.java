package QuizWebApp.controller;

import QuizWebApp.domain.*;
import QuizWebApp.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    private final UserService userService;
    private final QuestionService questionService;

    private final ContactService contactService;
    private final ResultService resultService;
    private final FeedbackService feedbackService;

    @Autowired
    AdminController(UserService userService, ResultService resultService, QuestionService questionService, ContactService contactService, FeedbackService feedbackService) {
        this.userService = userService;
        this.resultService = resultService;
        this.questionService = questionService;
        this.contactService = contactService;
        this.feedbackService = feedbackService;
    }

    @GetMapping("/admin")
    public String getAdmin(Model model,
                           HttpSession session) {
        User user = (User) session.getAttribute("user");

        if (!user.isAdmin()) {
            return "error";
        }

        List<String> questionCategory = questionService.getAllCategory();
        List<Contact> contacts = contactService.getAllContacts();
        List<User> users = userService.getAllUsers();
        List<Question> questions = questionService.getAllQuestions();
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();
        List<QuizResult> quizResults = resultService.getAllQuizResult();

        session.setAttribute("questionCategory", questionCategory);
        session.setAttribute("users", users);

        model.addAttribute("contacts", contacts);
        model.addAttribute("quizResults", quizResults);
        model.addAttribute("questions", questions);
        model.addAttribute("feedbacks", feedbacks);

        return "admin";
    }

    @PostMapping("/admin")
    public String postAdmin(@RequestParam String categoryFilter,
                            @RequestParam int userFilter,
                            Model model) {
        System.out.println(categoryFilter);
        List<User> users = userService.getAllUsers();
        List<Question> questions = questionService.getAllQuestions();
        List<Feedback> feedbacks = feedbackService.getAllFeedbacks();

        List<QuizResult> quizResults = new ArrayList<>();

        System.out.println();
        System.out.println("cate: " + categoryFilter);
        System.out.println("user: " + userFilter);
        // 0 mean all in user filter
        if (categoryFilter != "all" && userFilter == 0) {
            quizResults = resultService.getAllQuizResultByCategory(categoryFilter);
        } else if (categoryFilter != "all" && userFilter != 0) {
            quizResults = resultService.getQuizResultByCategoryAndId(categoryFilter, userFilter);
        } else if (categoryFilter == "all" && userFilter != 0) {
            quizResults = resultService.getQuizResultByUserId(userFilter);
        } else {
            quizResults = resultService.getAllQuizResult();
        }

        System.out.println(userFilter == 12);
        System.out.println(quizResults);

//        if (categoryFilter.equals("all") && userFilter.equals("all")) {
//            quizResults = resultService.getAllQuizResult();
//        } else {
//            quizResults = resultService.getAllQuizResultByCategory(categoryFilter);
//        }

        model.addAttribute("quizResults", quizResults);
        model.addAttribute("users", users);
        model.addAttribute("questions", questions);
        model.addAttribute("feedbacks", feedbacks);
        return "admin";
    }

    @GetMapping("/admin/user/{userid}")
    public String changeUserStatus(@PathVariable int userid) {
        userService.reverseUserStatusById(userid);
        return "redirect:/admin";
    }

    @GetMapping("/admin/question/{questionid}")
    public String changeQuestionStatus(@PathVariable int questionid) {
        questionService.reverseQuestionStatus(questionid);
        return "redirect:/admin";
    }

    @GetMapping("/quizDetails/{id}")
    public String getQuizResultById(@PathVariable int id,
                                    Model model) {
        QuizResult quizResult = resultService.getQuizResultById(id);
        List<QuestResult> questResults = resultService.getQuestResultByQuizId(id);

        int q1Id = questResults.get(0).getQuestionId();
        int q2Id = questResults.get(1).getQuestionId();
        int q3Id = questResults.get(2).getQuestionId();
        int q4Id = questResults.get(3).getQuestionId();
        int q5Id = questResults.get(4).getQuestionId();

        List<Question> quizQuestions = new ArrayList<>();

        for (int i = 0; i < questResults.size(); i++) {
            quizQuestions.add(questionService.getQuestionById(questResults.get(i).getQuestionId()));
        }

        String selectedQ1Choice = questResults.get(0).getUserChoice();
        String selectedQ2Choice = questResults.get(1).getUserChoice();
        String selectedQ3Choice = questResults.get(2).getUserChoice();
        String selectedQ4Choice = questResults.get(3).getUserChoice();
        String selectedQ5Choice = questResults.get(4).getUserChoice();

        List<Choice> question1Choices = questionService.getChoicesByQuestionId(q1Id);
        List<Choice> question2Choices = questionService.getChoicesByQuestionId(q2Id);
        List<Choice> question3Choices = questionService.getChoicesByQuestionId(q3Id);
        List<Choice> question4Choices = questionService.getChoicesByQuestionId(q4Id);
        List<Choice> question5Choices = questionService.getChoicesByQuestionId(q5Id);

        //Add All questions choices
        model.addAttribute("question1Choices", question1Choices);
        model.addAttribute("question2Choices", question2Choices);
        model.addAttribute("question3Choices", question3Choices);
        model.addAttribute("question4Choices", question4Choices);
        model.addAttribute("question5Choices", question5Choices);
        //Add user choice for every questions
        model.addAttribute("selectedQ1Choice", selectedQ1Choice);
        model.addAttribute("selectedQ2Choice", selectedQ2Choice);
        model.addAttribute("selectedQ3Choice", selectedQ3Choice);
        model.addAttribute("selectedQ4Choice", selectedQ4Choice);
        model.addAttribute("selectedQ5Choice", selectedQ5Choice);

        model.addAttribute("quizQuestions", quizQuestions);
        model.addAttribute("quizResult", quizResult);
        return "quiz-result-details";
    }


}
