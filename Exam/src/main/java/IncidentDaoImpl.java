import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class IncidentDaoImpl implements IncidentDao{
    public static Connection connexion;
    public IncidentDaoImpl(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connexion= DriverManager.getConnection("jdbc:mysql://localhost:3306/exam","root","");
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println("Erreur lors de la connexion à la base de données");
            e.printStackTrace();
        }
    }
    public void inserer(Incident incident) {
        String query="insert into incident values(?,?,?,?)";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        try(PreparedStatement pst=connexion.prepareStatement(query)){
            pst.setString(1,incident.getReference());
            pst.setString(2,incident.getTime().format(formatter));
            pst.setString(3,incident.getStatus());
            pst.setString(4,incident.getMembre().getIdentifiant());
            pst.executeUpdate();
        }
        catch(SQLException e){
            System.out.println("Erreur lors de l'insertion du membre dans la base de données");
            e.printStackTrace();
        }
    }
    public void inser(Set<Incident> incidents) {
        for(Incident incident:incidents){
            inserer(incident);
        }
    }
}
