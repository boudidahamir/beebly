/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.services;

import beebly.entity.Reclamation;
import beebly.entity.Reponse;
import beebly.entity.User;
import beebly.tools.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import beebly.tools.MailAHmed;

/**
 *
 * @author ahmed
 */
public class ServiceReponseIMP {
      Connection cnx;

    public ServiceReponseIMP() {
        cnx = MaConnection.getInstance().getCnx();
    }
    
    
    public void ajouter_reponse(Reponse reponse,Reclamation reclamation,User user) {
        
        String req = "INSERT INTO reponse (date,contenu) VALUES(?,?)" ;
        PreparedStatement  pst;
        String generatedColumns[] = { "ID" };
        ServiceReclamationIMP service_reclamation = new ServiceReclamationIMP();
        int id_reponse=0; 
        try { 
            pst = cnx.prepareStatement(req,generatedColumns);
            System.out.println(reponse.getDate());
            pst.setDate(1, reponse.getDate());
            pst.setString(2, reponse.getContenu());
            pst.executeUpdate();
            ResultSet rs = pst.getGeneratedKeys();
            if (rs.next()) {
                id_reponse = rs.getInt(1);
                System.out.println("Inserted ID -" + id_reponse); 
            }
            service_reclamation.update_reclamation_status(reclamation.getId(), id_reponse); 
            MailAHmed.envoyer(user, reclamation, reponse);
            System.out.println("Ajout d'une reponse effectué avec succée");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    }
    
    public Reponse get_reponse_by_id(int id) {
        PreparedStatement  pst;
        Reponse reponse=new Reponse(); 
        try {
            String req ="select * from reponse where id="+id+"";
            pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { 
                 reponse.setId(rs.getInt(1)); 
                 reponse.setDate(rs.getDate(2));
                 reponse.setContenu(rs.getString(3));
             }           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reponse;
    }
    
    public void update_reponse(Reponse reponse,Reclamation reclamation,User user) {
        String req = "UPDATE reponse SET date = ? , contenu = ? where id = ?";
        PreparedStatement  pst;
        try {
                pst = cnx.prepareStatement(req); 
                pst.setDate(1, reponse.getDate());
                pst.setString(2, reponse.getContenu());
                pst.setInt(3, reponse.getId());
                pst.executeUpdate(); 
                System.out.println("modification effectué avec succés");
                MailAHmed.envoyer_modification_reponse(user, reclamation, reponse);
        } catch (SQLException e) {
                System.err.println(e.getMessage());
        }
    }
    
}
