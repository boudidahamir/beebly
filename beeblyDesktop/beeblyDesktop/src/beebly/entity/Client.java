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
public class Client extends User {
    private int soldepoint;

    public Client() {
    }
    
    public Client(int soldepoint, String nom, String prenom, String adrmail, String mdp, String adresse, String tel, String type) {
        super(nom, prenom, adrmail, mdp, adresse, tel, type);
        this.soldepoint = soldepoint;
    }

    public Client(int soldepoint, int id, String nom, String prenom, String adrmail, String mdp, String adresse, String tel, String type) {
        super(id, nom, prenom, adrmail, mdp, adresse, tel, type);
        this.soldepoint = soldepoint;
    }

    public Client(int idClient) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Client(int aInt, String string) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getSoldepoint() {
        return soldepoint;
    }

    public void setSoldepoint(int soldepoint) {
        this.soldepoint = soldepoint;
    }

    @Override
    public String toString() {
        
        //return "Client{" + "soldepoint=" + soldepoint + super.toString() +"'}'\n";
        return "Client{" + "soldepoint=" + soldepoint + " id=" + this.getId() + " nom=" + this.getNom() + " prenom=" + this.getPrenom() + " adrmail=" + this.getAdrmail() + " adresse=" + this.getAdresse() + " tel=" + this.getTel() +"'}'\n";
    }



 

    
    
}
