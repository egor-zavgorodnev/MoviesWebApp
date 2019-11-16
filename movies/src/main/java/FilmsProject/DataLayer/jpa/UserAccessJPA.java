package FilmsProject.DataLayer.jpa;

import FilmsProject.Model.User;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
public class UserAccessJPA {

    private EntityManagerFactory factory = Persistence.createEntityManagerFactory("movies");
    private EntityManager manager = factory.createEntityManager();

    public UserAccessJPA() {
    }

    public List<User> getAll()
    {
        TypedQuery<User> q = manager.createQuery(
                "Select c from User c", User.class);
        return  q.getResultList();
    }

}