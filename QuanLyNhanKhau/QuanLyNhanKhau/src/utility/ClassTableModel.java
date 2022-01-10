package utility;

import Bean.ChauNhoBean;
import Bean.HoKhauBean;
import Bean.HocSinhBean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import models.GiaDinhModel;
import models.LeTetModel;
import models.NhanKhauModel;
import models.TieuSuModel;
import models.ThanhVienCuaHoModel;
import models.LichSuThayDoiNhanKhauModel;

/**
 *
 * @author Hai
 * class dinh nghia cac dang table co trong phan mem
 */
public class ClassTableModel {
    // bang cho main frame
    public DefaultTableModel setTableNhanKhau(List<NhanKhauModel> listItem, String[] listColumn) {
        final int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[columns];
        listItem.forEach((NhanKhauModel item) -> {
            obj[0] = item.getID();
            obj[1] = item.getHoTen();
            obj[2] = item.getNamSinh();
            obj[3] = item.getGioiTinh();
            obj[4] = item.getDiaChiHienNay();
            dtm.addRow(obj);
        });
        return dtm;
    }
    public DefaultTableModel setTableChauNho(List<ChauNhoBean> listChauNhoBean, String[] listColumn) {
        final int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 7 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[columns];
        listChauNhoBean.forEach((ChauNhoBean item) -> {
            obj[0] = item.getNhanKhauModel().getID();
            obj[1] = item.getNhanKhauModel().getHoTen();
            obj[2] = item.getNhanKhauModel().getNamSinh();
            obj[3] = item.getNhanKhauModel().getGioiTinh();
            obj[4] = item.getHoKhauModel().getMaHoKhau();
            obj[5] = item.getThanhVienCuaHoModel().getQuanHeVoiChuHo();
            obj[6] = item.getNhanKhauModel().getDiaChiHienNay();
            dtm.addRow(obj);
        });
        return dtm;
    }
    // bang cho danh sach hoc sinh
    public DefaultTableModel setTableHocSinh(List<HocSinhBean> listItem, String[] listColumn) {
        final int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[columns];
        listItem.forEach((HocSinhBean item) -> {
            obj[0] = item.getNhanKhauModel().getID();
            obj[1] = item.getNhanKhauModel().getHoTen();
            obj[2] = item.getNhanKhauModel().getNoiLamViec();
            obj[3] = item.getThanhVienCuaHoModel().getIdHoKhau();
            obj[4] = item.getThanhVienCuaHoModel().getQuanHeVoiChuHo();
            dtm.addRow(obj);
        });
        return dtm;
    }
    // bang trao qua
    public DefaultTableModel setTableTraoQua(List<HocSinhBean> listItem, String[] listColumn) {
        final int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[columns];
        listItem.forEach((HocSinhBean item) -> {
            obj[0] = item.getNhanKhauModel().getID();
            obj[1] = item.getNhanKhauModel().getHoTen();
            obj[2] = item.getTraoQuaHsgModel().getThanhTich();
            obj[3] = item.getTraoQuaHsgModel().getMinhChung();
            obj[4] = item.getTraoQuaHsgModel().getTrangThai();
            dtm.addRow(obj);
        });
        return dtm;
    }
    
    public DefaultTableModel setTableThongKe(ArrayList<ArrayList<Object>> thongKe, String[] listColumn) {
        final int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[columns];
       
        for (ArrayList<Object> row : thongKe) {
        	obj[0] = row.get(0);
			obj[1] = row.get(1);
		    obj[2] = row.get(2);
			obj[3] = row.get(3);
			obj[4] = row.get(4);
			dtm.addRow(obj);
		}
		
        return dtm;
    }
    
    // table cho tieusu
    public DefaultTableModel setTableTieuSu(List<TieuSuModel> tieuSu, String[] listColumn) {
        final int column = listColumn.length;
        
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                 return columnIndex == 5 ? Boolean.class : String.class;
            }
        };
        
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[column];
        
        tieuSu.forEach((TieuSuModel item) -> {
            obj[0] = item.getTuNgay().toString();
            obj[1] = item.getDenNgay().toString();
            obj[2] = item.getDiaChi();
            obj[3] = item.getNgheNghiep();
            obj[4] = item.getNoiLamViec();
            dtm.addRow(obj);
        });
        
        dtm.addRow(new Object[] {"", "", "", "", ""});
        
//        dtm.addTableModelListener(new TableModelListener() {
//            @Override
//            public void tableChanged(TableModelEvent e) {
//                int a = dtm.getRowCount();
//                if ((e.getLastRow() + 1) == dtm.getRowCount()) {
//                    System.out.println(); 
//                }
//                
//            }
//        });
        return dtm;
    }
    public DefaultTableModel setTableGiaDinh(List<GiaDinhModel> giaDinh, String[] listColumn) {
        final int column = listColumn.length;
        
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                 return columnIndex == 6 ? Boolean.class : String.class;
            }
        };
        
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[column];
        
        giaDinh.forEach((GiaDinhModel item) -> {
            obj[0] = item.getHoTen();
            obj[1] = item.getNamSinh().toString();
            obj[2] = item.getGioiTinh();
            obj[3] = item.getQuanHeVoiNhanKhau();
            obj[4] = item.getNgheNghiep();
            obj[5] = item.getDiaChiHienTai();
            dtm.addRow(obj);
        });
        
        dtm.addRow(new Object[] {"", "", "", "", "", ""});
        return dtm;
    }
    
    public DefaultTableModel setTableHoKhau(List<HoKhauBean> listItem, String[] listColumn) {
        final int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[columns];
        listItem.forEach((HoKhauBean item) -> {
            obj[0] = item.getHoKhauModel().getID();
            obj[1] = item.getHoKhauModel().getMaHoKhau();
            obj[2] = item.getChuHo().getHoTen();
            obj[3] = item.getHoKhauModel().getDiaChi();
            obj[4] = item.getHoKhauModel().getNgayLap();
            dtm.addRow(obj);
        });
        return dtm;
    }

    
    /*
     * Nguyen Trong Bang
     * lay lich su thay doi nhan khau
     * */
    public DefaultTableModel setTableLichSuThayDoiNK(List<LichSuThayDoiNhanKhauModel> listItem, String[] listColumn) {
        final int columns = listColumn.length;
        DefaultTableModel dtm = new DefaultTableModel()  {
            @Override
            public boolean isCellEditable(int row, int column) {
                return super.isCellEditable(row, column); //To change body of generated methods, choose Tools | Templates.
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                return columnIndex == 5 ? Boolean.class : String.class;
            }
        };
        dtm.setColumnIdentifiers(listColumn);
        Object[] obj;
        obj = new Object[columns];
        listItem.forEach((LichSuThayDoiNhanKhauModel item) -> {
            obj[0] = item.getID();
            obj[1] = item.getIdHoKhauMoi();
            obj[2] = item.getIdNhanKhau();
            obj[3] = item.getSuKien();
            obj[4] = item.getNgay();
            dtm.addRow(obj);
        });
        return dtm;
    }
    
}
