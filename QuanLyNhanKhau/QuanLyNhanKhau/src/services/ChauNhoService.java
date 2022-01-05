package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import Bean.ChauNhoBean;
import Bean.NhanKhauBean;
import controllers.LoginController;
import Bean.ChauNhoBean;
import models.ChungMinhThuModel;
import models.GiaDinhModel;
import models.HoKhauModel;
import models.LeTetModel;
import models.NhanKhauModel;
import models.ThanhVienCuaHoModel;
import models.TieuSuModel;
import utility.ClassTableModel;

public class ChauNhoService {
	
    public ChauNhoBean getChauNho(String cmt) {
    	ChauNhoBean chauNhoBean = new ChauNhoBean();  
        // truy cap db
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT *\r\n"
            		+ "FROM nhan_khau\r\n"
            		+ "INNER JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau \r\n"
            		+ "INNER JOIN thanh_vien_cua_ho ON nhan_khau.ID = thanh_vien_cua_ho.idNhanKhau \r\n"
            		+ "INNER JOIN ho_khau ON thanh_vien_cua_ho.idHoKhau = ho_khau.ID \r\n WHERE soCMT = " + cmt;
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            int idNhanKhau = -1;
            while (rs.next()){
                NhanKhauModel nhanKhau = chauNhoBean.getNhanKhauModel();
                ChungMinhThuModel chungMinhThuModel = chauNhoBean.getChungMinhThuModel();
                idNhanKhau = rs.getInt("idNhanKhau");
                nhanKhau.setID(idNhanKhau);
                nhanKhau.setBietDanh(rs.getString("bietDanh"));
                nhanKhau.setHoTen(rs.getString("hoTen"));
                nhanKhau.setGioiTinh(rs.getString("gioiTinh"));
                nhanKhau.setNamSinh(rs.getDate("namSinh"));
                nhanKhau.setNguyenQuan(rs.getString("nguyenQuan"));
                nhanKhau.setTonGiao(rs.getString("tonGiao"));
                nhanKhau.setDanToc(rs.getString("danToc"));
                nhanKhau.setQuocTich(rs.getString("quocTich"));
                nhanKhau.setSoHoChieu(rs.getString("soHoChieu"));
                nhanKhau.setNoiThuongTru(rs.getString("noiThuongTru"));
                nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));

                chungMinhThuModel.setIdNhanKhau(rs.getInt("idNhanKhau"));
                chungMinhThuModel.setSoCMT(rs.getString("soCMT"));
                chungMinhThuModel.setNgayCap(rs.getDate("ngayCap"));
                chungMinhThuModel.setNoiCap(rs.getString("noiCap"));
                
                HoKhauModel hoKhauModel = chauNhoBean.getHoKhauModel();
                hoKhauModel.setMaHoKhau(rs.getString("maHoKhau"));
                
