package ihm;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.swing.*;

public class TodoListApp {

    private static Logger logMain = LoggerFactory.getLogger(TodoListApp.class);

    public static void main(String[] args) {
        logMain.trace("Démarrage du programme.");
        SwingUtilities.invokeLater(
                () -> {
                    logMain.trace("Lancement de l'interface.");
                    JFrame app = new GUI();
                }
        );
        logMain.debug("Fin du programme.");
    }
}
