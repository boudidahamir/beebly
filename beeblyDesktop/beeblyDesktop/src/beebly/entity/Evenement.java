/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beebly.entity;

import java.util.Date;

/**
 *
 * @author khaled
 */
public class Evenement {
    private  int id ;
    private String libelle ;
    private String image ;
    private String date ;
    private  String description ;
    private String emplacement ;
    private int nb_place ;
    private int duree ;
    private int id_user ;

    // constructeur par defaut
    public Evenement() {
    }
    
    // les constructeurs parametreés

    public Evenement(int id, String libelle, String image, String date, String description, String emplacement, int nb_place, int duree, int id_user) {
        this.id = id;
        this.libelle = libelle;
        this.image = image;
        this.date = date;
        this.description = description;
        this.emplacement = emplacement;
        this.nb_place = nb_place;
        this.duree = duree;
        this.id_user = id_user;
    }

    public Evenement(String libelle, String image, String date, String description, String emplacement, int nb_place, int duree, int id_user) {
        this.libelle = libelle;
        this.image = image;
        this.date = date;
        this.description = description;
        this.emplacement = emplacement;
        this.nb_place = nb_place;
        this.duree = duree;
        this.id_user = id_user;
    }
    //getters and setters

    public int getId() {    
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmplacement() {
        return emplacement;
    }

    public void setEmplacement(String emplacement) {
        this.emplacement = emplacement;
    }

    public int getNb_place() {
        return nb_place;
    }

    public void setNb_place(int nb_place) {
        this.nb_place = nb_place;
    }

    public int getDuree() {
        return duree;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }

    public int getId_user() {
        return id_user;
    }

    // getters and setters
    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    // methode ToString
    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", libelle=" + libelle + ", image=" + image + ", date=" + date + ", description=" + description + ", emplacement=" + emplacement + ", nb_place=" + nb_place + ", duree=" + duree + ", id_user=" + id_user + '}';
    }

  
    
    
    
    
    
    
    
    
}
