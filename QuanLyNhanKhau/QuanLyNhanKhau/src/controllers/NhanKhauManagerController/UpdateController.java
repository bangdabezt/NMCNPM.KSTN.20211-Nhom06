package controllers.NhanKhauManagerController;

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

public class UpdateController {
    public boolean updatePeople(NhanKhauBean nhanKhauBean) throws SQLException, ClassNotFoundException{
        NhanKhauModel nhanKhau = nhanKhauBean.getNhanKhauModel();
        ChungMinhThuModel chungMinhThu = nhanKhauBean.getChungMinhThuModel();
        Connection connection = MysqlConnection.getMysqlConnection();
        // 1 - 19
        String query = "UPDATE nhan_khau SET "
        		+ "hoTen = ?, bietDanh = ?, namSinh = ?, gioiTinh = ?, noiSinh = ?, nguyenQuan = ?, danToc = ?, tonGiao = ?, quocTich = ?, soHoChieu = ?, noiThuongTru = ?, diaChiHienNay = ?, trinhDoHocVan = ?, TrinhDoChuyenMon = ?, bietTiengDanToc = ?, trinhDoNgoaiNgu = ?, ngheNghiep = ?, noiLamViec = ?, idNguoiTao = ?, ngayTao = ? "
        		+ "WHERE ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, nhanKhau.getHoTen());
        preparedStatement.setString(2, nhanKhau.getBietDanh());
        java.sql.Date namSinh = new java.sql.Date(nhanKhau.getNamSinh().getTime());
        preparedStatement.setDate(3, namSinh);
        preparedStatement.setString(4, nhanKhau.getGioiTinh());
        preparedStatement.setString(5, nhanKhau.getNoiSinh());
        preparedStatement.setString(6, nhanKhau.getNguyenQuan());
        preparedStatement.setString(7, nhanKhau.getDanToc());
        preparedStatement.setString(8, nhanKhau.getTonGiao());
        preparedStatement.setString(9, nhanKhau.getQuocTich());
        preparedStatement.setString(10, nhanKhau.getSoHoChieu());
        preparedStatement.setString(11, nhanKhau.getNoiThuongTru());
        preparedStatement.setString(12, nhanKhau.getDiaChiHienNay());
        preparedStatement.setString(13, nhanKhau.getTrinhDoHocVan());
        preparedStatement.setString(14, nhanKhau.getTrinhDoChuyenMon());
        preparedStatement.setString(15, nhanKhau.getBietTiengDanToc());
        preparedStatement.setString(16, nhanKhau.getTrinhDoNgoaiNgu());
        preparedStatement.setString(17, nhanKhau.getNgheNghiep());
        preparedStatement.setString(18, nhanKhau.getNoiLamViec());
        preparedStatement.setInt(19, nhanKhau.getIdNguoiTao());
        java.sql.Date createDate = new java.sql.Date(quanlynhankhau.QuanLyNhanKhau.calendar.getTime().getTime());
        preparedStatement.setDate(20, createDate);
        preparedStatement.setInt(21, nhanKhau.getID());
        preparedStatement.executeUpdate();

        
        String sql = "UPDATE chung_minh_thu SET " 
                    + "soCMT = ? WHERE idNhanKhau = ?";
        PreparedStatement prst = connection.prepareStatement(sql);
        prst.setString(1, chungMinhThu.getSoCMT());
        prst.setInt(2, nhanKhau.getID());
        prst.execute();
        String delete_tieu_su = "DELETE FROM tieu_su WHERE idNhanKhau = ?";
        PreparedStatement state = connection.prepareStatement(delete_tieu_su);
        state.setInt(1, nhanKhau.getID());
        state.execute();
        String delete_gia_dinh = "DELETE FROM gia_dinh WHERE idNhanKhau = ?";
        PreparedStatement stateGD = connection.prepareStatement(delete_gia_dinh);
        stateGD.setInt(1, nhanKhau.getID());
        stateGD.execute();
        nhanKhauBean.getListTieuSuModels().forEach(tieuSu -> {
            try {
            	
            	String sql_tieu_su = "INSERT INTO tieu_su(idNhanKhau, tuNgay, denNgay, diaChi, ngheNghiep, noiLamViec)" 
                            + " values (?, ?, ?, ?, ?, ?)";
            	
                PreparedStatement preStatement = connection.prepareStatement(sql_tieu_su);
                preStatement.setInt(1, nhanKhau.getID());
                Date tuNgay = new Date(tieuSu.getTuNgay().getTime());
                preStatement.setDate(2, tuNgay);
                preStatement.setDate(3, new Date(tieuSu.getDenNgay().getTime()));
                preStatement.setString(4, tieuSu.getDiaChi());
                preStatement.setString(5, tieuSu.getNgheNghiep());
                preStatement.setString(6, tieuSu.getNoiLamViec());
                preStatement.execute();
                preStatement.close();
            } catch (Exception e) {
                System.out.println(e);
            }
        });
        nhanKhauBean.getListGiaDinhModels().forEach(giaDinh -> {
            try {
            	String sql_gia_dinh = "INSERT INTO gia_dinh(idNhanKhau, hoTen, namSinh, gioiTinh, quanHeVoiNhanKhau, ngheNghiep, diaChiHienTai)" 
                            + " values (?, ?, ?, ?, ?, ?, ?)";
               
                PreparedStatement preStatement = connection.prepareStatement(sql_gia_dinh);
                preStatement.setInt(1, nhanKhau.getID());
                preStatement.setString(2, giaDinh.getHoTen());
                preStatement.setDate(3, new Date(giaDinh.getNamSinh().getTime()));
                preStatement.setString(4, giaDinh.getGioiTinh());
                preStatement.setString(5, giaDinh.getQuanHeVoiNhanKhau());
                preStatement.setString(6, giaDinh.getNgheNghiep());
                preStatement.setString(7, giaDinh.getDiaChiHienTai());
                preStatement.execute();
                preStatement.close();
            } catch (Exception e) {
                System.out.println("controllers.NhanKhauManagerController.AddNewController.addNewPeople()");
            }
        });
        
        connection.close();
        return true;
    }
}
