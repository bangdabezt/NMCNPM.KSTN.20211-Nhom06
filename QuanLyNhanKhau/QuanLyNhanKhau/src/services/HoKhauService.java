package services;

import Bean.HoKhauBean;
import controllers.LoginController;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import models.GiaDinhModel;
import models.HoKhauModel;
import models.NhanKhauModel;
import models.ThanhVienCuaHoModel;

/**
 *
 * @author Hai
 */
public class HoKhauService {
    // them moi ho khau
    public boolean addNew(HoKhauBean hoKhauBean) throws ClassNotFoundException, SQLException{
        Connection connection = MysqlConnection.getMysqlConnection();

        // Lay ra list nhan khau cu  
        //
        int ID_temp = hoKhauBean.getHoKhauModel().getID();
        
        // Lấy ra list nhân khẩu trước khi thay đổi
        String chuHoQuery = "SELECT idChuHo FROM ho_khau WHERE ID = " + ID_temp;
        String thanhVienQuery = "SELECT idNhanKhau FROM thanh_vien_cua_ho WHERE idHoKhau = " + ID_temp;
        
        int[] idThanhVien  = new int[100];
        int count = 0;
        
        // lay id chu ho
        PreparedStatement myPrep = connection.prepareStatement(chuHoQuery);
        ResultSet rss = myPrep.executeQuery();
        
        while(rss.next()) {
        	idThanhVien[count] = rss.getInt("idChuHo");
        	count++;
        }     
        myPrep.close();

        // Liet ke id cac thanh vien
        myPrep = connection.prepareStatement(thanhVienQuery);
        rss = myPrep.executeQuery();
        while(rss.next()) {
        	idThanhVien[count] = rss.getInt("idNhanKhau");
        	count++;
        }
        myPrep.close();
        
        
        
        
        String query;
        if(ID_temp != 0) {
        	query = "UPDATE ho_khau SET " 
                    + "maHoKhau = ?, idChuHo = ?, maKhuVuc = ?, diaChi = ?, ngayLap = NOW() WHERE ID = " + ID_temp;
        } else {
        	query = "INSERT INTO ho_khau(maHoKhau, idChuHo, maKhuVuc, diaChi, ngayLap)" 
                    + " values (?, ?, ?, ?, NOW())";
        }
        	
        PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, hoKhauBean.getHoKhauModel().getMaHoKhau());
        preparedStatement.setInt(2, hoKhauBean.getChuHo().getID());
        preparedStatement.setString(3, hoKhauBean.getHoKhauModel().getMaKhuVuc());
        preparedStatement.setString(4, hoKhauBean.getHoKhauModel().getDiaChi());

        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        
        String delete_s = "DELETE FROM thanh_vien_cua_ho WHERE idHoKhau = " + hoKhauBean.getHoKhauModel().getID(); 
        PreparedStatement delete_sql = connection.prepareStatement(delete_s);
        delete_sql.execute();
        
        
        if (rs.next() || ID_temp != 0) {
            int genID;
            if(ID_temp != 0) genID = ID_temp;
            else genID = rs.getInt(1);
            String sql = "INSERT INTO thanh_vien_cua_ho(idNhanKhau, idHoKhau, quanHeVoiChuHo)" 
                            + " values (?, ?, ?)";
            hoKhauBean.getListThanhVienCuaHo().forEach((ThanhVienCuaHoModel thanhVien) -> {     
                try { 
                    PreparedStatement preStatement = connection.prepareStatement(sql);
                    preStatement.setInt(1, thanhVien.getIdNhanKhau());
                    preStatement.setInt(2, genID);
                    preStatement.setString(3, thanhVien.getQuanHeVoiChuHo());
                    preStatement.executeUpdate();
                } catch (SQLException ex) {
                    Logger.getLogger(HoKhauService.class.getName()).log(Level.SEVERE, null, ex);
                }

            });
     
        }
        preparedStatement.close();
        
        // Lay ra list moi
        String chuHoQuery1 = "SELECT idChuHo FROM ho_khau WHERE maHoKhau = '" + hoKhauBean.getHoKhauModel().getMaHoKhau() + "'";
        String thanhVienQuery1 = "SELECT idNhanKhau FROM thanh_vien_cua_ho JOIN ho_khau ON idHoKhau = ID "
        		+ "WHERE maHoKhau = '" + hoKhauBean.getHoKhauModel().getMaHoKhau() + "'";
        
        int[] idThanhVienNew  = new int[100];
        int countNew = 0;
        
        // lay id chu ho
        myPrep = connection.prepareStatement(chuHoQuery1);
        rss = myPrep.executeQuery();
        
