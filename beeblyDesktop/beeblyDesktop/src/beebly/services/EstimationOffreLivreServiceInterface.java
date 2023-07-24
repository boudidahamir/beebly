package beebly.services;

import beebly.entity.EstimationOffreLivre;
import beebly.entity.enums.Etat;
import beebly.tools.MaConnection;
import beebly.tools.SmsTool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstimationOffreLivreServiceInterface implements ServiceInterface<EstimationOffreLivre> {

    Connection cnx;
    PropositionLivreServiceInterface propositionLivreService= new PropositionLivreServiceInterface();

    public EstimationOffreLivreServiceInterface() {
        cnx = MaConnection.getInstance().getCnx();
    }
    @Override
    public EstimationOffreLivre add(EstimationOffreLivre objet) {
        SmsTool.envoyer("On vous propose"+Integer.toString(objet.getPointEstime())+" sur votre livre "+
                objet.getProposition().getTitreLivre(),"+21628168997");
        try {
            String sql = "insert into estimationoffrelivre(idProposition,pointEstime,etat)"
                    + "values (?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, objet.getProposition().getIdProposition());
            ste.setInt(2, objet.getPointEstime());
            ste.setString(3, objet.getEtat().toString());
            ste.executeUpdate();
            System.out.println("Estimation Offre Livre ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;    }

    @Override
    public EstimationOffreLivre get(int id) {
        EstimationOffreLivre estimationoffrelivre=null;
        try {
            String sql = "select * from estimationoffrelivre where idEstimationOffreLivre=?";
            PreparedStatement s = cnx.prepareStatement(sql);
            s.setInt(1,id);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {

                 estimationoffrelivre  = new EstimationOffreLivre(rs.getInt(1), propositionLivreService.get( rs.getInt(2)),
                        rs.getInt(3), Etat.valueOf(rs.getString(4)));

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return estimationoffrelivre;
    }

    @Override
    public List<EstimationOffreLivre> getall() {


        List<EstimationOffreLivre> estimationOffreLivreList = new ArrayList<>();
        try {
            String sql = "select * from estimationoffrelivre";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);

            while (s.next()) {
                EstimationOffreLivre estimationoffrelivre  = new EstimationOffreLivre(s.getInt(1), propositionLivreService.get( s.getInt(2)),
                        s.getInt(3), Etat.valueOf(s.getString(4)));
                estimationOffreLivreList.add(estimationoffrelivre);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return estimationOffreLivreList;    }





    @Override
    public boolean delete(int id) {

        String sql = "delete from estimationoffrelivre where idEstimationOffreLivre=?";
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
    public boolean modify(EstimationOffreLivre objet) {
        return false;
    }
}
