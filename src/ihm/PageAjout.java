package ihm;

import javax.swing.*;
import java.awt.*;

public class PageAjout extends JFrame {

    JPanel panneauPrincipal;

    JLabel date;
    JLabel texte;

    JTextField txtDate;
    JTextField txTTexte;

    public PageAjout(){
        this.setTitle("TodoList");
        this.add(getPanneauPrincipal());
        this.pack();
        this.setVisible(true);
    }

    public JPanel getPanneauPrincipal(){
        if (panneauPrincipal == null){
            this.panneauPrincipal = new JPanel();
            this.panneauPrincipal.setBackground(new Color(83, 83, 83));
            this.panneauPrincipal.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = 0;
            gbc.insets = new Insets(15,15,15,15);
            this.panneauPrincipal.add(getDate(), gbc);
            gbc.gridx = 1;
            this.panneauPrincipal.add(getTxtDate(), gbc);
            gbc.gridy = 1;
            gbc.gridx = 0;
            this.panneauPrincipal.add(getTexte(),gbc);
            gbc.gridx = 1;
            this.panneauPrincipal.add(getTxTTexte(),gbc);
        }
       return this.panneauPrincipal;
    }

    public JLabel getDate(){
        if (this.date==null){
            this.date = new JLabel("Date");
            this.date.setForeground(Color.BLACK);
        }
        return this.date;
    }

    public JLabel getTexte(){
        if (this.texte==null){
            this.texte = new JLabel("Texte");
            this.texte.setForeground(Color.BLACK);
        }
        return this.texte;
    }

    public JTextField getTxtDate(){
        if (this.txtDate==null){
            this.txtDate = new JTextField(25);
            this.txtDate.setBackground(new Color(60,60,60));
        }
        return this.txtDate;
    }

    public JTextField getTxTTexte(){
        if (this.txTTexte==null){
            this.txTTexte = new JTextField(25);
            this.txTTexte.setBackground(new Color(60,60,60));
        }
        return this.txTTexte;
    }
}
