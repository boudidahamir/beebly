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
public class Admin extends User {
    private int cin;

    public Admin() {
    }

    public Admin(int cin, String nom, String prenom, String adrmail, String mdp, String adresse, String tel, String type) {
        super(nom, prenom, adrmail, mdp, adresse, tel, type);
        this.cin = cin;
    }

    public Admin(int cin, int id, String nom, String prenom, String adrmail, String mdp, String adresse, String tel, String type) {
        super(id, nom, prenom, adrmail, mdp, adresse, tel, type);
        this.cin = cin;
    }

    public int getCin() {
        return cin;
    }

    public void setCin(int cin) {
        this.cin = cin;
    }

    @Override
    public String toString() {
        return  "Admin{" + "cin=" + cin + " id=" + this.getId() + " nom=" + this.getNom() + " prenom=" + this.getPrenom() + " adrmail=" + this.getAdrmail() + " adresse=" + this.getAdresse() + " tel=" + this.getTel() +"'}'\n";
    }
    
    


}
