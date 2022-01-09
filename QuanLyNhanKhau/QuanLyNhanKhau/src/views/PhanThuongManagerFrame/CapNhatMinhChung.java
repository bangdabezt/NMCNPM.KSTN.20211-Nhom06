package views.PhanThuongManagerFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Bean.HocSinhBean;
import controllers.PhanThuongManagerController.DanhSachHocSinhController;
import services.HocSinhService;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JTextArea;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.border.CompoundBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.awt.event.ActionEvent;

public class CapNhatMinhChung extends JFrame {

	private JFrame parentFrame;
	private JPanel contentPane;
	private JTextField pathTextField;
	private JTextArea infoTextArea;
	private JButton browseBtn;
	private JComboBox<String> loaiHsComboBox;
	private HocSinhBean hocSinhBean;
	private HocSinhService hocSinhService = new HocSinhService();
	private JButton capNhatBtn;
	private String namHoc;
	private String loaiHS;
	private String path = null;
	private DanhSachHocSinhController controller;
	/**
	 * Launch the application.
	 */
	public CapNhatMinhChung(JFrame parentJFrame, HocSinhBean hocSinhBean, String namHoc, DanhSachHocSinhController controller) {
		this.hocSinhBean = hocSinhBean;
		initComponents();
		this.parentFrame = parentJFrame;
		this.namHoc = namHoc;
		this.controller = controller;
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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
		setTitle("Cập nhật minh chứng");
		setBounds(100, 100, 484, 334);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel thanhTichLbl = new JLabel("Thành tích");
		thanhTichLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		infoTextArea = new JTextArea();
		infoTextArea.setBorder(new CompoundBorder());
		infoTextArea.setEditable(false);
		//infoTextArea.setText("Họ tên: Nguyễn Văn A\nID: 00\r\nCon ông (bà): Nguyễn Văn B\r\nID hộ khẩu: 01");
		infoTextArea.setText("Họ tên: "+hocSinhBean.getNhanKhauModel().getHoTen()+"\nID: "+hocSinhBean.getNhanKhauModel().getID()+"\nCon ông (bà): "+ hocSinhService.timPhuHuynh(hocSinhBean) +"\nID hộ khẩu: "+hocSinhBean.getThanhVienCuaHoModel().getIdHoKhau());
		infoTextArea.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		loaiHsComboBox = new JComboBox<String>();
		loaiHsComboBox.setFont(new Font("Tahoma", Font.PLAIN, 14));
		loaiHsComboBox.setModel(new DefaultComboBoxModel<String>(new String[] {"Học sinh giỏi", "Học sinh khá", "Học sinh trung bình"}));
		
		JLabel titleLbl = new JLabel("Thông tin học sinh");
		titleLbl.setFont(new Font("Tahoma", Font.BOLD, 14));
		
		pathTextField = new JTextField();
		pathTextField.setColumns(10);
		
		JLabel mcLbl = new JLabel("Link minh chứng");
		mcLbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		browseBtn = new JButton("Browse");
		browseBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 JFileChooser fc = new JFileChooser();
				 int returnVal = fc.showOpenDialog(contentPane);
				 if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File file = fc.getSelectedFile();
			            pathTextField.setText(file.getPath());
			            path = file.getPath();
				 }
			}
		});
		browseBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		
		capNhatBtn = new JButton("Cập nhật");
		capNhatBtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		capNhatBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				capNhatBtnActionPerformed (e);
				
			}
		});
		
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(107)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(titleLbl, GroupLayout.PREFERRED_SIZE, 157, GroupLayout.PREFERRED_SIZE)
								.addComponent(infoTextArea, GroupLayout.PREFERRED_SIZE, 236, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(mcLbl, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
									.addGap(18))
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(thanhTichLbl, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)
									.addGap(31)))
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(loaiHsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(pathTextField, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.RELATED)
									.addComponent(browseBtn, GroupLayout.PREFERRED_SIZE, 90, GroupLayout.PREFERRED_SIZE)))))
					.addContainerGap())
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap(200, Short.MAX_VALUE)
					.addComponent(capNhatBtn)
					.addGap(169))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(20)
					.addComponent(titleLbl)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(infoTextArea, GroupLayout.PREFERRED_SIZE, 68, GroupLayout.PREFERRED_SIZE)
					.addGap(41)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(pathTextField, GroupLayout.PREFERRED_SIZE, 21, GroupLayout.PREFERRED_SIZE)
						.addComponent(mcLbl)
						.addComponent(browseBtn))
					.addGap(18)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(loaiHsComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(thanhTichLbl))
					.addPreferredGap(ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
					.addComponent(capNhatBtn)
					.addContainerGap())
		);
		contentPane.setLayout(gl_contentPane);
	}
	public void capNhatBtnActionPerformed (ActionEvent e) {
		loaiHS = (String) loaiHsComboBox.getSelectedItem();
		if (path == null) {
			JOptionPane.showMessageDialog(null, "Chưa cập nhật link minh chứng!", "Error!", JOptionPane.ERROR_MESSAGE);
		}
		hocSinhService.themMinhChung(hocSinhBean, path, loaiHS, namHoc);
		this.controller.setListHocSinhBeans(hocSinhService.getListHocSinh());
		this.controller.setDataTableTraoQua();
		this.controller.setText();
	}
}