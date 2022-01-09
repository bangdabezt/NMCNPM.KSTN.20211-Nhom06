package controllers.TimKiemManagerController;

import Bean.NhanKhauBean;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ButtonGroup;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import models.NhanKhauModel;
import services.NhanKhauService;
import services.StringService;
import utility.ClassTableModel;
import views.infoViews.InfoJframe;


/**
 *
 * @author Hai
 */
public class TimNhanKhauController {
    private JComboBox tieuChi1Jcb;
    private JComboBox tieuChi2Jcb;
    private JComboBox tieuChi3Jcb;
    private JTextField tieuChi1Jtf;
    private JTextField tieuChi2Jtf;
    private JTextField tieuChi3Jtf;
    
    private JPanel jpnView;
    private NhanKhauService nhanKhauService;
    private List<NhanKhauBean> listNhanKhauBeans;
    private ClassTableModel classTableModel;
    private final String[] COLUMNS = {"ID", "Họ tên", "Ngày sinh", "Giới tính", "Địa chỉ hiện tại"};
    private JFrame parentJFrame;
    
    public void setParentJFrame(JFrame parentJFrame) {
        this.parentJFrame = parentJFrame;
    }

    public TimNhanKhauController(JComboBox tc1Jcb, JComboBox tc2Jcb, JComboBox tc3Jcb,
    		JTextField tc1Jtf, JTextField tc2Jtf, JTextField tc3Jtf, JPanel jpnView) {
        this.tieuChi1Jcb = tc1Jcb;
        this.tieuChi2Jcb = tc2Jcb;
        this.tieuChi3Jcb = tc3Jcb;
        
        this.tieuChi1Jtf = tc1Jtf;
        this.tieuChi2Jtf = tc2Jtf;
        this.tieuChi3Jtf = tc3Jtf;
        
        this.jpnView = jpnView;
        
        this.nhanKhauService = new NhanKhauService();
        this.listNhanKhauBeans = new ArrayList<>();
        this.listNhanKhauBeans = this.nhanKhauService.getListNhanKhau();
        this.classTableModel = new ClassTableModel();
    }
    
    public JPanel getJpnView() {
        return jpnView;
    }

    public void setJpnView(JPanel jpnView) {
        this.jpnView = jpnView;
    }

