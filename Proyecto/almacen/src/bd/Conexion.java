/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author charlis
 */
public class Conexion {
    private static final String servidor="jdbc:mysql://localhost/mydb";
    private static final String user="root";
    private static final String psw="123456";
    private static final String driver="com.mysql.jdbc.Driver";
    private static Connection cnx;
    
    public Conexion(){
        
        try{
            Class.forName(driver);
            cnx=DriverManager.getConnection(servidor, user, psw);
            
        }catch(ClassNotFoundException ex){
            JOptionPane.showMessageDialog(null,"Conexion Fallida");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error");
        }
    }
    
    
    
    
    
    public Connection getConexion(){
    return cnx;
    }
}
