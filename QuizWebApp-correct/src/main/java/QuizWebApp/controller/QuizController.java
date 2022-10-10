package QuizWebApp.controller;

import QuizWebApp.domain.*;
import QuizWebApp.service.QuestionService;
import QuizWebApp.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
public class QuizController {
    private QuestionService questionService;
    private ResultService resultService;

    @Autowired
    QuizController(QuestionService questionService, ResultService resultService) {
        this.questionService = questionService;
        this.resultService = resultService;
    }


    @GetMapping("/quiz")
    public String getQuiz(@RequestParam String selectedCategory,
                          HttpServletRequest request,
                          Model model) {

        HttpSession session = request.getSession();

        List<Question> quizQuestions = questionService.getRandomQuestionsByCategory(selectedCategory);

        session.setAttribute("quizQuestions", quizQuestions);
        session.setAttribute("selectedCategory", selectedCategory);

        System.out.println(quizQuestions);

        model.addAttribute("quizQuestions", quizQuestions);
        return "quiz";
    }


    @PostMapping("/quiz")
    public String postQuiz(
                           HttpSession session) {

        return "redirect:/quiz-result";
    }


    @GetMapping("/quiz-question1")
    public String getQuestion1(HttpServletRequest request,
                               @RequestParam(defaultValue = "s") String selectedQ1Choice,
                               Model model) {
        Timestamp quizStartTime = new Timestamp(System.currentTimeMillis());

        HttpSession session = request.getSession();
        List<Question> ss = (List<Question>) session.getAttribute("quizQuestions");
        List<Choice> question1Choices = questionService.getChoicesByQuestionId(ss.get(0).getId());

        session.setAttribute("quizStartTime", quizStartTime);
        session.setAttribute("question1Choices", question1Choices);

        System.out.println(selectedQ1Choice);


        return "quiz-question1";
    }

    @PostMapping("/quiz-question1")
    public String postQuestion1(@RequestParam String selectedQ1Choice,
                                HttpSession session) {
        session.setAttribute("selectedQ1Choice", selectedQ1Choice);
        return "/quiz-question1";
    }

    @GetMapping("/quiz-question2")
    public String getQuestion2(HttpServletRequest request,
                               Model model) {

        HttpSession session = request.getSession();
        List<Question> ss = (List<Question>) session.getAttribute("quizQuestions");
        List<Choice> question2Choices = questionService.getChoicesByQuestionId(ss.get(1).getId());

        session.setAttribute("question2Choices", question2Choices);
        return "quiz-question2";
    }

    @PostMapping("/quiz-question2")
    public String postQuestion2(@RequestParam String selectedQ2Choice,
                                HttpSession session) {
        session.setAttribute("selectedQ2Choice", selectedQ2Choice);
        return "/quiz-question2";
    }

    @GetMapping("/quiz-question3")
    public String getQuestion3(HttpServletRequest request) {

        HttpSession session = request.getSession();
        List<Question> ss = (List<Question>) session.getAttribute("quizQuestions");
        List<Choice> question3Choices = questionService.getChoicesByQuestionId(ss.get(2).getId());

        session.setAttribute("question3Choices", question3Choices);
        return "quiz-question3";
    }

    @PostMapping("/quiz-question3")
    public String postQuestion3(@RequestParam String selectedQ3Choice,
                                HttpSession session) {
        session.setAttribute("selectedQ3Choice", selectedQ3Choice);
        return "/quiz-question3";
    }

    @GetMapping("/quiz-question4")
    public String getQuestion4(HttpServletRequest request) {

        HttpSession session = request.getSession();
        List<Question> ss = (List<Question>) session.getAttribute("quizQuestions");
        List<Choice> question4Choices = questionService.getChoicesByQuestionId(ss.get(3).getId());

        session.setAttribute("question4Choices", question4Choices);
        return "quiz-question4";
    }

    @PostMapping("/quiz-question4")
    public String postQuestion4(@RequestParam String selectedQ4Choice,
                                HttpSession session) {
        System.out.println(selectedQ4Choice);
        session.setAttribute("selectedQ4Choice", selectedQ4Choice);
        return "/quiz-question4";
    }

