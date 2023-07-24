/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp.entities;

import java.util.Date;

/**
 *
 * @author sahar
 */
public class Livre {
    
    private int id;
    private String titre;
    private String categorie;
    private Date datePublication;
    private String image;
    private double prix;
    private String descriptionEtatLivre;
    private int note;
    private User idUser;

    public Livre() {
    }

    public Livre(String titre, String categorie, Date datePublication, String image, double prix, String descriptionEtatLivre, int note) {
        this.titre = titre;
        this.categorie = categorie;
        this.datePublication = datePublication;
        this.image = image;
        this.prix = prix;
        this.descriptionEtatLivre = descriptionEtatLivre;
        this.note = note;
    }

    public Livre(int id, String titre, String categorie, Date datePublication, String image, double prix, String descriptionEtatLivre, int note) {
        this.id = id;
        this.titre = titre;
        this.categorie = categorie;
        this.datePublication = datePublication;
        this.image = image;
        this.prix = prix;
        this.descriptionEtatLivre = descriptionEtatLivre;
        this.note = note;
    }

    public User getIdUser() {
        return idUser;
    }

    public void setIdUser(User idUser) {
        this.idUser = idUser;
    }

    public int getId() {
        return id;
    }

    public String getTitre() {
        return titre;
    }

    public String getCategorie() {
        return categorie;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public String getImage() {
        return image;
    }

    public double getPrix() {
        return prix;
    }

    public String getDescriptionEtatLivre() {
        return descriptionEtatLivre;
    }

    public int getNote() {
        return note;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public void setDescriptionEtatLivre(String descriptionEtatLivre) {
        this.descriptionEtatLivre = descriptionEtatLivre;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Livre{" + "id=" + id + ", titre=" + titre + ", categorie=" + categorie + ", datePublication=" + datePublication + ", image=" + image + ", prix=" + prix + ", descriptionEtatLivre=" + descriptionEtatLivre + ", note=" + note + '}';
    }
    
}