                ThanhVienCuaHoModel thanhVienCuaHoModel = chauNhoBean.getThanhVienCuaHoModel();
                thanhVienCuaHoModel.setQuanHeVoiChuHo(rs.getString("quanHeVoiChuHo"));
            }
            preparedStatement.close();
            if (idNhanKhau > 0) {
                query = "SELECT * FROM tieu_su WHERE idNhanKhau = " + idNhanKhau;
                preparedStatement = (PreparedStatement)connection.prepareStatement(query);
                rs = preparedStatement.executeQuery();
                List<TieuSuModel> listTieuSuModels = new ArrayList<>();
                while (rs.next()) {                    
                    TieuSuModel tieuSuModel = new TieuSuModel();
                    tieuSuModel.setID(rs.getInt("ID"));
                    tieuSuModel.setIdNhanKhau(rs.getInt("idNhanKhau"));
                    tieuSuModel.setTuNgay(rs.getDate("tuNgay"));
                    tieuSuModel.setDenNgay(rs.getDate("denNgay"));
                    tieuSuModel.setDiaChi(rs.getString("diaChi"));
                    tieuSuModel.setNgheNghiep(rs.getString("ngheNghiep"));
                    tieuSuModel.setNoiLamViec(rs.getString("noiLamViec"));
                    listTieuSuModels.add(tieuSuModel);
                }
                chauNhoBean.setListTieuSuModels(listTieuSuModels);
                preparedStatement.close();
                
                query = "SELECT * FROM gia_dinh WHERE idNhanKhau = " + idNhanKhau;
                preparedStatement = (PreparedStatement)connection.prepareStatement(query);
                rs = preparedStatement.executeQuery();
                List<GiaDinhModel> listGiaDinhModels = new ArrayList<>();
                while (rs.next()) {
                    GiaDinhModel giaDinhModel = new GiaDinhModel();
                    giaDinhModel.setID(rs.getInt("ID"));
                    giaDinhModel.setHoTen(rs.getString("hoTen"));
                    giaDinhModel.setNamSinh(rs.getDate("namSinh"));
                    giaDinhModel.setGioiTinh(rs.getString("gioiTinh"));
                    giaDinhModel.setIdNhanKhau(rs.getInt("idNhanKhau"));
                    giaDinhModel.setDiaChiHienTai(rs.getString("diaChiHienTai"));
                    giaDinhModel.setNgheNghiep(rs.getString("ngheNghiep"));
                    giaDinhModel.setQuanHeVoiNhanKhau(rs.getString("quanHeVoiNhanKhau"));
                    listGiaDinhModels.add(giaDinhModel);
                }                    
                chauNhoBean.setListGiaDinhModels(listGiaDinhModels);
                preparedStatement.close();
            }
            connection.close();
        } catch (Exception e) {
            this.exceptionHandle(e.getMessage());
        }
        return chauNhoBean;
    }
	
	// Lay danh sach chau nho trong do tuoi tu a - b
	public List<ChauNhoBean> getListChauNho(int a, int b) {
        List<ChauNhoBean> list = new ArrayList<>();
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT *\r\n"
            		+ "FROM nhan_khau\r\n"
            		+ "INNER JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau \r\n"
            		+ "INNER JOIN thanh_vien_cua_ho ON nhan_khau.ID = thanh_vien_cua_ho.idNhanKhau \r\n"
            		+ "INNER JOIN ho_khau ON thanh_vien_cua_ho.idHoKhau = ho_khau.ID \r\n"
            		+ "WHERE YEAR(NOW()) - YEAR(namSinh) >= "+ a +" AND YEAR(NOW()) - YEAR(namSinh) <= " + b + "\r\n"
            		+ "ORDER BY ho_khau.maHoKhau";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                ChauNhoBean ChauNhoBean = new ChauNhoBean();
                NhanKhauModel nhanKhau = ChauNhoBean.getNhanKhauModel();
                nhanKhau.setID(rs.getInt("ID"));
                nhanKhau.setHoTen(rs.getString("hoTen"));
                nhanKhau.setGioiTinh(rs.getString("gioiTinh"));
                nhanKhau.setNamSinh(rs.getDate("namSinh"));
                nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                ChungMinhThuModel chungMinhThuModel = ChauNhoBean.getChungMinhThuModel();
                chungMinhThuModel.setIdNhanKhau(rs.getInt("idNhanKhau"));
                chungMinhThuModel.setSoCMT(rs.getString("soCMT"));
                chungMinhThuModel.setNgayCap(rs.getDate("ngayCap"));
                chungMinhThuModel.setNoiCap(rs.getString("noiCap"));
                HoKhauModel hoKhauModel = ChauNhoBean.getHoKhauModel();
                hoKhauModel.setMaHoKhau(rs.getString("maHoKhau"));
                ThanhVienCuaHoModel thanhVienCuaHoModel = ChauNhoBean.getThanhVienCuaHoModel();
                thanhVienCuaHoModel.setQuanHeVoiChuHo(rs.getString("quanHeVoiChuHo"));
                list.add(ChauNhoBean);
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
	
    public List<ChauNhoBean> search(String keyword, int a, int b) {
        List<ChauNhoBean> list = new  ArrayList<>();
        String query;
        if (keyword.trim().isEmpty()) {
            return this.getListChauNho(a, b);
        }
        keyword = "%" + keyword + "%";
        query = 	"SELECT * "
                + "FROM nhan_khau "
                + "INNER JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau \r\n"
        		+ "INNER JOIN thanh_vien_cua_ho ON nhan_khau.ID = thanh_vien_cua_ho.idNhanKhau \r\n"
        		+ "INNER JOIN ho_khau ON thanh_vien_cua_ho.idHoKhau = ho_khau.ID \r\n"
                + "WHERE (chung_minh_thu.soCMT LIKE ? "
                + "OR nhan_khau.ID LIKE ? "
    			+ "OR hoTen LIKE ? "
    			+ "OR bietDanh LIKE ? "
			    + "OR namSinh LIKE ? "
			    + "OR gioiTinh LIKE ? "
    			+ "OR diaChiHienNay LIKE ? "
    			+ "OR ho_khau.maHoKhau LIKE ? "
    			+ "OR quanHeVoiChuHo LIKE ? )"
    			+ " AND YEAR(NOW()) - YEAR(namSinh) >= "+ a +" AND YEAR(NOW()) - YEAR(namSinh) <= " + b
                + " ORDER BY ho_khau.maHoKhau";
    
        // execute query
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setNString(1, keyword);
            preparedStatement.setNString(2, keyword);
            preparedStatement.setNString(3, keyword);
            preparedStatement.setNString(4, keyword);
            preparedStatement.setNString(5, keyword);
            preparedStatement.setNString(6, keyword);
            preparedStatement.setNString(7, keyword);
            preparedStatement.setNString(8, keyword);
            preparedStatement.setNString(9, keyword);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                ChauNhoBean temp = new ChauNhoBean();
                NhanKhauModel nhanKhau = temp.getNhanKhauModel();
                nhanKhau.setID(rs.getInt("ID"));
                nhanKhau.setHoTen(rs.getString("hoTen"));
                nhanKhau.setGioiTinh(rs.getString("gioiTinh"));
                nhanKhau.setNamSinh(rs.getDate("namSinh"));
                nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                
                ChungMinhThuModel chungMinhThuModel = temp.getChungMinhThuModel();
                chungMinhThuModel.setIdNhanKhau(rs.getInt("idNhanKhau"));
                chungMinhThuModel.setSoCMT(rs.getString("soCMT"));
                chungMinhThuModel.setNgayCap(rs.getDate("ngayCap"));
                chungMinhThuModel.setNoiCap(rs.getString("noiCap"));
                
                HoKhauModel hoKhauModel = temp.getHoKhauModel();
                hoKhauModel.setMaHoKhau(rs.getString("maHoKhau"));
                ThanhVienCuaHoModel thanhVienCuaHoModel = temp.getThanhVienCuaHoModel();
                thanhVienCuaHoModel.setQuanHeVoiChuHo(rs.getString("quanHeVoiChuHo"));
                
                list.add(temp);
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

	public void taoSuKien(JFrame parentFrame,String suKien, String phanQuaCoBan, float giaTri, int aAge, int bAge,
			Date date) {
    	String query = "SELECT * FROM le_tet JOIN users on users.ID = le_tet.idNguoiTao";
    	try {
    		Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            
            List<LeTetModel> duplicate = new ArrayList<>();
            while (rs.next()){
                LeTetModel leTet = new LeTetModel();
                leTet.setIdSuKien(rs.getInt("idSuKien"));
                leTet.setTenSuKien(rs.getString("tenSuKien"));
                leTet.setMotSuatQua(rs.getString("motSuatQua"));
                leTet.setTongGiaTriMotSuat(rs.getDouble("tongGiaTriMotSuat"));
                leTet.setThoiGian(rs.getDate("thoiGian"));
                leTet.setThoiGianTao(rs.getDate("ngayTao"));
                leTet.setIdNguoiTao(rs.getInt("idNguoiTao"));
                leTet.setTenNguoiTao(rs.getString("userName"));
                if(leTet.getThoiGian().getYear() == date.getYear() && leTet.getThoiGian().getMonth() == date.getMonth()
                		&& leTet.getThoiGian().getDate() == date.getDate()) {
                	duplicate.add(leTet);
                }
            }
            preparedStatement.close();
            if(duplicate.size() != 0) {
            	if (! (JOptionPane.showConfirmDialog(parentFrame, this.listDuplicateLeTettoString(duplicate), "Confirm", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)) {
            		connection.close();
            		return;
            	}
            }
            String insertLeTetTable = "INSERT INTO le_tet(thoiGian, tenSuKien, motSuatQua, tongGiaTriMotSuat, ngayTao, idNguoiTao) VALUES"
            		+ "(?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(insertLeTetTable);
            java.sql.Date createDate = new java.sql.Date(date.getTime());
            preparedStatement.setDate(1, createDate);
            preparedStatement.setNString(2, suKien);
            preparedStatement.setNString(3, phanQuaCoBan);
            preparedStatement.setFloat(4, giaTri);
            createDate = new java.sql.Date(quanlynhankhau.QuanLyNhanKhau.calendar.getTime().getTime());
            preparedStatement.setDate(5, createDate);
            preparedStatement.setInt(6, 0);
            preparedStatement.execute();
            preparedStatement.close();
            
            String queryTemp = "SELECT * FROM le_tet WHERE idNguoiTao = 0";
            preparedStatement = (PreparedStatement)connection.prepareStatement(queryTemp);
            rs = preparedStatement.executeQuery();
            int idSuKien = 0;
            while(rs.next()) {
            	idSuKien = rs.getInt("idSuKien");
            	queryTemp = "UPDATE le_tet SET idNguoiTao = " + LoginController.currentUser.getID()+ " WHERE idNguoiTao = 0";
            	PreparedStatement tmpPreparedStatement = connection.prepareStatement(queryTemp);
            	tmpPreparedStatement.execute();
            	tmpPreparedStatement.close();
            	break;
            }
            preparedStatement.close();
            String queryInsert = "INSERT INTO trao_qua_le_tet(idHoKhau, idSuKien, soLuongQua, trangThai)"
            		+ "VALUES(?, ?, ?, ?)";
            query = "SELECT thanh_vien_cua_ho.idHoKhau, COUNT(nhan_khau.ID) as soLuongChauNho\r\n"
            		+ "FROM nhan_khau\r\n"
            		+ "INNER JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau \r\n"
            		+ "INNER JOIN thanh_vien_cua_ho ON nhan_khau.ID = thanh_vien_cua_ho.idNhanKhau \r\n"
            		+ "WHERE YEAR(NOW()) - YEAR(namSinh) >= "+ aAge +" AND YEAR(NOW()) - YEAR(namSinh) <= " + bAge + "\r\n"
            		+ "GROUP BY thanh_vien_cua_ho.idHoKhau";
            preparedStatement = connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            PreparedStatement preparedStatementInsert;
            while(rs.next()) {
            	int idHoKhau = rs.getInt("idHoKhau");
            	int soLuongChauNho = rs.getInt("soLuongChauNho");
            	preparedStatementInsert = connection.prepareStatement(queryInsert);
            	preparedStatementInsert.setInt(1, idHoKhau);
            	preparedStatementInsert.setInt(2, idSuKien);
            	preparedStatementInsert.setInt(3, soLuongChauNho);
            	preparedStatementInsert.setNString(4, "Chưa nhận");
            	preparedStatementInsert.execute();
            	preparedStatementInsert.close();
            }
            preparedStatement.close();
            JOptionPane.showMessageDialog(parentFrame, "Tạo sự kiện và danh sách nhận quà thành công!", "Success", JOptionPane.PLAIN_MESSAGE);
    		preparedStatement.close();
    		connection.close();
    	} catch (Exception mysqlException) {
    		this.exceptionHandle(mysqlException.getMessage());
    	}
	}
	
	public String listDuplicateLeTettoString(List<LeTetModel> leTetModelList) {
        String res =  "<html><style>p {padding: 5px; margin-left: 20px} table, th, td {border: 1px solid black; border-collapse: collapse;} table {width: 500px}</style> <div>"
                + "<p>Đã có sự kiện trong ngày này, bạn có muốn tạo thêm sự kiện mới không ?<b>" + "</p>"
                + "</tr>";
        res += "</table><h4>Lễ tết<table>"
                + "<tr>"
                + "<th>ID</th>"
                + "<th>Thời gian</th>"
                + "<th>Tên sự kiện</th>"
                + "<th>Một suất quà</th>"
                + "<th>Giá trị một suất</th>"
                + "<th>Người tạo</th>"
                + "<th>Thời gian tạo</th>"
                +"</tr>";
        for (LeTetModel leTetModel: leTetModelList) {
            res += "<tr>"
                    + "<td>"
                    + leTetModel.getIdSuKien()
                    + "</td>"
                    + "<td>"
                    + leTetModel.getThoiGian().toString()
                    + "</td>"
                    + "<td>"
                    + leTetModel.getTenSuKien()
                    + "</td>"
                    + "<td>"
                    + leTetModel.getMotSuatQua()
                    + "</td>"
                    + "<td>"
                    + leTetModel.getTongGiaTriMotSuat()
                    + "</td>"
                    + "</td>"
                    + leTetModel.getTenNguoiTao()
                    + "</td>"
                    + "</td>"
                    + leTetModel.getThoiGianTao().toString()
                    + "</td>"
                    +"</tr>";
        }
        res +=  "</table>"
                + "</div></html>";
        return res;
    }
}
