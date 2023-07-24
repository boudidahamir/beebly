/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.services;

import beebly.entity.Livre;
import beebly.tools.MaConnection;
import beebly.tools.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author amona
 */
public class LivreService {

    Connection myConnection;

    public LivreService() {
        myConnection = MaConnection.getInstance().getCnx();
    }

    public void Ajouter(Livre l) {
        try {
            String sq1 = "insert into livre(titre,categorie,image,description) values (?,?,?,?)";
            PreparedStatement ps = myConnection.prepareStatement(sq1);
           // ps.setInt(1, l.getIdLivre());
            ps.setString(1, l.getTitre());
            ps.setString(2, l.getCategorie());
            ps.setBytes(3, l.getPhoto());
            ps.setString(4, l.getDescription());
            
            

           

            ps.executeUpdate();
            System.out.println("ajout livre effectue");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Modifier(Livre l, String x, String y, String z, Date a) {
        try {

            String requte = "update livre set titre=?,categorie=?,datePublication=?,description=? where id=" + l.getIdLivre() + "";
            PreparedStatement pst = MyDB.getInstance().getConnection().prepareStatement(requte);
            pst.setString(1, l.getTitre());
            pst.setString(2, l.getCategorie());
            pst.setDate(3, (java.sql.Date) l.getDatePublication());
            pst.setString(4, l.getDescription());
            
            pst.setInt(5, l.getIdLivre());
           

            pst.executeUpdate();
            System.out.println("Livre Modifié");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());

        }

    }

    public void Supprimer(Livre l) {

        String query = "DELETE FROM livre WHERE livre.idLivre=" + l.getIdLivre() + " ";
        try {
            Statement st = MyDB.getInstance().getConnection().createStatement();
            st.executeUpdate(query);
            System.out.println("Livre supprimé ");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Livre> Afficher() {
        List<Livre> livres = new ArrayList<>();
        try {
            String req = "select * from livre";
            Statement st = myConnection.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Livre l = new Livre();
                // l.setId(rs.getInt("id"));
                l.setTitre(rs.getString("titre"));
                l.setCategorie(rs.getString("categorie"));
                l.setDatePublication(rs.getDate("datePublication"));
                l.setPhoto(rs.getBytes("image"));
                l.setDescription(rs.getString("description"));

                livres.add(l);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return livres;
    }
}
