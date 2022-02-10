/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Dao_Interfaces.ChuyenDeInterface;
import Dao_implements.ChuyenDeImpl;
import JDBC.XImage;
import Util.loginInfomation;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
//import javafx.stage.FileChooser;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChuyenDe;

/**
 *
 * @author NgocTV
 */
public class ChuyenDelFrame extends javax.swing.JInternalFrame {

    private ChuyenDeInterface cdDao;
    List<ChuyenDe> listCD = new ArrayList<>();
    int index;
    boolean vaitro;
    JFileChooser f = new JFileChooser();

    /**
     * Creates new form ChuyenDelFrame
     */
    public ChuyenDelFrame(boolean vaitro) {
        initComponents();
        this.cdDao = new ChuyenDeImpl();
        fillTable();
        this.vaitro = vaitro;
        this.setStatus(true, vaitro);
        tblChuyende.setDefaultEditor(Object.class, null);

    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblChuyende.getModel();
        model.setRowCount(0);
        try {
            listCD = cdDao.selectALL();
            for (ChuyenDe x : listCD) {
                model.addRow(
                        new Object[] { x.getMaCD(), x.getTenCD(), x.getHocPhi(), x.getThoiLuong(), x.getHinhAnh() });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void insert() {
        try {
            ChuyenDe cd = getText();
            cdDao.insert(cd);
            fillTable();
            JOptionPane.showMessageDialog(this, "Thêm chuyên đề thành công");
            clear();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void update() {
        try {
            ChuyenDe cd = getText();
            cdDao.update(cd);
            fillTable();
            JOptionPane.showMessageDialog(this, "Cập nhật chuyên đề thành công");
            clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteData() {
        try {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn xóa ?", "Xóa", JOptionPane.YES_NO_OPTION,
                    JOptionPane.YES_NO_OPTION);
            if (chon == JOptionPane.YES_OPTION) {
                String macd = txtMaCD.getText();
                cdDao.delete(macd);
                fillTable();
                JOptionPane.showMessageDialog(this, "Xóa chuyên đề thành công");
                clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ChuyenDe getText() {
        ChuyenDe cd = new ChuyenDe();
        cd.setMaCD(txtMaCD.getText());
        cd.setTenCD(txtTenCD.getText());
        cd.setHocPhi(Double.parseDouble(txtHocPhi.getText()));
        cd.setThoiLuong(Integer.parseInt(txtThoiLuong.getText()));
        cd.setMoTa(txtMoTa.getText());
        cd.setHinhAnh(lblAnh.getToolTipText());

        return cd;
    }

    public void setText(ChuyenDe cd) {
        txtMaCD.setText(cd.getMaCD());
        txtTenCD.setText(cd.getTenCD());
        txtThoiLuong.setText(String.valueOf(cd.getThoiLuong()));
        txtHocPhi.setText(String.valueOf(cd.getHocPhi()));
        txtMoTa.setText(cd.getMoTa());
        if (cd.getHinhAnh() != null) {
            lblAnh.setToolTipText(cd.getHinhAnh());
            lblAnh.setIcon(XImage.read(cd.getHinhAnh()));
        } else {
            lblAnh.setIcon(null);
        }
    }

    public void setStatus(boolean insertalbe, boolean vaitro) {
        // txtMaCD.setEditable(insertalbe);
        btnInsert.setEnabled(insertalbe);
        btnUpdate.setEnabled(!insertalbe);
        btnDelete.setEnabled(!insertalbe && vaitro);

        boolean first = this.index > 0;
        boolean last = this.index < tblChuyende.getRowCount() - 1;
        btnFirst.setEnabled(!insertalbe && first);
        btnPrev.setEnabled(!insertalbe && first);
        btnNext.setEnabled(!insertalbe && last);
        btnLast.setEnabled(!insertalbe && last);
    }

    public void clear() {
        this.setText(new ChuyenDe());
        this.setStatus(true, vaitro);
    }

    public void edit() {
        String row = (String) tblChuyende.getValueAt(index, 0);
        ChuyenDe cd = cdDao.selectById(row);
        if (cd != null) {
            this.setText(cd);
            this.setStatus(false, vaitro);
        }
    }

    private boolean check() {
        if (txtMaCD.getText().equals("")
                || txtHocPhi.getText().equals("")
                || txtMoTa.getText().equals("")
                || txtTenCD.getText().equals("")
                || txtThoiLuong.getText().equals("")
                || lblAnh.getIcon() == null) {
            JOptionPane.showMessageDialog(this, "Không được để trống dữ liệu");
            return false;
        }

        if (cdDao.selectById(txtMaCD.getText()) != null) {
            JOptionPane.showMessageDialog(this, "Mã đã tồn tại");
            return false;
        }
        if (txtMaCD.getText().length() != 5) {
            JOptionPane.showMessageDialog(this, "Mã Chuyên đề phải 5 kí tự");
            return false;
        }
        try {
            if (Double.parseDouble(txtHocPhi.getText()) < 0) {
                JOptionPane.showMessageDialog(this, "Học phí phải là số dương và lớn hơn 0");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Học phí phải là số");
        }
        try {
            if (Integer.parseInt(txtThoiLuong.getText()) < 0) {
                JOptionPane.showMessageDialog(this, "Thời lượng phải là số dương lớn hơn 0");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thời lượng phải là số");
        }

        return true;
    }

    private boolean checkUpdate() {
        if (txtMaCD.getText().equals("")
                || txtHocPhi.getText().equals("")
                || txtMoTa.getText().equals("")
                || txtTenCD.getText().equals("")
                || txtThoiLuong.getText().equals("")
                || lblAnh.getIcon() == null) { // Check null image
            JOptionPane.showMessageDialog(this, "Không được để trống dữ liệu");
            return false;
        }

        try {
            if (Double.parseDouble(txtHocPhi.getText()) < 0) {
                JOptionPane.showMessageDialog(this, "Học phí phải là số dương và lớn hơn 0");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Học phí phải là số");
        }
        try {
            if (Integer.parseInt(txtThoiLuong.getText()) < 0) {
                JOptionPane.showMessageDialog(this, "Thời lượng phải là số dương lớn hơn 0");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Thời lượng phải là số");
        }
        return true;
    }

    public void first() {
        index = 0;
        this.edit();
    }

    public void prev() {
        if (index > 0) {
            index--;
            this.edit();

        }
    }

    public void last() {
        index = tblChuyende.getRowCount() - 1;
        this.edit();

    }

    public void next() {
        if (index < tblChuyende.getRowCount() - 1) {
            index++;
            this.edit();
        }

    }

    void ChooseImage() {
        if (f.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = f.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            lblAnh.setIcon(icon);
            lblAnh.setToolTipText(file.getName());

        }
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

        jLabel1 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lblAnh = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaCD = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTenCD = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtThoiLuong = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtHocPhi = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtMoTa = new javax.swing.JTextArea();
        btnInsert = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnFirst = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblChuyende = new javax.swing.JTable();

        setClosable(true);
        setTitle("Quản lý chuyên đề");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 0, 0));
        jLabel1.setText("Quản lý chuyên đề");

        jLabel2.setText("Hình Logo");

        lblAnh.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        lblAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblAnhMouseClicked(evt);
            }
        });

        jLabel4.setText("Mã chuyên đề");

        txtMaCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaCDActionPerformed(evt);
            }
        });

        jLabel3.setText("Tên chuyên đề");

        txtTenCD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenCDActionPerformed(evt);
            }
        });

        jLabel5.setText("Thời lượng");

        jLabel6.setText("Học phí");

        jLabel7.setText("Mô tả chuyên đề");

        txtMoTa.setColumns(20);
        txtMoTa.setRows(5);
        jScrollPane2.setViewportView(txtMoTa);

        btnInsert.setText("Thêm");
        btnInsert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInsertActionPerformed(evt);
            }
        });

