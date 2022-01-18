package services;

import Bean.NhanKhauBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import models.ChungMinhThuModel;
import models.GiaDinhModel;
import models.NhanKhauModel;
import models.TieuSuModel;

/**
 *
 * @author Hai
 */
public class NhanKhauService {
    
    /* 
     * Ham lay ra 1 nhan khau trong db bang chung minh thu
     * 
     */
	public static boolean checkTamVang(int ID) {
		// truy cap db
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM tam_vang WHERE idNhanKhau = " + ID;
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
           
            if(rs.next()) return true;
            else return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
	}
	public static boolean checkTamTru(int ID) {
		// truy cap db
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM tam_tru WHERE idNhanKhau = " + ID;
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
           
            if(rs.next()) return true;
            else return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
	}
	public static boolean checkAlive(int ID) { 
        // truy cap db
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM khai_tu WHERE idNguoiChet = " + ID;
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
           
            if(rs.next()) return false;
            else return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return true;
	}
	
	public NhanKhauBean getNhanKhau(String cmt) {
        NhanKhauBean nhanKhauBean = new NhanKhauBean();  
        // truy cap db
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM nhan_khau INNER JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau WHERE soCMT = " + cmt;
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            int idNhanKhau = -1;
            while (rs.next()){
                NhanKhauModel nhanKhau = nhanKhauBean.getNhanKhauModel();
                ChungMinhThuModel chungMinhThuModel = nhanKhauBean.getChungMinhThuModel();
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
                nhanKhau.setTrinhDoHocVan(rs.getString("trinhDoHocVan"));
                nhanKhau.setTrinhDoChuyenMon(rs.getString("TrinhDoChuyenMon"));
                nhanKhau.setTrinhDoNgoaiNgu(rs.getString("trinhDoNgoaiNgu"));
                nhanKhau.setBietTiengDanToc(rs.getString("bietTiengDanToc"));
                nhanKhau.setNgheNghiep(rs.getString("ngheNghiep"));
                nhanKhau.setNoiLamViec(rs.getString("noiLamViec"));
                chungMinhThuModel.setIdNhanKhau(rs.getInt("idNhanKhau"));
                chungMinhThuModel.setSoCMT(rs.getString("soCMT"));
                chungMinhThuModel.setNgayCap(rs.getDate("ngayCap"));
                chungMinhThuModel.setNoiCap(rs.getString("noiCap"));
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
                nhanKhauBean.setListTieuSuModels(listTieuSuModels);
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
                nhanKhauBean.setListGiaDinhModels(listGiaDinhModels);
                preparedStatement.close();
            }
            connection.close();
        } catch (Exception e) {
            this.exceptionHandle(e.getMessage());
        }
        return nhanKhauBean;
    }
	
	
	
    public List<NhanKhauBean> getListNhanKhau() {
        List<NhanKhauBean> list = new ArrayList<>();
        try {
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
        return list;
    }
    
    public List<NhanKhauBean> statisticNhanKhau(int TuTuoi, int denTuoi, String gender, String Status, int tuNam, int denNam) {
        List<NhanKhauBean> list = new ArrayList<>();
        String query = "SELECT * FROM nhan_khau "
                    + " INNER JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau"
                    + " LEFT JOIN tam_tru ON nhan_khau.ID = tam_tru.idNhanKhau "
                    + " LEFT JOIN tam_vang ON nhan_khau.ID = tam_vang.idNhanKhau "
                    + " LEFT JOIN khai_tu ON nhan_khau.ID = khai_tu.idNguoiChet "
                    + " WHERE ROUND(DATEDIFF(CURDATE(),namSinh)/365 , 0) >= "
                    + TuTuoi
                    + " AND ROUND(DATEDIFF(CURDATE(),namSinh)/365 , 0) <= "
                    + denTuoi;
        if (!gender.equalsIgnoreCase("Toan Bo")) {
            query += " AND nhan_khau.gioiTinh = '" + gender + "'";
        }
        if (Status.equalsIgnoreCase("Toan bo")) {
            query += " AND (tam_tru.denNgay >= CURDATE() OR tam_tru.denNgay IS NULL)"
                    + " AND (tam_vang.denNgay <= CURDATE() OR tam_vang.denNgay IS NULL)";
        } else if (Status.equalsIgnoreCase("Thuong tru")) {
            query += " AND tam_tru.denNgay IS NULL AND tam_vang.denNgay IS NULL AND khai_tu.ngayKhai IS NULL";
            
        } else if (Status.equalsIgnoreCase("Tam tru")) {
            query += " AND (YEAR(tam_tru.tuNgay) BETWEEN "
                    + tuNam
                    + " AND "
                    + denNam
                    + ")";
        } else if (Status.equalsIgnoreCase("Tam vang")) {
            query += " AND (YEAR(tam_vang.tuNgay) BETWEEN "
                    + tuNam
                    + " AND "
                    + denNam
                    + ")";
        } else if(Status.equalsIgnoreCase("Dead")) {
        	query += " AND (YEAR(khai_tu.ngayKhai) BETWEEN "
                    + tuNam
                    + " AND "
                    + denNam
                    + ")";
        }
        query += " ORDER BY ngayTao DESC";
         try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            int idNhanKhau = -1;
            while (rs.next()){
                NhanKhauBean  nhanKhauBean = new NhanKhauBean();
                NhanKhauModel nhanKhau = nhanKhauBean.getNhanKhauModel();
                ChungMinhThuModel chungMinhThuModel = nhanKhauBean.getChungMinhThuModel();
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
                nhanKhau.setTrinhDoHocVan(rs.getString("trinhDoHocVan"));
                nhanKhau.setTrinhDoChuyenMon(rs.getString("TrinhDoChuyenMon"));
                nhanKhau.setTrinhDoNgoaiNgu(rs.getString("trinhDoNgoaiNgu"));
                nhanKhau.setBietTiengDanToc(rs.getString("bietTiengDanToc"));
                nhanKhau.setNgheNghiep(rs.getString("ngheNghiep"));
                nhanKhau.setNoiLamViec(rs.getString("noiLamViec"));
                chungMinhThuModel.setIdNhanKhau(rs.getInt("idNhanKhau"));
                chungMinhThuModel.setSoCMT(rs.getString("soCMT"));
                chungMinhThuModel.setNgayCap(rs.getDate("ngayCap"));
                chungMinhThuModel.setNoiCap(rs.getString("noiCap"));
                
                if (idNhanKhau > 0) {
                    String sql = "SELECT * FROM tieu_su WHERE idNhanKhau = " + idNhanKhau;
                    PreparedStatement prst = (PreparedStatement)connection.prepareStatement(sql);
                    ResultSet rs_temp = prst.executeQuery();
                    List<TieuSuModel> listTieuSuModels = new ArrayList<>();
                    while (rs_temp.next()) {                    
                        TieuSuModel tieuSuModel = new TieuSuModel();
                        tieuSuModel.setID(rs_temp.getInt("ID"));
                        tieuSuModel.setIdNhanKhau(rs_temp.getInt("idNhanKhau"));
                        tieuSuModel.setTuNgay(rs_temp.getDate("tuNgay"));
                        tieuSuModel.setDenNgay(rs_temp.getDate("denNgay"));
                        tieuSuModel.setDiaChi(rs_temp.getString("diaChi"));
                        tieuSuModel.setNgheNghiep(rs_temp.getString("ngheNghiep"));
                        tieuSuModel.setNoiLamViec(rs_temp.getString("noiLamViec"));
                        listTieuSuModels.add(tieuSuModel);
                    }
                    nhanKhauBean.setListTieuSuModels(listTieuSuModels);
                    prst.close();

                    sql = "SELECT * FROM gia_dinh WHERE idNhanKhau = " + idNhanKhau;
                    prst = (PreparedStatement)connection.prepareStatement(sql);
                    rs_temp = prst.executeQuery();
                    List<GiaDinhModel> listGiaDinhModels = new ArrayList<>();
                    while (rs_temp.next()) {
                        GiaDinhModel giaDinhModel = new GiaDinhModel();
                        giaDinhModel.setID(rs_temp.getInt("ID"));
                        giaDinhModel.setHoTen(rs_temp.getString("hoTen"));
                        giaDinhModel.setNamSinh(rs_temp.getDate("namSinh"));
                        giaDinhModel.setGioiTinh(rs_temp.getString("gioiTinh"));
                        giaDinhModel.setIdNhanKhau(rs_temp.getInt("idNhanKhau"));
                        giaDinhModel.setDiaChiHienTai(rs_temp.getString("diaChiHienTai"));
                        giaDinhModel.setNgheNghiep(rs_temp.getString("ngheNghiep"));
                        giaDinhModel.setQuanHeVoiNhanKhau(rs_temp.getString("quanHeVoiNhanKhau"));
                        listGiaDinhModels.add(giaDinhModel);
                    }                    
                    nhanKhauBean.setListGiaDinhModels(listGiaDinhModels);
                    prst.close();
                }
                list.add(nhanKhauBean);
            }
            preparedStatement.close();
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
        
        return list;
    }
    
    /*
     * ham tim kiem nhan khau theo ten
     */
    public List<NhanKhauBean> search(String keyword) {
        List<NhanKhauBean> list = new  ArrayList<>();
        String query;
        if (keyword.trim().isEmpty()) {
            return this.getListNhanKhau();
        }
        // truy cap db
        // create query
        try {
            long a = Long.parseLong(keyword);
            query = "SELECT * "
                    + "FROM nhan_khau "
                    + "INNER JOIN chung_minh_thu "
                    + "ON nhan_khau.ID = chung_minh_thu.idNhanKhau "
                    + "WHERE chung_minh_thu.soCMT LIKE '%"
                    + keyword
                    + "%' OR nhan_khau.ID LIKE '%"
                    + keyword
                    + "%'";
        } catch (Exception e) {
            query = "SELECT * "
                    + "FROM nhan_khau "
                    + "INNER JOIN chung_minh_thu "
                    + "ON nhan_khau.ID = chung_minh_thu.idNhanKhau "
                    + "WHERE hoTen LIKE BINARY'%" + keyword + "%'"
                    + "OR bietDanh LIKE BINARY'%" + keyword + "%'";
                    
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
                NhanKhauBean temp = new NhanKhauBean();
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
     * Hàm tìm kiếm nhân khẩu theo tiêu chí
     * Nguyễn Trọng Bằng
     */
    
    public List<NhanKhauBean> searchNhanKhau(String tc1, String tc2, String tc3,
    		                                 String thongTin1, String thongTin2, String thongTin3){
    	List<NhanKhauBean> list = new ArrayList<>();
    	
    	String query = "SELECT * FROM nhan_khau "
    			     + "INNER JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau ";
    			     /*+ "JOIN thanh_vien_cua_ho ON nhan_khau.ID = thanh_vien_cua_ho.idNhanKhau "
    			     + "JOIN ho_khau ON thanh_vien_cua_ho.idHoKhau = ho_khau.ID ";*/
    	
    	if(tc1.equalsIgnoreCase(tc2) && tc1.equalsIgnoreCase(tc3)) {
    		if(!tc1.equals("ALL")) query += " WHERE " + tc1 + " LIKE '%" + thongTin1 + "%'"
    				                         + " OR " + tc2 + " LIKE '%" + thongTin2 + "%'"
    				                         + " OR " + tc3 + " LIKE '%" + thongTin3 + "%'";
    	} else if (tc1.equalsIgnoreCase(tc2)) {
    		// tc1 = tc2 va khac tc3
    		if(!tc1.equals("ALL") && !tc3.equals("ALL")) query += " WHERE " + tc1 + " LIKE '%" + thongTin1 + "%'"
    				                                               + " OR " + tc2 + " LIKE '%" + thongTin2 + "%'"
    				                                              + " AND " + tc3 + " LIKE '%" + thongTin3 + "%'";
    		else if (!tc1.equals("ALL")) query += " WHERE " + tc1 + " LIKE '%" + thongTin1 + "%'"
                                                   + " OR " + tc2 + " LIKE '%" + thongTin2 + "%'";
    		else query += " WHERE " + tc3 + " LIKE '%" + thongTin3 + "%'";
    		
    	} else if (tc1.equalsIgnoreCase(tc3)) {
    		// tc1 = tc3 va khac tc2
    		if(!tc1.equals("ALL") && !tc2.equals("ALL")) query += " WHERE " + tc1 + " LIKE '%" + thongTin1 + "%'"
		                                                           + " OR " + tc3 + " LIKE '%" + thongTin3 + "%'"
		                                                          + " AND " + tc2 + " LIKE '%" + thongTin2 + "%'";
		    else if (!tc1.equals("ALL")) query += " WHERE " + tc1 + " LIKE '%" + thongTin1 + "%'"
		                                           + " OR " + tc3 + " LIKE '%" + thongTin3 + "%'";
		    else query += " WHERE " + tc2 + " LIKE '%" + thongTin2 + "%'";
    		
    	} else if (tc2.equalsIgnoreCase(tc3)) {
    		// tc2 = tc3 va khac tc1
    		if(!tc1.equals("ALL") && !tc3.equals("ALL")) query += " WHERE " + tc3 + " LIKE '%" + thongTin3 + "%'"
                                                                   + " OR " + tc2 + " LIKE '%" + thongTin2 + "%'"
                                                                  + " AND " + tc1 + " LIKE '%" + thongTin1 + "%'";
            else if (!tc3.equals("ALL")) query += " WHERE " + tc3 + " LIKE '%" + thongTin3 + "%'"
                                                   + " OR " + tc2 + " LIKE '%" + thongTin2 + "%'";
            else query += " WHERE " + tc1 + " LIKE '%" + thongTin1 + "%'";
    		
    	} else {
    		// 3 tc khac nhau doi mot
    		if(!tc1.equals("ALL") || !tc2.equals("ALL") || !tc3.equals("ALL")) {
    			query += " WHERE ";
    			if(!tc1.equals("ALL")) query += tc1 + " LIKE '%" + thongTin1 + "%' AND ";
    			if(!tc2.equals("ALL")) query += tc2 + " LIKE '%" + thongTin2 + "%' AND ";
    			if(!tc3.equals("ALL")) query += tc3 + " LIKE '%" + thongTin3 + "%' AND ";
    			query += "1";
    		}
    		
    	}
    	query += " ORDER BY ngayTao DESC";
    	// check it
    	try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            int idNhanKhau = -1;
            while (rs.next()){
                NhanKhauBean  nhanKhauBean = new NhanKhauBean();
                NhanKhauModel nhanKhau = nhanKhauBean.getNhanKhauModel();
                ChungMinhThuModel chungMinhThuModel = nhanKhauBean.getChungMinhThuModel();
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
                // con nhieu nua
                chungMinhThuModel.setIdNhanKhau(rs.getInt("idNhanKhau"));
                chungMinhThuModel.setSoCMT(rs.getString("soCMT"));
                chungMinhThuModel.setNgayCap(rs.getDate("ngayCap"));
                chungMinhThuModel.setNoiCap(rs.getString("noiCap"));
                
                if (idNhanKhau > 0) {
                    String sql = "SELECT * FROM tieu_su WHERE idNhanKhau = " + idNhanKhau;
                    PreparedStatement prst = (PreparedStatement)connection.prepareStatement(sql);
                    ResultSet rs_temp = prst.executeQuery();
                    List<TieuSuModel> listTieuSuModels = new ArrayList<>();
                    while (rs_temp.next()) {                    
                        TieuSuModel tieuSuModel = new TieuSuModel();
                        tieuSuModel.setID(rs_temp.getInt("ID"));
                        tieuSuModel.setIdNhanKhau(rs_temp.getInt("idNhanKhau"));
                        tieuSuModel.setTuNgay(rs_temp.getDate("tuNgay"));
                        tieuSuModel.setDenNgay(rs_temp.getDate("denNgay"));
                        tieuSuModel.setDiaChi(rs_temp.getString("diaChi"));
                        tieuSuModel.setNgheNghiep(rs_temp.getString("ngheNghiep"));
                        tieuSuModel.setNoiLamViec(rs_temp.getString("noiLamViec"));
                        listTieuSuModels.add(tieuSuModel);
                    }
                    nhanKhauBean.setListTieuSuModels(listTieuSuModels);
                    prst.close();

                    sql = "SELECT * FROM gia_dinh WHERE idNhanKhau = " + idNhanKhau;
                    prst = (PreparedStatement)connection.prepareStatement(sql);
                    rs_temp = prst.executeQuery();
                    List<GiaDinhModel> listGiaDinhModels = new ArrayList<>();
                    while (rs_temp.next()) {
                        GiaDinhModel giaDinhModel = new GiaDinhModel();
                        giaDinhModel.setID(rs_temp.getInt("ID"));
                        giaDinhModel.setHoTen(rs_temp.getString("hoTen"));
                        giaDinhModel.setNamSinh(rs_temp.getDate("namSinh"));
                        giaDinhModel.setGioiTinh(rs_temp.getString("gioiTinh"));
                        giaDinhModel.setIdNhanKhau(rs_temp.getInt("idNhanKhau"));
                        giaDinhModel.setDiaChiHienTai(rs_temp.getString("diaChiHienTai"));
                        giaDinhModel.setNgheNghiep(rs_temp.getString("ngheNghiep"));
                        giaDinhModel.setQuanHeVoiNhanKhau(rs_temp.getString("quanHeVoiNhanKhau"));
                        listGiaDinhModels.add(giaDinhModel);
                    }                    
                    nhanKhauBean.setListGiaDinhModels(listGiaDinhModels);
                    prst.close();
                }
                list.add(nhanKhauBean);
            }
            preparedStatement.close();
        } catch (Exception e) {
             System.out.println(e.getMessage());
        }
    	return list;
    }
    
    
    
    /*
     * hàm getCMT 
     * 
     * Nguyễn Quốc Hào
     * 
     * */
    /* 
     * Ham lay ra 1 nhan khau trong db bang chung minh thu
     * 
     */
	public static String getCMT(int id) {
		try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM chung_minh_thu WHERE idNhanKhau = " + id;
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                return rs.getString("soCMT");
            }
            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
		return null;
	}
	/*
	 * Hào
	 * */
	public static NhanKhauBean getNhanKhauByID(int ID) {
        NhanKhauBean nhanKhauBean = new NhanKhauBean();  
        // truy cap db
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM nhan_khau INNER JOIN chung_minh_thu ON nhan_khau.ID = chung_minh_thu.idNhanKhau WHERE nhan_khau.ID = " + ID;
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            int idNhanKhau = -1;
            while (rs.next()){
                NhanKhauModel nhanKhau = nhanKhauBean.getNhanKhauModel();
                ChungMinhThuModel chungMinhThuModel = nhanKhauBean.getChungMinhThuModel();
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
                nhanKhau.setTrinhDoHocVan(rs.getString("trinhDoHocVan"));
                nhanKhau.setTrinhDoChuyenMon(rs.getString("TrinhDoChuyenMon"));
                nhanKhau.setTrinhDoNgoaiNgu(rs.getString("trinhDoNgoaiNgu"));
                nhanKhau.setBietTiengDanToc(rs.getString("bietTiengDanToc"));
                nhanKhau.setNgheNghiep(rs.getString("ngheNghiep"));
                nhanKhau.setNoiLamViec(rs.getString("noiLamViec"));
                chungMinhThuModel.setIdNhanKhau(rs.getInt("idNhanKhau"));
                chungMinhThuModel.setSoCMT(rs.getString("soCMT"));
                chungMinhThuModel.setNgayCap(rs.getDate("ngayCap"));
                chungMinhThuModel.setNoiCap(rs.getString("noiCap"));
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
                nhanKhauBean.setListTieuSuModels(listTieuSuModels);
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
                nhanKhauBean.setListGiaDinhModels(listGiaDinhModels);
                preparedStatement.close();
            }
            connection.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return nhanKhauBean;
    }
    
	/*
     * Hàm xử lí ngoại lệ, thông báo lỗi
     */
    private void exceptionHandle(String message) {
        JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.ERROR_MESSAGE);
    }
}
