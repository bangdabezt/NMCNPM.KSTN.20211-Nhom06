package controllers.PhanThuongManagerController;
import Bean.TraoQuaHoKhauBean;
import models.LeTetModel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.EventObject;
import java.util.List;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import services.HoKhauService;
import services.TraoQuaHoKhauService;
import utility.TableModelHoKhau;
import views.PhanThuongManagerFrame.SuaPhanQuaLeTet;
import views.infoViews.InfoJframe;

public class DanhSachChauNhoController {
    private List<TraoQuaHoKhauBean> list;
    private JTextField searchJtf;
    private JPanel tableJpn;
    private JComboBox cbNam;
    private JComboBox cbSuKien;
    private JTextPane thongTin;
    private List<LeTetModel> listLeTetModel;
    private JTable table;
    private final TraoQuaHoKhauService traoQuaHoKhauService = new TraoQuaHoKhauService();
    private final TableModelHoKhau tableModelHoKhau = new TableModelHoKhau();
    private final String COLUNMS[] = {"Mã hộ khẩu", "Chủ hộ", "Địa chỉ", "Suất", "Tổng giá trị(đồng)", "Tình trạng quà"}; 
    private JFrame parentJFrame;

    public DanhSachChauNhoController(JFrame parentJFrame, JComboBox cbNam, JComboBox cbSuKien, JTextField textField_2, JPanel tablePanel,
			JTextPane thongTin) {
        this.searchJtf = textField_2;
        this.tableJpn = tablePanel;
        this.thongTin = thongTin;
        this.cbNam = cbNam;
        this.cbSuKien = cbSuKien;
        this.parentJFrame = parentJFrame;
        initAction();
        this.setComboBoxNam();
        this.setComboBoxSuKien();
        this.list = traoQuaHoKhauService.getListTraoQuaHoKhau(listLeTetModel.get(cbSuKien.getSelectedIndex()));
        setData();
    }
    
    public void setComboBoxNam() {
    	this.cbNam.removeAllItems();
    	List<Integer> res = traoQuaHoKhauService.getComboBoxNam();
    	for(Integer item : res) {
    		cbNam.addItem(item.intValue());
    	}
    }
    
    public void setComboBoxSuKien() {
    	int nam = ((Integer) cbNam.getSelectedItem()).intValue();
    	this.cbSuKien.removeAllItems();
    	listLeTetModel = traoQuaHoKhauService.getComboBoxSuKien(nam);
    	for(LeTetModel item : listLeTetModel) {
    		String tmp = item.getTenSuKien() + " - " + item.getThoiGian();
    		cbSuKien.addItem(tmp);
    	}
    }
    

