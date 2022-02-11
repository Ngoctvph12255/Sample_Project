/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao_Interfaces;

import java.util.List;
import model.ChuyenDe;

/**
 *
 * @author NgocTV
 */
public interface ChuyenDeInterface {

    public void insert(ChuyenDe entity);

    public void update(ChuyenDe entity);

    public void delete(String key);

    public List<ChuyenDe> selectALL();

    public ChuyenDe selectById(String key);

    public List<ChuyenDe> selectbySQL(String sql, Object... arg);
}
