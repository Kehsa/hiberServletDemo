package name.kehsa.tests.hiber;

import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

/**
 * user dao.
 * Author Kehsa.
 * Created on 12/29/16.
 */

@Service("userDao")
@Repository
@Transactional
public class UserBaseDao implements UserBaseDaoInterface {
    @PersistenceContext
    private EntityManager em;

    public User addNew(String str) {
        User obj = new User();
        obj.setLogin(str);
        obj = save(obj);
        return obj;
    }

    public <S extends User> S save(S s) {
        if (s.getId() == 0) {
            em.persist(s);
        } else {
            s = em.merge(s);
        }
        return s;
    }

    public <S extends User> Iterable<S> save(Iterable<S> ite) {
        for (S s : ite) {
            save(s);
        }
        return ite;
    }

    public User findOne(Integer integer) {
        return em.find(User.class, integer);
    }

    public boolean exists(Integer integer) {
        return em.find(User.class, integer) != null;
    }

    public Iterable<User> findAll() {
        TypedQuery<User> res = em.createNamedQuery("getAll", User.class);
        return res.getResultList();
    }

    public Iterable<User> findAll(Iterable<Integer> iterable) {
        List<User> res = new LinkedList<User>();
        for (Integer integer : iterable) {
            res.add(findOne(integer));
        }
        return res;
    }

    public long count() {
        return em.createNamedQuery("getAll", User.class).getResultList().size();
    }

    public void delete(Integer integer) {
        em.remove(findOne(integer));
    }

    public void delete(User user) {
        em.remove(user);
    }

    public void delete(Iterable<? extends User> iterable) {
        for (User user : iterable) {
            delete(user);
        }
    }

    public void deleteAll() {
        delete(findAll());
    }

    public void close() {
        em.close();
    }

    public void flush() {
        em.flush();
    }
}
