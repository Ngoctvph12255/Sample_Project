/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;

/**
 *
 * @author Dell
 */
public class JDBCHeader {
    static String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    static String ketnoi = "jdbc:sqlserver://localhost:1433;databaseName=Polypro";
    static String user = "sa";
    static String pass ="123";
    
    static {
        try {
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static PreparedStatement getstm(String sql,Object... arg) throws SQLException  {
        Connection con = DriverManager.getConnection(ketnoi,user,pass);
        PreparedStatement stm;
        if(sql.trim().startsWith("{")){
            stm = con.prepareCall(sql);//proc
            
        }else {
            stm = con.prepareStatement(sql);//sql
        }
        for(int i=0;i<arg.length;i++){
            stm.setObject(i+1, arg[i]);
        }
        return stm;
    }
    public static int update(String sql,Object... arg) throws SQLException{
        
        try {
           PreparedStatement stm = JDBCHeader.getstm(sql, arg);
           try{
               return stm.executeUpdate();
           }finally{
               stm.getConnection().close();
           }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
    public static ResultSet query(String sql,Object... arg) throws SQLException{
        PreparedStatement stm = JDBCHeader.getstm(sql, arg);
        return stm.executeQuery();
    }
    public static Object values(String sql,Object... arg){
        try {
            ResultSet rs = JDBCHeader.query(sql, arg);
            if(rs.next()){
                return rs.getObject(0);
            }
            rs.getStatement().getConnection().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
