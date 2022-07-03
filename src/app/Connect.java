
package app;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

public class Connect {
    public Connection conn;
    public Statement stmt;
    public ResultSet rs;
    
    public Connect(){
        try {
            conn = DriverManager.getConnection("jdbc:sqlite:pizza.db");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } 
    }
    
    public ArrayList<String> select(String[] columnsArray, String table){
        ArrayList<String> data = new ArrayList<String>();
        String columns = String.join(", ", columnsArray);
        
        String sql = "SELECT " + columns + " FROM " + table;
        System.out.println(sql);
        
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                String row = "";
                for (int i = 0; i < columnsArray.length; i++) {
                    row = row + rs.getString(columnsArray[i]) + "---";
                    
                }
                row = row.substring(0, row.length()-3);
                data.add(row);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
    
    public ArrayList<String> selectWhere(String[] columnsArray, String table, int[] whereUslovie, String[] usloviq){
        ArrayList<String> data = new ArrayList<String>();
        String columns = String.join(", ", columnsArray);
        String sql = "SELECT " + columns + " FROM " + table + " WHERE ";
        for (int i = 0; i < whereUslovie.length; i++) {
             sql = sql + columnsArray[whereUslovie[i]] + "= '" + usloviq[i] + "'" ;
        }
        System.out.println(sql);
        
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
               String row = "";
                for (int i = 0; i < columnsArray.length; i++) {
                    row = row + rs.getString(columnsArray[i]) + "---";
                    
                }
                row = row.substring(0, row.length()-3);
                data.add(row);
               
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
    
    public ArrayList<String> selectWhereLogin(String[] columnsArray, String table, int[] whereUslovie, String[] usloviq){
        ArrayList<String> data = new ArrayList<String>();
        String columns = String.join(", ", columnsArray);
        String sql = "SELECT " + columns + " FROM " + table + " WHERE ";
        for (int i = 0; i < whereUslovie.length; i++) {
             sql = sql + columnsArray[whereUslovie[i]] + " like '" + usloviq[i] + "' and ";
        }

        sql = sql.substring(0, sql.length()-5);
        System.out.println(sql);
        
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while(rs.next()){
               String row = "";
                for (int i = 0; i < columnsArray.length; i++) {
                    row = row + rs.getString(columnsArray[i]) + "---";
                    
                }
                row = row.substring(0, row.length()-3);
                data.add(row);
               
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return data;
    }
    
    public void insert(String table, String[] columnsArray, String[] valuesArray){
        String columns = String.join(", ", columnsArray);
        String values = String.join("', '", valuesArray);
        String sql = "Insert into " + table + " ("+columns+") values ('"+values+"')";

        System.out.println(sql);
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void update(String table, String[] columnsArray, String[] valuesArray, String whereCol, String whereVal){
        String sql = "update "+ table + " set ";
        for (int i = 0; i < columnsArray.length; i++) {
            sql=sql+columnsArray[i]+" = '"+valuesArray[i]+"', ";
        }
        sql=sql.substring(0, sql.length()-2);
        sql = sql + " WHERE "+ whereCol + " = '"+whereVal+"'";
        System.out.println(sql);
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void delete(String table, String column, String value){
        String sql = "delete from " + table + " where "+column+" = "+value;
        System.out.println(sql);
        try{
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void close(){
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Throwable ex) {
            Logger.getLogger(Connect.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
