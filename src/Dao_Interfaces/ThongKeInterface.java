/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Dao_Interfaces;

import java.util.List;

/**
 *
 * @author NgocTV
 */
public interface ThongKeInterface {
    public List<Object[]> getBangDiem(Integer makh);

    public List<Object[]> getSlNguoiHoc();

    public List<Object[]> getDiemChuyenDe();
}
