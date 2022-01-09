package services;

import models.LichSuThayDoiNhanKhauModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

import Bean.NhanKhauBean;
import models.ChungMinhThuModel;
import models.GiaDinhModel;
import models.NhanKhauModel;
import models.TieuSuModel;

public class LichSuNKService {

	
	/*
	 * Xem lich su thay doi nhan khau
	 */
	
	public List<LichSuThayDoiNhanKhauModel> getLSuThayDoiNK(String tuNgay, String denNgay, String idHoKhau){
		List<LichSuThayDoiNhanKhauModel> list = new ArrayList<>();
		try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM lich_su_thay_doink "
            		+ "WHERE ngay >= '" + tuNgay + "' and "
            		+       "ngay <= '" + denNgay + "'"
            		+ " and idHoKhauMoi = '" + idHoKhau + "'" ;
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
            	LichSuThayDoiNhanKhauModel LSuThayDoiNK = new LichSuThayDoiNhanKhauModel();
            	LSuThayDoiNK.setID(rs.getInt("ID"));
            	LSuThayDoiNK.setIdHoKhauMoi(rs.getString("idHoKhauMoi"));
            	LSuThayDoiNK.setIdNhanKhau(rs.getInt("idNhanKhau"));
            	LSuThayDoiNK.setSuKien(rs.getString("suKien"));
            	LSuThayDoiNK.setNgay(rs.getDate("ngay"));
                list.add(LSuThayDoiNK);
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		/*
		 * try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM nhan_khau INNER JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau ORDER BY ngayTao DESC LIMIT 0, 10";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                NhanKhauBean nhanKhauBean = new NhanKhauBean();
                NhanKhauModel nhanKhau = nhanKhauBean.getNhanKhauModel();
                nhanKhau.setID(rs.getInt("ID"));
                nhanKhau.setHoTen(rs.getString("hoTen"));
                nhanKhau.setGioiTinh(rs.getString("gioiTinh"));
                nhanKhau.setNamSinh(rs.getDate("namSinh"));
                nhanKhau.setDiaChiHienNay(rs.getString("diaChiHienNay"));
                ChungMinhThuModel chungMinhThuModel = nhanKhauBean.getChungMinhThuModel();
                chungMinhThuModel.setIdNhanKhau(rs.getInt("idNhanKhau"));
                chungMinhThuModel.setSoCMT(rs.getString("soCMT"));
                chungMinhThuModel.setNgayCap(rs.getDate("ngayCap"));
                chungMinhThuModel.setNoiCap(rs.getString("noiCap"));
                list.add(nhanKhauBean);
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		 * */
		return list;
	}
}
