package exam;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class Membre {
    private String identifiant;
    private String nom;
    private String prenom;
    private String email;
    private String phone;
    private Set<Incident> incidents;

    public Membre(String nom, String prenom, String email, String phone) {
        this.identifiant = generateRandomId();
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.phone = phone;

    }

    private String generateRandomId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder userId = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            userId.append(characters.charAt(random.nextInt(characters.length())));
        }
        return userId.toString();
    }
    @Override
    public boolean equals(Object o) {
        if(o.getClass()!=getClass()){
            return false;
        }
        Membre m = (Membre)o;
        if (m.getIdentifiant().equals(this.identifiant)) {
            return true;
        }
        return false;
    }
    @Override
    public int hashCode() {
        return identifiant.hashCode();
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }


}


