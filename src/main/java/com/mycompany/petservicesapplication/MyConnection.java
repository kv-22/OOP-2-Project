/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.petservicesapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fatim
 */
public class MyConnection {

    public static Connection connectdb() {
        Connection con = null;
        try {
            con = DriverManager.getConnection("jdbc:derby://localhost:1527/pawfect", "pawfect", "pawfect");
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(MyConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
