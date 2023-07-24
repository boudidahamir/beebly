/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import beebly.entity.Admin;
import beebly.tools.MaConnection;

/**
 *
 * @author amirb
 */
public class AdminServices implements InterfaceService<Admin> {
    Connection cnx;

    public AdminServices() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Admin t) {
        try {
            String sql = "insert into user(nom,prenom,adrmail,mdp,adresse,tel,type,cin,soldepoint)"
                    + "values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, t.getNom());
            ste.setString(2, t.getPrenom());
            ste.setString(3, t.getAdrmail());
            ste.setString(4, t.getMdp());
            ste.setString(5, t.getAdresse());
            ste.setString(6, t.getTel());
            ste.setString(7, "admin");
            ste.setInt(8, t.getCin());
            ste.setInt(9, 0);
            
            
            ste.executeUpdate();
            
            System.out.println("Admin ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Admin> getAll() {
        List<Admin> Admins = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user ";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Admin p = new Admin(s.getInt("cin"),s.getInt("id"),s.getString("nom"), s.getString("prenom"), s.getString("adrmail"), s.getString("mdp"), s.getString("adresse"), s.getString("tel"),s.getString("type"));
                Admins.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Admins;
    }

    public Admin finduser(int id) {
         try {
            String sql = "SELECT * FROM user where id="+id;
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            if (s.next()) {

                Admin p = new Admin(s.getInt("cin"),s.getInt("id"),s.getString("nom"), s.getString("prenom"), s.getString("adrmail"), s.getString("mdp"), s.getString("adresse"), s.getString("tel"),s.getString("type"));
                return p;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    public void supprimerAdmin(Admin p) {
        String sql = "delete from user where id=?";
        try {
            Statement ste1 = cnx.createStatement();
            
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, p.getId());
            ste.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());            
        }

    }

    public void modifierAdmin(Admin p) {
        try {
            String sql = "update user set nom=? , prenom=? , adrmail=? , mdp=? , adresse=? , tel=?,cin=?  where id=?";

            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, p.getNom());
            ste.setString(2, p.getPrenom());
            ste.setString(3, p.getAdrmail());
            ste.setString(4, p.getMdp());
            ste.setString(5, p.getAdresse());
            ste.setString(6, p.getTel());
            ste.setInt(7, p.getCin());
            ste.setInt(8,p.getId());
            ste.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

}
        public String getCin(int id) {
        try {
            String sql = "SELECT * FROM user where id="+id;
            Statement ste = cnx.createStatement();
            
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Admin p = new Admin(s.getInt("cin"),s.getInt("id"),s.getString("nom"), s.getString("prenom"), s.getString("adrmail"), s.getString("mdp"), s.getString("adresse"), s.getString("tel"),s.getString("type"));
                return ""+p.getCin();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "0";

    }
        
        public boolean checkmail(String mail) {
        boolean test=false;
         try {
            String sql = "SELECT * FROM user where adrmail='"+mail+"' ";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            if(s.next()) {
                test=true;

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return test;
    }

    @Override
    public List<Admin> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
        public List<Admin> search(String rech) {
        List<Admin> Admins = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user where nom like('"+rech+"%') or prenom like('"+rech+"%') or adrmail like('"+rech+"%')";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Admin p = new Admin(s.getInt("cin"),s.getInt("id"),s.getString("nom"), s.getString("prenom"), s.getString("adrmail"), s.getString("mdp"), s.getString("adresse"), s.getString("tel"),s.getString("type"));
                Admins.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Admins;
    }
    
}
