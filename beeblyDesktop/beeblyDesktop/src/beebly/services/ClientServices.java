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
import beebly.entity.Client;
import beebly.tools.MaConnection;

/**
 *
 * @author amirb
 */
public class ClientServices implements InterfaceService<Client> {
    Connection cnx; 

    public ClientServices() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public void ajouter(Client t) {
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
            ste.setString(7, "client");
            ste.setInt(8, 0);
            ste.setInt(9, t.getSoldepoint());
            
            
            ste.executeUpdate();
            
            System.out.println("Client ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }

    @Override
    public List<Client> getAll() {
        List<Client> Clients = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user where type='client' ";
            Statement ste = cnx.createStatement();
            
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Client p = new Client(s.getInt("soldepoint"),s.getInt("id"),s.getString("nom"), s.getString("prenom"), s.getString("adrmail"), s.getString("mdp"), s.getString("adresse"), s.getString("tel"),s.getString("type"));
                Clients.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Clients;
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
    public List<Client> findById(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void supprimerClient(Client p) {
        String sql = "delete from user where id=?";
        String sql1 = "SELECT * FROM user where nom='"+p.getNom()+"' and prenom='"+p.getPrenom()+"' and adrmail='"+p.getAdrmail()+"' and mdp='"+p.getMdp()+"' and adresse='"+p.getAdresse()+"' and tel='"+p.getTel()+"' and type='client' and cin=0 and soldepoint="+p.getSoldepoint();
        Client p1=new Client();
        try {
            Statement ste1 = cnx.createStatement();
            ResultSet s = ste1.executeQuery(sql1);
            
            while (s.next()) {
                p1 = new Client(s.getInt("soldepoint"),s.getInt("id"),s.getString("nom"), s.getString("prenom"), s.getString("adrmail"), s.getString("mdp"), s.getString("adresse"), s.getString("tel"),s.getString("type"));
            }
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, p1.getId());
            ste.executeUpdate();
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            System.out.println("client");
            
        }

    }

    public void modifierClient(Client p) {
        
        try {
            String sql = "update user set nom=? , prenom=? , adrmail=? , mdp=? , adresse=? , tel=?  where id=?";

            System.out.println(p.getPrenom());
            System.out.println(p.getNom());
            System.out.println(p.getId());
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, p.getNom());
            ste.setString(2, p.getPrenom());
            ste.setString(3, p.getAdrmail());
            ste.setString(4, p.getMdp());
            ste.setString(5, p.getAdresse());
            ste.setString(6, p.getTel());
            ste.setInt(7,p.getId());
            ste.executeUpdate();
            
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
      
    public String getsoldepoint(int id) {
        try {
            String sql = "SELECT * FROM user where id="+id;
            Statement ste = cnx.createStatement();
            
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Client p = new Client(s.getInt("soldepoint"),s.getInt("id"),s.getString("nom"), s.getString("prenom"), s.getString("adrmail"), s.getString("mdp"), s.getString("adresse"), s.getString("tel"),s.getString("type"));
                return ""+p.getSoldepoint();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "0";

    }
    
        public Client finduser(int id) {
         try {
            String sql = "SELECT * FROM user where id="+id;
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            if (s.next()) {

                Client p = new Client(s.getInt("soldepoint"),s.getInt("id"),s.getString("nom"), s.getString("prenom"), s.getString("adrmail"), s.getString("mdp"), s.getString("adresse"), s.getString("tel"),s.getString("type"));
                return p;
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }
        
                public List<Client> search(String rech) {
        List<Client> Clients = new ArrayList<>();
        try {
            String sql = "SELECT * FROM user where nom like('"+rech+"%') or prenom like('"+rech+"%') or adrmail like('"+rech+"%')";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {

                Client p = new Client(s.getInt("cin"),s.getInt("id"),s.getString("nom"), s.getString("prenom"), s.getString("adrmail"), s.getString("mdp"), s.getString("adresse"), s.getString("tel"),s.getString("type"));
                Clients.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return Clients;
    }
    
}
