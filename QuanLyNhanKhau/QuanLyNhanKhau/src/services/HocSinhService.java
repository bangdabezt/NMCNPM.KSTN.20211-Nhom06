package services;

import Bean.NhanKhauBean;
import Bean.HocSinhBean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import models.ChungMinhThuModel;
import models.ThanhVienCuaHoModel;
import models.NhanKhauModel;
import models.TieuSuModel;
import models.TraoQuaHsgModel;

public class HocSinhService {
	// lay danh sach hoc sinh tren dia ban
    public List<HocSinhBean> getListHocSinh() {
        List<HocSinhBean> list = new ArrayList<>();
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM ds_hoc_sinh";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
               	HocSinhBean hocSinhBean = new HocSinhBean();
                NhanKhauModel nhanKhau = hocSinhBean.getNhanKhauModel();
                nhanKhau.setID(rs.getInt("ID"));
                nhanKhau.setHoTen(rs.getString("hoTen"));
                nhanKhau.setNoiLamViec(rs.getString("noiLamViec"));
                ThanhVienCuaHoModel thanhVienCuaHoModel = hocSinhBean.getThanhVienCuaHoModel();
                thanhVienCuaHoModel.setIdHoKhau(rs.getInt("idHoKhau"));
                thanhVienCuaHoModel.setQuanHeVoiChuHo(rs.getString("quanHeVoiChuHo"));
                TraoQuaHsgModel traoQuaHsgModel = hocSinhBean.getTraoQuaHsgModel();
                traoQuaHsgModel.setMinhChung(rs.getBlob("minhChung"));
                traoQuaHsgModel.setThanhTich(rs.getString("thanhTich"));
                traoQuaHsgModel.setTrangThai(rs.getString("trangThai"));
                traoQuaHsgModel.setNamHoc(rs.getString("namHoc"));
                list.add(hocSinhBean);
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    //search
    public List<HocSinhBean> search(String keyword) {
        List<HocSinhBean> list = new  ArrayList<>();
        String query;
        if (keyword.trim().isEmpty()) {
            return this.getListHocSinh();
        }
        // truy cap db
        // create query
        try {
            long a = Long.parseLong(keyword);
            query = "SELECT * "
                    + "FROM ds_hoc_sinh "
                    + "WHERE ID LIKE '%"
                    + keyword
                    + "%'";
        } catch (Exception e) {
            query = "SELECT * "
                    + "FROM ds_hoc_sinh "
                    + "WHERE hoTen LIKE BINARY'%" + keyword + "%'";
                    
                    /*MATCH(hoTen, bietDanh) AGAINST ('"
                    + keyword
                    + "' IN NATURAL LANGUAGE MODE);";*/
        }
        
        // execute query
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
            	HocSinhBean hocSinhBean = new HocSinhBean();
                NhanKhauModel nhanKhau = hocSinhBean.getNhanKhauModel();
                nhanKhau.setID(rs.getInt("ID"));
                nhanKhau.setHoTen(rs.getString("hoTen"));
                nhanKhau.setNoiLamViec(rs.getString("noiLamViec"));
                ThanhVienCuaHoModel thanhVienCuaHoModel = hocSinhBean.getThanhVienCuaHoModel();
                thanhVienCuaHoModel.setIdHoKhau(rs.getInt("idHoKhau"));
                thanhVienCuaHoModel.setQuanHeVoiChuHo(rs.getString("quanHeVoiChuHo"));
                TraoQuaHsgModel traoQuaHsgModel = hocSinhBean.getTraoQuaHsgModel();
                traoQuaHsgModel.setMinhChung(rs.getBlob("minhChung"));
                traoQuaHsgModel.setThanhTich(rs.getString("thanhTich"));
                traoQuaHsgModel.setTrangThai(rs.getString("trangThai"));
                traoQuaHsgModel.setNamHoc(rs.getString("namHoc"));
                list.add(hocSinhBean);
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception mysqlException) {
            this.exceptionHandle(mysqlException.getMessage());
        }
        return list;
    }
    /*
     * Hàm xử lí ngoại lệ, thông báo lỗi
     */
    private void exceptionHandle(String message) {
        JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.ERROR_MESSAGE);
    }
    public void capNhatPhanQua(String namhoc, String phanQuaCoBan, float giaTri, int sl_hsg, int sl_hsk, int sl_hstb) {
    	String query = "INSERT INTO nam_hoc VALUES (?, ?, ?) ON DUPLICATE KEY UPDATE \n"
    					+ "motSuatQua = ?, tongGiaTriMotSuat = ?";
    	try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,namhoc);
            preparedStatement.setString(2,phanQuaCoBan);
            preparedStatement.setFloat(3,giaTri);
            preparedStatement.setString(4,phanQuaCoBan);
            preparedStatement.setFloat(5,giaTri);
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (Exception mysqlException) {
            this.exceptionHandle(mysqlException.getMessage());
        }
    	query = "INSERT INTO qua_hsg "+
    			"VALUES ('Học sinh giỏi', ?, ?)"+
    			"ON DUPLICATE KEY UPDATE soLuongSuatQua = ?";
    	try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,namhoc);
            preparedStatement.setInt(2,sl_hsg);
            preparedStatement.setInt(3,sl_hsg);
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (Exception mysqlException) {
            this.exceptionHandle(mysqlException.getMessage());
        }
    	query = "INSERT INTO qua_hsg "+
    			"VALUES ('Học sinh khá', ?, ?)"+
    			"ON DUPLICATE KEY UPDATE soLuongSuatQua = ?";
    	try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,namhoc);
            preparedStatement.setInt(2,sl_hsk);
            preparedStatement.setInt(3,sl_hsk);
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (Exception mysqlException) {
            this.exceptionHandle(mysqlException.getMessage());
        }
    	query = "INSERT INTO qua_hsg "+
    			"VALUES ('Học sinh trung bình', ?, ?)"+
    			"ON DUPLICATE KEY UPDATE soLuongSuatQua = ?";
    	try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,namhoc);
            preparedStatement.setInt(2,sl_hstb);
            preparedStatement.setInt(3,sl_hstb);
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (Exception mysqlException) {
            this.exceptionHandle(mysqlException.getMessage());
        }
    	query = "SELECT DISTINCT ID FROM nhan_khau WHERE ngheNghiep = 'Học sinh'";
    	ArrayList<Integer> idHocSinh = new ArrayList<Integer>();
    	try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	idHocSinh.add(rs.getInt("ID"));
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception mysqlException) {
            this.exceptionHandle(mysqlException.getMessage());
        }
    	for (Integer id : idHocSinh) {
    		query = "INSERT INTO trao_qua_hsg(idNhanKhau, namHoc) VALUES(?, ?)";
    		try {
                Connection connection = MysqlConnection.getMysqlConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1,id);
                preparedStatement.setString(2,namhoc);
                preparedStatement.execute();
                preparedStatement.close();
                connection.close();
            } catch (Exception mysqlException) {
                this.exceptionHandle(mysqlException.getMessage());
            }
    	}
    }
    public String timPhuHuynh (HocSinhBean hocSinhBean) {
    	String tenPhuHuynh = null;
    	int idHoKhau = hocSinhBean.getThanhVienCuaHoModel().getIdHoKhau();
    	String query = "SELECT hoTen FROM nhan_khau JOIN thanh_vien_cua_ho ON nhan_khau.ID = thanh_vien_cua_ho.idNhanKhau WHERE idHoKhau = " + idHoKhau 
    			+" AND quanHeVoiChuHo = 'Chủ hộ'";
    	try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	tenPhuHuynh = rs.getString("hoTen");
            	break;
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception mysqlException) {
            this.exceptionHandle(mysqlException.getMessage());
        }
    	return tenPhuHuynh;
    }
    public void themMinhChung(HocSinhBean hocSinhBean, String path, String thanhTich, String namHoc) {
    	if (path == null) {
    		return;
    	}
    	int id = hocSinhBean.getNhanKhauModel().getID();
    	FileInputStream in = null;
		try {
			in = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		String query = "UPDATE trao_qua_hsg "
    			+ "SET thanhTich = ?, minhChung = ?, trangThai = "
    			+ "CASE "
    			+ "	WHEN trangThai IS NULL THEN 'Chưa nhận' "
    			+ " ELSE trangThai "
    			+ "END "
    			+ "WHERE idNhanKhau = ? AND namHoc = ?";
    	try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1,thanhTich);
            preparedStatement.setBinaryStream(2,in);
            preparedStatement.setInt(3,id);
            preparedStatement.setString(4,namHoc);
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Cập nhật minh chứng thành công!", "Success", JOptionPane.PLAIN_MESSAGE);
            preparedStatement.close();
            connection.close();
        } catch (Exception mysqlException) {
            this.exceptionHandle(mysqlException.getMessage());
        }
    }
    public void traoQua(HocSinhBean hocSinhBean, Date date) {
    	if (hocSinhBean.getTraoQuaHsgModel().getTrangThai() == null) {
    		JOptionPane.showMessageDialog(null, "Học sinh này chưa có minh chứng!", "Error", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	if (hocSinhBean.getTraoQuaHsgModel().getTrangThai().charAt(0) != 'C') {
    		JOptionPane.showMessageDialog(null, "Học sinh này đã được trao quà rồi!", "Error", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
    	java.sql.Date sqlDate = new java.sql.Date(date.getTime());
    	String query = "UPDATE trao_qua_hsg "
    			+ "SET trangThai = 'Đã nhận', ngayNhan = ? "
    			+ "WHERE idNhanKhau = ? AND namHoc = ?";
    	try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, sqlDate);
            preparedStatement.setInt(2,hocSinhBean.getNhanKhauModel().getID());
            preparedStatement.setString(3,hocSinhBean.getTraoQuaHsgModel().getNamHoc());
            preparedStatement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Trao quà thành công!", "Success", JOptionPane.PLAIN_MESSAGE);
            preparedStatement.close();
            connection.close();
        } catch (Exception mysqlException) {
            this.exceptionHandle(mysqlException.getMessage());
        }
    }
    public ArrayList<Integer> laySoLuong(String namHoc) {
    	int soluonghs = 0, soluongmc = 0, soluongnq = 0;
    	String slmcquery = "SELECT COUNT(*) AS slmc "
    			+ "FROM trao_qua_hsg "
    			+ "WHERE namHoc = ? AND minhChung IS NOT NULL";
    	try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(slmcquery);
            preparedStatement.setString(1, namHoc);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	soluongmc = rs.getInt("slmc");
            	break;
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception mysqlException) {
            this.exceptionHandle(mysqlException.getMessage());
        }
    	String slnqquery = "SELECT COUNT(*) AS slnq "
    			+ "FROM trao_qua_hsg "
    			+ "WHERE namHoc = ? AND trangThai = 'Đã nhận'";
    	try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(slnqquery);
            preparedStatement.setString(1, namHoc);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	soluongnq = rs.getInt("slnq");
            	break;
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception mysqlException) {
            this.exceptionHandle(mysqlException.getMessage());
        }
    	String slhsquery = "SELECT COUNT(*) AS slhs "
    			+ "FROM ds_hoc_sinh";
    	try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(slhsquery);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	soluonghs = rs.getInt("slhs");
            	break;
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception mysqlException) {
            this.exceptionHandle(mysqlException.getMessage());
        }
		ArrayList<Integer> res = new ArrayList<Integer>();
		res.add(soluonghs);
		res.add(soluongmc);
		res.add(soluongnq);
		return res;
    }
}
