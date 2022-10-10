package QuizWebApp.controller;

import QuizWebApp.domain.Choice;
import QuizWebApp.domain.Question;
import QuizWebApp.domain.User;
import QuizWebApp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class QuestionController {
    private QuestionService questionService;


    @Autowired
    public QuestionController(QuestionService questionService) { this.questionService = questionService; }

    @GetMapping("/add-question")
    public String getAddQuestion(Model model) {
        List<String> questionCategory = questionService.getAllCategory();
        model.addAttribute("questionCategory", questionCategory);

        return "add-question";
    }

    @PostMapping("/add-question")
    public String postAddQuestion(@RequestParam String description,
                                  @RequestParam String category,
                                  @RequestParam String choice1,
                                  @RequestParam String choice2,
                                  @RequestParam String choice3,
                                  @RequestParam String choice4,
                                  @RequestParam String choice5,
                                  @RequestParam int answer) {

        Question question = new Question(category, description, true);

        questionService.createQuestion(question);

        int qid = questionService.getLatestId();

        List<Choice> choices = new ArrayList<>();

        choices.add(new Choice(qid, choice1, choices.size() == answer));
        choices.add(new Choice(qid, choice2, choices.size() == answer));
        choices.add(new Choice(qid, choice3, choices.size() == answer));
        choices.add(new Choice(qid, choice4, choices.size() == answer));
        choices.add(new Choice(qid, choice5, choices.size() == answer));

        questionService.createChoices(choices);

        return "redirect:/admin";
    }
}
