package views;

import controllers.NhanKhauManagerPanelController;
import javax.swing.JFrame;
import views.NhanKhauManagerFrame.AddNewPeopleJFrame;
import views.NhanKhauManagerFrame.DangKyTamTruJFrame;
import views.NhanKhauManagerFrame.DangKyTamVangJFrame;
import views.NhanKhauManagerFrame.KhaiTuJFrame;
import views.NhanKhauManagerFrame.PeopleChangeJFrame;

import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import javax.swing.GroupLayout.Alignment;
import javax.swing.GroupLayout;
import javax.swing.LayoutStyle.ComponentPlacement;

import Bean.NhanKhauBean;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class NhanKhauManagePanel extends javax.swing.JPanel {
    private NhanKhauManagerPanelController controller = null;
    private JFrame parentJFrame;
    
    public NhanKhauManagePanel(JFrame parentFrame) {
        this.parentJFrame = parentFrame;
        initComponents();
        controller = new NhanKhauManagerPanelController(tablePanel, jtfSearch, changeBtn);
        controller.setParentJFrame(parentJFrame);
        controller.setDataTable();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tamVangBtn = new javax.swing.JButton();
        tablePanel = new javax.swing.JPanel();
        addNewBtn = new javax.swing.JButton();
        changeBtn = new javax.swing.JButton();
        tamTruBtn = new javax.swing.JButton();
        khaiTuBtn = new javax.swing.JButton();
        jtfSearch = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        tamVangBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tamVangBtn.setText("ĐK tạm vắng");
        tamVangBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tamVangBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout tablePanelLayout = new javax.swing.GroupLayout(tablePanel);
        tablePanel.setLayout(tablePanelLayout);
        tablePanelLayout.setHorizontalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        tablePanelLayout.setVerticalGroup(
            tablePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 389, Short.MAX_VALUE)
        );

        addNewBtn.setBackground(new java.awt.Color(255, 255, 255));
        addNewBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        addNewBtn.setText("Thêm mới");
        addNewBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addNewBtnActionPerformed(evt);
            }
        });

        tamTruBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        tamTruBtn.setText("ĐK tạm trú");
        tamTruBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tamTruBtnActionPerformed(evt);
            }
        });

        khaiTuBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        khaiTuBtn.setText("Khai tử");
        khaiTuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                khaiTuBtnActionPerformed(evt);
            }
        });

        jtfSearch.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        jtfSearch.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jtfSearchActionPerformed(evt);
            }
        });
        
        changeBtn.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ChangeBtnActionPerformed(e);
        	}
        });
        changeBtn.setText("Chỉnh sửa");
        changeBtn.setFont(new Font("Arial", Font.BOLD, 14));
        changeBtn.setBackground(Color.WHITE);
        changeBtn.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        layout.setHorizontalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(jtfSearch, GroupLayout.PREFERRED_SIZE, 273, GroupLayout.PREFERRED_SIZE)
        					.addGap(0, 29, Short.MAX_VALUE))
        				.addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, 302, Short.MAX_VALUE))
        			.addGap(18)
        			.addGroup(layout.createParallelGroup(Alignment.TRAILING)
        				.addGroup(layout.createParallelGroup(Alignment.LEADING)
        					.addComponent(addNewBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
        					.addComponent(tamTruBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
        					.addComponent(tamVangBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
        					.addComponent(khaiTuBtn, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
        				.addComponent(changeBtn, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE))
        			.addContainerGap())
        );
        layout.setVerticalGroup(
        	layout.createParallelGroup(Alignment.LEADING)
        		.addGroup(layout.createSequentialGroup()
        			.addGap(40)
        			.addComponent(jtfSearch, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        			.addPreferredGap(ComponentPlacement.RELATED)
        			.addGroup(layout.createParallelGroup(Alignment.LEADING)
        				.addComponent(tablePanel, GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        				.addGroup(layout.createSequentialGroup()
        					.addComponent(addNewBtn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(tamVangBtn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(tamTruBtn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        					.addGap(18)
        					.addComponent(khaiTuBtn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        					.addPreferredGap(ComponentPlacement.UNRELATED)
        					.addComponent(changeBtn, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
        					.addContainerGap())))
        );
        this.setLayout(layout);
    }// </editor-fold>//GEN-END:initComponents

    private void addNewBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addNewBtnActionPerformed
        AddNewPeopleJFrame addNewPeopleJFrame = new AddNewPeopleJFrame(this.controller, this.parentJFrame);
        addNewPeopleJFrame.setLocationRelativeTo(null);
        addNewPeopleJFrame.setResizable(false);
        addNewPeopleJFrame.setVisible(true);
    }//GEN-LAST:event_addNewBtnActionPerformed

    private void ChangeBtnActionPerformed(java.awt.event.ActionEvent evt) {
    	NhanKhauBean info = controller.getInfo();
        PeopleChangeJFrame peopleChangeJFrame = new PeopleChangeJFrame(this.controller, this.parentJFrame, info);
        peopleChangeJFrame.setLocationRelativeTo(null);
        peopleChangeJFrame.setResizable(false);
        peopleChangeJFrame.setVisible(true);
    }//GEN-LAST:event_addNewBtnActionPerformed
    
    private void tamVangBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tamVangBtnActionPerformed
        // hien jframe dang ky tam vang
        DangKyTamVangJFrame dangKyTamVangJFrame = new DangKyTamVangJFrame(this.parentJFrame);
        dangKyTamVangJFrame.setLocationRelativeTo(null);
        dangKyTamVangJFrame.setResizable(false);
        dangKyTamVangJFrame.setVisible(true);
    }//GEN-LAST:event_tamVangBtnActionPerformed

    private void tamTruBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tamTruBtnActionPerformed
        DangKyTamTruJFrame dangKyTamTruJFrame = new DangKyTamTruJFrame(this.parentJFrame);
        dangKyTamTruJFrame.setLocationRelativeTo(null);
        dangKyTamTruJFrame.setResizable(false);
        dangKyTamTruJFrame.setVisible(true);
    }//GEN-LAST:event_tamTruBtnActionPerformed

    private void khaiTuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_khaiTuBtnActionPerformed
        KhaiTuJFrame khaiTuJFrame = new KhaiTuJFrame(this.parentJFrame);
        khaiTuJFrame.setLocationRelativeTo(null);
        khaiTuJFrame.setResizable(false);
        khaiTuJFrame.setVisible(true);
    }//GEN-LAST:event_khaiTuBtnActionPerformed

    private void jtfSearchActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jtfSearchActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jtfSearchActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addNewBtn;
    private javax.swing.JButton changeBtn;
    private javax.swing.JTextField jtfSearch;
    private javax.swing.JButton khaiTuBtn;
    private javax.swing.JPanel tablePanel;
    private javax.swing.JButton tamTruBtn;
    private javax.swing.JButton tamVangBtn;
}
