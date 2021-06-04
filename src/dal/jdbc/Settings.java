package dal.jdbc;

import java.util.Properties;

public class Settings {
    private static Properties propriete;

    static {
        try{
            propriete = new Properties();
            propriete.load(Settings.class.getResourceAsStream("settings.properties"));
        }catch (Exception e){
            System.out.println("Impossible de se connecter à la base de données");
        }
    }

    public static String getPropriete(String cle){
        String parametre = propriete.getProperty(cle, null);
        return parametre;
    }

}
