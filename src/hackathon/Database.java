package hackathon;

import java.sql.*;

public class Database {
    Connection c;
    
    Database(){
        try {
            Class.forName("org.postgresql.Driver");
            c = DriverManager
               .getConnection("jdbc:postgresql://localhost:5432/hackathon","postgres", "1234");
        } catch (Exception e) {
            c = null;
            e.printStackTrace();
            System.err.println(e.getClass().getName()+": "+e.getMessage());
        }
    }
    
    
    boolean executeQuery(String query) throws SQLException{
        System.out.println(query);
        try {
            Statement stmt = c.createStatement();
            stmt.executeUpdate(query);
            stmt.close();
            c.commit();
            return true;
        } catch(SQLException e){
            return false;
        }
    }
    
}
