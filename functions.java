import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Date;

// creating the class
class functions {
    public static void main(String[] args) { // Java starts executing

    String url = "jdbc:postgresql://localhost:5432/Assignment 3"; // database address
    String user = "postgres";
    String password = System.getenv("DB_PASSWORD");

    // handles conection connectivity and status 
    try {
        Class.forName("org.postgresql.Driver");

        Connection conn = DriverManager.getConnection(url, user, password);

        System.out.println("Connected to PostgreSQL successfully!");

        // original table
        getAllStudents(conn);

        // testing functions
        deleteStudent(conn, 3);
        updateStudentEmail(conn, 2, "testemail@cmail.carleton.ca");
        addStudent(conn, "Alik", "Nazlian", "alik.nazlian@cmail.carleton.ca", Date.valueOf("2024-09-04"));

        getAllStudents(conn);

        conn.close();

        // catches two possible errors
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
    // declare the function (and handles error in main())
    public static void getAllStudents(Connection conn) throws SQLException {
        Statement stmt = conn.createStatement(); // create statement 
        String SQL = "SELECT student_id, first_name, last_name, email, enrollment_date FROM students"; // create the query
        ResultSet rs = stmt.executeQuery(SQL); // using executeQuery() to extract data 

            // loop through the table
            while(rs.next()) {
                // and lable all variables
                int student_id = rs.getInt("student_id");
                String first_name = rs.getString("first_name");
                String last_name = rs.getString("last_name");
                String email = rs.getString("email");
                Date date = rs.getDate("enrollment_date");

                // create a string made up of the variables above
                System.out.println("ID: " + student_id + ", Name: " + first_name + ", Last name: " + last_name  + ", Email: " + email + ", Date: " + date);
            }
        // close the resultset and statement    
        rs.close();
        stmt.close();
    }

    public static void addStudent(Connection conn, String first_name, String last_name, String email, Date enrollment_date) throws SQLException {
        String SQL = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES (?, ?, ?, ?)"; // create the query and placeholders

        // create a prepared statement to fill in the placeholders by the student being inserted
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, first_name);
            pstmt.setString(2,  last_name);
            pstmt.setString(3, email);
            pstmt.setDate(4, enrollment_date);
            int rows = pstmt.executeUpdate(); // sending the prepared INSERT to the PostgreSQL
            
            if (rows > 0) {
                System.out.println("Student added successfully");
            } else {
                System.out.println("Failed to add student");
            }
        }
    }

    public static void updateStudentEmail(Connection conn, int student_id, String new_email) throws SQLException {
        String SQL = "UPDATE students SET email = ? WHERE student_id = ?"; // create the query and placeholders

        // following the order for the prepared statment
        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setString(1, new_email);
            pstmt.setInt(2, student_id);
            int rows = pstmt.executeUpdate(); // sending the prepared UPDATE to the PostgreSQL
            
            // error handeling if ID of student not found
            if (rows > 0) {
                System.out.println("Student email updated successfully");
            } else {
                System.out.println("Failed to update student email");
            }
        }
    }

    public static void deleteStudent(Connection conn, int student_id) throws SQLException {
        String SQL = "DELETE FROM students WHERE student_id = ?"; // create the query for deleting a student

        try (PreparedStatement pstmt = conn.prepareStatement(SQL)) {
            pstmt.setInt(1, student_id);
            int rows = pstmt.executeUpdate();  // sending the prepared DELETE to the PostgreSQL

            if (rows > 0) {
                System.out.println("Student deleted successfully");
            } else {
                System.out.println("Failed to delete student");
            }
        }
    }
}