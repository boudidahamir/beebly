/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.services;

import beebly.entity.LivreNumerique;
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
public class LivreNumeriqueService {

    Connection myc;

    public LivreNumeriqueService() {
       myc = MaConnection.getInstance().getCnx();
    }

    public LivreNumerique get(int id) {
        LivreNumerique lnm = new LivreNumerique();
        try {
            String sql = "select * from livrenumerique where idLivreNumerique=?";
            PreparedStatement ps = myc.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                lnm = new LivreNumerique(rs.getInt(1));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return lnm;
    }

    public void Ajouter(LivreNumerique lnm) {
        try {
            String sq1 = "insert into livrenumerique(idLivre,livrePdf,prix) values (?,?,?)";
            PreparedStatement ps = myc.prepareStatement(sq1);

            ps.setInt(1, lnm.getIdLivre());
            ps.setString(2, lnm.getLivrePdf());
            ps.setFloat(3, lnm.getPrix());

            ps.executeUpdate();
            System.out.println("ajout livre numerique effectue");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Modifier(int T, LivreNumerique lnm) {

        try {
            String req = "UPDATE livrenumerique SET idLivre=?,livrePdf=?,prix=? WHERE idLivreNumerique=?;";
            PreparedStatement ps = myc.prepareStatement(req);

            ps.setInt(1, lnm.getIdLivre());
            ps.setString(2, lnm.getLivrePdf());
            ps.setFloat(3, lnm.getPrix());
            ps.setInt(4, lnm.getIdLivreNum());

            ps.executeUpdate();
            System.out.println("livre numerique modifie avec succees");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Supprimer(LivreNumerique lnm) {
        try {
            Statement st = myc.createStatement();
            String req = "DELETE FROM livrenumerique WHERE idLivreNumerique = " + lnm.getIdLivreNum() + "";
            st.executeUpdate(req);
            System.out.println("Le livre numerique avec l'id = " + lnm.getIdLivreNum() + " a été supprimer avec succès...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
