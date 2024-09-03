package sem4.task1;

import sem4.models.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

public class Program {

    private final static Random random = new Random();

    public static void main(String[] args) {
        String URL = "jdbc:mysql://localhost:3306";
        String USER = "root";
        String PASSWORD = "password";

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)){

            createDatabase(connection);
            System.out.println("Database created successfully");

            useDatabase(connection);
            System.out.println("Use database successfully");

            createTable(connection);
            System.out.println("Create table successfully");

            int count = random.nextInt(5,11);
            for (int i = 0; i < count; i++)
                insertData(connection, Student.create());
            System.out.println("Insert data successfully");

            Collection<Student> students = readData(connection);
            for(var student: students)
                System.out.println(student);
            System.out.println("Read data successfully");

            for(var student: students){
                student.updateName();
                student.updateAge();
                updateData(connection, student);
            }
            System.out.println("Update data successfully");

            students = readData(connection);
            for(var student: students)
                System.out.println(student);
            System.out.println("Read data successfully");

            for(var student: students)
                deleteData(connection, student.getId());
            System.out.println("DELETE data successfully");

//            connection.close();
//            System.out.println("Database connection close successfully");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void createDatabase(Connection connection) throws SQLException{
        String createDataBaseSQL = "CREATE DATABASE IF NOT EXISTS studentsDB;";
        try(PreparedStatement statement = connection.prepareStatement(createDataBaseSQL)){
            statement.execute();
        }
    }

    private static void useDatabase(Connection connection) throws SQLException{
        String userDatabaseSQL = "USE studentsDB;";
        try(PreparedStatement statement = connection.prepareStatement(userDatabaseSQL)){
            statement.execute();
        }
    }

    private static void createTable(Connection connection) throws SQLException{
        String createTableSQL = "CREATE TABLE IF NOT EXISTS students (id INT AUTO_INCREMENT PRIMARY KEY, name VARCHAR(255), age INT);";
        try(PreparedStatement statement = connection.prepareStatement(createTableSQL)){
            statement.execute();
        }
    }

    private static void insertData(Connection connection, Student student) throws SQLException{
        String insertDataSQL = "INSERT INTO students (name, age) VALUES (?, ?);";
        try(PreparedStatement statement = connection.prepareStatement(insertDataSQL)){
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.executeUpdate();
        }
    }

    private static Collection<Student> readData(Connection connection) throws SQLException{
        ArrayList<Student> studentList = new ArrayList<>();
        String readDataSQL = "SELECT * FROM students;";
        try(PreparedStatement statement = connection.prepareStatement(readDataSQL)){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                studentList.add(new Student(id, name, age));
            }
            return studentList;
        }
    }

    private static void updateData(Connection connection, Student student) throws SQLException{
        String updateDataSQL = "UPDATE students SET name=?, age=? WHERE id=?;";
        try(PreparedStatement statement = connection.prepareStatement(updateDataSQL)){
            statement.setString(1, student.getName());
            statement.setInt(2, student.getAge());
            statement.setInt(3, student.getId());
            statement.executeUpdate();
        }
    }

    private static void deleteData(Connection connection, int id) throws SQLException{
        String deleteDataSQL = "DELETE FROM students WHERE id=?;";
        try(PreparedStatement statement = connection.prepareStatement(deleteDataSQL)){
            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }
}