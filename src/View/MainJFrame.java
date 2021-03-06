/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import JDBC.DateHelper;
import JDBC.XImage;
import Util.loginInfomation;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author NgocTV
 */
public class MainJFrame extends javax.swing.JFrame {

    
    
    String manv;
    boolean vaitro;
    NguoiHocJFrame nguoiHoc;
    ChuyenDelFrame chuyenDe;
    KhoaHocJFrame khoaHoc;
    HocVienJFrame hv;
    ThongKeJFrame tk;
    DoiMatKhauJFrame doiMatKhau;
    NhanVienJlFrame nhanVien;

    public MainJFrame() {
        initComponents();
        setIconImage(XImage.getAppIcon());
        dongHo();
        init();
        lblUser.setText("Xin chào:" + " " + manv);

    }

    public void dongHo() {
        Thread t1 = new Thread() {
            @Override
            public void run() {
                while (true) {
                    Calendar ca = new GregorianCalendar();
                    int gio = ca.get(Calendar.HOUR);
                    int phut = ca.get(Calendar.MINUTE);
                    int giay = ca.get(Calendar.SECOND);
                    int am_pm = ca.get(Calendar.AM_PM);

                    String day;
                    if (am_pm == 1) {
                        day = "PM";

                    } else {
                        day = "AM";
                    }
                    String time = gio + ":" + phut + ":" + giay + " " + day;
                    lblDongho.setText(time);
                }
            }

        };
        t1.start();
    }

    public void init() {
        this.setLocationRelativeTo(null);
        new CuaSoChaoJDialog(this, true).setVisible(true);
        this.opentdangNhap();
    }

    void opentdangNhap() {
        //Mở cửa sổ đăng nhập
       DangNhapJDiaLog dn = new DangNhapJDiaLog(this, true);
        dn.setVisible(true);
        this.vaitro = dn.vaitro;
        this.manv = dn.manv;
        lblUser.setText("Xin chào:" + " " + manv);
    }

    void openNhanVien() {
        if (loginInfomation.authenticated()) {
            nhanVien = new NhanVienJlFrame(vaitro, manv);
            destop.add(nhanVien).setVisible(true);
        }
    }

    void opentNguoiHoc() {
        //Kiểm tra đăng nhập
        if (loginInfomation.authenticated()) {
            nguoiHoc = new NguoiHocJFrame(this.vaitro, this.manv);
            destop.add(nguoiHoc);

            nguoiHoc.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng đăng nhập");
        }
    }

    void OpenChuyenDe() {
        if (loginInfomation.authenticated()) {
            chuyenDe = new ChuyenDelFrame(this.vaitro);
            destop.add(chuyenDe);

            chuyenDe.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng đăng nhập");
        }
    }

    void openKhoaHoc() {
        if (loginInfomation.authenticated()) {
            khoaHoc = new KhoaHocJFrame(vaitro, manv);
            destop.add(khoaHoc);
            khoaHoc.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng đăng nhập");
        }
    }

