package views.PhanThuongManagerFrame;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import controllers.PhanThuongManagerController.DanhSachHocSinhPanelController;
import services.HocSinhService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

public class SuaPhanQua extends JFrame {

	private JPanel contentPane;
	private JTextField phanQuaTxt;
	private JTextField giaTriTxt;
	private JTextField hsgTxt;
	private JTextField hskTxt;
	private JTextField hstbTxt;
	private JButton huyBtn;
	private JButton xacNhanBtn;
	private JFrame parentFrame;
	private DanhSachHocSinhPanelController controller;
	private String namHoc;
	/**
	 * Launch the application.
	 */
	public SuaPhanQua(JFrame parentFrame, DanhSachHocSinhPanelController controller, String namHoc) {
		this.parentFrame = parentFrame;
		this.controller = controller;
		this.namHoc = namHoc;
		initComponents();
		this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                close(); //To change body of generated methods, choose Tools | Templates.
            }
		});
	}
	
	public void close() {
		if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(null, "Are you sure to close?", "Warning!", JOptionPane.YES_NO_OPTION)) {
	        this.parentFrame.setEnabled(true);
	        this.dispose();
	    }
	}
	/**
	 * Create the frame.
	 */
	public void initComponents() {
		setTitle("Sửa phần quà");
		setBounds(100, 100, 485, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel infoLbl = new JLabel("Nhập thông tin 01 phần quà");
		infoLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		phanQuaTxt = new JTextField();
		phanQuaTxt.setColumns(10);
		
		JLabel phanQuaLbl = new JLabel("Phần quà cơ bản");
		phanQuaLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel giaTriLbl = new JLabel("Giá trị");
		giaTriLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		giaTriTxt = new JTextField();
		giaTriTxt.setColumns(10);
		
		JLabel soLuongLbl = new JLabel("Nhập số lượng phần quà");
		soLuongLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		JLabel hsgLbl = new JLabel("Học sinh giỏi");
		hsgLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		hsgTxt = new JTextField();
		hsgTxt.setColumns(10);
		
		JLabel hskLbl = new JLabel("Học sinh khá");
		hskLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		hskTxt = new JTextField();
		hskTxt.setColumns(10);
		
		JLabel hstbLbl = new JLabel("Học sinh TB");
		hstbLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		hstbTxt = new JTextField();
		hstbTxt.setColumns(10);
		
		huyBtn = new JButton("Hủy");
		huyBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		huyBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		
		xacNhanBtn = new JButton("Xác nhận");
		xacNhanBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		xacNhanBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmActionPerformed (e);
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(71)
							.addComponent(infoLbl, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(59)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(phanQuaLbl, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addComponent(giaTriLbl, GroupLayout.PREFERRED_SIZE, 45, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
									.addComponent(hskLbl, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addComponent(hsgLbl, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
										.addComponent(hstbLbl)
										.addPreferredGap(ComponentPlacement.RELATED))))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
										.addComponent(giaTriTxt)
										.addComponent(phanQuaTxt, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addGap(10)
									.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
										.addComponent(hskTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(hsgTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
										.addComponent(hstbTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(73)
							.addComponent(soLuongLbl, GroupLayout.PREFERRED_SIZE, 190, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(118)
							.addComponent(huyBtn)
							.addGap(85)
							.addComponent(xacNhanBtn)))
					.addContainerGap(156, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(40)
					.addComponent(infoLbl)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(phanQuaLbl)
						.addComponent(phanQuaTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(giaTriLbl)
						.addComponent(giaTriTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(33)
					.addComponent(soLuongLbl)
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(hsgLbl)
						.addComponent(hsgTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(hskLbl)
						.addComponent(hskTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(hstbLbl)
						.addComponent(hstbTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(huyBtn)
						.addComponent(xacNhanBtn))
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	public void confirmActionPerformed (ActionEvent evt) {
		try {
	    	String phanQuaCoBan = phanQuaTxt.getText();
	    	float giaTri = Float.parseFloat(giaTriTxt.getText()); 
	    	int sl_hsg = Integer.parseInt(hsgTxt.getText());
	    	int sl_hsk = Integer.parseInt(hskTxt.getText());
	    	int sl_hstb = Integer.parseInt(hstbTxt.getText());
	    	HocSinhService hocSinhService = new HocSinhService();
			hocSinhService .capNhatPhanQua(namHoc, phanQuaCoBan, giaTri, sl_hsg, sl_hsk, sl_hstb);
	    	JOptionPane.showMessageDialog(null, "Sửa phần quà thành công!", "Success", JOptionPane.PLAIN_MESSAGE);
	    	}
	    	catch(Exception e) {
	    		JOptionPane.showMessageDialog(null, "Có lỗi xảy ra. Vui lòng kiểm tra lại.", "Warning!!", JOptionPane.ERROR_MESSAGE);
				//e.printStackTrace();
	    	}
	}
}
