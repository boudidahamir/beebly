/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.services;

import beebly.entity.Commande;
import beebly.entity.LivreNumerique;
import beebly.tools.MyDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author amona
 */
public class LigneCommandeNumeriqueService {
    
    Connection myc;

    public LigneCommandeNumeriqueService() {
        myc = MyDB.getInstance().getConnection();
    }
    
    public void AjouterListe(Commande c) {

        c.getLivreNumeriqueList().forEach((i) -> {
            try {

                String sq1 = "insert into commandelivnumerique(idCommande,idLivre) values (?,?)";
                PreparedStatement ps = myc.prepareStatement(sq1);
                ps.setInt(1, c.getIdCommande());
                ps.setInt(2, i.getIdLivre());

                ps.executeUpdate();
                System.out.println("ajout ligne numerique effectue");
            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        });
    }

    public void ModifierListe(String T, Commande c) {

        c.getLivreNumeriqueList().forEach((i) -> {
            try {
                String req = "UPDATE commandelivnumerique SET idCommande=?,idLivre=? WHERE idCommande=?;";
                PreparedStatement ps = myc.prepareStatement(req);

                ps.setInt(1, c.getIdCommande());
                ps.setInt(2, i.getIdLivre());
                ps.setInt(3, c.getIdCommande());

                ps.executeUpdate();
                System.out.println("ligne numerique modifie avec succees");

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        });
    }

    public void SupprimerListe(Commande c) {

        try {
            Statement st = myc.createStatement();
            String req = "DELETE FROM commandelivnumerique WHERE idCommande = " + c.getIdCommande() + "";
            st.executeUpdate(req);
            System.out.println("La ligne numerique avec l'id = " + c.getIdCommande() + " a été supprimer avec succès...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<LivreNumerique> ChargerListe(Commande c) {
        List<LivreNumerique> listelivnumerique = new ArrayList<>();
        LivreNumeriqueService LivNumeriqueService = new LivreNumeriqueService();
        try {
            String req = "select * from commandelivnumerique WHERE idCommande=" + c.getIdCommande() + "";
            Statement st = myc.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                listelivnumerique.add(LivNumeriqueService.get(rs.getInt("idLivre")));
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return listelivnumerique;
    }
    
}
