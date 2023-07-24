package beebly.services;

import beebly.entity.ClotureAchat;
import beebly.entity.enums.EtatCloture;
import beebly.tools.MaConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClotureAchatServiceInterface implements ServiceInterface<ClotureAchat> {
    Connection cnx;
    public ClotureAchatServiceInterface() {
        cnx = MaConnection.getInstance().getCnx();
    }

    DétailsLivraisonServiceInterface détailsLivraisonService =new DétailsLivraisonServiceInterface();

    @Override
    public ClotureAchat add(ClotureAchat objet) {
        try {
            String sql = "insert into cloture_achat(id_details_livraison,pointAchatFinale,etatCloture,etatLivre)"
                    + "values (?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, objet.getDetailsLivraison().getIdDetailsLivraison());
            ste.setInt(2, objet.getPointAchatFinale());
            ste.setString(3, objet.getEtatCloture().toString());
            ste.setString(4, objet.getEtatLivre());

            ste.executeUpdate();
            System.out.println("cloture  ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public ClotureAchat get(int id) {
        return null;
    }

    @Override
    public List<ClotureAchat> getall() {
        List<ClotureAchat> clotureAchatList = new ArrayList<>();
        try {
            String sql = "select * from cloture_achat";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);

            while (s.next()) {
                ClotureAchat cloture_achat  = new ClotureAchat(s.getInt(1), détailsLivraisonService.get( s.getInt(2)),
                        s.getInt(3), EtatCloture.valueOf(s.getString(4)),s.getString(5));
                clotureAchatList.add(cloture_achat);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return clotureAchatList;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from cloture_achat where id_cloture=?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1,id);
            ste.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return true;
    }

    @Override
    public boolean modify(ClotureAchat objet) {
        return false;
    }
}
