package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JLabel;
import services.MysqlConnection;

/**
 *
 * @author Hai
 */
public class PhanThuongPanelController {
    private JLabel tongChauNhoLb;
    private JLabel tongHocSinhLb;

    public PhanThuongPanelController(JLabel tongChauNhoLb, JLabel tongHoKhau) {
        this.tongChauNhoLb = tongChauNhoLb;
        this.tongHocSinhLb = tongHoKhau;
    }
    
    public PhanThuongPanelController() {
    	
    }
    public void setData() {
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT COUNT(*) AS tong FROM nhan_khau WHERE YEAR(NOW()) - YEAR(namSinh) BETWEEN 0 AND 18";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                this.tongChauNhoLb.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();
            
            query = "SELECT COUNT(*) AS tong FROM nhan_khau WHERE ngheNghiep = 'Học sinh'";
            preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
                this.tongHocSinhLb.setText(String.valueOf(rs.getInt("tong")));
            }
            preparedStatement.close();
            
            connection.close();
        } catch (Exception e) {
        }
    }

    public JLabel gettongChauNhoLb() {
        return tongChauNhoLb;
    }

    public void settongChauNhoLb(JLabel tongChauNhoLb) {
        this.tongChauNhoLb = tongChauNhoLb;
    }

    public JLabel gettongHocSinhLb() {
        return tongHocSinhLb;
    }

    public void setTongHoKhau(JLabel tongHocSinhLb) {
        this.tongHocSinhLb = tongHocSinhLb;
    }


    
}
