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
public class Reponse {
    private int id; 
    private Date date; 
    private String contenu; 

    public Reponse() {
    }

    public Reponse(int id, Date date, String contenu) {
        this.id = id;
        this.date = date;
        this.contenu = contenu;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Reponse{" + "id=" + id + ", date=" + date + ", contenu=" + contenu + '}';
    }
    
    
    
}
