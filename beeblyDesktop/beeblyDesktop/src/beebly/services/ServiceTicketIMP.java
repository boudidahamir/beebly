/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beebly.services;

import beebly.entity.Evenement;
import beebly.entity.Ticket;
import beebly.entity.User;
import beebly.tools.MaConnection;
import beebly.tools.MailTools;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import beebly.tools.MyDB;
import beebly.tools.SmsTools;

/**
 * @author khaled
 */
public class ServiceTicketIMP implements ITicket<Ticket> {

    // connexion a la base de donneé
    Connection cnx;
        User user =UserService.currentuser ;
    // constructeur
    public ServiceTicketIMP() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajoutTicket(Ticket t) {
        SmsTools.sendSms(user);
        MailTools.sendMail(user);

        try {
            String req = "INSERT INTO ticket (prix,type,id_evenement) VALUES(?,?,?)";
            PreparedStatement ps = cnx.prepareStatement(req);

            ps.setInt(1, t.getPrix());
            ps.setString(2, t.getType());
            ps.setInt(3, t.getId_evenement());

            int value = ps.executeUpdate();
            if (value > 0) {
                System.out.println(" l insertion du ticket dont le type est  :" + t.getType() + " a ete effectuer avec sucess !!!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenementIMP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void modifierTicket(Ticket t, int id) {
   try {
            String req = "update ticket set prix = ? , type = ? , id_evenement = ? where id= " + id;

            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, t.getPrix());
            ps.setString(2, t.getType());
            ps.setInt(3, t.getId_evenement());
          

            int value = ps.executeUpdate();
            if (value > 0) {
                System.out.println(" la modification  du l'evenemnt dont la type est  :" + t.getType() + " a ete effectuer avec sucess !!!");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenementIMP.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public void supprimerTicket(int id) {
 try {
            String req = "DELETE FROM ticket WHERE id = ?";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            int value_supp = ps.executeUpdate();
            if (value_supp > 0) {
                System.out.println(" Suppression du ticket a ete effectuer avec sucess");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ServiceEvenementIMP.class.getName()).log(Level.SEVERE, null, ex);
        }    }

    @Override
    public List<Ticket> afficherTicket() {
  List<Ticket> list_Ticket = new ArrayList<>();

        try {

            String req = "select * from ticket";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                Ticket ticket = new Ticket();
                ticket.setId(rs.getInt(1));
                ticket.setPrix(rs.getInt("prix"));
                ticket.setType(rs.getString("type"));
                ticket.setId_evenement(rs.getInt("id_evenement"));
            

                list_Ticket.add(ticket);
                //System.out.println(list_Evenement);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); 
        }
        return list_Ticket;  
    }
    
    
    public List<Ticket> afficherTicketEventId() {
  List<Ticket> list_Ticket = new ArrayList<>();

        try {

            String req = "select id_evenement from ticket";
            Statement st = cnx.createStatement();
            ResultSet rs = st.executeQuery(req);

            while (rs.next()) {

                Ticket ticket = new Ticket();
                
                ticket.setId_evenement(rs.getInt("id_evenement"));
            

                list_Ticket.add(ticket);
                //System.out.println(list_Evenement);

            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage()); 
        }
        return list_Ticket;  
    }
    
    
    

}
