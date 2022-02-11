/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao_Interfaces;

import java.util.List;
import model.HocVien;

/**
 *
 * @author NgocTV
 */
public interface HocVienInterface {
    public void insert(HocVien entity);

    public void update(HocVien entity);

    public void delete(Integer manv);

    public List<HocVien> selectALL();
    
    public List<HocVien> selectByKhoaHoc(int makh);
    
    public HocVien selectById(String key);
    
    public HocVien findById(Integer mahv);

    public List<HocVien> selectBySQL(String sql, Object... arg);
}
