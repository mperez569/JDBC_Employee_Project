package finalproject;

import java.sql.*;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeCRUD {
    //previous local hostname --> localhost:3306
    //AWS hostname--> cis114.ctg5hfkjprwp.us-east-1.rds.amazonaws.com
    //port --> 3306
    //previous name --> finalproject
    //database name --> wright
    private final String jdbcURL="jdbc:mysql://cis114.ctg5hfkjprwp.us-east-1.rds.amazonaws.com:3306/wright";
    //previous username was root
    //new username is mperez569
    private final String jdbcUsername="mperez569";
    
    //previous password was Cps50164404!
    //new password is MartinPerez
    private final String jdbcPassword="MartinPerez";
    
    private static final String INSERT_EMPLOYEE_SQL="insert into Employee(EmpLname, EmpFname, EmpAddress1," +
    "EmpAddress2, EmpCity, EmpState, EmpDOB, EmpBaseSalary)" + "values"+
            "(?,?,?,?,?,?,?,?);";
    private static final String SELECT_EMPLOYEE_BY_ID="select * from Employee where EmpID=?";
    
    //the "Employee" word was lower case
    private static final String DELETE_EMPLOYEE_SQL="delete from Employee where empid=?";
    private static final String UPDATE_EMPLOYEE_SQL = "update Employee set EmpLname = ?, EmpFname = ?, EmpAddress1 = ?, "
            + "EmpAddress2 = ?, EmpCity = ?, EmpState = ?, EmpDOB = ?, EmpBaseSalary = ? where EmpId = ?";
    
    public EmployeeCRUD()
    {
    
    };
    
    protected Connection getConnection()
    {
        Connection conn=null;
        try
        {
            conn=DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return conn;
    }
    
    public void insertEmployee(Employee emp) 
    {
        try
        (
         Connection gust=getConnection();
         PreparedStatement ps=gust.prepareStatement(INSERT_EMPLOYEE_SQL);
        )
        {
            ps.setString(1, emp.getLname());
            ps.setString(2, emp.getFname());
            ps.setString(3, emp.getAddress1());
            ps.setString(4, emp.getAddress2());
            ps.setString(5, emp.getCity());
            ps.setString(6, emp.getState());
            ps.setString(7, emp.getDOB());
            ps.setDouble(8, emp.getSalary());
            ps.executeUpdate();
        }
        catch(SQLException e)
        {
            e.printStackTrace();
        }
    }
    
    public Employee selectEmployee(int empid)
    {
        Employee employee=null;
        try
        (
         Connection gust=getConnection();
         PreparedStatement ps=gust.prepareStatement(SELECT_EMPLOYEE_BY_ID);
        )
        {
            ps.setInt(1, empid);
            ResultSet rs=ps.executeQuery();
            if(rs.next())
            {
                String Lname=rs.getString(2);
                String Fname=rs.getString(3);
                String Address1=rs.getString(4);
                String Address2=rs.getString(5);
                String City=rs.getString(6);
                String State=rs.getString(7);
                String DOB=rs.getString(8);
                double Salary=rs.getDouble(9);
                employee=new Employee(empid, Lname, Fname, Address1, Address2, City, State, DOB, Salary);
                
            }
            //ResultSet rs = ps.executeUpdate(jdbcURL);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return employee;
    }
    
    public boolean deleteEmployee(int empid)
    {
        boolean rowDeleted=false;
        try
        (
         Connection gust=getConnection();
         PreparedStatement ps=gust.prepareStatement(DELETE_EMPLOYEE_SQL);
        )    
        {
            ps.setInt(1, empid);
            rowDeleted=ps.executeUpdate()>0;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return rowDeleted;
    }
    
    public boolean UpdateEmployee(Employee emp)
    {
        boolean rowUpdated = false;
        try
        (
         Connection gust=getConnection();
         PreparedStatement ps=gust.prepareStatement(UPDATE_EMPLOYEE_SQL);
        )    
        {
            ps.setString(1, emp.getLname());
            ps.setString(2, emp.getFname());
            ps.setString(3, emp.getAddress1());
            ps.setString(4, emp.getAddress2());
            ps.setString(5, emp.getCity());
            ps.setString(6, emp.getState());
            ps.setString(7, emp.getDOB());
            ps.setDouble(8, emp.getSalary());
            
            ps.setInt(9, emp.getId());
            
            rowUpdated = ps.executeUpdate() > 0;
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        return rowUpdated;
                
    }        
}
