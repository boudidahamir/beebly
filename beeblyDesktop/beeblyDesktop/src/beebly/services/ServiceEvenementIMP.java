/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beebly.services;

import beebly.entity.Evenement;
import beebly.tools.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import beebly.tools.MyDB;
import java.time.LocalDate;

/**
 *
 * @author khaled
 */
public class ServiceEvenementIMP implements IEvenement<Evenement> {

    // connexion a la base de donneé
    Connection cnx;

    // constructeur
    public ServiceEvenementIMP() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajoutEvenement(Evenement t) {
        try {
            String req = "INSERT INTO evenement (libelle,image,date,description,emplacement,nb_place,duree,id_user) VALUES(?,?,?,?,?,?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setString(1, t.getLibelle());
            ps.setString(2, t.getImage());
            ps.setString(3, t.getDate());
            ps.setString(4, t.getDescription());
            ps.setString(5, t.getEmplacement());
            ps.setInt(6, t.getNb_place());
            ps.setInt(7, t.getDuree());
            ps.setInt(8, t.getId_user());
            int value = ps.executeUpdate();
            if (value > 0) {
                System.out.println(" l insertion du l'evenemnt dont la libelle est  :" + t.getLibelle() + " a ete effectuer avec sucess !!!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenementIMP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void modifierEvenement(Evenement t, int id) {
        try {
            String req = "update evenement set libelle = ? , image = ? , date = ? , description = ? , emplacement = ? , nb_place = ? , duree = ? , id_user= ? where id= " + id;

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setString(1, t.getLibelle());
            ps.setString(2, t.getImage());
            ps.setString(3, t.getDate());
            ps.setString(4, t.getDescription());
            ps.setString(5, t.getEmplacement());
            ps.setInt(6, t.getNb_place());
            ps.setInt(7, t.getDuree());
            ps.setInt(8, 1);

            int value = ps.executeUpdate();
            if (value > 0) {
                System.out.println(" la modification  du l'evenemnt dont la libelle est  :" + t.getLibelle() + " a ete effectuer avec sucess !!!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenementIMP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void supprimerEvenement(int id) {
        try {
            String req = "DELETE FROM evenement WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            int value_supp = ps.executeUpdate();
            if (value_supp > 0) {
                System.out.println(" Suppression a ete effectuer avec sucess");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenementIMP.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public List<Evenement> afficherEvenements() {
        List<Evenement> list_Evenement = new ArrayList<>();

        try {

            String req = "select * from evenement";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                Evenement evenement = new Evenement();
                evenement.setId(rs.getInt(1));
                evenement.setLibelle(rs.getString("libelle"));
                evenement.setImage(rs.getString("image"));
                evenement.setDate(rs.getString("date"));
                evenement.setDescription(rs.getString("description"));
                evenement.setEmplacement(rs.getString("emplacement"));
                evenement.setNb_place(rs.getInt("nb_place"));
                evenement.setDuree(rs.getInt("duree"));
                evenement.setId_user(rs.getInt("id_user"));

                list_Evenement.add(evenement);
                //System.out.println(list_Evenement);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list_Evenement;

    }

    //--display events by emplacement--
    public List<Evenement> afficherEvenementsByEmpl(String empl) {
        ArrayList<Evenement> list = new ArrayList();
        try {
            String req = "Select * FROM evenement where emplacement ='" + empl + "'";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {

                Evenement evenement = new Evenement();
                evenement.setId(rs.getInt(1));
                evenement.setLibelle(rs.getString("libelle"));
                evenement.setImage(rs.getString("image"));
                evenement.setDate(rs.getString("date"));
                evenement.setDescription(rs.getString("description"));
                evenement.setEmplacement(rs.getString("emplacement"));
                evenement.setNb_place(rs.getInt("nb_place"));
                evenement.setDuree(rs.getInt("duree"));
                evenement.setId_user(rs.getInt("id_user"));

                list.add(evenement);

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
            System.out.println("Error in selecting Event");

        }
        return list;
    }

    public Map<String, Integer> countTicketByEmp() {
        Map<String, Integer> list = new HashMap<>();
        try {
            String req = "select COUNT(*) as count, emplacement from evenement e JOIN ticket t on e.id = t.id_evenement GROUP BY e.emplacement";
            Statement st = cnx.prepareStatement(req);
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                list.put(rs.getString("emplacement"), rs.getInt("count"));

            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return list;

    }

    public List<Evenement> get_evenemnts_by_date(LocalDate date) {
        List<Evenement> evenement_list = new ArrayList();
        PreparedStatement pst;
        try {
            String req = "select * from evenement where date='" + date + "' ";
            pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Evenement event = new Evenement();
                event.setId(rs.getInt(1));
                event.setLibelle(rs.getString(2));
                event.setImage(rs.getString(3));
                event.setDate(rs.getString(4));
                event.setDescription(rs.getString(5));
                event.setEmplacement(rs.getString(6));
                event.setNb_place(rs.getInt("nb_place"));
                event.setDuree(rs.getInt(rs.getInt(8)));
                event.setId_user(rs.getInt("id_user"));

                evenement_list.add(event);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return evenement_list;
    }

}
