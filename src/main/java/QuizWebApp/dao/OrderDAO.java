package QuizWebApp.dao;

import QuizWebApp.domain.Feedback;
import QuizWebApp.domain.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> getAllOrder();
}
