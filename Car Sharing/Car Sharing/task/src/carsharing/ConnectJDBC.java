package carsharing;

import java.sql.*;

public class ConnectJDBC {
    static final String JDBC_DRIVER = "org.h2.Driver";
    static String DB_URL = "jdbc:h2:D:\\HyperSkill\\Car Sharing\\Car Sharing\\task\\src\\carsharing\\db\\";

    static final String USER = "";
    static final String PASS = "";

    Connection conn = null;
    Statement stmt = null;

    public Connection createConnection(){
            try {
                Class.forName(JDBC_DRIVER);
//                System.out.println("Connecting to database...");
                conn = DriverManager.getConnection(DB_URL,USER,PASS);
                conn.setAutoCommit(true);
                return conn;
            } catch (SQLException sq){
                sq.printStackTrace();
            } catch (Exception e){
                e.printStackTrace();
            }

        return conn;
    }

    public Statement createStatement(){
        try{
            conn = createConnection();
            return conn.createStatement();
        } catch (SQLException sq){
            sq.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
        throw new RuntimeException("Create statement fail");
    }

    public void shutDown(){
//        System.out.println("Closing connection...........");
        try{
            if(stmt!=null) stmt.close();
        } catch(SQLException se2) {
            se2.printStackTrace();
        }
        try {
            if(conn!=null) conn.close();
        } catch(SQLException se){
            se.printStackTrace();
        }
    }

    public void update(String sql){
        try{
            stmt = createStatement();
//            System.out.println("Executing update");
            stmt.executeUpdate(sql);
            shutDown();
        } catch (SQLException sq){
            sq.printStackTrace();
        }
    }

    public ResultSet query(String sql){
        try{
            stmt = createStatement();

//            System.out.println("Executing query");
            return stmt.executeQuery(sql);
        } catch (SQLException sq){
            sq.printStackTrace();
        }
        shutDown();
        throw new RuntimeException();
    }
}

