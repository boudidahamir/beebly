/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.services;

import beebly.entity.LivreNeuf;
import beebly.tools.MaConnection;
import beebly.tools.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author amona
 */
public class LivreNeufService {

    Connection myc;

    public LivreNeufService() {
        myc = MaConnection.getInstance().getCnx();
    }

    public LivreNeuf get(int id) {
        LivreNeuf ln = new LivreNeuf();
        try {
            String sql = "select * from livreneuf where idLivreNeuf=?";
            PreparedStatement ps = myc.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                ln = new LivreNeuf(rs.getInt(1));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return ln;
    }

    public void Ajouter(LivreNeuf ln) {
        try {
            String sq1 = "insert into livreneuf(idLivre,stockRestant,prix) values (?,?,?)";
            PreparedStatement ps = myc.prepareStatement(sq1);

            ps.setInt(1, ln.getIdLivre());
            ps.setInt(2, ln.getStockRestant());
            ps.setFloat(3, ln.getPrix());

            ps.executeUpdate();
            System.out.println("ajout livre neuf effectue");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Modifier(int T, LivreNeuf ln) {

        try {
            String req = "UPDATE livreneuf SET idLivre=?,stockRestant=?,prix=? WHERE idLivreNeuf=?;";
            PreparedStatement ps = myc.prepareStatement(req);

            ps.setInt(1, ln.getIdLivre());
            ps.setInt(2, ln.getStockRestant());
            ps.setFloat(3, ln.getPrix());
            ps.setInt(4, ln.getIdLivreNeuf());

            ps.executeUpdate();
            System.out.println("livre neuf modifie avec succees");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Supprimer(LivreNeuf ln) {
        try {
            Statement st = myc.createStatement();
            String req = "DELETE FROM livreneuf WHERE idLivreNeuf = " + ln.getIdLivreNeuf() + "";
            st.executeUpdate(req);
            System.out.println("Le livre neuf avec l'id = " + ln.getIdLivreNeuf() + " a été supprimer avec succès...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
