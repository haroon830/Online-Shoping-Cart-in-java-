/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Search {
    static int electronics = 0;
    static int mobile = 0;
    static int kids = 0;
    
    
    public static ArrayList<ProductList> mobileSearch(String model){
        ArrayList<ProductList> list = new ArrayList<>();
        try{  
          Class.forName("oracle.jdbc.driver.OracleDriver");  
          Connection con=DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:orcl","hr","oracle");
            PreparedStatement ps = con.prepareStatement("SELECT * FROM electronics WHERE mbrand=? OR mmodel=? ");
            ps.setString(1, model);
            ps.setString(2, model);
            ResultSet rs = ps.executeQuery();
            ProductList pl, gl, kl=null;            
            while(rs.next()){
                pl = new ProductList(rs.getString("mbrand"),rs.getString("mmodel"),
                        rs.getInt("mprice"),rs.getInt("mquantity"),rs.getString("mdescription"),
                        rs.getString("mphoto"));
                electronics++;
                list.add(pl);
            }
            con.close();
         
            con=DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:orcl","hr","oracle");
       
            ps = con.prepareStatement("SELECT * FROM mobiles WHERE mbrand=? OR mmodel=? ");
            ps.setString(1, model);
            ps.setString(2, model);
            rs = ps.executeQuery();

            
            while(rs.next()){
                gl = new ProductList(rs.getString("mbrand"),rs.getString("mmodel"),
                        rs.getInt("mprice"),rs.getInt("mquantity"),rs.getString("mdescription"),
                        rs.getString("mphoto"));
                mobile++;
                list.add(gl);

            }
            con.close();
           
            con=DriverManager.getConnection( "jdbc:oracle:thin:@localhost:1521:orcl","hr","oracle");
            
            ps = con.prepareStatement("SELECT * FROM kids WHERE mbrand=? OR mmodel=? ");
            ps.setString(1, model);
            ps.setString(2, model);
            rs = ps.executeQuery();

            
            while(rs.next()){
                kl = new ProductList(rs.getString("mbrand"),rs.getString("mmodel"),
                        rs.getInt("mprice"),rs.getInt("mquantity"),rs.getString("mdescription"),
                        rs.getString("mphoto"));
                kids++;
                list.add(kl);

            }
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(MobileDB.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Search.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
   }
    
}
