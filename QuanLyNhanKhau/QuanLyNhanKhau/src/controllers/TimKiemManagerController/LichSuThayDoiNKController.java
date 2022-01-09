package controllers.TimKiemManagerController;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import models.HoKhauModel;
import Bean.HoKhauBean;
import Bean.NhanKhauBean;
import models.LichSuThayDoiNhanKhauModel;
import services.LichSuNKService;
import utility.ClassTableModel;
import views.infoViews.InfoJframe;

import com.toedter.calendar.JDateChooser;
import java.text.DateFormat;
import java.util.Date;
import java.text.SimpleDateFormat;

public class LichSuThayDoiNKController {
	private String tuNgay;
	private String denNgay;
	private HoKhauModel hokhauModel;
	private String idHoKhau;
    
    private JPanel jpnView;
    private LichSuNKService lichSuNKService;
    private List<LichSuThayDoiNhanKhauModel> listLichSuNKModels;
    private ClassTableModel classTableModel;
    private final String[] COLUMNS = {"ID", "Mã hộ khẩu", "ID Nhân khẩu","Sự Kiện", "Ngày"};
    private JFrame parentJFrame;
    
    public void setParentJFrame(JFrame parentJFrame) {
        this.parentJFrame = parentJFrame;
    }
	
    /**
     * Creates new form TachHoKhau
     * @param parentJFrame
     */

	public LichSuThayDoiNKController(JDateChooser tuNgay, JDateChooser denNgay, HoKhauBean hokhauBean, JPanel table) {
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date fromDate = tuNgay.getDate();
		//String a = df.format(dat.getDate());
        this.tuNgay = df.format(fromDate);
        //System.out.println(this.tuNgay);
        Date toDate = denNgay.getDate();
        this.denNgay = df.format(toDate);
        //System.out.println(this.tuNgay);
        
        this.hokhauModel = hokhauBean.getHoKhauModel();
        this.idHoKhau = hokhauModel.getMaHoKhau();
        this.jpnView = table;
        
        this.lichSuNKService = new LichSuNKService();
        //this.listLichSuNKModels = new ArrayList<>();
        this.listLichSuNKModels = this.lichSuNKService.getLSuThayDoiNK(this.tuNgay, this.denNgay, this.idHoKhau);
        this.classTableModel = new ClassTableModel();
    }
	
	public void setDataTable() {
    	//this.setData();
		List<LichSuThayDoiNhanKhauModel> listItem = new ArrayList<>();
        this.listLichSuNKModels.forEach(lsuthaydoiNK -> {
            listItem.add(lsuthaydoiNK);
        });
        DefaultTableModel model = classTableModel.setTableLichSuThayDoiNK(listItem, COLUMNS);
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
        /*table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                JOptionPane.showConfirmDialog(null, table.getSelectedRow());
                //if (e.getClickCount() > 1) {
                    NhanKhauBean temp = listNhanKhauBeans.get(table.getSelectedRow());
                    NhanKhauBean info = nhanKhauService.getNhanKhau(temp.getChungMinhThuModel().getSoCMT());
                    InfoJframe infoJframe = new InfoJframe(info.toString(), parentJFrame);
                    infoJframe.setLocationRelativeTo(null);
                    infoJframe.setVisible(true);
                //}
            }
            
        });*/
        
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
