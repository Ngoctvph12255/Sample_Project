/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao_implements;

import Dao_Interfaces.KhoaHocInterfaces;
import JDBC.JDBCHeader;
import java.sql.SQLException;
import java.util.List;
import model.KhoaHoc;

/**
 *
 * @author ACER
 */
public class KhoaHocImpl  implements KhoaHocInterfaces{

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
                entity.getMaNV()
            );
            
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
    public void delete(String manv) {}

    @Override
    public List<KhoaHoc> selectALL() {
        String sql = "select * from khoahoc";
        return selectbySQL(sql);
    }

    @Override
    public KhoaHoc find_by_id(Integer makh) {
        String sql = "select * from KhoaHoc where MaKH=?";
        List<KhoaHoc> list = selectbySQL(sql, makh);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<KhoaHoc> selectbySQL(String sql, Object... arg) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
