package beebly.services;

import beebly.entity.DétailsLivraison;
import beebly.entity.enums.EtatLivrasion;
import beebly.tools.MaConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DétailsLivraisonServiceInterface implements ServiceInterface<DétailsLivraison> {

    Connection cnx;
    EstimationOffreLivreServiceInterface estimationOffreLivreService=new EstimationOffreLivreServiceInterface();

    public DétailsLivraisonServiceInterface() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public DétailsLivraison add(DétailsLivraison objet) {
        try {
            String sql = "insert into detailslivraison(etatLivrasion,idEstimationOffreLivre,AdresseLivraison)"
                    + "values (?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, objet.getEtatLivrasion().toString());
            ste.setInt(2, objet.getEstimationOffreLivre().getIdEstimationOffreLivre());
            ste.setString(3, objet.getAdresseLivraison());

            ste.executeUpdate();
            System.out.println("détails livraison ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public DétailsLivraison get(int id) {
        DétailsLivraison détailsLivraison=null;
        try {
            String sql = "select * from detailslivraison where idDetailsLivraison=?";
            PreparedStatement s = cnx.prepareStatement(sql);
            s.setInt(1,id);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {

                 détailsLivraison  = new DétailsLivraison(rs.getInt(1), estimationOffreLivreService.get( rs.getInt(3)),
                        rs.getString(4), EtatLivrasion.valueOf(rs.getString(2)));
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return détailsLivraison;
    }

    @Override
    public List<DétailsLivraison> getall() {
        List<DétailsLivraison> détailsLivraisonList = new ArrayList<>();
        try {
            String sql = "select * from detailslivraison";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);

            while (s.next()) {
                DétailsLivraison détailsLivraison  = new DétailsLivraison(s.getInt(1), estimationOffreLivreService.get( s.getInt(3)),
                        s.getString(4), EtatLivrasion.valueOf(s.getString(2)));
                détailsLivraisonList.add(détailsLivraison);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return détailsLivraisonList;
    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from detailslivraison where idDetailsLivraison=?";
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
    public boolean modify(DétailsLivraison objet) {
        try {
            String sql = "update detailslivraison set etatLivrasion=? where idDetailsLivraison=?";

            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, objet.getEtatLivrasion().toString());
            ste.setInt(2, objet.getIdDetailsLivraison());
            ste.executeUpdate();


        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }


        return false;
    }
}