        btnUpdate.setText("Sửa");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setText("Xóa");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        jButton4.setText("Mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        btnFirst.setText("|<");
        btnFirst.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFirstActionPerformed(evt);
            }
        });

        btnPrev.setText("<<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(lblAnh, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                                142, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(32, 32, 32)
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(txtTenCD)
                                                        .addComponent(txtThoiLuong)
                                                        .addComponent(txtMaCD)
                                                        .addComponent(txtHocPhi)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addGroup(jPanel1Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addComponent(jLabel5)
                                                                        .addComponent(jLabel3)
                                                                        .addComponent(jLabel4)
                                                                        .addComponent(jLabel6))
                                                                .addGap(0, 0, Short.MAX_VALUE))))
                                        .addComponent(jScrollPane2)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGroup(jPanel1Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel7)
                                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                                .addComponent(btnInsert)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnUpdate)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnDelete)
                                                                .addGap(10, 10, 10)
                                                                .addComponent(jButton4)
                                                                .addGap(81, 81, 81)
                                                                .addComponent(btnFirst)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnPrev)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(btnNext)
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                                .addComponent(btnLast)))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap()));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(14, 14, 14)
                                                .addComponent(jLabel2))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING,
                                                jPanel1Layout.createSequentialGroup()
                                                        .addContainerGap()
                                                        .addComponent(jLabel4)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(txtMaCD, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtTenCD, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel5)
                                                .addGap(11, 11, 11)
                                                .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel6)
                                                .addGap(16, 16, 16)
                                                .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(lblAnh, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 61,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnInsert)
                                        .addComponent(btnUpdate)
                                        .addComponent(btnDelete)
                                        .addComponent(jButton4)
                                        .addComponent(btnFirst)
                                        .addComponent(btnPrev)
                                        .addComponent(btnNext)
                                        .addComponent(btnLast))
                                .addContainerGap(43, Short.MAX_VALUE)));

        tabs.addTab("Cập nhật", jPanel1);

        tblChuyende.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {

                },
                new String[] {
                        "Mã CD", "Tên CD", "Học Phí", "Thời lượng", "Hình"
                }));
        tblChuyende.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblChuyendeMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblChuyende);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 543, Short.MAX_VALUE));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        tabs.addTab("Danh sách", jPanel2);

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
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 411,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(27, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblAnhMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_lblAnhMouseClicked

        this.ChooseImage();
    }// GEN-LAST:event_lblAnhMouseClicked

    private void txtMaCDActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtMaCDActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtMaCDActionPerformed

    private void txtTenCDActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_txtTenCDActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_txtTenCDActionPerformed

    private void btnInsertActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnInsertActionPerformed

        if (check()) {
            insert();
        }
    }// GEN-LAST:event_btnInsertActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnUpdateActionPerformed

        if (checkUpdate()) {
            update();
        }
    }// GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnDeleteActionPerformed
        if (loginInfomation.isManager()) {
            deleteData();
        } else {
            JOptionPane.showMessageDialog(this, "Bạn không có quyền xóa");
        }

    }// GEN-LAST:event_btnDeleteActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton4ActionPerformed
        clear();
    }// GEN-LAST:event_jButton4ActionPerformed

    private void btnFirstActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnFirstActionPerformed
        first();
    }// GEN-LAST:event_btnFirstActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNextActionPerformed
        next();
    }// GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnPrevActionPerformed
        prev();
    }// GEN-LAST:event_btnPrevActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLastActionPerformed
        last();
    }// GEN-LAST:event_btnLastActionPerformed

    private void tblChuyendeMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblChuyendeMouseClicked
        if (evt.getClickCount() == 2) {
            this.index = tblChuyende.rowAtPoint(evt.getPoint());
            if (this.index >= 0) {
                this.edit();
                tabs.setSelectedIndex(0);
            }
        }
    }// GEN-LAST:event_tblChuyendeMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnFirst;
    private javax.swing.JButton btnInsert;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAnh;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblChuyende;
    private javax.swing.JTextField txtHocPhi;
    private javax.swing.JTextField txtMaCD;
    private javax.swing.JTextArea txtMoTa;
    private javax.swing.JTextField txtTenCD;
    private javax.swing.JTextField txtThoiLuong;
    // End of variables declaration//GEN-END:variables
}
