/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao_implements;

import Dao_Interfaces.ChuyenDeInterface;
import JDBC.JDBCHeader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ChuyenDe;

/**
 *
 * @author NgocTV
 */
public class ChuyenDeImpl implements ChuyenDeInterface {

    @Override
    public void insert(ChuyenDe entity) {
        String insert = "insert into chuyende values(?,?,?,?,?,?)";
        try {
            JDBCHeader.update(insert,
                    entity.getMaCD(),
                    entity.getTenCD(),
                    entity.getHocPhi(),
                    entity.getThoiLuong(),
                    entity.getHinhAnh(),
                    entity.getMoTa());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    public void update(ChuyenDe entity) {
        String sql = "update ChuyenDe set TenCD=?, HocPhi=?, ThoiLuong=?, Hinh=?, MoTa=? where MaCD=?";
        try {
            JDBCHeader.update(sql,
                    entity.getTenCD(),
                    entity.getHocPhi(),
                    entity.getThoiLuong(),
                    entity.getHinhAnh(),
                    entity.getMoTa(),
                    entity.getMaCD());
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void delete(String key) {
        String deleteData = "delete from chuyende where macd =?";
        try {
            JDBCHeader.update(deleteData, key);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<ChuyenDe> selectALL() {
        String selectCd = "select * from chuyende";
        return this.selectbySQL(selectCd);
    }

    @Override
    public ChuyenDe selectById(String key) {
        List<ChuyenDe> list = this.selectbySQL("select * from chuyende where macd=?", key);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<ChuyenDe> selectbySQL(String sql, Object... arg) {
        List<ChuyenDe> listCD = new ArrayList<>();
        try {
            ResultSet rs = JDBCHeader.query(sql, arg);
            while (rs.next()) {
                ChuyenDe cd = new ChuyenDe();
                cd.setMaCD(rs.getString("macd"));
                cd.setTenCD(rs.getString("tencd"));
                cd.setHocPhi(rs.getDouble("hocphi"));
                cd.setThoiLuong(rs.getInt("thoiluong"));
                cd.setHinhAnh(rs.getString("hinh"));
                cd.setMoTa(rs.getString("mota"));
                listCD.add(cd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return listCD;
    }

}
