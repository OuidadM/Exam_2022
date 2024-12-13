import GUI.InterfaceLoader;
import exam.Incident;
import exam.IncidentDaoImpl;
import exam.Membre;
import exam.MembreDaoImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public Set<Membre> chargerListeMembre(String filePath) {
        Set<Membre> membreSet = new HashSet<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line = "";
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                Membre m = new Membre(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim());
                boolean existeDeja = false;
                for (Membre membre : membreSet) {
                    if (membre.getNom().equals(m.getNom()) &&
                            membre.getPrenom().equals(m.getPrenom()) &&
                            membre.getEmail().equals(m.getEmail()) &&
                            membre.getPhone().equals(m.getPhone())) {
                        existeDeja = true;
                        break;
                    }
                }
                if (!existeDeja) {
                    membreSet.add(m);
                }
            }
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture du fichier: " + e.getMessage());
            e.printStackTrace();
        }
        return membreSet;
    }
    public static void main(String[] args) {
        InterfaceLoader.launch(InterfaceLoader.class, args);
        Membre m1=new Membre("Malak","Saadi","ms@gmail.com","+21345555");
        Incident i1=new Incident("1xxxxx","en cours",m1);
        MembreDaoImpl memberDaoImp=new MembreDaoImpl();
        IncidentDaoImpl incidentDao=new IncidentDaoImpl();
        memberDaoImp.insere(m1);
        incidentDao.inserer(i1);
        Incident i2=new Incident("2xxxxx","en cours",m1);
        Incident i3=new Incident("3xxxxx","en cours",m1);
        Incident i4=new Incident("4xxxxx","en cours",m1);
        Set<Incident> incidents=new HashSet<>();
        incidents.add(i2);
        incidents.add(i3);
        incidents.add(i4);
        incidentDao.inser(incidents);
        Main main=new Main();
        Set<Membre> membres=main.chargerListeMembre("./src/main/liste_membres.csv");
        for(Membre m:membres){
            System.out.println(m.getNom());
            memberDaoImp.insere(m);
        }
    }
}
