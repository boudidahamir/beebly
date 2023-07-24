/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.services;

import beebly.entity.LivreUse;
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
public class LivreUseService {

    Connection myc;

    public LivreUseService() {
        myc = MaConnection.getInstance().getCnx();
    }

    public LivreUse get(int id) {
        LivreUse lu = new LivreUse();
        try {
            String sql = "select * from livreuse where idLivreUse=?";
            PreparedStatement ps = myc.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

                lu = new LivreUse(rs.getInt(1));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

        return lu;
    }

    public void Ajouter(LivreUse lu) {
        try {
            String sq1 = "insert into Livreuse(idLivre,idPiece,prix,descriptionEtatLivre,note) values (?,?,?,?,?)";
            PreparedStatement ps = myc.prepareStatement(sq1);

            ps.setInt(1, lu.getIdLivre());
            ps.setInt(2, lu.getIdPiece());
            ps.setFloat(3, lu.getPrix());
            ps.setString(4, lu.getDescriptionEtatLivre());
            ps.setInt(5, lu.getNote());

            ps.executeUpdate();
            System.out.println("ajout livre use effectue");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Modifier(int T, LivreUse lu) {

        try {
            String req = "UPDATE livreuse SET idLivre=?,idPiece=?,prix=?,descriptionEtatLivre=?,note=? WHERE idLivreUse=?;";
            PreparedStatement ps = myc.prepareStatement(req);

            ps.setInt(1, lu.getIdLivre());
            ps.setInt(2, lu.getIdPiece());
            ps.setFloat(3, lu.getPrix());
            ps.setString(4, lu.getDescriptionEtatLivre());
            ps.setInt(5, lu.getNote());

            ps.executeUpdate();
            System.out.println("livre use modifie avec succees");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Supprimer(LivreUse lu) {
        try {
            Statement st = myc.createStatement();
            String req = "DELETE FROM livreuse WHERE idLivreUse = " + lu.getIdLivreUse() + "";
            st.executeUpdate(req);
            System.out.println("Le livre use avec l'id = " + lu.getIdLivreUse() + " a été supprimer avec succès...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
