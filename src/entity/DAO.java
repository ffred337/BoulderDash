package entity;

import java.sql.*;
/**
 * @author Fred X2026
 *
 */

public class DAO {
    private final static String timeZoneAttribute = "&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final static String dbName = "boulderdash";//to replace by boulderdash at the project's end
    private final static String dbAddress = "jdbc:mysql://localhost:3306/" + dbName + "?autoReconnect=true&useSSL=false";
    private final static String URL = dbAddress + timeZoneAttribute;
    private final static String LOGIN = "root";
    private final static String PASSWORD = "0123456789";
    private Connection connection;
    private Statement statement;

    public DAO(){
        this.connection = null;
        this.statement = null;
    }

    public Boolean open() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connection = DriverManager.getConnection(URL,LOGIN,PASSWORD);
            this.statement = this.connection.createStatement();
        } catch (final ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
        System.out.println("=== OUVERTURE DE LA BDD ===");// to remove at the project's end
        return true;
    }

    public void close() {
        try {
            this.statement.close();
            //this.connection.close();
            System.out.println("=== FERMETURE DE LA BDD ==="); // to remove at the project's end
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private int executeUpdate(String Query) {
        try {
            this.statement.executeUpdate(Query);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return 0;
        }
        return 1;
    }

    public String getLevel(int id){
        String level = "";
        try{
            final String query = "call getLevel(" + id + ");";
            final CallableStatement call = this.connection.prepareCall(query);
            call.execute();
            final ResultSet resultSet = call.getResultSet();
            if(resultSet.first()){
                level = resultSet.getString("content");
                return level;
            }
        }catch (final SQLException e) {
            e.printStackTrace();
        }
        return level;
    }
}
