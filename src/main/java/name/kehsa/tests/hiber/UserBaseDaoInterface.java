package name.kehsa.tests.hiber;

import org.springframework.data.repository.CrudRepository;

/**
 * user dao interface.
 * Author Kehsa.
 * Created on 12/29/16.
 */

interface UserBaseDaoInterface extends CrudRepository<User, Integer> {
    User addNew(String s);
    void close();
    void flush();
}
