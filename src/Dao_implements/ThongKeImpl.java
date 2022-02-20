/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao_implements;

import Dao_Interfaces.NhanVienInterface;
import Dao_Interfaces.ThongKeInterface;
import JDBC.JDBCHeader;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NgocTV
 */
public class ThongKeImpl implements ThongKeInterface {

    @Override
    public List<Object[]> getBangDiem(Integer makh) {
        List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_BangDiem(?)}";
                rs = JDBCHeader.query(sql, makh);
                 while (rs.next()) {
                    double diem = rs.getDouble("diem");
                    String xeploai = "Xuất sắc";
                    if (diem < 0) {
                        xeploai = "Chưa nhập";
                    } else if (diem < 5) {
                        xeploai = "Chưa đạt";
                    } else if (diem < 6.5) {
                        xeploai = "Trung bình";
                    } else if (diem < 7.5) {
                        xeploai = "Khá";
                    } else if (diem < 9) {
                        xeploai = "Giỏi";
                    }
                    Object[] model = {
                        rs.getString("manh"),
                        rs.getString("hoten"),
                        diem,
                        xeploai
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Object[]> getSlNguoiHoc() {
          List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeNguoiHoc}";
                rs = JDBCHeader.query(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getInt("Nam"),
                        rs.getInt("soluong"),
                        rs.getDate("dautien"),
                        rs.getDate("cuoicung")
                    };
                    list.add(model);

                }
            } finally {
                rs.getStatement().getConnection().close();

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Object[]> getDiemChuyenDe() {
       List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeDiem}";
                rs = JDBCHeader.query(sql);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("ChuyenDe"),
                        rs.getInt("SoHV"),
                        rs.getDouble("ThapNhat"),
                        rs.getDouble("CaoNhat"),
                        rs.getDouble("TrungBinh")
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Object[]> getDoanhThu(int nam) {
      List<Object[]> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                String sql = "{call sp_ThongKeDoanhThu(?)}";
                rs = JDBCHeader.query(sql, nam);
                while (rs.next()) {
                    Object[] model = {
                        rs.getString("Chuyende"),
                        rs.getInt("SoKH"),
                        rs.getInt("SoHV"),
                        rs.getDouble("DoanhThu"),
                        rs.getDouble("ThapNhat"),
                        rs.getDouble("CaoNhat"),
                        rs.getDouble("TrungBinh")
                    };
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

}
