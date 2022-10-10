package QuizWebApp.service;

import QuizWebApp.dao.FeedbackDAO;
import QuizWebApp.dao.hibernate.FeedbackDAOHibernateImpl;
import QuizWebApp.domain.Feedback;
import QuizWebApp.domain.hiberntae.FeedbackHibernate;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class FeedbackServiceTest {

    @Mock
    private FeedbackDAO feedbackDAOHibernate;

    @InjectMocks
    private FeedbackService feedbackService;

    @Before
    public void initMocks(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test_getAllFeedbacks_success() {
        List<Feedback> expected = new ArrayList<>();
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        expected.add(new Feedback(1, "wqeweqw", currentTime));
        Mockito.when(feedbackDAOHibernate.getAllFeedback()).thenReturn(expected);
        assertEquals(expected, feedbackService.getAllFeedbacks());
    }

    @Test
    public void test_getAllFeedbacks_successWhenEmpty() {
        List<Feedback> expected = new ArrayList<>();
        Mockito.when(feedbackDAOHibernate.getAllFeedback()).thenReturn(expected);
        assertEquals(expected, feedbackService.getAllFeedbacks());
    }

    @Test
    public void test_createNewFeedback_success() throws Exception {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        FeedbackHibernate expected = FeedbackHibernate.builder().rating(2)
                                                        .content("fwefwefw")
                                                        .fb_date(currentTime)
                                                        .build();

        Mockito.when(feedbackDAOHibernate.isFeedbackIdValid(expected.getId())).thenReturn(true);

        Mockito.when(feedbackDAOHibernate.createNewFeedback(expected)).thenReturn(expected);
        Feedback actual = feedbackService.createNewFeedback(expected);

        Mockito.verify(feedbackDAOHibernate, Mockito.times(1)).createNewFeedback(expected);
        assertEquals(expected, actual);
    }

    @Test
    public void test_createNewFeedback_failedOnDuplicatedId() {
        Timestamp currentTime = new Timestamp(System.currentTimeMillis());

        FeedbackHibernate expected = FeedbackHibernate.builder()
                .id(1)
                .rating(2)
                .content("fwefwefw")
                .fb_date(currentTime)
                .build();

        Mockito.when(feedbackDAOHibernate.isFeedbackIdValid(expected.getId())).thenReturn(false);
        assertThrows(Exception.class, () -> feedbackService.createNewFeedback(expected));
    }



}
