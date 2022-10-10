package QuizWebApp.controller;

import QuizWebApp.domain.Question;
import QuizWebApp.domain.QuizResult;
import QuizWebApp.domain.User;
import QuizWebApp.service.QuestionService;
import QuizWebApp.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.transform.Result;
import java.util.List;

@Controller
public class HomeController {
    private QuestionService questionService;
    private ResultService resultService;

    @Autowired
    HomeController(QuestionService questionService, ResultService resultService) {
        this.resultService = resultService;
        this.questionService = questionService;
    }

    @GetMapping("/home")
    public String getHome(Model model,
                          HttpSession session,
                          HttpServletRequest request) {
        User currentUser = (User) session.getAttribute("user");

        if (currentUser == null) { return "login"; }

        session.invalidate();
        HttpSession newSession = request.getSession(true);

        newSession.setAttribute("user", currentUser);


        List<String> questionCategory = questionService.getAllCategory();
        List<QuizResult> quizResults = resultService.getQuizResultByUserId(currentUser.getId());

        model.addAttribute("quizResults", quizResults);
        model.addAttribute("questionCategory", questionCategory);

        return "home";
    }

//    @PostMapping("/home")
//    @ResponseBody
//    public String postHome(@RequestParam String selectedCategory) {
//
//        System.out.println(selectedCategory);
//        return selectedCategory;
//    }
}
