package exam;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class MembreDaoImpl implements MembreDao {
    public static Connection connexion;
    public MembreDaoImpl(){
        try{
        Class.forName("com.mysql.cj.jdbc.Driver");
            connexion=DriverManager.getConnection("jdbc:mysql://localhost:3306/exam","root","");
        }
        catch(ClassNotFoundException | SQLException e){
            System.out.println("Erreur lors de la connexion à la base de données");
            e.printStackTrace();
        }
    }
    public void insere(Membre membre) {
        String query="insert into membre values(?,?,?,?,?)";
        try(PreparedStatement pst=connexion.prepareStatement(query)){
            pst.setString(1,membre.getIdentifiant());
            pst.setString(2,membre.getNom());
            pst.setString(3,membre.getPrenom());
            pst.setString(4,membre.getEmail());
            pst.setString(5,membre.getPhone());
            pst.executeUpdate();
        }
        catch(SQLException e){
        System.out.println("Erreur lors de l'insertion du membre dans la base de données");
            e.printStackTrace();
        }
    }
    public Set<Incident> chargerListIncidents(){
        Set<Incident> incidents=new HashSet<>();
        String query="SELECT * FROM incident";
        try(PreparedStatement pst=connexion.prepareStatement(query)) {
            ResultSet rs=pst.executeQuery();
            while(rs.next()){
                Membre m=getMembre(rs.getString("id_membre"));
                Incident i=new Incident(rs.getString("reference"), LocalDateTime.parse(rs.getString("time")),rs.getString("status"),m);
                incidents.add(i);
            }
        }
        catch(SQLException e){
            System.out.println("Erreur lors de la récupération des incidents à partir de la base de données");
            e.printStackTrace();
        }
        return incidents;
    }
    public Membre getMembre(String id){
        String query="SELECT * FROM membre WHERE identifiant=?";
        try(PreparedStatement pst=connexion.prepareStatement(query)) {
            pst.setString(1, id);
            ResultSet rs=pst.executeQuery();
            if(rs.next()){
                Membre m=new Membre(rs.getString("nom"),rs.getString("prenom"),rs.getString("email"),rs.getString("phone"));
                return m;
            }
            return null;
        }
        catch(SQLException e){
            System.out.println("Erreur de la récupération de l'utilisateur à partir de la base de données");
            e.printStackTrace();
            return null;
        }

    }
}
