/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beebly.entity;

/**
 *
 * @author khaled
 */
public class Ticket {
    
    // les attributs
    private int id ;
    private String type ;
    private int prix ;
    private int id_evenement ;
    
    // constructeur par defaut

    public Ticket() {
    }
    
    // les constructeurs parametreés

    public Ticket(int id, String type, int prix, int id_evenement) {
        this.id = id;
        this.type = type;
        this.prix = prix;
        this.id_evenement = id_evenement;
    }

    public Ticket(String type, int prix, int id_evenement) {
        this.type = type;
        this.prix = prix;
        this.id_evenement = id_evenement;
    }
    
    // les getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getId_evenement() {
        return id_evenement;
    }

    public void setId_evenement(int id_evenement) {
        this.id_evenement = id_evenement;
    }
    
    // la methode ToString

    @Override
    public String toString() {
        return "Ticket{" + "id=" + id + ", type=" + type + ", prix=" + prix + ", id_evenement=" + id_evenement + '}';
    }
    
    
}
