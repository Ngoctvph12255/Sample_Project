/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;

import JDBC.DateHelper;
import model.NhanVien;

/**
 *
 * @author NgocTV
 */
public class loginInfomation {
//    đối tượng chứa thông tin của người sử dụng sau khi đăng nhập
    public static NhanVien USER = null;
//    Xóa thông tin của người sử dụng khi có yêu cầu đăng xuất
    public static void logOff() {
        loginInfomation.USER = null;
    }
//    Kiểm tra xem đăng nhập hay chưa
    public static boolean authenticated() {
        return loginInfomation.USER != null;
    }
    //Kiểm tra xem có phải trưởng phòng không ?
    public static boolean isManager(){
        return loginInfomation.authenticated() && USER.isVaiTro();
    }
}
