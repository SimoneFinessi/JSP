package it.beta80group.stud.dao;

import it.beta80group.stud.model.TestModel;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class ResultSetMapper<T> {

    public List<T> mapResult(ResultSet set) throws SQLException {
        List<T> result = new ArrayList<T>();
        if(!set.wasNull()){
            while (set.next()){
                T row = mapResultRow(set);
                result.add(row);
            }
        }
        return result;
    }

    protected abstract T mapResultRow(ResultSet set) throws SQLException;
}
