package views.PhanThuongManagerFrame;

import Bean.HocSinhBean;
import controllers.LoginController;
import controllers.PhanThuongManagerController.LapDanhSachChauNhoController;
import controllers.PhanThuongManagerController.DanhSachHocSinhController;
import controllers.PhanThuongPanelController;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
import services.HocSinhService;


/**
 *
 * @author Hai
 */
public class LapDanhSachHocSinh extends javax.swing.JFrame {

    /**
     * Creates new form AddNewPeopleJFrame
     */
    private PhanThuongPanelController parentController;
    private JFrame parentFrame;
    private HocSinhBean hocSinhBean;
    private DanhSachHocSinhController controller;
    private HocSinhService hocSinhService = new HocSinhService();

    public LapDanhSachHocSinh(PhanThuongPanelController parentController, JFrame parentJFrame) {
        this.parentController = parentController;
        this.parentFrame = parentJFrame;
        this.parentFrame.setEnabled(false);
        this.hocSinhBean = new HocSinhBean();
        initComponents();
        setTitle("Lập danh sách phát quà");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        controller = new DanhSachHocSinhController(tablePanel,txtId);
        controller.setParentJFrame(parentFrame);
        controller.setDataTable();
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (JOptionPane.showConfirmDialog(null, "Are you sure to close?", "Warning!", JOptionPane.YES_NO_OPTION) == 0) {
                    close();
                }
            }
            
        });
    }
    
    /**
     * @wbp.parser.constructor
     */
    public LapDanhSachHocSinh(JFrame parentJFrame) {
        this.parentController = new PhanThuongPanelController(){
            /*@Override
            public void refreshData() {
                // do nothing
            }
            @Override
            public void initAction() {
                // do nothing
            }*/
            
            
        };
        this.parentFrame = parentJFrame;
        this.parentFrame.setEnabled(false);
        this.hocSinhBean = new HocSinhBean();
        initComponents();
        setTitle("Lập danh sách phát quà");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        controller = new DanhSachHocSinhController(tablePanel,txtId);
        controller.setParentJFrame(parentFrame);
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
        jLabel1 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        SuKienJTF.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        

        CreateBtn.setBackground(new java.awt.Color(255, 255, 255));
        CreateBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        CreateBtn.setText("Create");
        CreateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	createBtnActionPerformed(evt);
            }
        });

        CancelBtn.setBackground(new java.awt.Color(255, 255, 255));
        CancelBtn.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        CancelBtn.setText("Cancel");
        CancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
            	cancelBtnActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Năm học:");

        jLabel26.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel26.setText("Nhập thông tin 01 phần quà:");
        
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
        
        txtId = new JTextField();
        txtId.setFont(new Font("Arial", Font.PLAIN, 14));
        
        textField_3 = new JTextField();
        textField_3.setFont(new Font("Arial", Font.PLAIN, 14));
        
        lblPhnQu = new JLabel();
        lblPhnQu.setText("Học sinh giỏi:");
        lblPhnQu.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblGiTr = new JLabel();
        lblGiTr.setText("Học sinh TB:");
        lblGiTr.setFont(new Font("Arial", Font.BOLD, 14));
        
        textField_5 = new JTextField();
        textField_5.setFont(new Font("Arial", Font.PLAIN, 14));
        
        textField_6 = new JTextField();
        textField_6.setFont(new Font("Arial", Font.PLAIN, 14));
        
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        
        lblHcSinhKh = new JLabel();
        lblHcSinhKh.setText("Học sinh khá:");
        lblHcSinhKh.setFont(new Font("Arial", Font.BOLD, 14));
        
        textField_1 = new JTextField();
        textField_1.setFont(new Font("Arial", Font.PLAIN, 14));
        
        lblNhpSLng = new JLabel();
        lblNhpSLng.setText("Nhập số lượng phần quà:");
        lblNhpSLng.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblPhnQuC = new JLabel();
        lblPhnQuC.setText("Phần quà cơ bản:");
        lblPhnQuC.setFont(new Font("Arial", Font.BOLD, 14));
        
        lblGiTr_1 = new JLabel();
        lblGiTr_1.setText("Giá trị:");
        lblGiTr_1.setFont(new Font("Arial", Font.BOLD, 14));
        
        JLabel lblNewLabel = new JLabel("Danh sách học sinh trên địa bàn:");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
        

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1Layout.setHorizontalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(28)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addComponent(jLabel1)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(SuKienJTF, GroupLayout.PREFERRED_SIZE, 166, GroupLayout.PREFERRED_SIZE))
        				.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        					.addGroup(jPanel1Layout.createSequentialGroup()
        						.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
        						.addContainerGap())
        					.addGroup(jPanel1Layout.createSequentialGroup()
        						.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        							.addComponent(tablePanel, GroupLayout.PREFERRED_SIZE, 680, GroupLayout.PREFERRED_SIZE)
        							.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 306, GroupLayout.PREFERRED_SIZE))
        						.addGap(58)
        						.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        							.addGroup(jPanel1Layout.createSequentialGroup()
        								.addComponent(lblNhpSLng, GroupLayout.PREFERRED_SIZE, 195, GroupLayout.PREFERRED_SIZE)
        								.addContainerGap())
        							.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        								.addGroup(jPanel1Layout.createSequentialGroup()
        									.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        										.addGroup(jPanel1Layout.createSequentialGroup()
        											.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        												.addGroup(jPanel1Layout.createSequentialGroup()
        													.addGap(29)
        													.addComponent(CancelBtn, GroupLayout.PREFERRED_SIZE, 80, GroupLayout.PREFERRED_SIZE))
        												.addComponent(lblGiTr_1, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
        												.addComponent(lblPhnQuC, GroupLayout.PREFERRED_SIZE, 140, GroupLayout.PREFERRED_SIZE))
        											.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        											.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING, false)
        												.addGroup(jPanel1Layout.createSequentialGroup()
        													.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        														.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
        														.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 199, GroupLayout.PREFERRED_SIZE)
        														.addComponent(textField_5, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE))
        													.addPreferredGap(ComponentPlacement.RELATED))
        												.addGroup(jPanel1Layout.createSequentialGroup()
        													.addComponent(CreateBtn, GroupLayout.PREFERRED_SIZE, 79, GroupLayout.PREFERRED_SIZE)
        													.addGap(42))
        												.addGroup(jPanel1Layout.createSequentialGroup()
        													.addComponent(textField)
        													.addPreferredGap(ComponentPlacement.RELATED))))
        										.addGroup(jPanel1Layout.createSequentialGroup()
        											.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        												.addComponent(lblHcSinhKh, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
        												.addComponent(lblPhnQu, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
        												.addComponent(lblGiTr, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE))
        											.addGap(28)
        											.addComponent(textField_3, GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
        											.addPreferredGap(ComponentPlacement.RELATED)))
        									.addGap(51))
        								.addGroup(jPanel1Layout.createSequentialGroup()
        									.addComponent(jLabel26)
        									.addContainerGap())))))))
        );
        jPanel1Layout.setVerticalGroup(
        	jPanel1Layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(jPanel1Layout.createSequentialGroup()
        			.addGap(37)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        				.addComponent(SuKienJTF, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(jLabel26, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        				.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(jPanel1Layout.createSequentialGroup()
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, 48, GroupLayout.PREFERRED_SIZE)
        						.addComponent(lblPhnQuC, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
        					.addGap(18)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblGiTr_1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        						.addComponent(textField_6, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        					.addGap(39)
        					.addComponent(lblNhpSLng, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.RELATED)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.TRAILING)
        						.addComponent(lblPhnQu, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        						.addComponent(textField_5, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        					.addGap(18)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(lblHcSinhKh, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        						.addComponent(textField_3, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        					.addGap(18)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.LEADING)
        						.addComponent(lblGiTr, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        						.addComponent(textField, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE))
        					.addGap(65)
        					.addGroup(jPanel1Layout.createParallelGroup(Alignment.BASELINE)
        						.addComponent(CreateBtn, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
        						.addComponent(CancelBtn, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)))
        				.addComponent(tablePanel, GroupLayout.PREFERRED_SIZE, 408, GroupLayout.PREFERRED_SIZE))
        			.addGap(18)
        			.addComponent(txtId, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
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

    
    
    //GEN-LAST:event_CreateBtnActionPerformed
    // check cac gia tri duoc nhap vao form
    // xu ly su kien nhan nut create
    public void createBtnActionPerformed(java.awt.event.ActionEvent evt) {
    	try {
    		String namhoc = SuKienJTF.getText();
    		String phanQuaCoBan = textField_1.getText();
    		float giaTri = Float.parseFloat(textField_6.getText()); 
    		int sl_hsg = Integer.parseInt(textField_5.getText());
    		int sl_hsk = Integer.parseInt(textField_3.getText());
    		int sl_hstb = Integer.parseInt(textField.getText());
    		if (hocSinhService.capNhatPhanQua(namhoc, phanQuaCoBan, giaTri, sl_hsg, sl_hsk, sl_hstb) == 0)
    			JOptionPane.showMessageDialog(null, "Tạo danh sách phát quà và phần quà thành công!", "Success", JOptionPane.PLAIN_MESSAGE);
    	}
    	catch(Exception e) {
    		JOptionPane.showMessageDialog(null, "Có lỗi xảy ra. Vui lòng kiểm tra lại.", "Warning!", JOptionPane.ERROR_MESSAGE);
			//e.printStackTrace();
    	}
    }

    public void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {
    	if (JOptionPane.showConfirmDialog(this, "Are you sure to close?", "Confirm", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            this.parentFrame.setEnabled(true);
            dispose();
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelBtn;
    private javax.swing.JButton CreateBtn;
    private javax.swing.JTextField SuKienJTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JPanel jPanel1;
    private JTextField txtId;
    private JTextField textField_3;
    private JLabel lblPhnQu;
    private JLabel lblGiTr;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField;
    private JLabel lblHcSinhKh;
    private JTextField textField_1;
    private JLabel lblNhpSLng;
    private JLabel lblPhnQuC;
    private JLabel lblGiTr_1;
    private JPanel tablePanel;
}