	public void initAction() {
        this.cbNam.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		setComboBoxSuKien();
        	}
        });
        this.cbSuKien.addItemListener(new ItemListener() {
        	public void itemStateChanged(ItemEvent e) {
        		if(cbSuKien.getSelectedIndex() >= 0) {
        			searchJtf.setText("");
        			list = traoQuaHoKhauService.getListTraoQuaHoKhau(listLeTetModel.get(cbSuKien.getSelectedIndex()));
        			setData();
        			thongTin.setText(traoQuaHoKhauService.getThongTinText(listLeTetModel.get(cbSuKien.getSelectedIndex())));
        		}
        	}

        });

        this.searchJtf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                String key = searchJtf.getText().trim();
                if (key.isEmpty()) {
                    list = traoQuaHoKhauService.getListTraoQuaHoKhau(listLeTetModel.get(cbSuKien.getSelectedIndex()));
                } else {
                    list = traoQuaHoKhauService.search(key, listLeTetModel.get(cbSuKien.getSelectedIndex()));
                }
                setData();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                String key = searchJtf.getText().trim();
                if (key.isEmpty()) {
                    list = traoQuaHoKhauService.getListTraoQuaHoKhau(listLeTetModel.get(cbSuKien.getSelectedIndex()));
                } else {
                    list = traoQuaHoKhauService.search(key, listLeTetModel.get(cbSuKien.getSelectedIndex()));
                }
                setData();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                String key = searchJtf.getText().trim();
                if (key.isEmpty()) {
                    list = traoQuaHoKhauService.getListTraoQuaHoKhau(listLeTetModel.get(cbSuKien.getSelectedIndex()));
                } else {
                    list = traoQuaHoKhauService.search(key, listLeTetModel.get(cbSuKien.getSelectedIndex()));
                }
                setData();
            }
        });
    }

    public void setData() {
        DefaultTableModel model = tableModelHoKhau.setTableTraoQuaHoKhau(list, COLUNMS);
        
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
                if (e.getClickCount() > 1) {
                    TraoQuaHoKhauBean temp = list.get(table.getSelectedRow());
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
    public void traoQua(Date date) {
    	int row = table.getSelectedRow();
		if (row == -1) {
			JOptionPane.showMessageDialog(null, "Chưa chọn đối tượng trao quà!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if(list.get(row).getTrangThaiNhanQua().equals("Đã nhận")) {
			JOptionPane.showMessageDialog(null, "Hộ gia đình này đã được trao quà trong sự kiện này rồi!", "Lỗi!", JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(null, "Xác nhận trao quà cho hộ gia đình " + 
				list.get(row).getHoKhauModel().getMaHoKhau() + 
				"\n" + "Chủ hộ: " + list.get(row).getChuHo().getHoTen() +
				"\n" + "Sự kiện: " + list.get(row).getLeTetModel().getTenSuKien()+", ngày: " + list.get(row).getLeTetModel().getThoiGian()
			, "Confirm!", JOptionPane.YES_NO_OPTION)) {
			traoQuaHoKhauService.traoQua(list.get(row), date);
            String key = searchJtf.getText().trim();
            if (key.isEmpty()) {
                list = traoQuaHoKhauService.getListTraoQuaHoKhau(listLeTetModel.get(cbSuKien.getSelectedIndex()));
            } else {
                list = traoQuaHoKhauService.search(key, listLeTetModel.get(cbSuKien.getSelectedIndex()));
            }
            setData();
			thongTin.setText(traoQuaHoKhauService.getThongTinText(listLeTetModel.get(cbSuKien.getSelectedIndex())));
		}

    }
    
    public void suaPhanQua() {
    	try {
    	if(!traoQuaHoKhauService.checkSuKien(listLeTetModel.get(cbSuKien.getSelectedIndex()))) {
    		JOptionPane.showMessageDialog(null, "Đã trao quà cho một số hộ, không thể thay đổi phần quà!", "Warning!!", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	parentJFrame.setEnabled(false);
    	SuaPhanQuaLeTet suaQua = new SuaPhanQuaLeTet();
		suaQua.getXacNhanBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(suaQua.getPhanQua().getText().length() == 0 || suaQua.getGiaTri().getText().length() == 0) {
					JOptionPane.showMessageDialog(null, "Chưa nhập đủ thông tin, vui lòng kiểm tra lại", "Warning!!", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String phanQuaCoBan = suaQua.getPhanQua().getText();
	        	double giaTri = Double.parseDouble(suaQua.getGiaTri().getText());
	        	traoQuaHoKhauService.suaQua(phanQuaCoBan, giaTri, listLeTetModel.get(cbSuKien.getSelectedIndex()));
	        	parentJFrame.setEnabled(true);
				suaQua.dispose();
				list = traoQuaHoKhauService.getListTraoQuaHoKhau(listLeTetModel.get(cbSuKien.getSelectedIndex()));
				setData();
			}
		});
		suaQua.getHuyBtn().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(null, "Are you sure to close?", "Warning!", JOptionPane.YES_NO_OPTION)) {
					parentJFrame.setEnabled(true);
					suaQua.dispose();
			    }
			}
		});
		suaQua.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
            	if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(null, "Are you sure to close?", "Warning!", JOptionPane.YES_NO_OPTION)) {
					parentJFrame.setEnabled(true);
					suaQua.dispose();
			    }
            }
		});
		suaQua.setVisible(true);
    	}
    	catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Có lỗi xảy ra. Vui lòng kiểm tra lại.", "Warning!!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
    	}
    	
    }
    public void setParentJFrame(JFrame parentJFrame) {
        this.parentJFrame = parentJFrame;
    }
    
    public List<TraoQuaHoKhauBean> getList() {
        return list;
    }

    public void setList(List<TraoQuaHoKhauBean> list) {
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
}
    