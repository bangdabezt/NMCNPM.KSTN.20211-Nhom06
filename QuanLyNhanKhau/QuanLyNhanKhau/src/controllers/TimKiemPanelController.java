package controllers;

import Bean.HoKhauBean;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;
import java.util.Objects;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import services.HoKhauService;
import utility.TableModelHoKhau;
import views.TimKiemManagerFrame.LSuThayDoiNhanKhauJFrame;
import views.infoViews.InfoJframe;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 *
 * @author Hai
 */
public class TimKiemPanelController {
    private List<HoKhauBean> list;
    private JTextField searchJtf;
    private JPanel tableJpn;
    private final HoKhauService hoKhauService = new HoKhauService();
    private final TableModelHoKhau tableModelHoKhau = new TableModelHoKhau();
    private final String COLUNMS[] = {"Mã hộ khẩu", "Họ tên chủ hộ", "Địa chỉ"}; 
    private HoKhauBean hokhauBean = null;
    private JFrame parentJFrame;
    private JDateChooser tuNgayCalendar;
    private JDateChooser denNgayCalendar;
    private JButton lichSuButton;
    private JTable table;

    public TimKiemPanelController(JTextField searchJtf, JPanel tableJpn, JButton lichSuButton,
    		                      JDateChooser tuNgayCalendar, JDateChooser denNgayCalendar) {
        this.searchJtf = searchJtf;
        this.tableJpn = tableJpn;
        this.lichSuButton = lichSuButton;
        this.tuNgayCalendar = tuNgayCalendar;
        this.denNgayCalendar = denNgayCalendar;
        this.list = hoKhauService.getListHoKhau();
        setData();
        initAction();
        //this.hokhauBean = list.get(table.getSelectedRow());
    }
    
    public void initAction() {
        this.searchJtf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String key = searchJtf.getText().trim();
                if (key.isEmpty()) {
                    list = hoKhauService.getListHoKhau();
                } else {
                    list = hoKhauService.search(key);
                }
                setData();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String key = searchJtf.getText().trim();
                if (key.isEmpty()) {
                    list = hoKhauService.getListHoKhau();
                } else {
                    list = hoKhauService.search(key);
                }
                setData();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String key = searchJtf.getText().trim();
                if (key.isEmpty()) {
                    list = hoKhauService.getListHoKhau();
                } else {
                    list = hoKhauService.search(key);
                }
                setData();
            }
        });
        
        this.lichSuButton.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		lichSuThayDoiNhanKhauActionPerformed(e);
        	}
        });
    }

    public void setData() {
        DefaultTableModel model = tableModelHoKhau.setTableHoKhau(list, COLUNMS);
        
        table = new JTable(model) {
            @Override
            public boolean editCellAt(int row, int column, EventObject e) {
                return false;
            }
            
        };
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        table.getTableHeader().setPreferredSize(new Dimension(100, 50));
        table.setRowHeight(50);
        table.validate();
        table.repaint();
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
            	hokhauBean = list.get(table.getSelectedRow());
                if (e.getClickCount() > 1) {
                    HoKhauBean temp = list.get(table.getSelectedRow());
                    InfoJframe infoJframe = new InfoJframe(temp.toString(), parentJFrame);
                    infoJframe.setLocationRelativeTo(null);
                    infoJframe.setVisible(true);
                }
            }
            
        });
        
        JScrollPane scroll = new JScrollPane();
        scroll.getViewport().add(table);
        tableJpn.removeAll();
        tableJpn.setLayout(new BorderLayout());
        tableJpn.add(scroll);
        tableJpn.validate();
        tableJpn.repaint();
    }

    public void setParentJFrame(JFrame parentJFrame) {
        this.parentJFrame = parentJFrame;
    }
    
    public List<HoKhauBean> getList() {
        return list;
    }

    public void setList(List<HoKhauBean> list) {
        this.list = list;
    }

    public JTextField getSearchJtf() {
        return searchJtf;
    }

    public void setSearchJtf(JTextField searchJtf) {
        this.searchJtf = searchJtf;
    }

    public JPanel getTableJpn() {
        return tableJpn;
    }

    public void setTableJpn(JPanel tableJpn) {
        this.tableJpn = tableJpn;
    }
    
    public void lichSuThayDoiNhanKhauActionPerformed(ActionEvent e) {
    	if(Objects.isNull(this.hokhauBean)) JOptionPane.showMessageDialog(new JFrame(), "Hãy chọn 1 hộ khẩu để xem!", "ERROR", JOptionPane.ERROR_MESSAGE);
    	else {
	    	LSuThayDoiNhanKhauJFrame lichSu = new LSuThayDoiNhanKhauJFrame(this.parentJFrame, this.tuNgayCalendar, this.denNgayCalendar, 
	    			                                                       this.hokhauBean);
	    	lichSu.setLocationRelativeTo(null);
	    	lichSu.setResizable(false);
	    	lichSu.setVisible(true);
    	}
    }
}
