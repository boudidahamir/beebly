package beebly.services;

import beebly.entity.Client;
import beebly.tools.MaConnection;

import java.sql.*;
import java.util.List;

public class ClientServiceInterface implements ServiceInterface<Client> {

    @Override
    public Client add(Client objet) {
        return null;
    }

    Connection cnx;

    public ClientServiceInterface() {
        cnx = MaConnection.getInstance().getCnx();
    }

    @Override
    public Client get(int id) {
        Client c=new Client();
        try {
            String sql = "select * from user where id=?";
            PreparedStatement s = cnx.prepareStatement(sql);
            s.setInt(1,id);
            ResultSet rs = s.executeQuery();
            while (rs.next()) {

                 c = new Client(rs.getInt(1), rs.getString(2));


            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

       return c;
    }

    @Override
    public List<Client> getall() {
        return null;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean modify(Client objet) {
        return false;
    }
}
