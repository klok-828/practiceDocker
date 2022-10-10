package QuizWebApp.controller;

import QuizWebApp.domain.Feedback;
import QuizWebApp.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Controller
public class FeedbackController {

    @Resource(name = "feedbackService")
    private final FeedbackService feedbackService;

    @Autowired
    FeedbackController(FeedbackService feedbackService) { this.feedbackService = feedbackService; }

    @GetMapping("/feedback")
    public String getFeedback() {
        return "feedback";
    }

    @PostMapping("/feedback")
    public String createNewFeedback(@RequestParam int rating,
                                    @RequestParam String content) throws Exception {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        Feedback feedback = new Feedback(rating, content, currentTime);

        feedbackService.createNewFeedback(feedback);

        return "redirect:/login";
    }
}
