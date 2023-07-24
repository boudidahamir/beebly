/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.services;

import beebly.entity.Reclamation;
import beebly.entity.User;
import beebly.tools.MaConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ahmed
 */
public class ServiceReclamationIMP {
        Connection cnx;

    public ServiceReclamationIMP() {
        cnx = MaConnection.getInstance().getCnx();
    }
    
    Date date_1 = new Date();
    String today_date = new SimpleDateFormat("yyyy-MM-dd").format(date_1); 
    
    public void ajouter_reclamtion(Reclamation reclamation) {
        
        String req = "INSERT INTO reclamation (type,sujet,description,date,photo,status,id_user) VALUES(?,?,?,?,?,?,?)" ;
        PreparedStatement  pst;
        try {
            pst = cnx.prepareStatement(req);
            System.out.println(reclamation.getSujet());
            pst.setString(1, reclamation.getType());
            pst.setString(2, reclamation.getSujet());
            pst.setString(3, reclamation.getDescription());
            pst.setDate(4, reclamation.getDate());
            pst.setBytes(5, reclamation.getPhoto());
            pst.setString(6, reclamation.getStatus()); 
            pst.setInt(7, reclamation.getUser().getId());
            pst.executeUpdate();
            System.out.println("Réclamation envoyée avec succés");
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        
    } 
    
    
    public List<Reclamation> get_reclamations() {
        List<Reclamation> reclamation_list = new ArrayList();
        PreparedStatement  pst;
        try {
            String req ="select * from reclamation ORDER BY id DESC, date DESC";
            pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { 
                 Reclamation reclamation=new Reclamation(); 
                 reclamation.setId(rs.getInt(1));
                 reclamation.setType(rs.getString(2));
                 reclamation.setSujet(rs.getString(3));
                 reclamation.setDescription(rs.getString(4)); 
                 reclamation.setDate(rs.getDate(5));
                 reclamation.setPhoto(rs.getBytes(6));
                 reclamation.setStatus(rs.getString(7));
                 reclamation.setUser(get_user_by_id(rs.getInt(8)));
                 reclamation.setReponse(new ServiceReponseIMP().get_reponse_by_id(rs.getInt(9)));
                 reclamation_list.add(reclamation);              
             }           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reclamation_list;
    }
    
    public Reclamation get_reclamation_by_id(int id) {
        PreparedStatement  pst;
        Reclamation reclamation=new Reclamation(); 
        try {
            String req ="select * from reclamation where id="+id+"";
            pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { 
                 reclamation.setId(rs.getInt(1));
                 reclamation.setType(rs.getString(2));
                 reclamation.setSujet(rs.getString(3));
                 reclamation.setDescription(rs.getString(4)); 
                 reclamation.setDate(rs.getDate(5));
                 reclamation.setPhoto(rs.getBytes(6));
                 reclamation.setStatus(rs.getString(7));
                 reclamation.setUser(get_user_by_id(rs.getInt(8)));
                 reclamation.setReponse(new ServiceReponseIMP().get_reponse_by_id(rs.getInt(9)));
             }           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reclamation;
    }
    
    public List<Reclamation> get_reclamations_by_user_id(int id) {
        List<Reclamation> reclamation_list = new ArrayList();
        PreparedStatement  pst;
        try {
            String req ="select * from reclamation where id_user="+id+" ORDER BY id DESC, date DESC";
            pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { 
                 Reclamation reclamation=new Reclamation(); 
                 reclamation.setId(rs.getInt(1));
                 reclamation.setType(rs.getString(2));
                 reclamation.setSujet(rs.getString(3));
                 reclamation.setDescription(rs.getString(4)); 
                 reclamation.setDate(rs.getDate(5));
                 reclamation.setPhoto(rs.getBytes(6));
                 reclamation.setStatus(rs.getString(7));
                  reclamation.setUser(get_user_by_id(rs.getInt(8)));
                 reclamation.setReponse(new ServiceReponseIMP().get_reponse_by_id(rs.getInt(9)));
                 reclamation_list.add(reclamation);              
             }           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reclamation_list;
    }
    public List<Reclamation> get_reclamations_by_user_email(String email) {
        List<Reclamation> reclamation_list = new ArrayList();
        PreparedStatement  pst;
        try {
            String req ="select * from reclamation r join user u on r.id_user=u.id where email='"+email+"' ORDER BY r.id DESC, date DESC";
            pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { 
                 Reclamation reclamation=new Reclamation(); 
                 reclamation.setId(rs.getInt(1));
                 reclamation.setType(rs.getString(2));
                 reclamation.setSujet(rs.getString(3));
                 reclamation.setDescription(rs.getString(4)); 
                 reclamation.setDate(rs.getDate(5));
                 reclamation.setPhoto(rs.getBytes(6));
                 reclamation.setStatus(rs.getString(7));
                  reclamation.setUser(get_user_by_id(rs.getInt(8)));
                 reclamation.setReponse(new ServiceReponseIMP().get_reponse_by_id(rs.getInt(9)));
                 reclamation_list.add(reclamation);              
             }           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reclamation_list;
    }
    
    public List<Reclamation> get_reclamations_by_type(String type) {
        List<Reclamation> reclamation_list = new ArrayList();
        PreparedStatement  pst;
        try {
            String req ="select * from reclamation where type='"+type+"' ORDER BY id DESC, date DESC";
            pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { 
                 Reclamation reclamation=new Reclamation(); 
                 reclamation.setId(rs.getInt(1));
                 reclamation.setType(rs.getString(2));
                 reclamation.setSujet(rs.getString(3));
                 reclamation.setDescription(rs.getString(4)); 
                 reclamation.setDate(rs.getDate(5));
                 reclamation.setPhoto(rs.getBytes(6));
                 reclamation.setStatus(rs.getString(7));
                 reclamation.setUser(get_user_by_id(rs.getInt(8)));
                 reclamation.setReponse(new ServiceReponseIMP().get_reponse_by_id(rs.getInt(9)));
                 reclamation_list.add(reclamation);              
             }           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reclamation_list;
    }
    
    public List<Reclamation> get_reclamations_by_user_id_by_type(int id,String type) {
        List<Reclamation> reclamation_list = new ArrayList();
        PreparedStatement  pst;
        try {
            String req ="select * from reclamation where id_user="+id+" and type='"+type+"' ORDER BY id DESC, date DESC";
            pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { 
                 Reclamation reclamation=new Reclamation(); 
                 reclamation.setId(rs.getInt(1));
                 reclamation.setType(rs.getString(2));
                 reclamation.setSujet(rs.getString(3));
                 reclamation.setDescription(rs.getString(4)); 
                 reclamation.setDate(rs.getDate(5));
                 reclamation.setPhoto(rs.getBytes(6));
                 reclamation.setStatus(rs.getString(7));
                 reclamation.setUser(get_user_by_id(rs.getInt(8)));
                 reclamation.setReponse(new ServiceReponseIMP().get_reponse_by_id(rs.getInt(9)));
                 reclamation_list.add(reclamation);              
             }           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reclamation_list;
    }
    public List<Reclamation> get_reclamations_by_status(String status) {
        List<Reclamation> reclamation_list = new ArrayList();
        PreparedStatement  pst;
        try {
            String req ="select * from reclamation where status='"+status+"' ORDER BY id DESC, date DESC";
            pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { 
                 Reclamation reclamation=new Reclamation(); 
                 reclamation.setId(rs.getInt(1));
                 reclamation.setType(rs.getString(2));
                 reclamation.setSujet(rs.getString(3));
                 reclamation.setDescription(rs.getString(4)); 
                 reclamation.setDate(rs.getDate(5));
                 reclamation.setPhoto(rs.getBytes(6));
                 reclamation.setStatus(rs.getString(7));
                 reclamation.setUser(get_user_by_id(rs.getInt(8)));
                 reclamation.setReponse(new ServiceReponseIMP().get_reponse_by_id(rs.getInt(9)));
                 reclamation_list.add(reclamation);              
             }           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reclamation_list;
    }
    public List<Reclamation> get_reclamations_by_user_id_by_status(int id,String status) {
        List<Reclamation> reclamation_list = new ArrayList();
        PreparedStatement  pst;
        try {
            String req ="select * from reclamation where id_user="+id+" and status='"+status+"' ORDER BY id DESC, date DESC";
            pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { 
                 Reclamation reclamation=new Reclamation(); 
                 reclamation.setId(rs.getInt(1));
                 reclamation.setType(rs.getString(2));
                 reclamation.setSujet(rs.getString(3));
                 reclamation.setDescription(rs.getString(4)); 
                 reclamation.setDate(rs.getDate(5));
                 reclamation.setPhoto(rs.getBytes(6));
                 reclamation.setStatus(rs.getString(7));
                 reclamation.setUser(get_user_by_id(rs.getInt(8)));
                 reclamation.setReponse(new ServiceReponseIMP().get_reponse_by_id(rs.getInt(9)));
                 reclamation_list.add(reclamation);              
             }           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reclamation_list;
    }
    
    public List<Reclamation> get_reclamations_by_date(LocalDate date) {
        List<Reclamation> reclamation_list = new ArrayList();
        PreparedStatement  pst;
        try {
            String req ="select * from reclamation where date='"+date+"' ";
            pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { 
                 Reclamation reclamation=new Reclamation(); 
                 reclamation.setId(rs.getInt(1));
                 reclamation.setType(rs.getString(2));
                 reclamation.setSujet(rs.getString(3));
                 reclamation.setDescription(rs.getString(4)); 
                 reclamation.setDate(rs.getDate(5));
                 reclamation.setPhoto(rs.getBytes(6));
                 reclamation.setStatus(rs.getString(7));
                  reclamation.setUser(get_user_by_id(rs.getInt(8)));
                 reclamation.setReponse(new ServiceReponseIMP().get_reponse_by_id(rs.getInt(9)));
                 reclamation_list.add(reclamation);              
             }           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reclamation_list;
    }
    public List<Reclamation> get_reclamations_by_user_id_by_date(int id,LocalDate date) {
        List<Reclamation> reclamation_list = new ArrayList();
        PreparedStatement  pst;
        try {
            String req ="select * from reclamation where id_user="+id+" and date='"+date+"' ";
            pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { 
                 Reclamation reclamation=new Reclamation(); 
                 reclamation.setId(rs.getInt(1));
                 reclamation.setType(rs.getString(2));
                 reclamation.setSujet(rs.getString(3));
                 reclamation.setDescription(rs.getString(4)); 
                 reclamation.setDate(rs.getDate(5));
                 reclamation.setPhoto(rs.getBytes(6));
                 reclamation.setStatus(rs.getString(7));
                  reclamation.setUser(get_user_by_id(rs.getInt(8)));
                 reclamation.setReponse(new ServiceReponseIMP().get_reponse_by_id(rs.getInt(9)));
                 reclamation_list.add(reclamation);              
             }           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return reclamation_list;
    }
    public void update_reclamation(Reclamation reclamation) {
        String req = "UPDATE reclamation SET sujet = ? , description = ?, type = ? ,photo = ? where id = ?";
        PreparedStatement  pst;
        try {
                pst = cnx.prepareStatement(req); 
                pst.setString(1, reclamation.getSujet());
                pst.setString(2, reclamation.getDescription());
                pst.setString(3, reclamation.getType());
                pst.setBytes(4,reclamation.getPhoto());
                pst.setInt(5,reclamation.getId());
                pst.executeUpdate(); 
                System.out.println("modification de la reclamation effectué avec succés");
        } catch (SQLException e) {
                System.err.println(e.getMessage());
        }
    }
    
    public void update_reclamation_status(int id,int id_reponse) {
        String req = "UPDATE reclamation SET status = ? , id_reponse = ? where id = ?";
        PreparedStatement  pst;
        try {
                pst = cnx.prepareStatement(req); 
                pst.setString(1, "traitée");
                pst.setInt(2, id_reponse);
                pst.setInt(3,id);
                pst.executeUpdate(); 
                System.out.println("modification de la reclamation effectué avec succés");
        } catch (SQLException e) {
                System.err.println(e.getMessage());
        }
    }
    
    public void delete_reclamation(int id) {
        try {
            String req = "delete from reclamation where id= ? ";
            PreparedStatement ps = cnx.prepareStatement(req);
            ps.setInt(1, id);
            ps.executeUpdate();
            System.out.println("suppression de la reclamation effectué avec succés");
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
    public User get_user_by_id(int id) {
        PreparedStatement  pst;
        User user=new User(); 
        try {
            String req ="select * from user where id="+id+"";
            pst = cnx.prepareStatement(req);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) { 
                 user.setId(rs.getInt("id"));
                 user.setNom(rs.getString("nom"));
                 user.setPrenom(rs.getString("prenom"));
                 user.setAdrmail(rs.getString("adrmail"));
                 user.setMdp(rs.getString("mdp"));
                 user.setTel(rs.getString("tel"));
             }           
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return user;
    }
    
          
}