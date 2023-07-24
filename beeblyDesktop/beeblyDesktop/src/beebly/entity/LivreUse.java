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
public class LivreUse extends Livre{
    
    private int idLivreUse;
    private int idPiece;
    private Livre liv;
    private float prix;
    private String descriptionEtatLivre;
    private int note;

    public LivreUse(int idPiece, Livre liv, float prix, String descriptionEtatLivre, int note) {
        this.idPiece = idPiece;
        this.liv = liv;
        this.prix = prix;
        this.descriptionEtatLivre = descriptionEtatLivre;
        this.note = note;
    }

    public LivreUse(int idPiece, Livre liv, float prix, String descriptionEtatLivre, int note, String titre, String categorie, Date datePublication, String description, byte[] photo) {
        super(titre, categorie, datePublication, description, photo);
        this.idPiece = idPiece;
        this.liv = liv;
        this.prix = prix;
        this.descriptionEtatLivre = descriptionEtatLivre;
        this.note = note;
    }

    public LivreUse() {
    }

    public LivreUse(int idLivreUse) {
        this.idLivreUse = idLivreUse;
    }

    public int getIdLivreUse() {
        return idLivreUse;
    }

    public Livre getLiv() {
        return liv;
    }
    
    public int getIdPiece() {
        return idPiece;
    }

    public float getPrix() {
        return prix;
    }

    public String getDescriptionEtatLivre() {
        return descriptionEtatLivre;
    }

    public int getNote() {
        return note;
    }

    public void setIdPiece(int idPiece) {
        this.idPiece = idPiece;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setDescriptionEtatLivre(String descriptionEtatLivre) {
        this.descriptionEtatLivre = descriptionEtatLivre;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public void setIdLivreUse(int idLivreUse) {
        this.idLivreUse = idLivreUse;
    }

    public void setLiv(Livre liv) {
        this.liv = liv;
    }

    @Override
    public String toString() {
        return "LivreUse{" + "idLivreUse=" + idLivreUse + ", idPiece=" + idPiece + ", liv=" + liv + ", prix=" + prix + ", descriptionEtatLivre=" + descriptionEtatLivre + ", note=" + note + '}';
    }
    
    
    
    
}
