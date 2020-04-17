/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

/**
 *
 * @author 0809379
 */

import domain.Product;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import java.util.ArrayList;

public class ProductDA {
    private static ArrayList<Product> products = new ArrayList<Product>(5);
    
    public static void add(Product p) {
        products.add(p);
    }
    
    public static Product find (String code) {
        for (int i = 0; i < products.size(); i++) {
            if (code.equals(products.get(i).getCode()))
                return products.get(i);
        }
        return null;
    }
    
    public static ArrayList<Product> getProducts() {
        return products;
    }
    
    public static void init() {
        
        Connection connection;
        Statement statement;
        try{
             if (products.size() <= 0) {
            Product p;
            connection = (Connection)
            DriverManager.getConnection("jdbc:derby://localhost:1527/AssignmentDB","CIS640","CIS640");
            
            System.out.println("Connection = " + connection);
            statement = connection.createStatement();
            ResultSet rs;
            
            String sql = "Select Code, Description, Price " 
                    + "from Product";
            rs = statement.executeQuery(sql);
            while (rs.next()) {
                p = new Product();
                System.out.println("RS = " + rs);
                 System.out.println("p Code = " + rs.getString(1));
                p.setCode(rs.getString(1));
                p.setDescription(rs.getString(2));
                p.setPrice(rs.getDouble(3));
                p.add();
            }

            }
        }catch(Exception e){
            
        }
       
    }
}
