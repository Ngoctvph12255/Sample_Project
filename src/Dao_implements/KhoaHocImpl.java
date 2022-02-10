/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao_implements;

import Dao_Interfaces.KhoaHocInterfaces;
import JDBC.JDBCHeader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.KhoaHoc;

/**
 *
 * @author ACER
 */
public class KhoaHocImpl implements KhoaHocInterfaces {

    @Override
    public void insert(KhoaHoc entity) {
        String sql = "insert into khoahoc(MaCD, HocPhi, ThoiLuong, NgayKG, GhiChu, MaNV,NgayTao) values(?,?,?,?,?,?,GETDATE())";
        try {
            java.sql.Date sqlDate = new java.sql.Date(entity.getNgayKG().getTime());
            JDBCHeader.update(
                    sql,
                    entity.getMaCD(),
                    entity.getHocPhi(),
                    entity.getThoiLuong(),
                    sqlDate,
                    entity.getGhiChu(),
                    entity.getMaNV());

            System.out.println("ok");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void update(KhoaHoc model) {
        String sql = "update KhoaHoc set MaCD=?, HocPhi=?, ThoiLuong=?, NgayKG=?, GhiChu=?, MaNV=? where MaKH=?";
        try {
            JDBCHeader.update(sql,
                    model.getMaCD(),
                    model.getHocPhi(),
                    model.getThoiLuong(),
                    new java.sql.Date(model.getNgayKG().getTime()),
                    model.getGhiChu(),
                    model.getMaNV(),
                    model.getMaKH());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(Integer makh) {
        String delete ="delete from khoahoc where makh= ?";
        try {
            JDBCHeader.update(delete, makh);
        } catch (SQLException ex) {
            Logger.getLogger(KhoaHocImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<KhoaHoc> selectALL() {
        String sql = "select * from khoahoc";
        return selectbySQL(sql);
    }

    @Override
    public KhoaHoc findById(Integer makh) {
        String sql = "select * from KhoaHoc where MaKH=?";
        List<KhoaHoc> list = selectbySQL(sql, makh);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<KhoaHoc> selectbySQL(String sql, Object... arg) {
        List<KhoaHoc> listKH = new ArrayList<>();
        try {
            ResultSet rs = JDBCHeader.query(sql, arg);
            while (rs.next()) {
                KhoaHoc kh = new KhoaHoc();
                kh.setMaKH(rs.getInt("MaKH"));
                kh.setMaCD(rs.getString("MaCD"));
                kh.setHocPhi(rs.getDouble("hocphi"));
                kh.setThoiLuong(rs.getInt("thoiluong"));
                kh.setNgayKG(rs.getDate("ngayKG"));
                kh.setGhiChu(rs.getString("ghichu"));
                kh.setMaNV(rs.getString("manv"));
                kh.setNgayTao(rs.getDate("ngaytao"));
                listKH.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listKH;
    }

    @Override
    public List<KhoaHoc> selectByChuyenDe(String macd) {
        String sql = "select * from khoahoc where macd=?";
        return selectbySQL(sql, macd);
    }

    @Override
    public List<Integer> selectYear() {
      String sql ="select distinct year(ngayKG) from khoahoc order by year(NgayKG) desc";
        List<Integer> list= new ArrayList<>();
        try {
            ResultSet rs = JDBCHeader.query(sql);
            while(rs.next()){
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
        } catch (SQLException e) {
           throw new RuntimeException(e);
        }
       return list;
        
    }

}