    @GetMapping("/quiz-question5")
    public String getQuestion5(HttpServletRequest request) {

        HttpSession session = request.getSession();
        List<Question> ss = (List<Question>) session.getAttribute("quizQuestions");
        List<Choice> question5Choices = questionService.getChoicesByQuestionId(ss.get(4).getId());

        session.setAttribute("question5Choices", question5Choices);
        return "quiz-question5";
    }

    @PostMapping("/quiz-question5")
    public String postQuestion5(@RequestParam String selectedQ5Choice,
                                HttpSession session) {
        System.out.println(selectedQ5Choice);
        session.setAttribute("selectedQ5Choice", selectedQ5Choice);
        return "/quiz-question5";
    }

    @GetMapping("/quiz-result")
    public String getQuizResult(HttpSession session,
                                Model model) {
        Timestamp quizStartTime = (Timestamp) session.getAttribute("quizStartTime");
        Timestamp quizEndTime = new Timestamp(System.currentTimeMillis());

        List<Question> quizQuestions = (List<Question>) session.getAttribute("quizQuestions");

        int q1Id = quizQuestions.get(0).getId();
        int q2Id = quizQuestions.get(1).getId();
        int q3Id = quizQuestions.get(2).getId();
        int q4Id = quizQuestions.get(3).getId();
        int q5Id = quizQuestions.get(4).getId();

        List<Choice> question1Choices = questionService.getChoicesByQuestionId(q1Id);
        List<Choice> question2Choices = questionService.getChoicesByQuestionId(q2Id);
        List<Choice> question3Choices = questionService.getChoicesByQuestionId(q3Id);
        List<Choice> question4Choices = questionService.getChoicesByQuestionId(q4Id);
        List<Choice> question5Choices = questionService.getChoicesByQuestionId(q5Id);

        List<Boolean> questionResults = new ArrayList<>();

        String q1UserChoice = (String) session.getAttribute("selectedQ1Choice");
        String q2UserChoice = (String) session.getAttribute("selectedQ2Choice");
        String q3UserChoice = (String) session.getAttribute("selectedQ3Choice");
        String q4UserChoice = (String) session.getAttribute("selectedQ4Choice");
        String q5UserChoice = (String) session.getAttribute("selectedQ5Choice");

        boolean q1Result = questionService.checkAns(q1UserChoice, q1Id);
        boolean q2Result = questionService.checkAns(q2UserChoice, q2Id);
        boolean q3Result = questionService.checkAns(q3UserChoice, q3Id);
        boolean q4Result = questionService.checkAns(q4UserChoice, q4Id);
        boolean q5Result = questionService.checkAns(q5UserChoice, q5Id);


        System.out.println("q1UserChoice: " + q1UserChoice);
        System.out.println("q1CorrectAnswer: " + questionService.getAnswerByQuestionId(q1Id));
        System.out.println("q1Result: " + q1Result);
        questionResults.add(q1Result);
        questionResults.add(q2Result);
        questionResults.add(q3Result);
        questionResults.add(q4Result);
        questionResults.add(q5Result);

        int quizPoint = 0;

        for (boolean result : questionResults) {
            if (result == true) {
                quizPoint++;
            }
        }

        boolean isPass = quizPoint >= 3 ? true : false;

        model.addAttribute("q1Result", q1Result);
        model.addAttribute("q2Result", q2Result);
        model.addAttribute("q3Result", q3Result);
        model.addAttribute("q4Result", q4Result);
        model.addAttribute("q5Result", q5Result);
        model.addAttribute("isPass", isPass);
        model.addAttribute("quizStartTime", quizStartTime);
        model.addAttribute("quizEndTime", quizEndTime);

        String category = (String) session.getAttribute("selectedCategory");

        User currentUser = (User) session.getAttribute("user");
        QuizResult quizResult = new QuizResult(category, currentUser.getId(), quizStartTime, quizEndTime, isPass);

        resultService.createNewQuizResult(quizResult);

        int qrId = resultService.getLatestId();

        List<QuestResult> questResults = new ArrayList<>();

        questResults.add(new QuestResult(qrId, q1Id, q1UserChoice));
        questResults.add(new QuestResult(qrId, q2Id, q2UserChoice));
        questResults.add(new QuestResult(qrId, q3Id, q3UserChoice));
        questResults.add(new QuestResult(qrId, q4Id, q4UserChoice));
        questResults.add(new QuestResult(qrId, q5Id, q5UserChoice));

        resultService.createNewQuestResults(questResults);

        return "quiz-result";
    }


}
