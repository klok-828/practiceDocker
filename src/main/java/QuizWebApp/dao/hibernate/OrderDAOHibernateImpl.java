package QuizWebApp.dao.hibernate;

import QuizWebApp.dao.AbstractHibernateDAO;
import QuizWebApp.dao.OrderDAO;
import QuizWebApp.domain.Feedback;
import QuizWebApp.domain.Order;
import QuizWebApp.domain.hiberntae.OrderHibernate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderDAOHibernateImpl extends AbstractHibernateDAO<OrderHibernate> implements OrderDAO {

    public OrderDAOHibernateImpl() { setClazz(OrderHibernate.class); }

    @Override
    public List<Order> getAllOrder() {
        List<OrderHibernate> list = this.getAll();
        return list.stream().map(order -> (Order) order).collect(Collectors.toList());
    }
}
