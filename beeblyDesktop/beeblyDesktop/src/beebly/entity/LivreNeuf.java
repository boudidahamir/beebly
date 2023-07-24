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
public class LivreNeuf extends Livre{
    
    private int idLivreNeuf;
    private Livre liv;
    private int stockRestant;
    private float prix;

    public LivreNeuf() {
    }

    public LivreNeuf(int idLivreNeuf) {
        this.idLivreNeuf = idLivreNeuf;
    }
    
    public LivreNeuf(Livre liv, int stockRestant, float prix) {
        this.liv = liv;
        this.stockRestant = stockRestant;
        this.prix = prix;
    }

    public LivreNeuf(Livre liv, int stockRestant, float prix, String titre, String categorie, Date datePublication, String description, byte[] photo) {
        super(titre, categorie, datePublication, description, photo);
        this.liv = liv;
        this.stockRestant = stockRestant;
        this.prix = prix;
    }
    
    public int getIdLivreNeuf() {
        return idLivreNeuf;
    }

    public void setIdLivreNeuf(int idLivreNeuf) {
        this.idLivreNeuf = idLivreNeuf;
    }
    
    public int getStockRestant() {
        return stockRestant;
    }

    public float getPrix() {
        return prix;
    }

    public Livre getLiv() {
        return liv;
    }

    public void setStockRestant(int stockRestant) {
        this.stockRestant = stockRestant;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public void setLiv(Livre liv) {
        this.liv = liv;
    }

    @Override
    public String toString() {
        return "LivreNeuf{" + "idLivreNeuf=" + idLivreNeuf + ", liv=" + liv + ", stockRestant=" + stockRestant + ", prix=" + prix + '}';
    }

    
    
    
    

    

        
    
    
}
