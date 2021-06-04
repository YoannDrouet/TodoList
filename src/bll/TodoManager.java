package bll;

import bll.bo.Todo;
import dal.DALException;
import dal.DAOFactory;
import dal.TodoDAO;

import java.util.List;

/**
 * Classe permettant la jonction entre l'interface utilisateur et la base de données,
 * permet d'envoyer des requêtes vers la table todo
 *
 * @author Yoann Drouet
 */
public class TodoManager {

    private static TodoManager instance;
    private static TodoDAO todoDAO = DAOFactory.getTodoDAO();

    public TodoManager() {
    }

    public static TodoManager getInstance(){
        if (instance == null){
            instance = new TodoManager();
        }
        return instance;
    }

    /**
     * Insère dans la base de données si cette chose à faire n'est pas déjà dans la liste
     * @param todo chose à insérer
     * @throws BLLException
     */
    public void insert(Todo todo) throws BLLException{
        if (verification(this.selectAll(), todo)){
            try {
                todoDAO.insert(todo);
            } catch (DALException e) {
                throw new BLLException("BLL - L'insertion a échoué");
            }
        }
    }

    /**
     * Modifie une chose à faire dans la liste si on n'a pas déjà une autre chose semblable
     * @param todo la chose à modifier
     * @throws BLLException
     */
    public void update(Todo todo) throws BLLException{
        if (verification(this.selectAll(), todo)){
            try {
                todoDAO.update(todo);
            } catch (DALException e) {
                throw new BLLException("DAL - La modification a échoué");
            }
        }
    }

    /**
     * Supprime quelque chose de la TodoList
     * @param todo
     * @throws BLLException
     */
    public void delete(Todo todo) throws BLLException{
        try {
            todoDAO.delete(todo);
        } catch (DALException e){
            throw new BLLException("BLL - La suppression a échoué");
        }
    }

    /**
     * Récupère toute le TodoList
     * @return une liste contenant la TodoList
     * @throws BLLException
     */
    public List<Todo> selectAll() throws BLLException {
        try {
            return todoDAO.selectAll();
        } catch (DALException e) {
            throw new BLLException("BLL - La récupération de la liste a échoué");
        }
    }

    /**
     * Vérifie que la chose à faire n'est pas déjà dans la base de données
     * @param liste La TodoList
     * @param todo Ce dont l'on souhaite vérifier l'existence
     * @return
     * @throws BLLException
     */
    public boolean verification(List<Todo> liste, Todo todo) throws BLLException {
        String test = todo.getTexte();
        for (Todo unTodo : liste){
            if (test.equalsIgnoreCase(unTodo.getTexte())){
                throw new BLLException(todo.getTexte() + " est déjà dans votre TodoList");
            }
        }
        return true;
    }
}