        while(rss.next()) {
        	idThanhVienNew[countNew] = rss.getInt("idChuHo");
        	countNew++;
        }     
        myPrep.close();

        // Liet ke id cac thanh vien
        myPrep = connection.prepareStatement(thanhVienQuery1);
        rss = myPrep.executeQuery();
        while(rss.next()) {
        	idThanhVienNew[countNew] = rss.getInt("idNhanKhau");
        	countNew++;
        }
        myPrep.close();
        
        // So sanh
        // Tim kiem thanh vien da chuyen di
        for(int i=0; i<count; i++) {
        	boolean check = false;
        	for(int j=0; j<countNew; j++) {
        		if(idThanhVien[i] == idThanhVienNew[j]) {
        			check = true; // Tim thay thanh vien ho khau cu trong ho khau moi
        			break;
        		}
        	}
        	if(check == false) {
        		// Thanh vien thu i da chuyen di
        		String qr = "INSERT INTO lich_su_thay_doink(idHoKhauMoi, idNhanKhau, suKien, ngay) VALUES ('"
                		+ hoKhauBean.getHoKhauModel().getMaHoKhau() + "', "
                		+ idThanhVien[i] + ", "
                		+ "'Xóa khỏi hộ khẩu'" + ", "
                		+ "NOW()"
                		+")";
                PreparedStatement preStatement = connection.prepareStatement(qr);
                preStatement.executeUpdate();
        	}
        }
        
        // Tim kiem thanh vien da them vao
        for(int i=0; i<countNew; i++) {
        	boolean check = false;
        	for(int j=0; j<count; j++) {
        		if(idThanhVienNew[i] == idThanhVien[j]) {
        			check = true; // Tim thay thanh vien ho khau moi
        			break;
        		}
        	}
        	if(check == false) {
        		// Thanh vien thu i moi chuyen vao
        		String qr = "INSERT INTO lich_su_thay_doink(idHoKhauMoi, idNhanKhau, suKien, ngay) VALUES ('"
                		+ hoKhauBean.getHoKhauModel().getMaHoKhau() + "', "
                		+ idThanhVienNew[i] + ", "
                		+ "'Thêm mới nhân khẩu'" + ", "
                		+ "NOW()"
                		+")";
                PreparedStatement preStatement = connection.prepareStatement(qr);
                preStatement.executeUpdate();
        	}
        }
        
