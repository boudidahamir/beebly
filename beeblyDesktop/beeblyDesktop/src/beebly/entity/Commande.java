/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.entity;

import java.util.List;

/**
 *
 * @author amona
 */
public class Commande {

    private int idCommande;
    private Client client;
    private List<LivreNeuf> livreNeufList;
    private List<LivreNumerique> livreNumeriqueList;
    private List<LivreUse> livreUseList;
    private String adresseLivraison;
    private EtatCommande etatCommande;
    private float prix;

    public Commande() {
    }

    public Commande(Client client, List<LivreNeuf> livreNeufList, List<LivreNumerique> livreNumeriqueList, List<LivreUse> livreUseList, String adresseLivraison, EtatCommande etatCommande, float prix) {
        this.client = client;
        this.livreNeufList = livreNeufList;
        this.livreNumeriqueList = livreNumeriqueList;
        this.livreUseList = livreUseList;
        this.adresseLivraison = adresseLivraison;
        this.etatCommande = etatCommande;
        this.prix = prix;
    }

    public Commande(int idCommande, Client client, List<LivreNeuf> livreNeufList, List<LivreNumerique> livreNumeriqueList, List<LivreUse> livreUseList, String adresseLivraison, EtatCommande etatCommande, float prix) {
        this.idCommande = idCommande;
        this.client = client;
        this.livreNeufList = livreNeufList;
        this.livreNumeriqueList = livreNumeriqueList;
        this.livreUseList = livreUseList;
        this.adresseLivraison = adresseLivraison;
        this.etatCommande = etatCommande;
        this.prix = prix;
    }

    public Commande(int idCommande, Client client, String adresseLivraison, EtatCommande etatCommande, float prix) {
        this.idCommande = idCommande;
        this.client = client;
        this.adresseLivraison = adresseLivraison;
        this.etatCommande = etatCommande;
        this.prix = prix;
    }

    public Commande(int i) {
        this.client.setId(i);
    }

    public Commande(int i, String adresseLivraison, float prix) {
        this.client.setId(i);
        this.adresseLivraison = adresseLivraison;
        this.prix = prix;
    }
    

    

    public Commande(Client client, String string, EtatCommande etatCommande, float aFloat) {
        this.client = client;
        this.adresseLivraison = string;
        this.etatCommande = etatCommande;
        this.prix = aFloat;
    }

    public Commande(Client client, String string, float aFloat) {
        this.client = client;
        this.adresseLivraison = string;
        this.prix = aFloat;
    }

    public int getIdCommande() {
        return idCommande;
    }

    public Client getClient() {
        return client;
    }

    public List<LivreNeuf> getLivreNeufList() {
        return livreNeufList;
    }

    public List<LivreNumerique> getLivreNumeriqueList() {
        return livreNumeriqueList;
    }

    public List<LivreUse> getLivreUseList() {
        return livreUseList;
    }

    public String getAdresseLivraison() {
        return adresseLivraison;
    }

    public EtatCommande getEtatCommande() {
        return etatCommande;
    }

    public float getPrix() {
        return prix;
    }

    public void setIdCommande(int idCommande) {
        this.idCommande = idCommande;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setLivreNeufList(List<LivreNeuf> livreNeufList) {
        this.livreNeufList = livreNeufList;
    }

    public void setLivreNumeriqueList(List<LivreNumerique> livreNumeriqueList) {
        this.livreNumeriqueList = livreNumeriqueList;
    }

    public void setLivreUseList(List<LivreUse> livreUseList) {
        this.livreUseList = livreUseList;
    }

    public void setAdresseLivraison(String adresseLivraison) {
        this.adresseLivraison = adresseLivraison;
    }

    public void setEtatCommande(EtatCommande etatCommande) {
        this.etatCommande = etatCommande;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    @Override
    public String toString() {
        return "Commande{" + "idCommande=" + idCommande + ", client=" + client + ", livreNeufList=" + livreNeufList + ", livreNumeriqueList=" + livreNumeriqueList + ", livreUseList=" + livreUseList + ", adresseLivraison=" + adresseLivraison + ", etatCommande=" + etatCommande + ", prix=" + prix + '}';
    }

    

}
