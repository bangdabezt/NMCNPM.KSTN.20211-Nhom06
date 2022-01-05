package views.PhanThuongManagerFrame;

import Bean.NhanKhauBean;
import controllers.LoginController;
import controllers.PhanThuongManagerController.LapDanhSachChauNhoController;
import controllers.PhanThuongPanelController;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import models.ChungMinhThuModel;
import models.NhanKhauModel;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.GroupLayout;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 *
 * @author Hai
 */
public class LapDanhSachChauNho extends javax.swing.JFrame {

    private PhanThuongPanelController parentController;
    private JFrame parentFrame;
    private NhanKhauBean nhanKhauBean;
    private LapDanhSachChauNhoController controller;


    public LapDanhSachChauNho(PhanThuongPanelController parentController, JFrame parentJFrame) {
        this.parentController = parentController;
        this.parentFrame = parentJFrame;
        this.parentFrame.setEnabled(false);
        this.nhanKhauBean = new NhanKhauBean();
        initComponents();
        setTitle("Lập danh sách phát quà");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        controller = new LapDanhSachChauNhoController(tablePanel, jtfSearch, aAge, bAge);
        controller.setParentJFrame(parentJFrame);
        controller.setDataTable();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure to close??", "Warning!!", JOptionPane.YES_NO_OPTION) == 0) {
                    close();
                }
            }
            
        });
    }
    
    void close() {
        this.parentFrame.setEnabled(true);
        dispose();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        SuKienJTF = new javax.swing.JTextField();
        CreateBtn = new javax.swing.JButton();
        CancelBtn = new javax.swing.JButton();
        CancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	cancelBtnActionPerformed(evt);
            }
        });
        jLabel1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        ngaySuKien = new com.toedter.calendar.JDateChooser();
        jLabel26 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        SuKienJTF.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        

        CreateBtn.setBackground(new java.awt.Color(255, 255, 255));
        CreateBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        CreateBtn.setText("Create");
        CreateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	createActionPerformed(evt);
            }
        });

        CancelBtn.setBackground(new java.awt.Color(255, 255, 255));
        CancelBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        CancelBtn.setText("Cancel");
        

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Sự kiện:");

        jLabel8.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel8.setText("Ngày:");

        jLabel26.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel26.setText("Độ tuổi trao quà:");
        
        aAge = new JTextField("0");
        aAge.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
        	}
        });
        aAge.setHorizontalAlignment(SwingConstants.CENTER);
        aAge.setFont(new Font("Arial", Font.PLAIN, 14));
        
        tablePanel = new JPanel();
        GroupLayout gl_tablePanel = new GroupLayout(tablePanel);
        gl_tablePanel.setHorizontalGroup(
        	gl_tablePanel.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 302, Short.MAX_VALUE)
        		.addGap(0, 302, Short.MAX_VALUE)
        );
        gl_tablePanel.setVerticalGroup(
        	gl_tablePanel.createParallelGroup(Alignment.LEADING)
        		.addGap(0, 224, Short.MAX_VALUE)
        		.addGap(0, 224, Short.MAX_VALUE)
        );
        tablePanel.setLayout(gl_tablePanel);
        
        bAge = new JTextField("18");
        bAge.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
        	}
        });
        bAge.setHorizontalAlignment(SwingConstants.CENTER);
        bAge.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel jLabel4 = new JLabel();
        jLabel4.setText("=>");
        jLabel4.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel jLabel5 = new JLabel();
        jLabel5.setText("Tuổi");
        jLabel5.setFont(new Font("Arial", Font.ITALIC, 12));
        
        jtfSearch = new JTextField();
        jtfSearch.setFont(new Font("Arial", Font.PLAIN, 14));
        
        phanQua = new JTextField();
        phanQua.setFont(new Font("Arial", Font.PLAIN, 14));
        
        giaTriPanel = new JTextField();
        giaTriPanel.addKeyListener(new KeyAdapter() {
        	@Override
        	public void keyTyped(KeyEvent e) {
        		if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
        	}
        });
        giaTriPanel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        lblPhnQu = new JLabel();
        lblPhnQu.setText("Phần quà:");
        lblPhnQu.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblGiTr = new JLabel();
        lblGiTr.setText("Giá trị (VNĐ):");
        lblGiTr.setFont(new Font("Arial", Font.BOLD, 14));
        

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(28)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(jLabel1)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(SuKienJTF, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE)
        					.addGap(69)
        					.addComponent(jLabel8)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(ngaySuKien, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE)
        					.addGap(197)
        					.addComponent(jLabel26)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(aAge, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 18, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(bAge, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 38, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap())
        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        					.addGroup(jPanel1Layout.createSequentialGroup()
        						.addComponent(jtfSearch, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
        						.addContainerGap())
        					.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        						.addComponent(tablePanel, GroupLayout.PREFERRED_SIZE, 707, GroupLayout.PREFERRED_SIZE)
        						.addPreferredGap(ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
        						.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        							.addGroup(jPanel1Layout.createSequentialGroup()
        								.addComponent(CancelBtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE)
        								.addGap(33)
        								.addComponent(CreateBtn, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
        								.addGap(109))
        							.addGroup(jPanel1Layout.createSequentialGroup()
        								.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        									.addComponent(lblGiTr, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
        									.addComponent(lblPhnQu, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
        								.addPreferredGap(ComponentPlacement.RELATED)
        								.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false)
        									.addComponent(giaTriPanel)
        									.addComponent(phanQua, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE))
        								.addGap(49)))))))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(36)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(jLabel5, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        						.addComponent(bAge, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jLabel4, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        						.addComponent(aAge, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        						.addComponent(jLabel26, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(37)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(ngaySuKien, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        						.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        							.addComponent(SuKienJTF, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        							.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        							.addComponent(jLabel8, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)))))
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGap(115)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblPhnQu, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        						.addComponent(phanQua, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        					.addGap(30)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblGiTr, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        						.addComponent(giaTriPanel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        					.addPreferredGap(ComponentPlacement.RELATED, 206, Short.MAX_VALUE)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(CancelBtn, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
        						.addComponent(CreateBtn, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
        				.addGroup(Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addComponent(tablePanel, GroupLayout.PREFERRED_SIZE, 399, GroupLayout.PREFERRED_SIZE)))
        			.addGap(18)
        			.addComponent(jtfSearch, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addGap(23))
        );
        jPanel1.setLayout(jPanel1Layout);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 1132, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addComponent(jPanel1, GroupLayout.DEFAULT_SIZE, 587, Short.MAX_VALUE)
        );
        getContentPane().setLayout(layout);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    
    // su ly su kien nhan nut create
    //GEN-LAST:event_CreateBtnActionPerformed
    
    
    // check cac gia tri duoc nhap vao form
    public void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {
    	if (JOptionPane.showConfirmDialog(this, "Are you sure to close?", "Confirm", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            this.parentFrame.setEnabled(true);
            dispose();
        }
    }
    
    public void createActionPerformed(java.awt.event.ActionEvent evt) {
    	if(SuKienJTF.getText().length() == 0 || phanQua.getText().length() == 0 || aAge.getText().length() == 0 || bAge.getText().length() == 0) {
    		JOptionPane.showMessageDialog(null, "Chưa nhập đủ thông tin, vui lòng kiểm tra lại", "Warning!!", JOptionPane.ERROR_MESSAGE);
			return;
    	}
    	
    	try {
        	String suKien = SuKienJTF.getText();
        	String phanQuaCoBan = phanQua.getText();
        	float giaTri = Float.parseFloat(giaTriPanel.getText()); 
        	int aAgeV = Integer.parseInt(aAge.getText());
        	int bAgeV = Integer.parseInt(bAge.getText());
        	if(controller.getChauNhoService().getListChauNho(aAgeV, bAgeV).size() == 0) {
        		JOptionPane.showMessageDialog(null, "Không có ai trong danh sách nhận quà", "Warning!!", JOptionPane.ERROR_MESSAGE);
    			return;                		
        	}
        	Date date = ngaySuKien.getDate();
        	controller.getChauNhoService().taoSuKien(this, suKien, phanQuaCoBan, giaTri, aAgeV, bAgeV, date);
        	}
        	catch(Exception e) {
        		JOptionPane.showMessageDialog(null, "Có lỗi xảy ra. Vui lòng kiểm tra lại.", "Warning!!", JOptionPane.ERROR_MESSAGE);
    			e.printStackTrace();
        	}
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelBtn;
    private javax.swing.JButton CreateBtn;
    private javax.swing.JTextField SuKienJTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private com.toedter.calendar.JDateChooser ngaySuKien;
    private JTextField aAge;
    private JTextField bAge;
    private JTextField jtfSearch;
    private JTextField phanQua;
    private JTextField giaTriPanel;
    private JLabel lblPhnQu;
    private JLabel lblGiTr;
    private JPanel tablePanel;
}