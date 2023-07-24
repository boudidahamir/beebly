package beebly.services;

import javafx.scene.chart.XYChart;
import beebly.entity.PropositionLivre;
import beebly.tools.MaConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PropositionLivreServiceInterface implements ServiceInterface<PropositionLivre> {

    Connection cnx;

    ClientServiceInterface clientService;

    public PropositionLivreServiceInterface() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public PropositionLivre add(PropositionLivre objet) {
        try {
            String sql = "insert into propositionlivre(idclient,titreLivre,editon,dateProposition,descriptionEtat)"
                    + "values (?,?,?,?,?)";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setInt(1, objet.getClient().getId());
            ste.setString(2, objet.getTitreLivre());
            ste.setString(3, objet.getEditon());
            ste.setDate(4,Date.valueOf(LocalDate.now()) );
            ste.setString(5, objet.getDescriptionEtat());

            ste.executeUpdate();
            System.out.println("PropositionLivre ajoutée");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return null;
    }

    @Override
    public PropositionLivre get(int id) {
        clientService =new ClientServiceInterface();
        PropositionLivre propositionLivre=null;
        try {
            String sql = "select * from propositionlivre where idPropositionLivre=?";
            PreparedStatement s = cnx.prepareStatement(sql);
            s.setInt(1,id);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {

                PropositionLivre p = new PropositionLivre(rs.getInt(1),clientService.get(rs.getInt(2)),
                        rs.getString(3), rs.getString(4)
                        , rs.getString(6),rs.getDate(5));
              propositionLivre=p;

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return propositionLivre;

    }





    public List<PropositionLivre> getTraite() {
        clientService =new ClientServiceInterface();
        List<PropositionLivre> PropositionLivreList = new ArrayList<>();
        try {
            String sql = "select * from propositionlivre INNER JOIN " +
                    "estimationoffrelivre ON propositionlivre.idPropositionLivre = estimationoffrelivre.idProposition";

            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {



                PropositionLivre p = new PropositionLivre(s.getInt(1),clientService.get(s.getInt(2)),
                        s.getString(3), s.getString(4)
                        , s.getString(6),s.getDate(5));
                PropositionLivreList.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return PropositionLivreList;    }


    public List<PropositionLivre> getNonTraite() {
        clientService =new ClientServiceInterface();
        List<PropositionLivre> PropositionLivreList = new ArrayList<>();
        try {
            String sql = "select * from propositionlivre LEFT JOIN " +
                    "estimationoffrelivre ON propositionlivre.idPropositionLivre = estimationoffrelivre.idProposition" +
                    " WHERE estimationoffrelivre.idProposition IS NULL ";


            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {



                PropositionLivre p = new PropositionLivre(s.getInt(1),clientService.get(s.getInt(2)),
                        s.getString(3), s.getString(4)
                        , s.getString(6),s.getDate(5));
                PropositionLivreList.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return PropositionLivreList;    }


    @Override
    public List<PropositionLivre> getall() {
    clientService =new ClientServiceInterface();
        List<PropositionLivre> PropositionLivreList = new ArrayList<>();
        try {
            String sql = "select * from propositionlivre";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            while (s.next()) {



                PropositionLivre p = new PropositionLivre(s.getInt(1),clientService.get(s.getInt(2)),
                        s.getString(3), s.getString(4)
                        , s.getString(6),s.getDate(5));
                  PropositionLivreList.add(p);

            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return PropositionLivreList;    }

    @Override
    public boolean delete(int id) {
        String sql = "delete from PropositionLivre where idPropositionLivre=?";
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
    public boolean modify(PropositionLivre objet) {


        return false;
    }



    public XYChart.Series getStat(int mois,int année){
        XYChart.Series xy =new XYChart.Series();

        clientService =new ClientServiceInterface();
        PropositionLivre propositionLivre=null;
        try {
            String sql = "select COUNT(idPropositionLivre),dateProposition from propositionlivre" +
                    " WHERE month(dateProposition)=? AND year(dateProposition)=? GROUP BY" +
                    " dateProposition ORDER BY dateProposition ";

            PreparedStatement s = cnx.prepareStatement(sql);
            s.setInt(1,mois);
            s.setInt(2,année);


            ResultSet rs = s.executeQuery();
            while (rs.next()) {

                xy.getData().add(new XYChart.Data(String.valueOf(rs.getDate(2)),rs.getInt(1)) );


            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }










        return xy;
    }
}
