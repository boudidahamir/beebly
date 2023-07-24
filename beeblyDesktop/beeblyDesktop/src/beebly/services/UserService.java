/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beebly.services;

import beebly.entity.User;
import beebly.tools.MaConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.FileOutputStream;
import java.sql.PreparedStatement;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.FillPatternType;

/**
 *
 * @author amirb
 */
public class UserService {

    Connection cnx;
    public static User currentuser;

    public UserService() {
        cnx = MaConnection.getInstance().getCnx();
    }

    public String login(String mail, String mdp) {
        String type = "nouser";
        try {
            String sql = "SELECT * FROM user where adrmail='" + mail + "' && mdp='" + mdp + "'";
            Statement ste = cnx.createStatement();
            ResultSet s = ste.executeQuery(sql);
            if (s.next()) {
                currentuser = new User(s.getInt("id"), s.getString("nom"), s.getString("prenom"), s.getString("adrmail"), s.getString("mdp"), s.getString("adresse"), s.getString("tel"), s.getString("type"));
                System.out.println(currentuser.getId());
                return s.getString("type");
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return type;
    }

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);

        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }

    public void generateExcel() {
        String sql = "select * from user";
        Statement ste;
        try {

            ste = cnx.prepareStatement(sql);
            ResultSet rs = ste.executeQuery(sql);
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFCellStyle csCouleur = wb.createCellStyle();
            csCouleur.setFillForegroundColor((short) 45);
            HSSFFont font = wb.createFont();
            font.setBold(true);
            font.setColor((short) 13);

            csCouleur.setFont(font);

            csCouleur.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            HSSFSheet sheet = wb.createSheet("user details ");
            HSSFCellStyle style = createStyleForTitle(wb);
            HSSFRow header = sheet.createRow(0);

            header.createCell(1).setCellValue("id");
            header.createCell(2).setCellValue("Nom");
            header.createCell(3).setCellValue("Prenom");
            header.createCell(4).setCellValue("Email");
            header.createCell(5).setCellValue("Adresse");
            header.createCell(6).setCellValue("Tel");
            header.createCell(7).setCellValue("Type");
            header.createCell(8).setCellValue("Cin");
            header.createCell(9).setCellValue("Soldepoint");

            header.getCell(1).setCellStyle(csCouleur);
            header.getCell(2).setCellStyle(csCouleur);
            header.getCell(3).setCellStyle(csCouleur);
            header.getCell(4).setCellStyle(csCouleur);
            header.getCell(5).setCellStyle(csCouleur);
            header.getCell(6).setCellStyle(csCouleur);
            header.getCell(7).setCellStyle(csCouleur);
            header.getCell(8).setCellStyle(csCouleur);
            header.getCell(9).setCellStyle(csCouleur);

            int index = 1;
            System.out.println("22222");
            while (rs.next()) {
                System.out.println("3333");
                HSSFCellStyle cs1Couleur = wb.createCellStyle();
                cs1Couleur.setFillForegroundColor((short) 45);
                HSSFRow row = sheet.createRow(index);

                row.createCell(1).setCellValue(rs.getString("id"));
                row.createCell(2).setCellValue(rs.getString("nom"));
                row.createCell(3).setCellValue(rs.getString("prenom"));
                row.createCell(4).setCellValue(rs.getString("adrmail"));
                row.createCell(5).setCellValue(rs.getString("adresse"));
                row.createCell(6).setCellValue(rs.getString("tel"));
                row.createCell(7).setCellValue(rs.getString("type"));
                row.createCell(8).setCellValue(rs.getString("cin"));
                row.createCell(9).setCellValue(rs.getString("soldepoint"));
                row.getCell(1).setCellStyle(cs1Couleur);
                row.getCell(2).setCellStyle(cs1Couleur);
                row.getCell(3).setCellStyle(cs1Couleur);
                row.getCell(4).setCellStyle(cs1Couleur);
                row.getCell(5).setCellStyle(cs1Couleur);
                row.getCell(6).setCellStyle(cs1Couleur);
                row.getCell(7).setCellStyle(cs1Couleur);
                row.getCell(8).setCellStyle(cs1Couleur);
                row.getCell(9).setCellStyle(cs1Couleur);
                index++;
            }
            System.out.println("1111");
            FileOutputStream fileOut = new FileOutputStream("C:\\Users\\amirb\\OneDrive\\Bureau\\test\\users.Xls");
            wb.write(fileOut);
            fileOut.close();
            ste.close();
            rs.close();

        } catch (SQLException e) {
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }

    public boolean getUtilisateurByEmail(String email) {
        boolean exist = false;

        try {
            String sql = "SELECT * FROM user where adrmail=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, email);

            ResultSet rs = ste.executeQuery();//resultat requete sql
            if (rs.first()) {
                return true;
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return exist;

    }

    public String getUtilisateurByEmail1(String email) {

        try {
            String sql = "SELECT * FROM user where adrmail=?";
            PreparedStatement ste = cnx.prepareStatement(sql);
            ste.setString(1, email);

            ResultSet rs = ste.executeQuery();//resultat requete sql
            if (rs.first()) {

                return rs.getString("nom") + " " + rs.getString("prenom");
            }

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
        return "";

    }

    public void ChangePasswordWithEmail(String email, String newPassword) {//autoincrement
        String sql = "UPDATE user SET mdp = ? WHERE adrmail = ?";
        try {
            PreparedStatement ste = cnx.prepareStatement(sql);

            ste.setString(1, newPassword);
            ste.setString(2, email);

            ste.executeUpdate();
            System.out.println("mot de passe modifié avec succées");

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
