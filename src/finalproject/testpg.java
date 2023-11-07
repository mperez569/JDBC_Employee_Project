/*
package finalproject;

public class testpg {
    package finalproject;

import java.sql.*;

public class FinalProject {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/finalproject";
        String login = "root";
        String password = "Cps50164404!";
        Connection conn = null;
        Statement myquery;
        ResultSet results;
        try
        {
            conn = DriverManager.getConnection(url, login, password);
            myquery = conn.createStatement();
            results = myquery.executeQuery("select * from employee;");
            while(results.next())
            {
                Employee emp = new Employee(results.getInt(1), results.getString(2), 
                        results.getString(3), results.getString(4), results.getString(5),
                        results.getString(6), results.getString(7), results.getString(8),
                        results.getDouble(9));
                System.out.println(emp.toString());
            }
            conn.close();
        }
        
        catch(SQLException ex)
        {
            ex.printStackTrace();
            
        }
    }
}
*/
