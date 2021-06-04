package ihm;

import bll.BLLException;
import bll.TodoManager;
import bll.bo.Todo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.sql.Date;
import java.util.List;

public class GUI extends JFrame {
    private JPanel panneauPrincipal;
    private JPanel todoList;
    private JPanel groupeBouton;

    private JLabel dateDuJour;

    private JButton ajouter;
    private JButton supprimer;

    private SimpleDateFormat formatDate = new SimpleDateFormat("EEEEE dd MM yyyy");
    private TodoManager todoManager = TodoManager.getInstance();

    public GUI() {
        this.setTitle("TodoList");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(getPanneauPrincipal());
        this.pack();
        this.setVisible(true);
    }

    private JPanel getPanneauPrincipal() {
        if (this.panneauPrincipal == null){
            this.panneauPrincipal = new JPanel();
            this.panneauPrincipal.setLayout(new BoxLayout(this.panneauPrincipal, BoxLayout.Y_AXIS));
            this.panneauPrincipal.add(getDateDuJour());
            this.panneauPrincipal.setBackground(new Color(45,45,45));
            this.panneauPrincipal.add(getTodoList());
            this.panneauPrincipal.add(getGroupeBouton());
        }
        return this.panneauPrincipal;
    }

    private JPanel getGroupeBouton(){
        if (this.groupeBouton == null){
            this.groupeBouton = new JPanel();
            this.groupeBouton.setLayout(new BoxLayout(this.groupeBouton, BoxLayout.X_AXIS));
            this.groupeBouton.add(getAjouter());
            this.groupeBouton.add(getSupprimer());
            this.groupeBouton.setBackground(new Color(45,45,45));
        }
        return this.groupeBouton;
    }

    private JButton getAjouter() {
        if (this.ajouter == null){
            ImageIcon icon = new ImageIcon("Images/Ajouter.png");
            Image img = icon.getImage();
            Image resize = img.getScaledInstance(30,30,Image.SCALE_SMOOTH);
            icon.setImage(resize);
            this.ajouter = new JButton(new ImageIcon(resize));
            this.ajouter.setBorderPainted(false);
            this.ajouter.setOpaque(false);
            this.ajouter.setContentAreaFilled(false);
            this.ajouter.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame pageAjout = new PageAjout();
                }
            });
        }
        return this.ajouter;
    }

    private JButton getSupprimer(){
        if (this.supprimer == null){
            ImageIcon icon = new ImageIcon("Images/Supprimer.png");
            Image img = icon.getImage();
            Image resize = img.getScaledInstance(30,30,Image.SCALE_SMOOTH);
            icon.setImage(resize);
            this.supprimer = new JButton(new ImageIcon(resize));
            this.supprimer.setBorderPainted(false);
            this.supprimer.setOpaque(false);
            this.supprimer.setContentAreaFilled(false);
        }
        return this.supprimer;
    }

    private JLabel getDateDuJour(){
        if (this.dateDuJour == null){
            this.dateDuJour = new JLabel("Nous somme le " + this.formatDate.format(Date.valueOf(LocalDate.now())));
            this.dateDuJour.setAlignmentX(Component.CENTER_ALIGNMENT);
            this.dateDuJour.setForeground(Color.WHITE);
        }
        return this.dateDuJour;
    }

    private JPanel getTodoList(){
        if (this.todoList == null){
            this.todoList = new JPanel();
            this.todoList.setBackground(new Color(83, 83, 83));
            this.todoList.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            List<Todo> todoList = null;
            try {
                todoList = this.todoManager.selectAll();
            } catch (BLLException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < todoList.size(); i++) {
                gbc.gridy = i;
                gbc.gridx = 0;
                gbc.insets = new Insets(0,0,0,5);
                JLabel date = new JLabel(formatDate.format(Date.valueOf(todoList.get(i).getDate())));
                date.setForeground(Color.WHITE);
                this.todoList.add(date, gbc);
                gbc.gridx = 1;
                gbc.insets = new Insets(0,0,0,0);
                Label texte = new Label(todoList.get(i).getTexte());
                texte.setForeground(Color.white);
                this.todoList.add(texte, gbc);
                gbc.gridx = 2;
                gbc.insets = new Insets(0,5,0,0);
                this.todoList.add(getBoutonListeModifier(String.valueOf(todoList.get(i).getId())), gbc);
                gbc.gridx = 3;
                gbc.insets = new Insets(0,0,0,0);
                this.todoList.add(getBoutonListeSupprimer(String.valueOf(todoList.get(i).getId())), gbc);
            }
        }
        return this.todoList;
    }

    public JButton getBoutonListeModifier(String id){
        ImageIcon icon = new ImageIcon("Images/Modifier.png");
        Image img = icon.getImage();
        Image resize = img.getScaledInstance(15,15,Image.SCALE_SMOOTH);
        icon.setImage(resize);
        JButton bouton = new JButton(icon);
        bouton.setActionCommand(id);
        bouton.setBorderPainted(false);
        bouton.setOpaque(false);
        bouton.setContentAreaFilled(false);
        return bouton;
    }

    public JButton getBoutonListeSupprimer(String id){
        ImageIcon icon = new ImageIcon("Images/Supprimer.png");
        Image img = icon.getImage();
        Image resize = img.getScaledInstance(15,15,Image.SCALE_SMOOTH);
        icon.setImage(resize);
        JButton bouton = new JButton(icon);
        bouton.setActionCommand(id);
        bouton.setBorderPainted(false);
        bouton.setOpaque(false);
        bouton.setContentAreaFilled(false);
        return bouton;
    }
}
