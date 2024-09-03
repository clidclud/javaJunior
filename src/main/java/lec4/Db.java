package lec4;

import java.sql.*;

public class Db {
    private final static String URL = "jdbc:mysql://localhost:3306";
    private final static String USER = "root";
    private final static String PASSWORD = "password";

    public static void connection() {
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){
            Statement statement = connection.createStatement();
            statement.execute("DROP SCHEMA 'test'");
            statement.execute("CREATE SCHEMA 'test'");
            statement.execute("CREATE TABLE 'test'.'table' ('id' INT NOT NULL, 'firstname' VARCHAR(45) NULL, 'lastname' VARCHAR(45) NULL, PRIMARY KEY('id');");
            statement.execute("INSERT INTO 'test'.'table' ('id', 'firstname', 'lastname')\nVALUES (1,'Иванов','Иван');");

            ResultSet set = statement.executeQuery("SELECT * FROM test.table;");
            while (set.next()){
                System.out.println(set.getString(3) + " " + set.getString(2) + " " + set.getInt(1));
            }

        } catch (SQLException e){
            throw new RuntimeException();
        }
    }
}