    void openDoiMatKhau() {
        if(loginInfomation.authenticated()){
            doiMatKhau = new DoiMatKhauJFrame();
            destop.add(doiMatKhau);
            doiMatKhau.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "vui lòng đăng nhập!");
        }
    }

    void logoff() {
        loginInfomation.logOff();
        JOptionPane.showMessageDialog(this, "Đã đăng xuất");
        opentdangNhap();
    }

    void exit() {
        int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thoát ?", "Thoát",
                JOptionPane.YES_NO_OPTION, JOptionPane.YES_NO_OPTION);
        if (chon == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }


    void openThongKe(int index) {
        if (loginInfomation.authenticated()) {
            tk = new ThongKeJFrame(index, this.vaitro);
            destop.add(tk);
            tk.setVisible(true);

        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng đăng nhập");
        }
    }

    void openHocVien() {
        if (loginInfomation.authenticated()) {
            hv = new HocVienJFrame();
            destop.add(hv).setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Vui lòng đăng nhập");
        }
    }
    void openAbout(){ }
    void openWebsite() throws URISyntaxException, IOException {
        Desktop.getDesktop().browse(getClass().getResource("/JDBC/index.html").toURI());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btnDangXuat = new javax.swing.JButton();
        btnKetThuc = new javax.swing.JButton();
        btnChuyende = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        lblTrangThai = new javax.swing.JLabel();
        lblDongho = new javax.swing.JLabel();
        destop = new javax.swing.JDesktopPane();
        lblUser = new javax.swing.JLabel();
        menu = new javax.swing.JMenuBar();
        mnuHeThong = new javax.swing.JMenu();
        mniDangNhap = new javax.swing.JMenuItem();
        mniDangXuat = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        mniDoiMatKhau = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mniKetThuc = new javax.swing.JMenuItem();
        mnuQuanLy = new javax.swing.JMenu();
        mniChuyenDe = new javax.swing.JMenuItem();
        mniKhoaHoc = new javax.swing.JMenuItem();
        mniNguoiHoc = new javax.swing.JMenuItem();
        mniHocVien = new javax.swing.JMenuItem();
        mniNhanVien = new javax.swing.JMenuItem();
        mnuThongKe = new javax.swing.JMenu();
        mniLuongNguoiHoc = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        mniBangdiem = new javax.swing.JMenuItem();
        mniDiemChuyenDe = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        mniDoanhThu = new javax.swing.JMenuItem();
        mnuTroGiup = new javax.swing.JMenu();
        mniHuongDan = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        mniGioiThieu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Hệ Thống Quản Lý Đào Tạo");

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setRollover(true);

        btnDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout.png"))); // NOI18N
        btnDangXuat.setText("Đăng xuất");
        btnDangXuat.setFocusable(false);
        btnDangXuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDangXuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDangXuatActionPerformed(evt);
            }
        });
        jToolBar1.add(btnDangXuat);

        btnKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Stop.png"))); // NOI18N
        btnKetThuc.setText("Kết thúc");
        btnKetThuc.setFocusable(false);
        btnKetThuc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnKetThuc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKetThucActionPerformed(evt);
            }
        });
        jToolBar1.add(btnKetThuc);

        btnChuyende.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Properties.png"))); // NOI18N
        btnChuyende.setText("Chuyên đề");
        btnChuyende.setFocusable(false);
        btnChuyende.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnChuyende.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btnChuyende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChuyendeActionPerformed(evt);
            }
        });
        jToolBar1.add(btnChuyende);

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Conference.png"))); // NOI18N
        jButton4.setText("Người học");
        jButton4.setFocusable(false);
        jButton4.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton4.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton4);

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Certificate.png"))); // NOI18N
        jButton5.setText("Khóa học");
        jButton5.setFocusable(false);
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton5);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Help.png"))); // NOI18N
        jButton6.setText("Hướng dẫn");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jToolBar1.add(jButton6);

        lblTrangThai.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Info.png"))); // NOI18N
        lblTrangThai.setText("Hệ quản lý đào tạo");

        lblDongho.setForeground(new java.awt.Color(51, 51, 51));
        lblDongho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Alarm.png"))); // NOI18N

        javax.swing.GroupLayout destopLayout = new javax.swing.GroupLayout(destop);
        destop.setLayout(destopLayout);
        destopLayout.setHorizontalGroup(
            destopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        destopLayout.setVerticalGroup(
            destopLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 516, Short.MAX_VALUE)
        );

        lblUser.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        lblUser.setForeground(new java.awt.Color(255, 0, 51));

        mnuHeThong.setText("Hệ Thống");

        mniDangNhap.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniDangNhap.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Key.png"))); // NOI18N
        mniDangNhap.setText("Đăng Nhập");
        mniDangNhap.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangNhapActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniDangNhap);

        mniDangXuat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniDangXuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/logout.png"))); // NOI18N
        mniDangXuat.setText("Đăng Xuất");
        mniDangXuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDangXuatActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniDangXuat);
        mnuHeThong.add(jSeparator2);

        mniDoiMatKhau.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Refresh.png"))); // NOI18N
        mniDoiMatKhau.setText("Đổi Mật Khẩu");
        mniDoiMatKhau.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDoiMatKhauActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniDoiMatKhau);
        mnuHeThong.add(jSeparator3);

        mniKetThuc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        mniKetThuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Stop.png"))); // NOI18N
        mniKetThuc.setText("Kết Thúc");
        mniKetThuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniKetThucActionPerformed(evt);
            }
        });
        mnuHeThong.add(mniKetThuc);

        menu.add(mnuHeThong);

        mnuQuanLy.setText("Quản Lý");

        mniChuyenDe.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniChuyenDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Properties.png"))); // NOI18N
        mniChuyenDe.setText("Chuyên Đề");
        mniChuyenDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniChuyenDeActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniChuyenDe);

        mniKhoaHoc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniKhoaHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Certificate.png"))); // NOI18N
        mniKhoaHoc.setText("Khóa Học");
        mnuQuanLy.add(mniKhoaHoc);

        mniNguoiHoc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniNguoiHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Conference.png"))); // NOI18N
        mniNguoiHoc.setText("Người Học");
        mniNguoiHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniNguoiHocActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniNguoiHoc);

        mniHocVien.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniHocVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/User.png"))); // NOI18N
        mniHocVien.setText("Học Viên");
        mniHocVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniHocVienActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniHocVien);

        mniNhanVien.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mniNhanVien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/User group.png"))); // NOI18N
        mniNhanVien.setText("Nhân Viên");
        mniNhanVien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniNhanVienActionPerformed(evt);
            }
        });
        mnuQuanLy.add(mniNhanVien);

        menu.add(mnuQuanLy);

        mnuThongKe.setText("Thống Kê");

        mniLuongNguoiHoc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        mniLuongNguoiHoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Clien list.png"))); // NOI18N
        mniLuongNguoiHoc.setText("Người học từng năm");
        mniLuongNguoiHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniLuongNguoiHocActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniLuongNguoiHoc);
        mnuThongKe.add(jSeparator4);

        mniBangdiem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        mniBangdiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Card file.png"))); // NOI18N
        mniBangdiem.setText("Bảng điểm khóa");
        mniBangdiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniBangdiemActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniBangdiem);

        mniDiemChuyenDe.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        mniDiemChuyenDe.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Bar chart.png"))); // NOI18N
        mniDiemChuyenDe.setText("Điểm từng khóa học");
        mniDiemChuyenDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDiemChuyenDeActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniDiemChuyenDe);
        mnuThongKe.add(jSeparator5);

        mniDoanhThu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        mniDoanhThu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Dollar.png"))); // NOI18N
        mniDoanhThu.setText("Doanh thu chuyên đề");
        mniDoanhThu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniDoanhThuActionPerformed(evt);
            }
        });
        mnuThongKe.add(mniDoanhThu);

        menu.add(mnuThongKe);

        mnuTroGiup.setText("Trợ Giúp");

        mniHuongDan.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        mniHuongDan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Help.png"))); // NOI18N
        mniHuongDan.setText("Hướng dẫn sử dụng");
        mniHuongDan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniHuongDanActionPerformed(evt);
            }
        });
        mnuTroGiup.add(mniHuongDan);
        mnuTroGiup.add(jSeparator6);

        mniGioiThieu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, 0));
        mniGioiThieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Brick house.png"))); // NOI18N
        mniGioiThieu.setText("Giới thiệu sản phẩm");
        mniGioiThieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mniGioiThieuActionPerformed(evt);
            }
        });
        mnuTroGiup.add(mniGioiThieu);

        menu.add(mnuTroGiup);

        setJMenuBar(menu);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 773, Short.MAX_VALUE)
            .addComponent(jSeparator1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblTrangThai)
                .addGap(31, 31, 31)
                .addComponent(lblUser, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDongho, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21))
            .addComponent(destop)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(destop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblTrangThai, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblDongho, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lblUser, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void mniKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniKetThucActionPerformed
        exit();
    }//GEN-LAST:event_mniKetThucActionPerformed

    private void mniChuyenDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniChuyenDeActionPerformed
        OpenChuyenDe();
    }//GEN-LAST:event_mniChuyenDeActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        opentNguoiHoc();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void btnChuyendeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChuyendeActionPerformed

        OpenChuyenDe();
    }//GEN-LAST:event_btnChuyendeActionPerformed

    private void mniNhanVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniNhanVienActionPerformed
      
        openNhanVien();
    }//GEN-LAST:event_mniNhanVienActionPerformed

    private void mniDoiMatKhauActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDoiMatKhauActionPerformed

        openDoiMatKhau();
    }//GEN-LAST:event_mniDoiMatKhauActionPerformed

    private void mniNguoiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniNguoiHocActionPerformed

        opentNguoiHoc();
    }//GEN-LAST:event_mniNguoiHocActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        openKhoaHoc();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void mniHocVienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHocVienActionPerformed
       openHocVien();
    }//GEN-LAST:event_mniHocVienActionPerformed

    private void mniDangNhapActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangNhapActionPerformed
        opentdangNhap();
    }//GEN-LAST:event_mniDangNhapActionPerformed

    private void btnKetThucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKetThucActionPerformed
        exit();
    }//GEN-LAST:event_btnKetThucActionPerformed

    private void btnDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDangXuatActionPerformed
        logoff();
    }//GEN-LAST:event_btnDangXuatActionPerformed

    private void mniLuongNguoiHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniLuongNguoiHocActionPerformed
        openThongKe(1);
    }//GEN-LAST:event_mniLuongNguoiHocActionPerformed

    private void mniDangXuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDangXuatActionPerformed
        logoff();

    }//GEN-LAST:event_mniDangXuatActionPerformed

    private void mniBangdiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniBangdiemActionPerformed
        openThongKe(0);
    }//GEN-LAST:event_mniBangdiemActionPerformed

    private void mniDiemChuyenDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDiemChuyenDeActionPerformed
        openThongKe(2);
    }//GEN-LAST:event_mniDiemChuyenDeActionPerformed

    private void mniDoanhThuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniDoanhThuActionPerformed
        openThongKe(3);
    }//GEN-LAST:event_mniDoanhThuActionPerformed

    private void mniGioiThieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniGioiThieuActionPerformed
       openAbout();
    }//GEN-LAST:event_mniGioiThieuActionPerformed

    private void mniHuongDanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mniHuongDanActionPerformed
        try {
            openWebsite();
        } catch (URISyntaxException ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_mniHuongDanActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
          try {
            openWebsite();
        } catch (URISyntaxException ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(MainJFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChuyende;
    private javax.swing.JButton btnDangXuat;
    private javax.swing.JButton btnKetThuc;
    private javax.swing.JDesktopPane destop;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblDongho;
    private javax.swing.JLabel lblTrangThai;
    private javax.swing.JLabel lblUser;
    private javax.swing.JMenuBar menu;
    private javax.swing.JMenuItem mniBangdiem;
    private javax.swing.JMenuItem mniChuyenDe;
    private javax.swing.JMenuItem mniDangNhap;
    private javax.swing.JMenuItem mniDangXuat;
    private javax.swing.JMenuItem mniDiemChuyenDe;
    private javax.swing.JMenuItem mniDoanhThu;
    private javax.swing.JMenuItem mniDoiMatKhau;
    private javax.swing.JMenuItem mniGioiThieu;
    private javax.swing.JMenuItem mniHocVien;
    private javax.swing.JMenuItem mniHuongDan;
    private javax.swing.JMenuItem mniKetThuc;
    private javax.swing.JMenuItem mniKhoaHoc;
    private javax.swing.JMenuItem mniLuongNguoiHoc;
    private javax.swing.JMenuItem mniNguoiHoc;
    private javax.swing.JMenuItem mniNhanVien;
    private javax.swing.JMenu mnuHeThong;
    private javax.swing.JMenu mnuQuanLy;
    private javax.swing.JMenu mnuThongKe;
    private javax.swing.JMenu mnuTroGiup;
    // End of variables declaration//GEN-END:variables
}