        connection.close();
        return true;
    }
    
    
    public boolean checkPerson(int id) {
    	
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM ho_khau LEFT JOIN thanh_vien_cua_ho ON ho_khau.ID = thanh_vien_cua_ho.idHoKhau"
                        + " WHERE ho_khau.idChuHo = "
                        + id 
                        + " OR thanh_vien_cua_ho.idNhanKhau = "
                        + id;
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                return false;
            }
        } catch (Exception e) {
        	System.out.println(e.getMessage());
        }
        return true;
    }
     
    // lay ra 10 ho khau
    public List<HoKhauBean> getListHoKhau() {
        List<HoKhauBean> list = new ArrayList<>();
        
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * FROM ho_khau INNER JOIN nhan_khau ON ho_khau.idChuHo = nhan_khau.ID ORDER BY ngayTao DESC LIMIT 0, 10";
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                HoKhauBean temp = new HoKhauBean();
                HoKhauModel hoKhauModel = temp.getHoKhauModel();
                hoKhauModel.setID(rs.getInt("ID"));
                hoKhauModel.setIdChuHo(rs.getInt("idCHuHo"));
                hoKhauModel.setMaHoKhau(rs.getString("maHoKhau"));
                hoKhauModel.setMaKhuVuc(rs.getString("maKhuVuc"));
                hoKhauModel.setNgayLap(rs.getDate("ngayLap"));
                hoKhauModel.setDiaChi(rs.getString("diaChi"));
                NhanKhauModel chuHo = temp.getChuHo();
                chuHo.setID(rs.getInt("nhan_khau.ID"));
                chuHo.setHoTen(rs.getString("hoTen"));
                chuHo.setGioiTinh(rs.getString("gioiTinh"));
                chuHo.setNamSinh(rs.getDate("namSinh"));
                chuHo.setDiaChiHienNay(rs.getString("diaChiHienNay"));

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
    
    // tim kiem the ten chu ho va ma ho khau
    public List<HoKhauBean> search(String key) {
        List<HoKhauBean> list = new ArrayList<>();
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            String query = "SELECT * "
                        + "FROM ho_khau "
                        + "INNER JOIN nhan_khau "
                        + "ON ho_khau.idChuHo = nhan_khau.ID "
                        + "WHERE maHoKhau LIKE BINARY'%" + key + "%'"
                        + "OR hoTen LIKE BINARY'%" + key + "%'";
                        /*+ "MATCH(maHoKhau) AGAINST ('"
                        + key
                        + "' IN NATURAL LANGUAGE MODE);";*/
            PreparedStatement preparedStatement = (PreparedStatement)connection.prepareStatement(query);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                HoKhauBean temp = new HoKhauBean();
                HoKhauModel hoKhauModel = temp.getHoKhauModel();
                hoKhauModel.setID(rs.getInt("ID"));
                hoKhauModel.setIdChuHo(rs.getInt("idChuHo"));
                hoKhauModel.setMaHoKhau(rs.getString("maHoKhau"));
                hoKhauModel.setMaKhuVuc(rs.getString("maKhuVuc"));
                hoKhauModel.setNgayLap(rs.getDate("ngayLap"));
                hoKhauModel.setDiaChi(rs.getString("diaChi"));
                NhanKhauModel chuHo = temp.getChuHo();
                chuHo.setID(rs.getInt("nhan_khau.ID"));
                chuHo.setHoTen(rs.getString("hoTen"));
                chuHo.setGioiTinh(rs.getString("gioiTinh"));
                chuHo.setNamSinh(rs.getDate("namSinh"));
                chuHo.setDiaChiHienNay(rs.getString("diaChiHienNay"));
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
    
    /**
     * ham tao moi ho khau va up date lai thong tin nhan khau co trong ho cu
     * @param hoKhauBean ho khau moi duoc tach ra
     */
    public void tachHoKhau(HoKhauBean hoKhauBean, HoKhauBean hoKhauSelected) {
        /**
         * xoa cac thanh vien co trong moi ra khoi bang thanh_vien_cua_ho
         */
        
        // xoa chu ho
        String query = "DELETE FROM thanh_vien_cua_ho WHERE idNhanKhau = " + hoKhauBean.getChuHo().getID();   
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            int rs = preparedStatement.executeUpdate();
            
            //
            // Nguyen Trong Bang
            String qr = "INSERT INTO lich_su_thay_doink(idHoKhauMoi, idNhanKhau, suKien, ngay) VALUES ('"
            		+ hoKhauSelected.getHoKhauModel().getMaHoKhau() + "', "
            		+ hoKhauBean.getChuHo().getID() + ", "
            		+ "'Xóa khỏi hộ khẩu'" + ", "
            		+ "NOW()"
            		+")";
            PreparedStatement preStatement = connection.prepareStatement(qr);
            int rss = preStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // xoa cac thanh vien
        
        hoKhauBean.getListThanhVienCuaHo().forEach((ThanhVienCuaHoModel item) -> {
            String sql = "DELETE FROM thanh_vien_cua_ho WHERE idNhanKhau = " + item.getIdNhanKhau();
            try {
                Connection connection = MysqlConnection.getMysqlConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                int rs = preparedStatement.executeUpdate();
                
                //
                String qr = "INSERT INTO lich_su_thay_doink(idHoKhauMoi, idNhanKhau, suKien, ngay) VALUES ('"
                		+ hoKhauSelected.getHoKhauModel().getMaHoKhau() + "', "
                		+ item.getIdNhanKhau() + ", "
                		+ "'Xóa khỏi hộ khẩu'" + ", "
                		+ "NOW()"
                		+")";
                PreparedStatement preStatement = connection.prepareStatement(qr);
                int rss = preStatement.executeUpdate();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        
        /**
         * tao ho khau moi voi hoKhauBean
         */
        try {
            this.addNew(hoKhauBean);
            JOptionPane.showMessageDialog(null, "Success!");
        } catch (Exception e) {
            System.out.println("services.HoKhauService.tachHoKhau()");
        } 
        
    }
    public void chuyenDi(int idhoKhau, String noiChuyenDen, String lyDoChuyen) {
        String sql = "UPDATE ho_khau SET lyDoChuyen = '"
                + lyDoChuyen
                + "',"
                + "ngayChuyenDi = NOW(), "
                + "diaChi = '"
                + noiChuyenDen
                + "',"
                + "nguoiThucHien = "
                + LoginController.currentUser.getID()
                + " WHERE ho_khau.ID = " + idhoKhau;
        try {
            Connection connection = MysqlConnection.getMysqlConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            int rs = preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("services.HoKhauService.chuyenDi()");
            System.out.println(e.getMessage());
        }
    }
}
