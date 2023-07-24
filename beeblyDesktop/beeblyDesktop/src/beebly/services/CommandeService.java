/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.services;

import beebly.entity.Client;
import beebly.entity.Commande;
import beebly.entity.EtatCommande;
import beebly.tools.MaConnection;
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
public class CommandeService {

    Connection myc;

    public CommandeService() {
        myc = MaConnection.getInstance().getCnx();
    }

    public void Ajouter(Commande c) {
        try {
            String sq1 = "insert into commande(idClient,adresselivraison,prix) values (?,?,?)";
            PreparedStatement ps = myc.prepareStatement(sq1);
            ps.setInt(1, c.getClient().getId());
            ps.setString(2, c.getAdresseLivraison());
           // ps.setString(3, c.getEtatCommande().toString());
            ps.setFloat(3, c.getPrix());

            ps.executeUpdate();
            System.out.println("ajout commande effectue");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Modifier(String T, Commande c) {

        try {
            String req = "UPDATE commande SET adresselivraison=?,prix=? WHERE idCommande=?;";
            PreparedStatement ps = myc.prepareStatement(req);

            ps.setString(1, T);
            
            ps.setFloat(2, c.getPrix());
            ps.setInt(3, c.getIdCommande());
            

            ps.executeUpdate();
            System.out.println("commande modifie avec succees");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void Supprimer(Commande c) {
        try {
            Statement st = myc.createStatement();
            String req = "DELETE FROM commande WHERE idCommande = " + c.getIdCommande() + "";
            st.executeUpdate(req);
            System.out.println("La commande avec l'id = " + c.getIdCommande() + " a été supprimer avec succès...");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Commande> Afficher() {
        List<Commande> commandes = new ArrayList<>();
        try {
            String req = "select * from commande";
            Statement st = myc.createStatement();
            ResultSet rs = st.executeQuery(req);
            while (rs.next()) {
                Commande c = new Commande();
                
                c.getClient().setId(rs.getInt("idClient"));
                c.setAdresseLivraison(rs.getString("adresselivraison"));
               /* try {
                    c.setEtatCommande(EtatCommande.valueOf(rs.getString("etatcommande")));
                } catch (IllegalArgumentException ex) {

                    System.err.println("Invalid value for EtatCommande: " + rs.getString("etatcommande"));
                    c.setEtatCommande(EtatCommande.NouvelEtat);
                }*/
                c.setPrix(rs.getFloat("prix"));

                commandes.add(c);
            }
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }

        return commandes;
    }
}
