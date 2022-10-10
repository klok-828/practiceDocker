package QuizWebApp.controller;

import QuizWebApp.domain.Contact;
import QuizWebApp.domain.Feedback;
import QuizWebApp.domain.User;
import QuizWebApp.service.ContactService;
import QuizWebApp.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;

@Controller
public class ContactController {
    private final ContactService contactService;

    @Autowired
    ContactController(ContactService contactService) { this.contactService = contactService; }

    @GetMapping("/contact")
    public String getContact() {
        return "contact";
    }

    @PostMapping("/contact")
    public String postContact(@RequestParam String firstName,
                              @RequestParam String lastName,
                              @RequestParam String subject,
                              @RequestParam String content,
                              HttpSession session) {

        User user = (User) session.getAttribute("user");
        Contact contact = new Contact(firstName, lastName, subject, content, user.getId());

        contactService.createNewContact(contact);

        return "redirect:/home";
    }
}
