/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Fayechi
 */
public class MaConnection {
       private Connection cnx;
        String url = "jdbc:mysql://localhost:3306/web";
        String user = "root";
        String pwd = "";
        public static MaConnection ct;

    private MaConnection() {
        try {
            cnx = DriverManager.getConnection(url,user,pwd);
            System.out.println("Cnx etablie ");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public static MaConnection getInstance(){
        if(ct ==null)
            ct= new MaConnection();
        return ct;
    }

    public Connection getCnx() {
        return cnx;
    }

   
        
}
