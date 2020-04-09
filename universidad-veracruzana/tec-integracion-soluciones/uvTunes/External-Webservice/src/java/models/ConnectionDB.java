package models;

import java.sql.Connection; 
import java.sql.DriverManager; 
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ConnectionDB {
    public String status = "Not connected";
    private Connection connection = null;  
    
    public boolean getMySQLConnection() {  
        try {
            String driverName = "com.mysql.jdbc.Driver";
            Class.forName(driverName);
 
            String serverName = "uvtunes-database.mysql.database.azure.com";
            String mydatabase = "uvtunes"; 
            String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
            String username = "uvtunes@uvtunes-database";
            String password = "DJdmqeMlyn!";
 
            connection = DriverManager.getConnection(url, username, password);
            
            status = (connection != null ? "Connected" : "Unable to connect"); 
 
            return true;
        } catch (ClassNotFoundException e) {  
            System.out.println("MySQL driver not found -> \n" + e); 
            return false;
        } catch (SQLException e) {
            System.out.println("It was not possible to connect -> \n" + e); 
            return false; 
        }
    }
    
    public String getConnectionLog() { 
        return status; 
    }
 
    public boolean closeConnection() { 
        try {
            connection.close(); 
            return true; 
        } catch (SQLException e) {
            return false; 
        }
    }
    
    public void restartConnection() { 
        closeConnection();
        getMySQLConnection();
    }
    
    public ArrayList<ArrayList<String>> executeSelect(String query) { 
        ArrayList<ArrayList<String>> results = new ArrayList<ArrayList<String>>();
        
        try {
            Statement statm = connection.createStatement();
            ResultSet rs = statm.executeQuery(query);
            
            while (rs.next()) {
                ArrayList<String> row = new ArrayList<String>();
                
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    row.add(rs.getObject(i + 1).toString());
                }
                
                results.add(row);
            }
            
            statm.close();
            
            return results;
        } catch (Exception e) {
            System.out.println("Exception -> \n" + e); 
            return null;
        }
    }
    
    public boolean executeInsert(String query) {
        boolean success = false;
        
        try {
            Statement statm = connection.createStatement();                        
            statm.executeUpdate(query);            
            statm.close();
            
            success = true;
        } catch (SQLException e) {
            System.out.println("Exception -> \n" + e);  
        }
        
        return success;
    }
}