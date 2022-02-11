/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao_Interfaces;

import java.util.List;
import model.KhoaHoc;

/**
 *
 * @author NgocTV
 */
public interface KhoaHocInterfaces {
    public void insert(KhoaHoc entity);

    public void update(KhoaHoc entity);

    public void delete(Integer manv);

    public List<KhoaHoc> selectALL();

    public List<KhoaHoc> selectByChuyenDe(String MaCD);

    public List<Integer> selectYear();

    public KhoaHoc findById(Integer key);

    public List<KhoaHoc> selectbySQL(String sql, Object... arg);
}
