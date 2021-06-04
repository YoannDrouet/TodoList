package dal;

import bll.bo.Todo;

import java.util.List;

public interface TodoDAO {

    void insert(Todo todo) throws DALException;

    void update(Todo todo) throws DALException;

    void delete(Todo todo) throws DALException;

    List<Todo> selectAll() throws DALException;
}
