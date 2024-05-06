package it.beta80group.stud.dao;

import it.beta80group.stud.model.TestModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
/*
* CREATE TABLE hrportal.dbo.testtable (
	id bigint IDENTITY(0,1) NOT NULL,
	username varchar(100) NULL,
	password varchar(100) NULL,
	CONSTRAINT testtable_PK PRIMARY KEY (id)
);

*
* */
public class Testdao {
    private static final String UPDATE_QUERY = "UPDATE tt SET tt.username = ?, tt.password = ? FROM dbo.testtable tt WHERE id = ?";
    private static final String SAVE_QUERY = "INSERT INTO dbo.testtable(username, password) VALUES(?, ?)";
    private static final String GET_ALL_QUERY = "SELECT * FROM dbo.testtable";
    private static final String GET_BY_ID_QUERY = "SELECT * FROM dbo.testtable WHERE id = ?";
    private static final String DELETE_QUERY = "DELETE FROM dbo.testtable WHERE id = ?";
    public static void save(TestModel model) throws SQLException {
        Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(SAVE_QUERY);
        preparedStatement.setString(1, model.getUsername());
        preparedStatement.setString(2, model.getPassword());
        preparedStatement.execute();
        connection.close();
    }
    public static void delete(TestModel model) throws SQLException {
        Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE_QUERY);
        preparedStatement.setLong(1, model.getId());
        preparedStatement.execute();
        connection.close();
    }
    public static List<TestModel> getAll() throws SQLException{
        Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_QUERY);
        ResultSet resultSet = preparedStatement.executeQuery();
        TestModelResultSetMapper mapper = new TestModelResultSetMapper();
        List<TestModel> testModels = mapper.mapResult(resultSet);
        connection.close();
        return testModels;
    }

    public static TestModel getById(Long id) throws SQLException {
        Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_QUERY);
        preparedStatement.setLong(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        TestModelResultSetMapper mapper = new TestModelResultSetMapper();
        List<TestModel> testModels = mapper.mapResult(resultSet);
        TestModel result = null;
        if(!testModels.isEmpty()){
            result = testModels.get(0);
        }
        connection.close();
        return result;
    }

    public static void update(TestModel testModel) throws SQLException {
        Connection connection = DataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
        preparedStatement.setString(1, testModel.getUsername());
        preparedStatement.setString(2, testModel.getPassword());
        preparedStatement.setLong(3, testModel.getId());
        preparedStatement.execute();
        connection.close();
    }

    static class TestModelResultSetMapper extends ResultSetMapper<TestModel>{
        @Override
        protected TestModel mapResultRow(ResultSet set) throws SQLException {
            TestModel model = new TestModel();
            model.setId(set.getLong(1));
            model.setUsername(set.getString(2));
            model.setPassword(set.getString(3));
            return model;
        }
    }
}
