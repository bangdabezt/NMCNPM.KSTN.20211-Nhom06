package controllers.PhanThuongManagerController;

import Bean.ChauNhoBean;
import Bean.NhanKhauBean;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;

import models.ChungMinhThuModel;
import models.NhanKhauModel;
import services.ChauNhoService;
import services.MysqlConnection;
import services.NhanKhauService;
import utility.ClassTableModel;
import views.infoViews.InfoJframe;

/**
 *
 * @author Hai
 */

// lop thuc hien cac chuc nang trong giao dien them moi nhan khau

public class LapDanhSachChauNhoController {
    
    private JPanel jpnView;
    private JTextField jtfSearch;
    private ChauNhoService chauNhoService;
    private List<ChauNhoBean> listChauNhoBean;
    private ClassTableModel classTableModel = null;
    private final String[] COLUMNS = {"ID", "Họ tên", "Ngày sinh", "Giới tính", "Mã hộ khẩu", "Quan hệ với chủ hộ", "Địa chỉ hiện tại"};
    private JFrame parentJFrame;
    private JTextField aAge;
    private JTextField bAge;

    public LapDanhSachChauNhoController(JPanel jpnView, JTextField jtfSearch, JTextField aAge, JTextField bAge) {
        this.jpnView = jpnView;
        this.jtfSearch = jtfSearch;
        classTableModel = new ClassTableModel();
        this.chauNhoService = new ChauNhoService();
        this.aAge = aAge;
        this.bAge = bAge;
        this.listChauNhoBean = this.chauNhoService.getListChauNho(Integer.parseInt(aAge.getText()), Integer.parseInt(bAge.getText()));
        initAction();
    }
    public LapDanhSachChauNhoController() {
    	
    }
    
    //
    public void initAction(){
        this.jtfSearch.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                int a = 0, b = 18;
                try {
                a = Integer.parseInt(aAge.getText());
                b = Integer.parseInt(bAge.getText());
                } catch(Exception e1) {
                	listChauNhoBean.clear(); setDataTable();
                	return;
                }
                listChauNhoBean = chauNhoService.search(key.trim(), a, b);
                setDataTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                int a = 0, b = 18;
                try {
                a = Integer.parseInt(aAge.getText());
                b = Integer.parseInt(bAge.getText());
                } catch(Exception e1) {
                	listChauNhoBean.clear(); setDataTable();
                	return;
                }
                listChauNhoBean = chauNhoService.search(key.trim(), a, b);
                setDataTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                int a = 0, b = 18;
                try {
                a = Integer.parseInt(aAge.getText());
                b = Integer.parseInt(bAge.getText());
                } catch(Exception e1) {
                	listChauNhoBean.clear(); setDataTable();
                	return;
                }
                listChauNhoBean = chauNhoService.search(key.trim(), a, b);
                setDataTable();
            }
        });
        this.aAge.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                int a = 0, b = 18;
                try {
                a = Integer.parseInt(aAge.getText());
                b = Integer.parseInt(bAge.getText());
                } catch(Exception e1) {
                	listChauNhoBean.clear(); setDataTable();
                	return;
                }
                listChauNhoBean = chauNhoService.search(key.trim(), a, b);
                setDataTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                int a = 0, b = 18;
                try {
                a = Integer.parseInt(aAge.getText());
                b = Integer.parseInt(bAge.getText());
                } catch(Exception e1) {
                	listChauNhoBean.clear(); setDataTable();
                	return;
                }
                listChauNhoBean = chauNhoService.search(key.trim(), a, b);
                setDataTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                int a = 0, b = 18;
                try {
                a = Integer.parseInt(aAge.getText());
                b = Integer.parseInt(bAge.getText());
                } catch(Exception e1) {
                	listChauNhoBean.clear(); setDataTable();
                	return;
                }
                listChauNhoBean = chauNhoService.search(key.trim(), a, b);
                setDataTable();
            }
        });
        this.bAge.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                int a = 0, b = 18;
                try {
                	
                a = Integer.parseInt(aAge.getText());
                b = Integer.parseInt(bAge.getText());
                } catch(Exception e1) {
                	listChauNhoBean.clear(); setDataTable();
                	return;
                }
                listChauNhoBean = chauNhoService.search(key.trim(), a, b);
                setDataTable();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                int a = 0, b = 18;
                try {
                a = Integer.parseInt(aAge.getText());
                b = Integer.parseInt(bAge.getText());
                } catch(Exception e1) {
                	listChauNhoBean.clear(); setDataTable();
                	return;
                }
                listChauNhoBean = chauNhoService.search(key.trim(), a, b);
                setDataTable();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String key = jtfSearch.getText();
                int a = 0, b = 18;
                try {
                a = Integer.parseInt(aAge.getText());
                b = Integer.parseInt(bAge.getText());
                } catch(Exception e1) {
                	listChauNhoBean.clear(); setDataTable();
                	return;
                }
                listChauNhoBean = chauNhoService.search(key.trim(), a, b);
                setDataTable();
            }
        });
        
    }
    
    public void setDataTable() {
        DefaultTableModel model = classTableModel.setTableChauNho(this.listChauNhoBean, COLUMNS);
        JTable table = new JTable(model) {
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
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
//                JOptionPane.showConfirmDialog(null, table.getSelectedRow());
                if (e.getClickCount() > 1) {
                    ChauNhoBean temp = listChauNhoBean.get(table.getSelectedRow());
                    ChauNhoBean info = chauNhoService.getChauNho(temp.getChungMinhThuModel().getSoCMT());
                    InfoJframe infoJframe = new InfoJframe(info.toString(), parentJFrame);
                    infoJframe.setLocationRelativeTo(null);
                    infoJframe.setVisible(true);
                }
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

    public void setParentJFrame(JFrame parentJFrame) {
        this.parentJFrame = parentJFrame;
    }
    
    public void refreshData() {
        this.listChauNhoBean = this.chauNhoService.getListChauNho(0, 18);
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


	public ChauNhoService getChauNhoService() {
		return chauNhoService;
	}


	public void setChauNhoService(ChauNhoService chauNhoService) {
		this.chauNhoService = chauNhoService;
	}


	public List<ChauNhoBean> getListChauNhoBean() {
		return listChauNhoBean;
	}


	public void setListChauNhoBean(List<ChauNhoBean> listChauNhoBean) {
		this.listChauNhoBean = listChauNhoBean;
	}


	public ClassTableModel getClassTableModel() {
		return classTableModel;
	}


	public void setClassTableModel(ClassTableModel classTableModel) {
		this.classTableModel = classTableModel;
	}


	public JTextField getaAge() {
		return aAge;
	}


	public void setaAge(JTextField aAge) {
		this.aAge = aAge;
	}


	public JTextField getbAge() {
		return bAge;
	}


	public void setbAge(JTextField bAge) {
		this.bAge = bAge;
	}


	public String[] getCOLUMNS() {
		return COLUMNS;
	}


	public JFrame getParentJFrame() {
		return parentJFrame;
	}
    
    
}
