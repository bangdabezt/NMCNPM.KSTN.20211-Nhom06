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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class SuaPhanQuaLeTet extends JFrame {

	private JPanel contentPane;
	private JTextField phanQua;
	private JTextField giaTri;
	private JButton huyBtn;
	private JButton xacNhanBtn;
	/**
	 * Launch the application.
	 */
	public SuaPhanQuaLeTet() {
		initComponents();

	}
	
	/**
	 * Create the frame.
	 */
	public void initComponents() {
		setTitle("Sửa phần quà");
		setBounds(100, 100, 501, 293);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel infoLbl = new JLabel("Nhập thông tin 01 phần quà");
		infoLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		phanQua = new JTextField();
		phanQua.setColumns(10);
		
		JLabel phanQuaLbl = new JLabel("Phần quà cơ bản");
		phanQuaLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		JLabel giaTriLbl = new JLabel("Giá trị (đồng)");
		giaTriLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		giaTri = new JTextField();
		giaTri.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
        		if(!Character.isDigit(e.getKeyChar())) {
					e.consume();
				}
			}
		});
		giaTri.setColumns(10);
		
		huyBtn = new JButton("Hủy");
		huyBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));

		
		setXacNhanBtn(new JButton("Xác nhận"));
		getXacNhanBtn().setFont(new Font("Tahoma", Font.PLAIN, 14));

		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(67)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(phanQuaLbl, GroupLayout.PREFERRED_SIZE, 116, GroupLayout.PREFERRED_SIZE)
								.addComponent(giaTriLbl, GroupLayout.PREFERRED_SIZE, 103, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addComponent(giaTri, GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
								.addComponent(phanQua, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(114)
							.addComponent(huyBtn, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
							.addGap(71)
							.addComponent(xacNhanBtn, GroupLayout.PREFERRED_SIZE, 106, GroupLayout.PREFERRED_SIZE)))
					.addGap(77))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(146, Short.MAX_VALUE)
					.addComponent(infoLbl, GroupLayout.PREFERRED_SIZE, 217, GroupLayout.PREFERRED_SIZE)
					.addGap(114))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(infoLbl)
					.addGap(47)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(phanQua, GroupLayout.PREFERRED_SIZE, 33, GroupLayout.PREFERRED_SIZE)
						.addComponent(phanQuaLbl))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(giaTri, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
						.addComponent(giaTriLbl))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(huyBtn, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
						.addComponent(xacNhanBtn, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(46, Short.MAX_VALUE))
		);
		contentPane.setLayout(gl_contentPane);
	}

	public JButton getXacNhanBtn() {
		return xacNhanBtn;
	}

	public void setXacNhanBtn(JButton xacNhanBtn) {
		this.xacNhanBtn = xacNhanBtn;
	}

	public JButton getHuyBtn() {
		return huyBtn;
	}

	public void setHuyBtn(JButton huyBtn) {
		this.huyBtn = huyBtn;
	}

	public JTextField getPhanQua() {
		return phanQua;
	}

	public void setPhanQua(JTextField phanQua) {
		this.phanQua = phanQua;
	}

	public JTextField getGiaTri() {
		return giaTri;
	}

	public void setGiaTri(JTextField giaTri) {
		this.giaTri = giaTri;
	}


}
