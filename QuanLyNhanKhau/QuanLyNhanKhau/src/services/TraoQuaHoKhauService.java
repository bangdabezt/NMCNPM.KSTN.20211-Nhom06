package services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;

import Bean.TraoQuaHoKhauBean;
import controllers.LoginController;
import models.HoKhauModel;
import models.LeTetModel;
import models.NhanKhauModel;
import models.ThanhVienCuaHoModel;
import models.UserMoldel;

public class TraoQuaHoKhauService {
	
	
    public List<TraoQuaHoKhauBean> getListTraoQuaHoKhau(LeTetModel leTetModel) {
        List<TraoQuaHoKhauBean> list = new ArrayList<>();
        
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM trao_qua_le_tet "
            		+ "INNER JOIN ho_khau ON ho_khau.ID = trao_qua_le_tet.idHoKhau "
            		+ "INNER JOIN nhan_khau ON ho_khau.idChuHo = nhan_khau.ID "
            		+ "INNER JOIN le_tet ON le_tet.idSuKien = trao_qua_le_tet.idSuKien "
            		+ "LEFT OUTER JOIN users ON trao_qua_le_tet.idNguoiGhiNhan = users.ID "
            		+ " WHERE trao_qua_le_tet.idSuKien = " + leTetModel.getIdSuKien()
            		+ " ORDER BY ho_khau.maHoKhau ";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                TraoQuaHoKhauBean temp = new TraoQuaHoKhauBean();
                HoKhauModel hoKhauModel = temp.getHoKhauModel();
                hoKhauModel.setID(rs.getInt("ID"));
                hoKhauModel.setIdChuHo(rs.getInt("idCHuHo"));
                hoKhauModel.setMaHoKhau(rs.getString("maHoKhau"));
                hoKhauModel.setMaKhuVuc(rs.getString("maKhuVuc"));
                hoKhauModel.setNgayLap(rs.getDate("ngayLap"));
                hoKhauModel.setDiaChi(rs.getString("diaChi"));
                NhanKhauModel chuHo = temp.getChuHo();
                chuHo.setID(rs.getInt("ID"));
                chuHo.setHoTen(rs.getString("hoTen"));
                chuHo.setGioiTinh(rs.getString("gioiTinh"));
                chuHo.setNamSinh(rs.getDate("namSinh"));
                chuHo.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                LeTetModel leTet = temp.getLeTetModel();
                leTet.setIdNguoiTao(rs.getInt("ID"));
                leTet.setIdSuKien(rs.getInt("idSuKien"));
                leTet.setMotSuatQua(rs.getString("motSuatQua"));
                leTet.setTenSuKien(rs.getString("tenSuKien"));
                leTet.setThoiGian(rs.getDate("thoiGian"));
                leTet.setThoiGianTao(rs.getDate("ngayTao"));
                leTet.setTongGiaTriMotSuat(rs.getDouble("tongGiaTriMotSuat"));
                temp.setNgayGhiNhan(rs.getDate("thoiGianGhiNhan"));
                temp.setNgayNhanQua(rs.getDate("ngayNhanQua"));
                temp.setSoLuongQua(rs.getInt("soLuongQua"));
                temp.setTrangThaiNhanQua(rs.getString("trangThai"));
                UserMoldel userModel = temp.getNguoiGhiNhan();
                userModel.setUserName(rs.getString("userName"));
;
                
                try {
                    String sql = "SELECT * FROM nhan_khau INNER JOIN thanh_vien_cua_ho ON nhan_khau.ID = thanh_vien_cua_ho.idNhanKhau "
                            + "WHERE thanh_vien_cua_ho.idHoKhau = "
                            + hoKhauModel.getID();
                    PreparedStatement prst = connection.prepareStatement(sql);
                    ResultSet rs_1 = prst.executeQuery();
                    List<NhanKhauModel> listNhanKhau = temp.getListNhanKhauModels();
                    List<ThanhVienCuaHoModel> listThanhVienCuaHo = temp.getListThanhVienCuaHo();
                    while (rs_1.next()) {
                        NhanKhauModel nhanKhauModel = new NhanKhauModel();
                        ThanhVienCuaHoModel thanhVienCuaHoModel = new ThanhVienCuaHoModel();
                        nhanKhauModel.setID(rs_1.getInt("idNhanKhau"));
                        nhanKhauModel.setBietDanh(rs_1.getString("bietDanh"));
                        nhanKhauModel.setHoTen(rs_1.getString("hoTen"));
                        nhanKhauModel.setGioiTinh(rs_1.getString("gioiTinh"));
                        nhanKhauModel.setNamSinh(rs_1.getDate("namSinh"));
                        nhanKhauModel.setNguyenQuan(rs_1.getString("nguyenQuan"));
                        nhanKhauModel.setTonGiao(rs_1.getString("tonGiao"));
                        nhanKhauModel.setDanToc(rs_1.getString("danToc"));
                        nhanKhauModel.setQuocTich(rs_1.getString("quocTich"));
                        nhanKhauModel.setSoHoChieu(rs_1.getString("soHoChieu"));
                        nhanKhauModel.setNoiThuongTru(rs_1.getString("noiThuongTru"));
                        nhanKhauModel.setDiaChiHienNay(rs_1.getString("diaChiHienNay"));
                        
                        thanhVienCuaHoModel.setIdHoKhau(rs_1.getInt("idHoKhau"));
                        thanhVienCuaHoModel.setIdNhanKhau(rs_1.getInt("idNhanKhau"));
                        thanhVienCuaHoModel.setQuanHeVoiChuHo(rs_1.getString("quanHeVoiChuHo"));
                        listNhanKhau.add(nhanKhauModel);
                        listThanhVienCuaHo.add(thanhVienCuaHoModel);
                    }
                } catch (Exception e) {
                    System.out.println("services.HoKhauService.getListHoKhau()");
                    System.out.println(e.getMessage());
                }
                list.add(temp);
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
    

    public List<TraoQuaHoKhauBean> search(String key, LeTetModel leTetModel ) {
        List<TraoQuaHoKhauBean> list = new ArrayList<>();
        key = "%" + key + "%";
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM trao_qua_le_tet "
            		+ "INNER JOIN ho_khau ON ho_khau.ID = trao_qua_le_tet.idHoKhau "
            		+ "INNER JOIN nhan_khau ON ho_khau.idChuHo = nhan_khau.ID "
            		+ "INNER JOIN le_tet ON le_tet.idSuKien = trao_qua_le_tet.idSuKien "
            		+ "LEFT OUTER JOIN users ON trao_qua_le_tet.idNguoiGhiNhan = users.ID "
                    + "WHERE (maHoKhau LIKE ? "
                    + "OR nhan_khau.hoTen LIKE ? "
        			+ "OR ho_khau.diaChi LIKE ? "
    			    + "OR trao_qua_le_tet.trangThai LIKE ? )"
    			    + " AND trao_qua_le_tet.idSuKien = " + leTetModel.getIdSuKien();

            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            preparedStatement.setNString(1, key);
            preparedStatement.setNString(2, key);
            preparedStatement.setNString(3, key);
            preparedStatement.setNString(4, key);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                TraoQuaHoKhauBean temp = new TraoQuaHoKhauBean();
                HoKhauModel hoKhauModel = temp.getHoKhauModel();
                hoKhauModel.setID(rs.getInt("ID"));
                hoKhauModel.setIdChuHo(rs.getInt("idChuHo"));
                hoKhauModel.setMaHoKhau(rs.getString("maHoKhau"));
                hoKhauModel.setMaKhuVuc(rs.getString("maKhuVuc"));
                hoKhauModel.setNgayLap(rs.getDate("ngayLap"));
                hoKhauModel.setDiaChi(rs.getString("diaChi"));
                NhanKhauModel chuHo = temp.getChuHo();
                chuHo.setID(rs.getInt("ID"));
                chuHo.setHoTen(rs.getString("hoTen"));
                chuHo.setGioiTinh(rs.getString("gioiTinh"));
                chuHo.setNamSinh(rs.getDate("namSinh"));
                chuHo.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                LeTetModel leTet = temp.getLeTetModel();
                leTet.setIdNguoiTao(rs.getInt("ID"));
                leTet.setIdSuKien(rs.getInt("idSuKien"));
                leTet.setMotSuatQua(rs.getString("motSuatQua"));
                leTet.setTenSuKien(rs.getString("tenSuKien"));
                leTet.setThoiGian(rs.getDate("thoiGian"));
                leTet.setThoiGianTao(rs.getDate("ngayTao"));
                leTet.setTongGiaTriMotSuat(rs.getDouble("tongGiaTriMotSuat"));
                temp.setNgayGhiNhan(rs.getDate("thoiGianGhiNhan"));
                temp.setNgayNhanQua(rs.getDate("ngayNhanQua"));
                temp.setSoLuongQua(rs.getInt("soLuongQua"));
                temp.setTrangThaiNhanQua(rs.getString("trangThai"));
                UserMoldel userModel = temp.getNguoiGhiNhan();
                userModel.setUserName(rs.getString("userName"));
                try {
                    String sql = "SELECT * FROM nhan_khau INNER JOIN thanh_vien_cua_ho ON nhan_khau.ID = thanh_vien_cua_ho.idNhanKhau "
                            + "WHERE thanh_vien_cua_ho.idHoKhau = "
                            + hoKhauModel.getID();
                    PreparedStatement prst = connection.prepareStatement(sql);
                    ResultSet rs_1 = prst.executeQuery();
                    List<NhanKhauModel> listNhanKhau = temp.getListNhanKhauModels();
                    List<ThanhVienCuaHoModel> listThanhVienCuaHo = temp.getListThanhVienCuaHo();
                    while (rs_1.next()) {
                        NhanKhauModel nhanKhauModel = new NhanKhauModel();
                        ThanhVienCuaHoModel thanhVienCuaHoModel = new ThanhVienCuaHoModel();
                        nhanKhauModel.setID(rs_1.getInt("ID"));
                        nhanKhauModel.setBietDanh(rs_1.getString("bietDanh"));
                        nhanKhauModel.setHoTen(rs_1.getString("hoTen"));
                        nhanKhauModel.setGioiTinh(rs_1.getString("gioiTinh"));
                        nhanKhauModel.setNamSinh(rs_1.getDate("namSinh"));
                        nhanKhauModel.setNguyenQuan(rs_1.getString("nguyenQuan"));
                        nhanKhauModel.setTonGiao(rs_1.getString("tonGiao"));
                        nhanKhauModel.setDanToc(rs_1.getString("danToc"));
                        nhanKhauModel.setQuocTich(rs_1.getString("quocTich"));
                        nhanKhauModel.setSoHoChieu(rs_1.getString("soHoChieu"));
                        nhanKhauModel.setNoiThuongTru(rs_1.getString("noiThuongTru"));
                        nhanKhauModel.setDiaChiHienNay(rs_1.getString("diaChiHienNay"));
                        
                        thanhVienCuaHoModel.setIdHoKhau(rs_1.getInt("idHoKhau"));
                        thanhVienCuaHoModel.setIdNhanKhau(rs_1.getInt("ID"));
                        thanhVienCuaHoModel.setQuanHeVoiChuHo(rs_1.getString("quanHeVoiChuHo"));
                        listNhanKhau.add(nhanKhauModel);
                        listThanhVienCuaHo.add(thanhVienCuaHoModel);
                    }
                } catch (Exception e) {
                    System.out.println("services.HoKhauService.search()");
                    System.out.println(e.getMessage());
                }
                list.add(temp);
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
    }
	
	public List<Integer> getComboBoxNam() {
		List<Integer> list = new ArrayList<>();
        
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT distinct year(le_tet.thoiGian) as nam\r\n"
            		+ "from le_tet order by year(le_tet.thoiGian) desc";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                list.add(rs.getInt("nam"));
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
	}
	public List<LeTetModel> getComboBoxSuKien(int nam) {
		List<LeTetModel> list = new ArrayList<>();
        
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT *\r\n"
            		+ " from le_tet "
            		+ " JOIN users on users.ID = le_tet.idNguoiTao "
            		+ " where year(thoiGian) = " + nam
            		+" order by ngayTao desc";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
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
                list.add(leTet);
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return list;
	}
	
	public String getThongTinText(LeTetModel leTetModel) {
		int soHo = 0;
		int soHoNhanQua = 0;
		int soHoDaNhanQua = 0;
		try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT count(*) as soHo\r\n"
            		+ " from ho_khau";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                soHo = rs.getInt("soHo");
                break;
            }
            preparedStatement.close();
            
            query = "SELECT count(*) as soHoNhanQua\r\n"
            		+ " from trao_qua_le_tet where trao_qua_le_tet.idSuKien = " + leTetModel.getIdSuKien();
            preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            rs = preparedStatement.executeQuery();
            while (rs.next()){
            	soHoNhanQua = rs.getInt("soHoNhanQua");
            	break;
            }
            preparedStatement.close();
            
            query = "SELECT count(*) as soHoDaNhanQua\r\n"
            		+ " from trao_qua_le_tet where trao_qua_le_tet.idSuKien = " + leTetModel.getIdSuKien()
            		+" and trao_qua_le_tet.trangThai = ? ";
            preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            preparedStatement.setNString(1, "Đã nhận");
            rs = preparedStatement.executeQuery();
            while (rs.next()){
            	soHoDaNhanQua = rs.getInt("soHoDaNhanQua");
            	break;
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		String res = "Người tạo: "+ leTetModel.getTenNguoiTao() +"\r\nThời gian tạo sự kiện: " + leTetModel.getThoiGianTao() 
					+ "\r\nCó " + soHoNhanQua + "/" + soHo + " hộ gia đình trong diện trao quà \r\nCó " + soHoDaNhanQua + "/" + soHoNhanQua + " hộ đã nhận quà";
		return res;
	}
	public void traoQua(TraoQuaHoKhauBean traoQuaHoKhau , Date date) {
		try {
            Connection connection = MysqlConnection.getMysqlConnection();
            java.sql.Date tempDate = new java.sql.Date(quanlynhankhau.QuanLyNhanKhau.calendar.getTime().getTime());
            int idNguoiGhiNhan = LoginController.currentUser.getID();
            String query = "UPDATE \r\n"
            		+ " trao_qua_le_tet "
            		+ " SET  trangThai = ? , ngayNhanQua = ? , idNguoiGhiNhan = ? , thoiGianGhiNhan = ?"
            		+ " where idHoKhau = ? and idSuKien = ? ";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            preparedStatement.setNString(1, "Đã nhận");
            preparedStatement.setDate(2, new java.sql.Date(date.getTime()));
            preparedStatement.setInt(3, idNguoiGhiNhan);
            preparedStatement.setDate(4, tempDate);
            preparedStatement.setInt(5, traoQuaHoKhau.getHoKhauModel().getID());
            preparedStatement.setInt(6, traoQuaHoKhau.getLeTetModel().getIdSuKien());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
	public void suaQua(String phanQuaCoBan, double giaTri, LeTetModel leTetModel) {
		try {
            Connection connection = MysqlConnection.getMysqlConnection();
            java.sql.Date tempDate = new java.sql.Date(quanlynhankhau.QuanLyNhanKhau.calendar.getTime().getTime());
            int idNguoiGhiNhan = LoginController.currentUser.getID();
            String query = "UPDATE \r\n"
            		+ " le_tet "
            		+ " SET  motSuatQua = ? , tongGiaTriMotSuat = ?  "
            		+ " where idSuKien = ? ";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            preparedStatement.setNString(1, phanQuaCoBan);
            preparedStatement.setDouble(2, giaTri);
            preparedStatement.setInt(3, leTetModel.getIdSuKien());
            preparedStatement.execute();
            preparedStatement.close();
            connection.close();
            JOptionPane.showMessageDialog(null, "Sửa phần quà cho sự kiện thành công!", "Success", JOptionPane.PLAIN_MESSAGE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
	}
	
	public boolean checkSuKien(LeTetModel leTetModel) {
		try {
			int soHoDaNhanQua = 0;
			Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT count(*) as soHoDaNhanQua\r\n"
            		+ " from trao_qua_le_tet where trao_qua_le_tet.idSuKien = " + leTetModel.getIdSuKien()
            		+" and trao_qua_le_tet.trangThai = ? ";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            preparedStatement.setNString(1, "Đã nhận");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
            	soHoDaNhanQua = rs.getInt("soHoDaNhanQua");
            	break;
            }
            preparedStatement.close();
            connection.close();
            if(soHoDaNhanQua != 0) return false;
            else return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		return true;
	}
	
	
}
