/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.entity;

import java.util.Date;

/**
 *
 * @author amona
 */
public class Livre {
    
    private int idLivre;
    private String titre;
    private String categorie;
    private Date datePublication;
    private String description;
    private byte[] photo;

    public Livre() {
    }

    public Livre(String titre, String categorie, Date datePublication, String description, byte[] photo) {
        this.titre = titre;
        this.categorie = categorie;
        this.datePublication = datePublication;
        this.description = description;
        this.photo = photo;
    }

    public Livre(String titre, String categorie, Date datePublication, String description) {
        this.titre = titre;
        this.categorie = categorie;
        this.datePublication = datePublication;
        this.description = description;
    }

    public Livre(String titre, String categorie, String description, byte[] photo) {
        this.titre = titre;
        this.categorie = categorie;
        this.description = description;
        this.photo = photo;
    }

    public Livre(String titre, String categorie, String description) {
        this.titre = titre;
        this.categorie = categorie;
        this.description = description;
    }

    public Livre(int idLivre, String titre, String categorie, Date datePublication, String description) {
        this.idLivre = idLivre;
        this.titre = titre;
        this.categorie = categorie;
        this.datePublication = datePublication;
        this.description = description;
    }

    
    

    public String getDescription() {
        return description;
    }

    public int getIdLivre() {
        return idLivre;
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

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
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

    @Override
    public String toString() {
        return "Livre{" + "idLivre=" + idLivre + ", titre=" + titre + ", categorie=" + categorie + ", datePublication=" + datePublication + ", description=" + description + '}';
    }

    
    
}
