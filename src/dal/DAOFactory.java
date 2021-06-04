package dal;

import dal.jdbc.TodoDAOJdbcImpl;

public class DAOFactory {
    private static TodoDAO todoDAO = new TodoDAOJdbcImpl();

    public static TodoDAO getTodoDAO(){
        return todoDAO;
    }
}
