package name.kehsa.tests.hiber;

import org.springframework.context.support.GenericXmlApplicationContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Hello servlet!
 * Author Kehsa.
 * Created 2016-12-28.
 */

@WebServlet("/")
public class App extends HttpServlet {

    private UserBaseDaoInterface dao;

    @Override
    public void destroy() {
        dao.close();
        super.destroy();
    }

    @Override
    public void init() throws ServletException {
        super.init();

        GenericXmlApplicationContext gxc = new GenericXmlApplicationContext();
        gxc.load("classpath:spring.conf.xml");
        gxc.refresh();

        dao = gxc.getBean("userDao", UserBaseDaoInterface.class);
        dao.addNew("first_user").setPass("Cat");
        dao.addNew("second_user").setPass("Dog");
        dao.addNew("third_user").setPass("CatDog");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        PrintWriter w = resp.getWriter();
        resp.setContentType("text/html");
        w.print("<!Doctype html>\n<html>\n<head>\n" +
                "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "<title>DB viewer</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "<h1>Data base view</h1>" +
                "<table>\n" +
                "<tr> <th>id</th> <th>username</th> <th>password</th> </tr>");

        for (User user : dao.findAll()) {
            w.print("<tr><td>\n" +
                    user.getId() +
                    "\n</td><td>\n" +
                    user.getLogin() +
                    "\n</td><td>\n" +
                    user.getPass() +
                    "\n</td>" +
                    "</tr>");
        }

        w.print("</table>\n</body>\n</html>");
        w.close();
    }
 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {
 
        super.doPost(req, resp);
    }
 
}