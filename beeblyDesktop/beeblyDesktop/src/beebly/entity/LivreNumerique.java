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
public class LivreNumerique extends Livre{

    private int idLivreNum; 
    private Livre liv;
    private String livrePdf;
    private float prix;

    public LivreNumerique() {
    }

    public LivreNumerique(String titre, String categorie, Date datePublication, String description, byte[] photo) {
        super(titre, categorie, datePublication, description, photo);
    }

    public LivreNumerique(int idLivreNum) {
        this.idLivreNum = idLivreNum;
    }

    public LivreNumerique(int idLivreNum, String titre, String categorie, Date datePublication, String description, byte[] photo) {
        super(titre, categorie, datePublication, description, photo);
        this.idLivreNum = idLivreNum;
    }
    
    public LivreNumerique(Livre liv, String livrePdf, float prix) {
        this.liv = liv;
        this.livrePdf = livrePdf;
        this.prix = prix;
    }

    public LivreNumerique(Livre liv, String livrePdf, float prix, String titre, String categorie, Date datePublication, String description, byte[] photo) {
        super(titre, categorie, datePublication, description, photo);
        this.liv = liv;
        this.livrePdf = livrePdf;
        this.prix = prix;
    }

    public int getIdLivreNum() {
        return idLivreNum;
    }

    public String getLivrePdf() {
        return livrePdf;
    }

    public float getPrix() {
        return prix;
    }

    public Livre getLiv() {
        return liv;
    }

    public void setIdLivreNum(int idLivreNum) {
        this.idLivreNum = idLivreNum;
    }

    public void setLivrePdf(String livrePdf) {
        this.livrePdf = livrePdf;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setLiv(Livre liv) {
        this.liv = liv;
    }

    @Override
    public String toString() {
        return "LivreNumerique{" + "idLivreNum=" + idLivreNum + ", liv=" + liv + ", livrePdf=" + livrePdf + ", prix=" + prix + '}';
    }

    

    
    
    
    
    
    
}
