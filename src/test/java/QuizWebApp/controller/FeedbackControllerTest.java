package QuizWebApp.controller;

import QuizWebApp.domain.Feedback;
import QuizWebApp.domain.hiberntae.FeedbackHibernate;
import QuizWebApp.service.FeedbackService;
import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = FeedbackController.class)
public class FeedbackControllerTest {

    @Mock
    private FeedbackService feedbackService;

    private FeedbackController feedbackController;


    @Autowired
    private MockMvc mockMvc;


    @Before
    public void setup() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/jsp/view/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.openMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(new FeedbackController(feedbackService))
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void test_getFeedback() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/feedback"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("feedback"));
    }

    @Test
    public void test_postFeedback() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/feedback")
                        .param("rating", String.valueOf(2))
                        .param("content", "wfwe"))
                .andExpect(MockMvcResultMatchers.status().is3xxRedirection())
                .andExpect(MockMvcResultMatchers.view().name("redirect:/login"));
    }

//    @Test
//    public void test_createNewFeedback() throws Exception {
//        FeedbackHibernate expected = FeedbackHibernate.builder().
//                rating(2).content("fwefw").build();
//
//        Mockito.when(feedbackService.createNewFeedback(expected)).thenReturn(expected);
//
//        Gson gson = new Gson();
//        String expectedJson = gson.toJson(expected);
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/feedback")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(expectedJson))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(MockMvcResultMatchers.content().json(expectedJson));
//    }
}
