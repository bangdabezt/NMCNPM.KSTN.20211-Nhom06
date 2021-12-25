package controllers.PhanThuongManagerController;

import Bean.NhanKhauBean;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import models.ChungMinhThuModel;
import models.NhanKhauModel;
import services.MysqlConnection;

/**
 *
 * @author Hai
 */

// lop thuc hien cac chuc nang trong giao dien them moi nhan khau

public class LapDanhSachChauNhoController {
    public boolean addNewPeople(NhanKhauBean nhanKhauBean) throws SQLException, ClassNotFoundException{
        
        Connection connection = MysqlConnection.getMysqlConnection();
        // 1 - 19
       
        connection.close();
        return true;
    }
}
