COMP3005 - Assignment 3

Student name: Alik Nazlian
Student ID: 101337906

                                    Purpose
The purpose of this assignment is to write 4 functions to preform CRUD operations
by populating a table in pgAdmin and connecting to it by performing SQL queries. 

functions:
1. getAllStudents() 
    - this function returns all the students in the table including their
      student id, name, last name, email, date of enrollement by looping
      through the table with while() organizing each result into a java string
      and closing the connection when table is finished

2. addStudent(first_name, last_name, email, enrollment_date)
    - for inserting a student into a table, we can create placeholders in
      the query using 'VALUES (?, ?, ?, ?)' that'll later be populated by
      the students specification and sending it to PostgreSQL to be executed
    - error check with int rows to return the number of rows effected (successful
      if rows = 1)

3. updateStudentEmail(student_id, new_email)
    - updating a students email required writing the query in a way where SET
      had its values equal the placeholders that'll be populated in the prepared
      statement for PostgreSQL to handle
    - error handeling here is done by setting rows equal to the pstmt.executeUpdate()
      to check if the numbers of rows effected changed after the function ran

4. deleteStudent(student_id)
    - by creating a query for deletation of a student with only one placeholder 
      to be handeled by the prepared statment a student can successfully be removed
      from the table
    - error handeling here also uses int rows to check if a student was deleted


- To test the functions, I first called getAllStudents() to display the original table.
- I then called addStudent(), updateStudentEmail(), and deleteStudent() to perform the database operations.
- Finally, I called getAllStudents() again to display the updated table and verify the changes.

                            Compile and run instruction

1. Download the project folder from GitHub and extract the files, open the folder in VS code
2. Ensure that Java JDK and PostgreSQL are installed in your system
3. in pgAdmin create a new database named 'Assignment 3'
4. Open the Query tool and execute the students.sql script to create and populate the table
5. Open functions.java in VS code
6. Open a terminal in the project folder and set your local PostgreSQL password: 
    - export DB_PASSWORD='your_postgresql_password'
7. If your PosgreSQL driver in the lib folder is not configured in VS code, run the following in a terminal:
   - javac -cp ".;lib/*" functions.java
   - java -cp ".;lib/*" functions
8. (ignore this step if you did the one before it) press the triangular Run button at the top 
   right to execute main()

what you should see
    1. original student table displayed
    2. success messages for INSERT, UPDATE, and DELETE operations
    3. an updated version of student table, with the changes made by the functions in main()

To see changes in pgAdmin:
1. Open a Query tool and execute 'SELECT * FROM students ORDER BY student_id;' 
2. Open VS code an execute the main() method in functions.java
3. Return to pgAdmin and execute the same SQL query
4. Compare the before and after results to see chnages in students table

Video link: https://drive.google.com/file/d/1E6FaBvEDxzAx0cNZgnvoy9zoW4HKzcYH/view?usp=sharing 