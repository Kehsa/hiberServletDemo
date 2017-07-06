package name.kehsa.tests.hiber;

import jdk.nashorn.internal.ir.annotations.Ignore;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class UrerDaoTest extends TestCase {
    private UserBaseDaoInterface users;
    /**
     * Create the test case

     * @param testName name of the test case
     */
    public UrerDaoTest(String testName ) {
        super( testName );
        GenericXmlApplicationContext gxc = new GenericXmlApplicationContext();
        gxc.load("classpath:spring.conf.xml");
        gxc.refresh();
        users = gxc.getBean("userDao", UserBaseDaoInterface.class);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite( UrerDaoTest.class );
    }

    public void testAdd() {
        String username = "someuser";
        User user = users.addNew(username);
        assertEquals(username, users.findOne(user.getId()).getLogin());
    }

    /*
    public void testFindAll() {
        users.deleteAll();
        users.addNew("q");
        users.addNew("w");
        users.addNew("e");
        users.addNew("r");
        users.addNew("t");
        users.addNew("y");
        users.flush();

        List<Integer> list = Arrays.asList(0, 1, 2, 3, 4, 5);
        Iterable<User> res = users.findAll(list);

        assertNotNull(res);

        Iterator<Integer> idIterator = list.iterator();
        for (User user : res) {
            if (idIterator.hasNext()) {
                Integer next = idIterator.next();
                int id = user.getId();
                assertEquals(((int) next), id);
            }
        }
    }*/

    public void testDelete() {
        users.addNew("user");
        users.deleteAll();
        assertEquals(0, users.count());
    }
}
