/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao_implements;

import Dao_Interfaces.NhanVienInterface;
import JDBC.JDBCHeader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.NhanVien;

/**
 *
 * @author NgocTV
 */
public class NhanVienImpl implements NhanVienInterface {

    String insert_sql = "insert into nhanvien values(?,?,?,?)";
    String select_id_sql = "select * from nhanvien where manv=?";
    String update_sql = "update NhanVien set MatKhau = ?, HoTen = ?, VaiTro = ? where MaNV = ?";
    String select_all = "select * from NhanVien";

    @Override
    public void insert(NhanVien entity) {
        try {
            JDBCHeader.update(insert_sql,
                    entity.getMaNV(),
                    entity.getMatKhau(),
                    entity.getHoTen(),
                    entity.isVaiTro());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void update(NhanVien entity) {
        try {
            JDBCHeader.update(update_sql,
                    entity.getMatKhau(),
                    entity.getHoTen(),
                    entity.isVaiTro(),
                    entity.getMaNV());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(String key) {
        try {
            String deletetData = "delete from nhanvien where manv=?";
            JDBCHeader.update(deletetData, key);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<NhanVien> selectALL() {

        List<NhanVien> listNV = this.selectbySQL(select_all);
        return listNV;
    }

    @Override
    public NhanVien selectById(String id) {
        List<NhanVien> listNV = this.selectbySQL(select_id_sql, id);
        if (listNV.isEmpty()) {
            return null;
        }
        return listNV.get(0);
    }

    @Override
    public List<NhanVien> selectbySQL(String sql, Object... arg) {
        List<NhanVien> listNV = new ArrayList<>();
        try {
            ResultSet rs = JDBCHeader.query(sql, arg);
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MaNV"));
                nv.setHoTen(rs.getString("HoTen"));
                nv.setMatKhau(rs.getString("MatKhau"));
                nv.setVaiTro(rs.getBoolean("VaiTro"));
                listNV.add(nv);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listNV;
    }

}
