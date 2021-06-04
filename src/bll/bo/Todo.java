package bll.bo;

import java.sql.Date;
import java.time.LocalDate;

/**
 * Classe Todo qui permet de créer une liste de chose à faire.
 * <ul>
 *     <li><b>date :</b> La date limite pour réaliser la chose prévue</li>
 *     <li><b>id :</b> L'id qui sera attribué à l'instance en base de données</li>
 *     <li><b>texte :</b> Le texte qui indique ce que l'on a à faire</li>
 * </ul>
 *
 * @author Yoann Drouet
 */
public class Todo {

    private LocalDate date;
    private int id;
    private String texte;

    public Todo(LocalDate date, String texte) {
        this.date = date;
        this.texte = texte;
    }

    public Todo(int id, LocalDate date, String texte) {
        this.date = date;
        this.id = id;
        this.texte = texte;
    }

    public Todo(String texte) {
        this.texte = texte;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTexte() {
        return texte;
    }

    public void setTexte(String texte) {
        this.texte = texte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return this.date + " - " + this.texte;
    }
}
