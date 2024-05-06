package it.beta80group.stud.services;

import it.beta80group.stud.dao.DataSource;
import it.beta80group.stud.dao.Testdao;
import it.beta80group.stud.model.TestModel;
import it.beta80group.stud.servlet.HelloWorldServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.SQLException;
import java.util.List;

public class HelloWorldService  {
    private DataSource dataSource;
    private static HelloWorldService INSTANCE = null;
    final Logger logger = LogManager.getLogger(HelloWorldService.class);
    protected HelloWorldService(){
        dataSource = DataSource.getInstance();
    }

    public static HelloWorldService getInstance(){
        if(INSTANCE == null){
            INSTANCE = new HelloWorldService();
        }
        return INSTANCE;
    }

    public void save(String username, String password) throws SQLException {
        logger.info("CALLED save({},{})", username, password);
        TestModel model = new TestModel();
        model.setUsername(username);
        model.setPassword(password);
        Testdao.save(model);
    }

    public List<TestModel> list() throws SQLException {
        logger.info("CALLED list()");
        List<TestModel> all = Testdao.getAll();
        return all;
    }
    
    public TestModel getById(Long id) throws SQLException {
        TestModel byId = Testdao.getById(id);
        return byId;
    }

    public void delete(TestModel m) throws SQLException {
        Testdao.delete(m);
    }

    public void update(TestModel testModel) throws SQLException {
        Testdao.update(testModel);
    }
}
