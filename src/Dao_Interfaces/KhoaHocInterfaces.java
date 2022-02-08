/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao_Interfaces;

import java.util.List;
import model.KhoaHoc;

/**
 *
 * @author ACER
 */
public interface KhoaHocInterfaces {
    public void insert(KhoaHoc entity);

    public void update(KhoaHoc entity);

    public void delete(String manv);

    public List<KhoaHoc> selectALL();

    public KhoaHoc findById(Integer key);

    public List<KhoaHoc> selectbySQL(String sql, Object... arg);
}
