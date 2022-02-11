/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Dao_Interfaces.ChuyenDeInterface;
import Dao_Interfaces.KhoaHocInterfaces;
import Dao_implements.ChuyenDeImpl;
import Dao_implements.KhoaHocImpl;
import JDBC.DateHelper;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.ChuyenDe;
import model.KhoaHoc;

/**
 *
 * @author NgocTV
 */
public class KhoaHocJFrame extends javax.swing.JInternalFrame {

    // KhoaHocDAO dao = new KhoaHocDAO();
    private KhoaHocInterfaces khDao;
    private ChuyenDeInterface cdDao;
    List<KhoaHoc> listKH = new ArrayList<>();
    int index;
    boolean vaitro;
    String manv;
    List<ChuyenDe> listCD;
    int row;

    /**
     * Creates new form KhoaHocJFrame
     */
    public KhoaHocJFrame(boolean vaitro, String manv) {
        initComponents();
        this.khDao = new KhoaHocImpl();
        this.cdDao = new ChuyenDeImpl();
        this.vaitro = vaitro;
        this.manv = manv;
        fillComboboxChuyeDe();
        setStatus(true);
        tblKHoc.setDefaultEditor(Object.class, null);
        // fillTable();
    }

    void fillComboboxChuyeDe() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbbChuyenDe.getModel();
        model.removeAllElements();
        try {
            listCD = cdDao.selectALL();
            for (ChuyenDe x : listCD) {
                model.addElement(x);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    void chonChuyenDe() {
        ChuyenDe cd = (ChuyenDe) cbbChuyenDe.getSelectedItem();
        txtChuyenDe.setText(cd.getTenCD());
        txtHocPhi.setText(String.valueOf(cd.getHocPhi()));
        txtGhichu.setText(cd.getTenCD());
        txtNgayTao.setText(DateHelper.toString(DateHelper.now(), "dd/MM/yyyy"));
        txtNguoiTao.setText(this.manv);
        txtThoiLuong.setText(String.valueOf(cd.getThoiLuong()));

        fillTable();
        this.row = -1;
        tabs.setSelectedIndex(1);

    }

    void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tblKHoc.getModel();
        model.setRowCount(0);
        try {
            ChuyenDe cd = (ChuyenDe) cbbChuyenDe.getSelectedItem();
            listKH = khDao.selectByChuyenDe(cd.getMaCD());
            for (KhoaHoc x : listKH) {
                model.addRow(new Object[] {
                        x.getMaKH(),
                        x.getThoiLuong(),
                        x.getHocPhi(),
                        DateHelper.toString(x.getNgayKG(), "dd/MM/yyyy"),
                        x.getMaNV(),
                        DateHelper.toString(x.getNgayTao(), "dd/MM/yyyy")
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private KhoaHoc getTextForm() {
        KhoaHoc kh = new KhoaHoc();

        for (ChuyenDe cd : listCD) {
            if (cd.equals(cbbChuyenDe.getSelectedItem())) {
                kh.setMaCD(cd.getMaCD());
            }
        }

        kh.setGhiChu(txtGhichu.getText());
        kh.setThoiLuong(Integer.parseInt(txtThoiLuong.getText()));
        kh.setHocPhi(Double.parseDouble(txtHocPhi.getText()));
        kh.setMaNV(txtNguoiTao.getText());
        kh.setNgayKG(DateHelper.toDate(txtKhaiGiang.getText(), "dd/MM/yyyy"));
        kh.setNgayTao(DateHelper.toDate(txtNgayTao.getText(), "dd/MM/yyyy"));
        kh.setMaKH((int) tblKHoc.getValueAt(index, 0));
        return kh;
    }

    public void insert() {
        KhoaHoc kh = getTextForm();
        try {
            khDao.insert(kh);
            this.fillTable();
            JOptionPane.showMessageDialog(this, "Thêm thành công");
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Thêm thất bại");

        }
    }

    public void update() {
        KhoaHoc kh = getTextForm();
        try {
            khDao.update(kh);
            this.fillTable();
            // this.clear();
            JOptionPane.showMessageDialog(this, "cập nhật thành công");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Cập nhật thất bại");

        }
    }

    public void delete() {
        int chon = JOptionPane.showConfirmDialog(this, "Bạn muốn xóa khóa học này ?", "Xóa?",
                JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            try {
                khDao.delete((Integer) tblKHoc.getValueAt(this.index, 0));
                this.fillTable();

                JOptionPane.showMessageDialog(this, "Xóa thành công");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
            }
        }
    }

    public void setTextForm(KhoaHoc kh) {
        for (ChuyenDe cd : listCD) {
            if (cd.getMaCD().equals(kh.getMaCD())) {
                cbbChuyenDe.setSelectedItem(cd.getTenCD());
                txtChuyenDe.setText(cd.getTenCD());
                txtHocPhi.setText(String.valueOf(kh.getHocPhi()));
                txtNguoiTao.setText(kh.getMaNV());
                txtThoiLuong.setText(String.valueOf(kh.getThoiLuong()));
                txtNgayTao.setText(DateHelper.toString(kh.getNgayTao(), "dd/MM/yyyy"));
                txtKhaiGiang.setText(DateHelper.toString(kh.getNgayKG(), "dd/MM/yyyy"));
                return;
            }
        }

    }

    public void setStatus(boolean insertalbe) {
        btnThem.setEnabled(insertalbe);
        btnSua.setEnabled(!insertalbe);
        btnXoa.setEnabled(!insertalbe && vaitro);

        boolean first = this.index > 0;
        boolean last = this.index < tblKHoc.getRowCount() - 1;
        btnFist.setEnabled(!insertalbe && first);
        btnPrev.setEnabled(!insertalbe && first);
        btnPrev.setEnabled(!insertalbe && last);
        btnLast.setEnabled(!insertalbe && last);
        txtChuyenDe.setEditable(false);
        txtHocPhi.setEditable(false);
        txtThoiLuong.setEditable(false);
        txtNguoiTao.setEditable(false);
        txtNgayTao.setEditable(false);
    }

    private boolean Check() {
        SimpleDateFormat dg = new SimpleDateFormat();
        dg.applyPattern("dd/MM/YYYY");

        try {
            if (txtKhaiGiang.getText().equals("")) {
                JOptionPane.showMessageDialog(this, "Không để trống ngày khai giảng");
                return false;
            } else {
                Date ngay = dg.parse(txtKhaiGiang.getText());
            }
        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Không đúng định dạng ngày");
            return false;
        }
        return true;
    }

    public void edit() {
        int manv = (int) tblKHoc.getValueAt(index, 0);
        KhoaHoc kh = khDao.findById(manv);
        this.setTextForm(kh);
        setStatus(false);
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
        index = tblKHoc.getRowCount() - 1;
        this.edit();

    }

    public void next() {
        if (index < tblKHoc.getRowCount() - 1) {
            index++;
            this.edit();
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

        jPanel1 = new javax.swing.JPanel();
        cbbChuyenDe = new javax.swing.JComboBox<>();
        tabs = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtChuyenDe = new javax.swing.JTextField();
        txtKhaiGiang = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtHocPhi = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtThoiLuong = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNguoiTao = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNgayTao = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtGhichu = new javax.swing.JTextArea();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btnFist = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        btnNext = new javax.swing.JButton();
        btnLast = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblKHoc = new javax.swing.JTable();

        setClosable(true);
        setTitle("Khóa học");

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chuyên Đề",
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION,
                new java.awt.Font("Tahoma", 0, 14))); // NOI18N

        cbbChuyenDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbbChuyenDeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(cbbChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE, 451,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(cbbChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(22, Short.MAX_VALUE)));

        jLabel1.setText("Chuyên đề");

        jLabel2.setText("Khai Giảng");

        txtChuyenDe.setForeground(new java.awt.Color(204, 0, 0));

        jLabel3.setText("Học phí");

        jLabel4.setText("Thời lượng");

        jLabel5.setText("Người tạo");

        jLabel6.setText("Ngày tạo");

        jLabel7.setText("Ghi chú");

        txtGhichu.setColumns(20);
        txtGhichu.setRows(5);
        jScrollPane2.setViewportView(txtGhichu);

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

        jButton5.setText("Mới");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS));

        btnFist.setText("|<");
        btnFist.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFistActionPerformed(evt);
            }
        });
        jPanel4.add(btnFist);

        btnPrev.setText("<<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });
        jPanel4.add(btnPrev);

        btnNext.setText(">>");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });
        jPanel4.add(btnNext);

