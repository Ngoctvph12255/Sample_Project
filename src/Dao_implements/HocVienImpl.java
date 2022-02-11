/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao_implements;

import Dao_Interfaces.HocVienInterface;
import Dao_Interfaces.KhoaHocInterfaces;
import JDBC.JDBCHeader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.HocVien;
import model.KhoaHoc;

/**
 *
 * @author NgocTV
 */
public class HocVienImpl implements HocVienInterface {

    @Override
    public void insert(HocVien entity) {
      String sql = "insert into HocVien(MaKH, MaNH, Diem) values (?, ?, ?)";
        try {
            JDBC.JDBCHeader.update(sql,
                    entity.getMaKH(),
                    entity.getMaNH(),
                    entity.getDiem());
        } catch (SQLException ex) {
            Logger.getLogger(HocVienImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(HocVien entity) {
       String sql = "update HocVien set MaKH=?, MaNH=?, Diem=? where MaHV=?";
        try {
            JDBC.JDBCHeader.update(sql,
                    entity.getMaKH(),
                    entity.getMaNH(),
                    entity.getDiem(),
                    entity.getMaHV());
        } catch (SQLException ex) {
            Logger.getLogger(HocVienImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void delete(Integer key) {
       String sql = "delete from HocVien where MaHV=?";
        try {
            JDBC.JDBCHeader.update(sql, key);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<HocVien> selectALL() {
        String sql = "select * from hocvien";
        return selectBySQL(sql);
    }

    @Override
    public List<HocVien> selectByKhoaHoc(int makh) {
        String sql = "select * from hocvien where makh=?";
        return selectBySQL(sql, makh);
    }
    
    @Override
    public HocVien selectById(String key) {
        List<HocVien> listNH = selectBySQL("select * from HocVien where MaHV=?", key);
        if (listNH.isEmpty()) {
            return null;
        }
        return listNH.get(0);
    }
    
    @Override
    public HocVien findById(Integer mahv) {
        String sql = "select * from HocVien where MaHV=?";
        List<HocVien> list = selectBySQL(sql, mahv);
        return list.size() > 0 ? list.get(0) : null;
    }

    @Override
    public List<HocVien> selectBySQL(String sql, Object... arg) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

}
