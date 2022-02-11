/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao_implements;

import Dao_Interfaces.NguoiHocInterface;
import Dao_Interfaces.NhanVienInterface;
import JDBC.JDBCHeader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.NguoiHoc;

/**
 *
 * @author NgocTV
 */
public class NguoiHocImpl implements NguoiHocInterface {

    @Override
    public void insert(NguoiHoc entity) {
        String sql = "insert into NguoiHoc (MaNH, HoTen, GioiTinh, NgaySinh, DienThoai, Email, GhiChu, MaNV,NgayDK) values (?, ?, ?, ?, ?, ?, ?, ?,GETDATE())";
        try {
            JDBC.JDBCHeader.update(sql,
                    entity.getMaNH(),
                    entity.getTenNH(),
                    entity.isGt(),
                    new java.sql.Date(entity.getNgaySinh().getTime()),
                    entity.getSdt(),
                    entity.getEmail(),
                    entity.getGhiChu(),
                    entity.getMaNV());
        } catch (SQLException ex) {
            Logger.getLogger(NguoiHocImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public void update(NguoiHoc entity) {
        String sql = "update NguoiHoc set HoTen=?, NgaySinh=?, GioiTinh=?, DienThoai=?, Email=?, GhiChu=?, MaNV=? where MaNH=?";
        try {
            JDBCHeader.update(sql, 
                    entity.getTenNH(),
                    entity.getNgaySinh(),
                    entity.isGt(),
                    entity.getSdt(),
                    entity.getEmail(),
                    entity.getGhiChu(), 
                    entity.getMaNV(), 
                    entity.getMaNH());
        } catch (SQLException ex) {
            Logger.getLogger(NguoiHocImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }

    @Override
    public void delete(String key) {
       String sql = "delete from Nguoihoc where manh =?";
        try {
            JDBCHeader.update(sql, key);
        } catch (SQLException ex) {
            Logger.getLogger(NguoiHocImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<NguoiHoc> selectALL() {
       String sql = "select *from NguoiHoc";
        return selectbySQL(sql);
    }

    @Override
    public NguoiHoc selectById(String key) {
        List<NguoiHoc> listNH = selectbySQL("select * from nguoihoc where manh=?", key);
        if (listNH.isEmpty()) {
            return null;
        }
        return listNH.get(0);
    }

    @Override
    public List<NguoiHoc> selectNotInCourse(int makh, String key) {
        String sql = "select * from nguoihoc "
                + " where hoten like ? and "
                + " manh not in(select manh from hocvien where makh=?)";
        return selectbySQL(sql, "%" + key + "%", makh);
    }

    @Override
    public List<NguoiHoc> selectByKeyword(String keyword) {
        String sql = "select * from NguoiHoc where HoTen like ?";
        return selectbySQL(sql, "%" + keyword + "%");
    }

    @Override
    public List<NguoiHoc> selectbySQL(String sql, Object... arg) {
       List<NguoiHoc> listNH = new ArrayList<>();
        try {
            ResultSet rs = JDBCHeader.query(sql, arg);
            while (rs.next()) {
                NguoiHoc nh = new NguoiHoc();
                nh.setMaNH(rs.getString("MaNH"));
                nh.setTenNH(rs.getString("hoten"));
                nh.setGt(rs.getBoolean("gioitinh"));
                nh.setNgaySinh(rs.getDate("ngaysinh"));
                nh.setSdt(rs.getString("dienthoai"));
                nh.setEmail(rs.getString("Email"));
                nh.setGhiChu(rs.getString("Ghichu"));
                nh.setMaNV(rs.getString("manv"));
                nh.setNgayDK(rs.getDate("NgayDK"));
                listNH.add(nh);
            }
            rs.getStatement().getConnection().close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNH;
    }


}