        btnLast.setText(">|");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });
        jPanel4.add(btnLast);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGroup(jPanel2Layout
                                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jScrollPane2,
                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                        .addGroup(jPanel2Layout.createParallelGroup(
                                                                                javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                false)
                                                                                .addComponent(txtNguoiTao,
                                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                                .addGroup(jPanel2Layout
                                                                                        .createParallelGroup(
                                                                                                javax.swing.GroupLayout.Alignment.LEADING)
                                                                                        .addGroup(jPanel2Layout
                                                                                                .createParallelGroup(
                                                                                                        javax.swing.GroupLayout.Alignment.TRAILING,
                                                                                                        false)
                                                                                                .addComponent(txtHocPhi,
                                                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                                        222,
                                                                                                        Short.MAX_VALUE)
                                                                                                .addComponent(jLabel1,
                                                                                                        javax.swing.GroupLayout.Alignment.LEADING)
                                                                                                .addComponent(
                                                                                                        txtChuyenDe,
                                                                                                        javax.swing.GroupLayout.Alignment.LEADING))
                                                                                        .addComponent(jLabel3)))
                                                                        .addComponent(jLabel5))
                                                                .addPreferredGap(
                                                                        javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                                        javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                        Short.MAX_VALUE)
                                                                .addGroup(jPanel2Layout.createParallelGroup(
                                                                        javax.swing.GroupLayout.Alignment.LEADING,
                                                                        false)
                                                                        .addComponent(jLabel2)
                                                                        .addComponent(txtKhaiGiang,
                                                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                                                220, Short.MAX_VALUE)
                                                                        .addComponent(jLabel4)
                                                                        .addComponent(txtThoiLuong)
                                                                        .addComponent(jLabel6)
                                                                        .addComponent(txtNgayTao))))
                                                .addGap(20, 20, 20))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING,
                                                jPanel2Layout.createSequentialGroup()
                                                        .addComponent(jLabel7)
                                                        .addContainerGap())
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout
                                                .createSequentialGroup()
                                                .addComponent(btnThem)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnSua)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(btnXoa)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jButton5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
                                                        139, Short.MAX_VALUE)
                                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 215,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtChuyenDe, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtKhaiGiang, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtThoiLuong, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtHocPhi, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel5))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(txtNgayTao, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNguoiTao, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 56,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout
                                                .createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                .addComponent(btnThem)
                                                .addComponent(btnSua)
                                                .addComponent(btnXoa)
                                                .addComponent(jButton5))
                                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(95, Short.MAX_VALUE)));

        tabs.addTab("Cập nhật", jPanel2);

        tblKHoc.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null, null, null },
                        { null, null, null, null, null, null },
                        { null, null, null, null, null, null },
                        { null, null, null, null, null, null }
                },
                new String[] {
                        "Mã KH", "Thời lượng", "Học phí", "Ngày KG", "Tạo bởi", "Ngày Tạo"
                }));
        tblKHoc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblKHocMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblKHoc);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
                                .addContainerGap()));
        jPanel3Layout.setVerticalGroup(
                jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 361,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

        tabs.addTab("Danh sách", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(tabs)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap()));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 410,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnNextActionPerformed
        this.next();
    }// GEN-LAST:event_btnNextActionPerformed

    private void cbbChuyenDeActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cbbChuyenDeActionPerformed

        this.chonChuyenDe();
    }// GEN-LAST:event_cbbChuyenDeActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnThemActionPerformed
        if (Check()) {
            this.insert();
        }

    }// GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnSuaActionPerformed
        if (Check()) {
            this.update();
        }

    }// GEN-LAST:event_btnSuaActionPerformed

    private void btnFistActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnFistActionPerformed
        first();
    }// GEN-LAST:event_btnFistActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnLastActionPerformed
        last();

    }// GEN-LAST:event_btnLastActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnPrevActionPerformed
        prev();
    }// GEN-LAST:event_btnPrevActionPerformed

    private void tblKHocMouseClicked(java.awt.event.MouseEvent evt) {// GEN-FIRST:event_tblKHocMouseClicked
        if (evt.getClickCount() == 2) {
            index = tblKHoc.rowAtPoint(evt.getPoint());
            if (index >= 0) {
                edit();
                tabs.setSelectedIndex(0);
            }
        }
    }// GEN-LAST:event_tblKHocMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_jButton5ActionPerformed
        txtKhaiGiang.setText("");
    }// GEN-LAST:event_jButton5ActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnXoaActionPerformed
        delete();
    }// GEN-LAST:event_btnXoaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFist;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbbChuyenDe;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tblKHoc;
    private javax.swing.JTextField txtChuyenDe;
    private javax.swing.JTextArea txtGhichu;
    private javax.swing.JTextField txtHocPhi;
    private javax.swing.JTextField txtKhaiGiang;
    private javax.swing.JTextField txtNgayTao;
    private javax.swing.JTextField txtNguoiTao;
    private javax.swing.JTextField txtThoiLuong;
    // End of variables declaration//GEN-END:variables

}
