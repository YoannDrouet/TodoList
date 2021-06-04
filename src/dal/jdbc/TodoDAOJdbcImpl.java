package dal.jdbc;

import bll.bo.Todo;
import dal.DALException;
import dal.TodoDAO;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Classe permettant d'envoyer des requêtes SQL à la base de données SQLite
 *
 * @author Yoann Drouet
 */
public class TodoDAOJdbcImpl implements TodoDAO {

    private final String SQL_INSERT = "INSERT INTO todo(date, texte) VALUES (?,?);";
    private final String SQL_UPDATE = "UPDATE todo SET date=?, texte=? WHERE id=?;";
    private final String SQL_DELETE = "DELETE FROM todo WHERE id=?;";
    private final String SQL_SELECT_ALL = "SELECT * FROM todo ORDER BY date;";

    /**
     * Insère dans la base de données la chose à faire que l'on met en paramètre
     * @param todo Ce que l'on souhaite rajouter dans la TodoList
     * @throws DALException
     */
    @Override
    public void insert(Todo todo) throws DALException {
        try (PreparedStatement unStm = JDBCTools.getConnection().prepareStatement((SQL_INSERT))){
            unStm.setDate(1, todo.getDate()==null ? null : Date.valueOf(todo.getDate()));
            unStm.setString(2, todo.getTexte());
            unStm.executeUpdate();
            //Permet de récupérer l'id qui est généré automatique
            if (unStm.getGeneratedKeys().next()) {
                todo.setId(unStm.getGeneratedKeys().getInt(1));
            }
        } catch (SQLException throwables) {
            throw new DALException("DAL - L'insertion a échoué");
        }
    }

    /**
     * Modifie une chose à faire qui est déjà dans la base de données
     * @param todo Ce que l'on souhaite modifier dans la TodoList
     * @throws DALException
     */
    @Override
    public void update(Todo todo) throws DALException {
        try (PreparedStatement unStm = JDBCTools.getConnection().prepareStatement((SQL_UPDATE))){
            unStm.setDate(1, todo.getDate()==null ? null : Date.valueOf(todo.getDate()));
            unStm.setString(2, todo.getTexte());
            unStm.setInt(3, todo.getId());
            unStm.executeUpdate();
        } catch (SQLException throwables) {
            throw new DALException("DAL - La modification a échoué");
        }
    }

    /**
     * Supprime une chose à faire de la base de données
     * @param todo Ce que l'on souhaite supprimer dans la TodoList
     * @throws DALException
     */
    @Override
    public void delete(Todo todo) throws DALException {
        try (PreparedStatement unStm = JDBCTools.getConnection().prepareStatement((SQL_DELETE))){
            unStm.setInt(1, todo.getId());
            unStm.executeUpdate();
        } catch (SQLException throwables) {
            throw new DALException("DAL - La suppression a échoué");
        }
    }

    /**
     * Récupère une liste de chose à faire dans la base de données
     * @return L'intégralité de la TodoList
     * @throws DALException
     */
    @Override
    public List<Todo> selectAll() throws DALException {
        List<Todo> listeTodo = new ArrayList<>();
        try (PreparedStatement unStm = JDBCTools.getConnection().prepareStatement((SQL_SELECT_ALL))){
            ResultSet rs = unStm.executeQuery();
            //Ajoute un à un les éléments de la base de données dans la liste
            while (rs.next()){
                listeTodo.add(new Todo(rs.getInt("id"),
                        rs.getDate("date").toLocalDate(),
                        rs.getString("texte")));
            }
        } catch (SQLException throwables) {
            throw new DALException("DAL - La récupération de la liste a échoué");
        }
        return listeTodo;
    }
}