    public void setData() {
        String tieuChi1 = StringService.covertToString((String)this.tieuChi1Jcb.getSelectedItem());
        String tieuChi2 = StringService.covertToString((String)this.tieuChi2Jcb.getSelectedItem());
        String tieuChi3 = StringService.covertToString((String)this.tieuChi3Jcb.getSelectedItem());
        
        switch(tieuChi1) {
        case "Toan bo":
        	tieuChi1 = "ALL";
        	break;
        case "Ma nhan khau":
        	tieuChi1 = "nhan_khau.maNhanKhau";
        	break;
        case "Ho ten":
        	tieuChi1 = "nhan_khau.hoTen";
        	break;
        case "Ma ho khau":
        	tieuChi1 = "ho_khau.maHoKhau";
        	break;
        case "Biet danh":
        	tieuChi1 = "nhan_khau.bietDanh";
        	break;
        case "Ngay sinh":
        	tieuChi1 = "nhan_khau.ngaySinh";
        	break;
        case "Noi sinh":
        	tieuChi1 = "nhan_khau.noiSinh";
        	break;
        case "Nguyen quan":
        	tieuChi1 = "nhan_khau.nguyenQuan";
        	break;
        case "Dan toc":
        	tieuChi1 = "nhan_khau.danToc";
        	break;
        case "Ton giao":
        	tieuChi1 = "nhan_khau.tonGiao";
        	break;
        case "Quoc tich":
        	tieuChi1 = "nhan_khau.quocTich";
        	break;
        case "Ho chieu":
        	tieuChi1 = "nhan_khau.soHoChieu";
        	break;
        case "Noi thuong tru":
        	tieuChi1 = "nhan_khau.noiThuongTru";
        	break;
        case "Đia chi hien nay":
        	tieuChi1 = "nhan_khau.diaChiHienNay";
        	break;
        case "Hoc van":
        	tieuChi1 = "nhan_khau.trinhDoHocVan";
        	break;
        case "Chuyen mon":
        	tieuChi1 = "nhan_khau.TrinhDoChuyenMon";
        	break;
        case "Nghe nghiep":
        	tieuChi1 = "nhan_khau.ngheNghiep";
        	break;
        case "Noi lam viec":
        	tieuChi1 = "nhan_khau.noiLamViec";
        	break;
        case "Tien an":
        	tieuChi1 = "nhan_khau.tienAn";
        	break;
        default:
        	//System.out.println("123");
        }
        
        switch(tieuChi2) {
        case "Toan bo":
        	tieuChi2 = "ALL";
        	break;
        case "Ma nhan khau":
        	tieuChi2 = "nhan_khau.maNhanKhau";
        	break;
        case "Ho ten":
        	tieuChi2 = "nhan_khau.hoTen";
        	break;
        case "Ma ho khau":
        	tieuChi2 = "ho_khau.maHoKhau";
        	break;
        case "Biet danh":
        	tieuChi2 = "nhan_khau.bietDanh";
        	break;
        case "Ngay sinh":
        	tieuChi2 = "nhan_khau.ngaySinh";
        	break;
        case "Noi sinh":
        	tieuChi2 = "nhan_khau.noiSinh";
        	break;
        case "Nguyen quan":
        	tieuChi2 = "nhan_khau.nguyenQuan";
        	break;
        case "Dan toc":
        	tieuChi2 = "nhan_khau.danToc";
        	break;
        case "Ton giao":
        	tieuChi2 = "nhan_khau.tonGiao";
        	break;
        case "Quoc tich":
        	tieuChi2 = "nhan_khau.quocTich";
        	break;
        case "Ho chieu":
        	tieuChi2 = "nhan_khau.soHoChieu";
        	break;
        case "Noi thuong tru":
        	tieuChi2 = "nhan_khau.noiThuongTru";
        	break;
        case "Đia chi hien nay":
        	tieuChi2 = "nhan_khau.diaChiHienNay";
        	break;
        case "Hoc van":
        	tieuChi2 = "nhan_khau.trinhDoHocVan";
        	break;
        case "Chuyen mon":
        	tieuChi2 = "nhan_khau.TrinhDoChuyenMon";
        	break;
        case "Nghe nghiep":
        	tieuChi2 = "nhan_khau.ngheNghiep";
        	break;
        case "Noi lam viec":
        	tieuChi2 = "nhan_khau.noiLamViec";
        	break;
        case "Tien an":
        	tieuChi2 = "nhan_khau.tienAn";
        	break;
        default:
        	//System.out.println("123");
        }
        
        
        switch(tieuChi3) {
        case "Toan bo":
        	tieuChi3 = "ALL";
        	break;
        case "Ma nhan khau":
        	tieuChi3 = "nhan_khau.maNhanKhau";
        	break;
        case "Ho ten":
        	tieuChi3 = "nhan_khau.hoTen";
        	break;
        case "Ma ho khau":
        	tieuChi3 = "ho_khau.maHoKhau";
        	break;
        case "Biet danh":
        	tieuChi3 = "nhan_khau.bietDanh";
        	break;
        case "Ngay sinh":
        	tieuChi3 = "nhan_khau.ngaySinh";
        	break;
        case "Noi sinh":
        	tieuChi3 = "nhan_khau.noiSinh";
        	break;
        case "Nguyen quan":
        	tieuChi3 = "nhan_khau.nguyenQuan";
        	break;
        case "Dan toc":
        	tieuChi3 = "nhan_khau.danToc";
        	break;
        case "Ton giao":
        	tieuChi3 = "nhan_khau.tonGiao";
        	break;
        case "Quoc tich":
        	tieuChi3 = "nhan_khau.quocTich";
        	break;
        case "Ho chieu":
        	tieuChi3 = "nhan_khau.soHoChieu";
        	break;
        case "Noi thuong tru":
        	tieuChi3 = "nhan_khau.noiThuongTru";
        	break;
        case "Đia chi hien nay":
        	tieuChi3 = "nhan_khau.diaChiHienNay";
        	break;
        case "Hoc van":
        	tieuChi3 = "nhan_khau.trinhDoHocVan";
        	break;
        case "Chuyen mon":
        	tieuChi3 = "nhan_khau.TrinhDoChuyenMon";
        	break;
        case "Nghe nghiep":
        	tieuChi3 = "nhan_khau.ngheNghiep";
        	break;
        case "Noi lam viec":
        	tieuChi3 = "nhan_khau.noiLamViec";
        	break;
        case "Tien an":
        	tieuChi3 = "nhan_khau.tienAn";
        	break;
        default:
        	//System.out.println("123");
        }
        
        String thongTin1;
        String thongTin2;
        String thongTin3;
        
        if (!this.tieuChi1Jtf.getText().trim().isEmpty()) {
            thongTin1 = this.tieuChi1Jtf.getText().trim();
        } else {
            thongTin1 = "NULL";
        }
        if (!this.tieuChi2Jtf.getText().trim().isEmpty()) {
            thongTin2 = this.tieuChi2Jtf.getText().trim();
        } else {
            thongTin2 = "NULL";
        }
        if (!this.tieuChi3Jtf.getText().trim().isEmpty()) {
            thongTin3 = this.tieuChi3Jtf.getText().trim();
        } else {
          	thongTin3 = "NULL";
        }
       
        this.listNhanKhauBeans = this.nhanKhauService.searchNhanKhau(tieuChi1, tieuChi2, tieuChi3, 
                                           thongTin1, thongTin2, thongTin3);
        //setDataTable();
    }
    
    public void setDataTable() {
    	this.setData();
        List<NhanKhauModel> listItem = new ArrayList<>();
        this.listNhanKhauBeans.forEach(nhankhau -> {
            listItem.add(nhankhau.getNhanKhauModel());
        });
        DefaultTableModel model = classTableModel.setTableNhanKhau(listItem, COLUMNS);
        JTable table = new JTable(model);
        
        // thiet ke bang
        
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.getColumnModel().getColumn(0).setMaxWidth(80);
        table.getColumnModel().getColumn(0).setMinWidth(80);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                JOptionPane.showConfirmDialog(null, table.getSelectedRow());
                //if (e.getClickCount() > 1) {
                    NhanKhauBean temp = listNhanKhauBeans.get(table.getSelectedRow());
                    NhanKhauBean info = nhanKhauService.getNhanKhau(temp.getChungMinhThuModel().getSoCMT());
                    InfoJframe infoJframe = new InfoJframe(info.toText(), parentJFrame);
                    infoJframe.setLocationRelativeTo(null);
                    infoJframe.setVisible(true);
                //}
            }
            
        });
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scroll);
        jpnView.validate();
        jpnView.repaint();
    }

   
    
}