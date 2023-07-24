/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.entity;

import java.sql.*;

/**
 *
 * @author ahmed
 */
public class Reclamation {
   private int id;
   private String type;
   private String sujet;
   private String description;
   private Date date; 
   private byte[] photo;
   private String status="en cours";
   private User user;
   private Reponse reponse; 
   
   
   public Reclamation(){};
   
   public Reclamation(int id,String type, String sujet,String description, Date date, byte[] photo, String status, User user, Reponse reponse) {
        this.id = id; 
        this.type=type;
        this.sujet=sujet;
        this.description = description;
        this.date = date;
        this.photo = photo;
        this.status = status;
        this.user = user;
        this.reponse = reponse;
    } 

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
    
    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Reponse getReponse() {
        return reponse;
    }

    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
    }

    @Override
    public String toString() {
        return "Reclamation{" + "id=" + id + ", type=" + type + ", sujet=" + sujet + ", description=" + description + ", date=" + date + ", photo=" + photo + ", status=" + status + ", user=" + user + ", reponse=" + reponse + '}';
    }

  

    
   
    
   
    
}
