/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao_Interfaces;

import java.util.List;
import model.NguoiHoc;

/**
 *
 * @author NgocTV
 */
public interface NguoiHocInterface {

    public void insert(NguoiHoc entity);

    public void update(NguoiHoc entity);

    public void delete(String key);

    public List<NguoiHoc> selectALL();

    public NguoiHoc selectById(String key);

    public List<NguoiHoc> selectNotInCourse(int makh, String key);

    public List<NguoiHoc> selectByKeyword(String keyword);

    public List<NguoiHoc> selectbySQL(String sql, Object... arg);
}
