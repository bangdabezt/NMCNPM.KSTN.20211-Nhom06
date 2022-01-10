package controllers.PhanThuongManagerController;

import Bean.HocSinhBean;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.EventObject;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import models.NhanKhauModel;
import services.HocSinhService;
import utility.ClassTableModel;
import views.infoViews.InfoJframe;
import views.PhanThuongManagerFrame.CapNhatMinhChung;
import views.PhanThuongManagerFrame.SuaPhanQua;

/**
 *
 * @author Hai
 */
public class DanhSachHocSinhController {
    
    private JPanel jpnView;
    private JTextField jtfSearch;
    private JTextField namHocField = null;
    private HocSinhService hocSinhService;
    private List<HocSinhBean> listHocSinhBeans;
    private ClassTableModel classTableModel = null;
    private final String[] COLUMNS = {"ID", "Họ tên", "Trường", "ID Hộ Khẩu", "Quan hệ với chủ hộ"};
    private final String[] COLUMNSTRAOQUA = {"ID", "Họ tên", "Thành tích", "Minh chứng", "Trạng thái"};
    private final String[] COLUMNSTHONGKE = {"ID", "Họ tên", "Thành tích","Số suất", "Giá trị"};
    private JFrame parentJFrame;
    private JButton suaPhanQuaBtn;
    private JButton capNhatMcBtn;
    private JButton traoQuaBtn;
    private JDateChooser ngayTrao;
    private JTextPane info;
    private DefaultTableModel model;
    private JTable table;
    private JComboBox namHocCb;
    private JTextPane hsLabel;
    private ArrayList<ArrayList<Object>> thongKe;
    private JTextPane phanQuaPane;
    
    public DanhSachHocSinhController(JPanel jpnView, JTextField jtfSearch) {
        this.jpnView = jpnView;
        this.jtfSearch = jtfSearch;
        classTableModel = new ClassTableModel();
        this.hocSinhService = new HocSinhService();
        this.listHocSinhBeans = this.hocSinhService.getListViewHocSinh();
        initAction();
    }

	public DanhSachHocSinhController(JPanel jpnView, JTextPane phanQuaPane, JTextField jtfSearch, JTextField namHocField, JButton suaPhanQuaBtn, JButton capNhatMcBtn, JButton traoQuaBtn, JDateChooser ngayTrao, JTextPane info, JFrame parentFrame) {
        this.jpnView = jpnView;
        this.jtfSearch = jtfSearch;
        this.namHocField = namHocField;
        classTableModel = new ClassTableModel();
        this.hocSinhService = new HocSinhService();
        this.listHocSinhBeans = this.hocSinhService.getListHocSinh();
        this.suaPhanQuaBtn = suaPhanQuaBtn;
        this.capNhatMcBtn = capNhatMcBtn;
        this.traoQuaBtn = traoQuaBtn;
        this.ngayTrao = ngayTrao;
        this.info = info;
        this.phanQuaPane = phanQuaPane;
        this.parentJFrame = parentFrame;
        initAction();
    }
	
	public DanhSachHocSinhController(JPanel jpnView, JTextPane hsLabel, JTextField jtfSearch, JComboBox namHocCb, JFrame parentFrame) {
        this.jpnView = jpnView;
        this.jtfSearch = jtfSearch;
        this.namHocCb = namHocCb;
        classTableModel = new ClassTableModel();
        this.hocSinhService = new HocSinhService();
        this.listHocSinhBeans = this.hocSinhService.getListHocSinh();
        this.hsLabel = hsLabel;
        this.parentJFrame = parentFrame;
        initAction();
        String namHoc = (String) namHocCb.getSelectedItem();
    	setDataTableThongKe(namHoc);
    	setTextThongKe();
    }
	
    public DanhSachHocSinhController() {
    }
    
