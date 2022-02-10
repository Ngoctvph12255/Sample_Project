/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Dao_Interfaces.NhanVienInterface;
import Dao_implements.NhanVienImpl;
import Util.loginInfomation;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.NhanVien;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dell
 */
public class NhanVienJlFrame extends javax.swing.JInternalFrame {

    public NhanVienInterface nvDao;
    List<NhanVien> listNV = new ArrayList<>();
    int index;
    String manv;
    boolean vaitro;

    /**
     * Creates new form NhanVienJInternalFrame
     */
    public NhanVienJlFrame(boolean vaitro, String manv) {
        initComponents();
        this.nvDao = new NhanVienImpl();
        this.vaitro = vaitro;
        this.manv = manv;
        fillTable();
        setStatus(true);
        // Không cho chỉnh sửa trên table
        tblNhanVien.setDefaultEditor(Object.class, null);

    }

    // Đổ dữ liệu vào tblHocVien
    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);

        try {

            listNV = nvDao.selectALL();
            for (NhanVien x : listNV) {
                model.addRow(new Object[] { x.getMaNV(), x.getMatKhau(), x.getHoTen(),
                        x.isVaiTro() ? "Trưởng phòng" : "Nhân viên" });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // [btnThem]
    public void insert() {

        String mk2 = txtMK2.getText();
        NhanVien nv = getForm();
        if (!mk2.equals(nv.getMatKhau())) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới không trùng nhau !");
        } else {
            try {
                nvDao.insert(nv);
                fillTable();
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công");
                clear();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại");
            }
        }

    }

    // [btnsua]
    public void update() {
        String mk2 = txtMK2.getText();
        NhanVien nv = getForm();
        if (!mk2.equals(nv.getMatKhau())) {
            JOptionPane.showMessageDialog(this, "Mật khẩu mới không trùng nhau !");
        } else {
            try {
                nvDao.update(nv);
                fillTable();
                JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thành công");
                clear();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Cập nhật nhân viên thất bại");
            }
        }
    }

    // [btnXoa]
    public void delete() {

        if (!loginInfomation.isManager()) {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền xóa nhân viên này!");
        } else {
            int choose = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa không?", "Xóa", JOptionPane.YES_NO_OPTION,
                    JOptionPane.YES_NO_OPTION);
            String manv = txtMaNV.getText();
            try {
                if (choose == JOptionPane.YES_OPTION) {
                    nvDao.delete(manv);
                    fillTable();
                    this.clear();
                    JOptionPane.showMessageDialog(this, "Xóa thành công");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
            }
        }

    }

    // Hiển thi NhanVienleen form
    public void setForm(NhanVien nv) {
        txtMaNV.setText(nv.getMaNV());
        txtHVT.setText(nv.getHoTen());
        txtMK.setText(nv.getMatKhau());
        txtMK2.setText(nv.getMatKhau());

        rdoTP.setSelected(nv.isVaiTro());
        rdoNV.setSelected(!nv.isVaiTro());
    }

    // Lấy thông tin của form
    private NhanVien getForm() {
        NhanVien nv = new NhanVien();
        nv.setMaNV(txtMaNV.getText());
        nv.setMatKhau(txtMK.getText());
        nv.setHoTen(txtHVT.getText());
        nv.setVaiTro(rdoTP.isSelected());
        return nv;

    }

    // [btnClear]
    public void clear() {
        this.setForm(new NhanVien());
        this.index = -1;
        this.setStatus(true);
    }

    // [tblHocVien double click]
    public void edit() {
        try {
            String manv = (String) tblNhanVien.getValueAt(index, 0);
            NhanVien nv = nvDao.selectById(manv);
            if (manv != null) {
                this.setForm(nv);
                tabs.setSelectedIndex(0);
                setStatus(false);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Lỗi");
        }
    }

    private boolean CheckInsert() {
        if (txtMaNV.getText().equals("")
                || txtHVT.getText().equals("")
                || txtMK.getPassword().equals("")
                || txtMK2.getPassword().equals("")) {
            JOptionPane.showMessageDialog(this, "Không được để trống dữ liệu");
            return false;
        }
        if (nvDao.selectById(txtMaNV.getText()) != null) {
            JOptionPane.showMessageDialog(this, "Mã sinh viên đã tồn tại");
            return false;
        }
        if (txtMK.getText().length() < 3) {
            JOptionPane.showMessageDialog(this, "Mật khẩu phải lớn hơn 3 kí tự");
            return false;
        }
        if (!txtHVT.getText().matches("[a-zA-Z][a-zA-Z ]+")) {
            JOptionPane.showMessageDialog(this, "Tên không được chứa kí tự đặc biệt");
            return false;
        }
        if (!rdoTP.isSelected() && !rdoNV.isSelected()) {
            JOptionPane.showMessageDialog(this, "Bạn chưa chọn chức vụ");
            return false;
        }
        return true;
    }

    private boolean CheckUpdate() {
        return true;
    }

    // [btnFirst]
    public void first() {
        index = 0;
        this.edit();
    }

    // [btnPre]
    public void prev() {
        if (index > 0) {
            index--;
            this.edit();

        }
    }

    // [btnLast]
    public void last() {
        index = tblNhanVien.getRowCount() - 1;
        this.edit();

    }

    // [btnNext]
    public void next() {
        if (index < tblNhanVien.getRowCount() - 1) {
            index++;
            this.edit();
        }

    }

    // Cập nhât trạng thái các nút
    void setStatus(boolean b) {
        txtMaNV.setEditable(b);
        btnThem.setEnabled(b);
        btnSua.setEnabled(!b);
        btnXoa.setEnabled(!b);

        boolean first = this.index > 0;
        boolean last = this.index < tblNhanVien.getRowCount() - 1;
        btnFirst.setEnabled(!b && first);
        btnFrev.setEnabled(!b && first);
        btnNext.setEnabled(!b && last);
        btnLast.setEnabled(!b && last);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        tabs = new javax.swing.JTabbedPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtHVT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        rdoTP = new javax.swing.JRadioButton();
        rdoNV = new javax.swing.JRadioButton();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnMoi = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnFrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        txtMK = new javax.swing.JPasswordField();
        txtMK2 = new javax.swing.JPasswordField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Quản Lý Nhân Viên");

        jLabel2.setText("Mã nhân viên");

        txtMaNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaNVActionPerformed(evt);
            }
        });

        jLabel3.setText("Mật khẩu");

        jLabel4.setText("Xác nhận mật khẩu");

        jLabel5.setText("Họ và tên");

        txtHVT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtHVTActionPerformed(evt);
            }
        });

        jLabel6.setText("Vai trò");

        buttonGroup1.add(rdoTP);
        rdoTP.setText("Trưởng phòng");

        buttonGroup1.add(rdoNV);
        rdoNV.setText("Nhân viên");

        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnMoi.setText("Mới");
        btnMoi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoiActionPerformed(evt);
            }
        });

        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnFrev.setText("<<");
        btnFrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFrevActionPerformed(evt);
            }
        });

        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnLast.setText("|>");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addGroup(jPanel4Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                .addComponent(jLabel2)
                                                .addComponent(jLabel3)
                                                .addComponent(txtMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, 292,
                                                        Short.MAX_VALUE)
                                                .addComponent(jLabel4)
                                                .addComponent(jLabel5)
                                                .addComponent(txtHVT, javax.swing.GroupLayout.DEFAULT_SIZE, 292,
                                                        Short.MAX_VALUE)
                                                .addComponent(txtMK)
                                                .addComponent(txtMK2))
                                        .addGroup(jPanel4Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                        .addComponent(rdoTP)
                                                        .addGap(10, 10, 10)
                                                        .addComponent(rdoNV)
                                                        .addGap(230, 230, 230))
                                                .addGroup(jPanel4Layout.createSequentialGroup()
                                                        .addComponent(btnThem)
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btnMoi, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20,
                                                                Short.MAX_VALUE)
                                                        .addComponent(btnFirst)
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btnFrev)
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(btnNext)
                                                        .addPreferredGap(
                                                                javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(btnLast))))
                                .addContainerGap(22, Short.MAX_VALUE)));
        jPanel4Layout.setVerticalGroup(
                jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGap(13, 13, 13)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addGap(11, 11, 11)
                                .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMK2, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(13, 13, 13)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtHVT, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(rdoTP)
                                        .addComponent(rdoNV))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING,
                                                javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(jPanel4Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(btnThem)
                                                .addComponent(btnSua)
                                                .addComponent(btnMoi)
                                                .addComponent(btnFirst)
                                                .addComponent(btnFrev)
                                                .addComponent(btnNext)
                                                .addComponent(btnLast)))
                                .addContainerGap(67, Short.MAX_VALUE)));

        tabs.addTab("Cập nhật", jPanel4);

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Mã NV", "Mật Khẩu", "Họ Và Tên", "Vai Trò"
                }));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                                .addContainerGap()));
        jPanel7Layout.setVerticalGroup(
                jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel7Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 356,
                                        Short.MAX_VALUE)));

        tabs.addTab("Danh Sách", jPanel7);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Quản Lý Nhân Viên Quản Trị");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(tabs)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 395,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(20, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtMaNVActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtMaNVActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtMaNVActionPerformed

    private void txtHVTActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtHVTActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtHVTActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSuaActionPerformed
        if (CheckUpdate()) {
            update();
        }

    }// GEN-LAST:event_btnSuaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThemActionPerformed
        if (CheckInsert()) {
            insert();
        }

    }// GEN-LAST:event_btnThemActionPerformed

    private void btnMoiActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnMoiActionPerformed
        clear();
    }// GEN-LAST:event_btnMoiActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnXoaActionPerformed
        if (loginInfomation.isManager()) {
            delete();
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không được phép xóa nhân viên");
        }

    }// GEN-LAST:event_btnXoaActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnFirstActionPerformed
        first();
    }// GEN-LAST:event_btnFirstActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNextActionPerformed
        next();
    }// GEN-LAST:event_btnNextActionPerformed

    private void btnFrevActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnFrevActionPerformed
        prev();
    }// GEN-LAST:event_btnFrevActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLastActionPerformed
        last();
    }// GEN-LAST:event_btnLastActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblNhanVienMouseClicked
        if (evt.getClickCount() == 2) {
            index = tblNhanVien.rowAtPoint(evt.getPoint());
            if (index >= 0) {
                edit();
                tabs.setSelectedIndex(0);
            }
        }
    }// GEN-LAST:event_tblNhanVienMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnFrev;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnMoi;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rdoNV;
    private javax.swing.JRadioButton rdoTP;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtHVT;
    private javax.swing.JPasswordField txtMK;
    private javax.swing.JPasswordField txtMK2;
    private javax.swing.JTextField txtMaNV;
    // End of variables declaration//GEN-END:variables
}
