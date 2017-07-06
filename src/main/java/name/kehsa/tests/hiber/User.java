package name.kehsa.tests.hiber;

import org.hibernate.annotations.NamedNativeQueries;
import org.hibernate.annotations.NamedNativeQuery;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Hibernate db entity - user
 * Author Kehsa.
 * Created 2016-12-28.
 */

@NamedNativeQueries({
        @NamedNativeQuery(
                name = "getAll",
                query = "SELECT * FROM testjava.user",
                resultClass = User.class
        )
})
@Entity
@Table(name = "user", schema = "testjava")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int mId;

    @Column(name = "login", length = 40, nullable = false, unique = true)
    private String mValue;

    @Column(name = "pass", length = 20, nullable = false)
    private String mPass = "qwerty";

    public int getId() {
        return mId;
    }

    public void setId(int mId) {
        this.mId = mId;
    }

    public String getLogin() {
        return mValue;
    }

    public void setLogin(String mValue) {
        this.mValue = mValue;
    }

    public String getPass() {
        return mPass;
    }

    public void setPass(String mPass) {
        this.mPass = mPass;
    }
}
