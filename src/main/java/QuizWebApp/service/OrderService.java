package QuizWebApp.service;

import QuizWebApp.dao.OrderDAO;
import QuizWebApp.domain.Order;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class OrderService {
    private OrderDAO orderHibernateDAO;

    @Autowired
    public void setOrderHibernateDAO(OrderDAO orderHibernateDAO) {
        this.orderHibernateDAO = orderHibernateDAO;
    }

    @Transactional
    public List<Order> getAllOrders() { return orderHibernateDAO.getAllOrder(); }

    @Async("taskExecutor")
    @Transactional
    public CompletableFuture<List<Order>> getAllOrdersAsync() {
        List<Order> orderList = orderHibernateDAO.getAllOrder();
        return CompletableFuture.completedFuture(orderList);
    }
}
