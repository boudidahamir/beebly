/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beebly.entity;

/**
 *
 * @author khaled
 */
public class Utilisateurkhaled {
    
    private int id;
    private String nom;
    private String prenom;
    private String Email;
    private String mot_de_passe;
    private int num_tel;
    
    // constructeur par defaut

    public Utilisateurkhaled() {
    }
    
    // les constructeurs parematreés

    public Utilisateurkhaled(int id, String nom, String prenom, String Email, String mot_de_passe, int num_tel) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.Email = Email;
        this.mot_de_passe = mot_de_passe;
        this.num_tel = num_tel;
    }

    public Utilisateurkhaled(String nom, String prenom, String Email, String mot_de_passe, int num_tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.Email = Email;
        this.mot_de_passe = mot_de_passe;
        this.num_tel = num_tel;
    }

    public Utilisateurkhaled(String nom, String prenom, String Email, int num_tel) {
        this.nom = nom;
        this.prenom = prenom;
        this.Email = Email;
        this.num_tel = num_tel;
    }




    public Utilisateurkhaled(String nom, String prenom, String Email) {
        this.nom = nom;
        this.prenom = prenom;
        this.Email = Email;
    }
    
    // getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public int getNum_tel() {
        return num_tel;
    }

    public void setNum_tel(int num_tel) {
        this.num_tel = num_tel;
    }

    public static Utilisateurkhaled getUser_connecter() {
        return user_connecter;
    }

    public static void setUser_connecter(Utilisateurkhaled user_connecter) {
        Utilisateurkhaled.user_connecter = user_connecter;
    }

    
    // toString

    @Override
    public String toString() {
        return "Utilisateur{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", Email=" + Email + ", mot_de_passe=" + mot_de_passe + ", num_tel=" + num_tel + '}';
    }

    
    
    // utilisateur connectee
          public static Utilisateurkhaled user_connecter;
    
    
}