    public void initAction(){
    	
        
    	if (namHocCb!=null) {
    		ArrayList<String> namHocList = hocSinhService.getAllNamHoc();
    		String [] namHocArr = namHocList.toArray(new String[namHocList.size()]);
    		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(namHocArr);
    		namHocCb.setModel(model);
    		this.namHocCb.addItemListener(new ItemListener() {
            	public void itemStateChanged(ItemEvent e) {
            		if(namHocCb.getSelectedIndex() >= 0) {
            			String namHoc = (String) namHocCb.getSelectedItem();
            	    	setDataTableThongKe(namHoc);
            	    	setTextThongKe();
            		}
            	}

            });
    	}
    	
    	if (traoQuaBtn!=null)
    		traoQuaBtn.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				traoQuaBtnActionPerformed(e);
    			}
    		});
    	if (suaPhanQuaBtn!=null)
    		suaPhanQuaBtn.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				suaPhanQuaBtnActionPerformed(e);
    			}
    		});
    	if (capNhatMcBtn!=null)
    		capNhatMcBtn.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				themMcBtnActionPerformed(e);
    			}
    		});
        this.jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                listHocSinhBeans = hocSinhService.search(key.trim());
                if (namHocField != null && listHocSinhBeans.size() > 0) setDataTableTraoQua();
                else setDataTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                listHocSinhBeans = hocSinhService.search(key.trim());
                if (namHocField != null && listHocSinhBeans.size() > 0) setDataTableTraoQua();
                else setDataTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                listHocSinhBeans = hocSinhService.search(key.trim());
                if (namHocField != null && listHocSinhBeans.size() > 0) setDataTableTraoQua();
                else setDataTable();
            }
        });
    }
    
    public void setDataTable() {
        model = classTableModel.setTableHocSinh(this.listHocSinhBeans, COLUMNS);
        table = new JTable(model) {
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
            
        };
        
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
        /*
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                JOptionPane.showConfirmDialog(null, table.getSelectedRow());
                if (e.getClickCount() > 1) {
                    NhanKhauBean temp = listNhanKhauBeans.get(table.getSelectedRow());
                    NhanKhauBean info = nhanKhauService.getNhanKhau(temp.getChungMinhThuModel().getSoCMT());
                    InfoJframe infoJframe = new InfoJframe(info.toString(), parentJFrame);
                    infoJframe.setLocationRelativeTo(null);
                    infoJframe.setVisible(true);
                }
            }
            
        });
        */
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scroll);
        jpnView.validate();
        jpnView.repaint();
    }
    
    public void setDataTableTraoQua() {
    	namHocField.setText(this.listHocSinhBeans.get(0).getTraoQuaHsgModel().getNamHoc());
    	namHocField.setEditable(false);
        model = classTableModel.setTableTraoQua(this.listHocSinhBeans, COLUMNSTRAOQUA);
        table = new JTable(model) {
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
            
        };
        
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
        /*
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                JOptionPane.showConfirmDialog(null, table.getSelectedRow());
                if (e.getClickCount() > 1) {
                    NhanKhauBean temp = listNhanKhauBeans.get(table.getSelectedRow());
                    NhanKhauBean info = nhanKhauService.getNhanKhau(temp.getChungMinhThuModel().getSoCMT());
                    InfoJframe infoJframe = new InfoJframe(info.toString(), parentJFrame);
                    infoJframe.setLocationRelativeTo(null);
                    infoJframe.setVisible(true);
                }
            }
            
        });
        */
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scroll);
        jpnView.validate();
        jpnView.repaint();
    }

    public void setDataTableThongKe(String namHoc) {
    	thongKe = hocSinhService.getThongKe(namHoc);
        model = classTableModel.setTableThongKe(thongKe, COLUMNSTHONGKE);
        table = new JTable(model) {
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
            
        };
        
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
        /*
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                JOptionPane.showConfirmDialog(null, table.getSelectedRow());
                if (e.getClickCount() > 1) {
                    NhanKhauBean temp = listNhanKhauBeans.get(table.getSelectedRow());
                    NhanKhauBean info = nhanKhauService.getNhanKhau(temp.getChungMinhThuModel().getSoCMT());
                    InfoJframe infoJframe = new InfoJframe(info.toString(), parentJFrame);
                    infoJframe.setLocationRelativeTo(null);
                    infoJframe.setVisible(true);
                }
            }
            
        });
        */
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        scroll.setPreferredSize(new Dimension(1350, 400));
        jpnView.removeAll();
        jpnView.setLayout(new BorderLayout());
        jpnView.add(scroll);
        jpnView.validate();
        jpnView.repaint();
    }	
    
    public void setTextThongKe() {
    	int soluongqua = 0;
    	float tonggt = 0;
    	for (ArrayList<Object> row : thongKe) {
    		soluongqua += (int)row.get(3);
    		tonggt += (float)row.get(4);
    	}
    	hsLabel.setText("Tổng số lượng phần quà: "+ (long)soluongqua + 
    			"\nTổng giá trị: " + (long) tonggt + " VND");
    }
    
    public void setTextPhanQua() {
    	String namHoc = this.listHocSinhBeans.get(0).getTraoQuaHsgModel().getNamHoc();
    	ArrayList<Object> phanQua = hocSinhService.getPhanQua(namHoc);
    	this.phanQuaPane.setText("Phần quà cơ bản: " + phanQua.get(0) +
    							"\nGiá trị: " + ((Integer)phanQua.get(1)).toString() +
    						    "\nHSG: " + ((Integer)phanQua.get(2)).toString() + ", HSK: " + ((Integer)phanQua.get(3)).toString() + ", HSTB: " + ((Integer)phanQua.get(4)).toString());
    }
    
    public void setText() {
    	String namHoc = listHocSinhBeans.get(0).getTraoQuaHsgModel().getNamHoc();
    	ArrayList<Integer> sl = hocSinhService.laySoLuong(namHoc);
    	int slhs = sl.get(0), slmc = sl.get(1), slnq = sl.get(2);
    	info.setText("Có " + slmc + "/" + slhs + " em đã nộp minh chứng.\n"
    			+ "Có " + slnq + "/" + slmc + " em đã nhận quà.");
    }
    
    public void setParentJFrame(JFrame parentJFrame) {
        this.parentJFrame = parentJFrame;
    }
    
    public void refreshData() {
        this.listHocSinhBeans = this.hocSinhService.getListHocSinh();
        setDataTable();
    }
    public JPanel getJpnView() {
        return jpnView;
    }

    public void setJpnView(JPanel jpnView) {
        this.jpnView = jpnView;
    }

    public JTextField getJtfSearch() {
        return jtfSearch;
    }

    public void setJtfSearch(JTextField jtfSearch) {
        this.jtfSearch = jtfSearch;
    }
    
    public List<HocSinhBean> getListHocSinhBeans() {
		return listHocSinhBeans;
	}

	public void setListHocSinhBeans(List<HocSinhBean> listHocSinhBeans) {
		this.listHocSinhBeans = listHocSinhBeans;
	}
	
    public void themMcBtnActionPerformed(ActionEvent e) {
    	int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Chưa chọn học sinh!", "Error!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (listHocSinhBeans.get(row).getTraoQuaHsgModel().getTrangThai() != null && listHocSinhBeans.get(row).getTraoQuaHsgModel().getTrangThai().charAt(0) != 'C') {
    		JOptionPane.showMessageDialog(null, "Học sinh này đã được trao quà rồi!", "Error", JOptionPane.ERROR_MESSAGE);
    		return;
    	}
		CapNhatMinhChung capNhatMinhChung = new CapNhatMinhChung(parentJFrame, listHocSinhBeans.get(row), listHocSinhBeans.get(0).getTraoQuaHsgModel().getNamHoc(), this);
		capNhatMinhChung.setLocationRelativeTo(null);
		capNhatMinhChung.setResizable(false);
		capNhatMinhChung.setVisible(true);
    }
    
    public void suaPhanQuaBtnActionPerformed(ActionEvent e) {
    	SuaPhanQua suaPhanQua = new SuaPhanQua(parentJFrame, this, listHocSinhBeans.get(0).getTraoQuaHsgModel().getNamHoc());
    	parentJFrame.setEnabled(false);
    	suaPhanQua.setLocationRelativeTo(null);
    	suaPhanQua.setResizable(false);
    	suaPhanQua.setVisible(true);
    	//setTextPhanQua();
    }
    
    public void traoQuaBtnActionPerformed(ActionEvent e) {
    	int row = table.getSelectedRow();
    	Date date = ngayTrao.getDate();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Chưa chọn học sinh!", "Error!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		hocSinhService.traoQua(listHocSinhBeans.get(row), date);
		listHocSinhBeans = this.hocSinhService.getListHocSinh();
		this.setDataTableTraoQua();
		this.setText();
    }
    public void thongkeBtnActionPerformed(ActionEvent e) {
    	String namHoc = (String) namHocCb.getSelectedItem();
    	setDataTableThongKe(namHoc);
    	setTextThongKe();
    }
}