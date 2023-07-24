/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.entity;

/**
 *
 * @author amirb
 */
public class User {
    private int id;
    private String nom,prenom,adrmail,mdp,adresse,tel,type;

    public User() {
    }

    public User(String nom, String prenom, String adrmail, String mdp, String adresse, String tel, String type) {
        this.nom = nom;
        this.prenom = prenom;
        this.adrmail = adrmail;
        this.mdp = mdp;
        this.adresse = adresse;
        this.tel = tel;
        this.type = type;
    }

    public User(int id, String nom, String prenom, String adrmail, String mdp, String adresse, String tel, String type) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.adrmail = adrmail;
        this.mdp = mdp;
        this.adresse = adresse;
        this.tel = tel;
        this.type = type;
    }





    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdrmail() {
        return adrmail;
    }

    public String getMdp() {
        return mdp;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdrmail(String adrmail) {
        this.adrmail = adrmail;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", nom=" + nom + ", prenom=" + prenom + ", adrmail=" + adrmail + ", mdp=" + mdp + ", adresse=" + adresse + '}';
    }
    
    
    
    
}
