/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao_Interfaces;

import java.util.List;
import model.NhanVien;

/**
 *
 * @author ACER
 */
public interface NhanVienInterface {
    
    public void insert(NhanVien entity);
    public void update(NhanVien entity);
    public void delete(String manv);
    public List<NhanVien> selectALL();
    public NhanVien select_by_id(String key);
    public List<NhanVien> selectbySQL(String sql, Object... arg);
}
