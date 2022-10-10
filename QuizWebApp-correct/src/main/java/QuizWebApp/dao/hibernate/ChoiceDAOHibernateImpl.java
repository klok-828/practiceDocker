package QuizWebApp.dao.hibernate;

import QuizWebApp.dao.AbstractHibernateDAO;
import QuizWebApp.dao.ChoiceDAO;
import QuizWebApp.domain.Choice;
import QuizWebApp.domain.hiberntae.ChoiceHibernate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository("choiceDAOHibernateImpl")
public class ChoiceDAOHibernateImpl extends AbstractHibernateDAO<ChoiceHibernate> implements ChoiceDAO {

    public ChoiceDAOHibernateImpl() {
        setClazz(ChoiceHibernate.class);
    }

    @Override
    public String getAnswerByQuestionId(int id) {
        List<ChoiceHibernate> list = this.getAll();
        list = list.stream().filter(ch -> ch.getQuestion_id() == id && ch.is_answer())
                .collect(Collectors.toList());
        System.out.println(list.get(0).getDescription());
        return list.get(0).getDescription();
    }

    @Override
    public void createNewChoices(List<Choice> choiceList) {
        for (int i = 0; i < choiceList.size(); i++) {
            add((ChoiceHibernate) choiceList.get(i));
        }
    }

    @Override
    public List<Choice> getChoicesByQuestionId(int id) {
        List<ChoiceHibernate> list = this.getAll();
        list = list.stream().filter(ch -> ch.getQuestion_id() == id).collect(Collectors.toList());

        List<Choice> choices = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            choices.add(list.get(i));
        }

        return choices;
    }
}
