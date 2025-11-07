import java.sql.*;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;


public class applicationOperations {

    // 1. get all students - retrieves and displays all records from the students table
    public static void getAllStudents(){
        try{
            //connect to our Db connection class
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

            //execute query
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students ORDER BY student_id");

            //print results to console
            //System.out.println(" <><><><><><> Students <><><><><><>");
            while(resultSet.next()){
                System.out.print(resultSet.getString("student_id") + "\t");
                System.out.print(resultSet.getString("first_name") + "\t");
                System.out.print(resultSet.getString("last_name") + "\t");
                System.out.print(resultSet.getString("email") + "\t");
                System.out.print(resultSet.getString("enrollment_date"));
                System.out.println("\n") ;
            }

            connection.close();
        }
        catch(Exception e){
            System.out.println("Error in getAllStudents(): " + e);
        }
    }


    // 2. addStudent
    public static void addStudent(String first_name, String last_name, String email, String enrollment_date){
        try {
            //connect to our Db connection class
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();


            // check to see if student with this email already exists
            statement.executeQuery("SELECT email FROM students WHERE email = '" + email + "'");
            ResultSet resultSet = statement.getResultSet();

            if(resultSet.next()){
                // email already exists in db ; cant add - needs to be unique
                System.out.println("Cannot add student because student with email <" + email + "> already exists");
                System.out.println("try again with a unique email" + "\n");
            }
            else{
                // was unique, add to db
                // insert query
                String insert = "INSERT INTO students (first_name, last_name, email, enrollment_date) VALUES " +
                        "('" + first_name + "', '" + last_name + "', '" + email + "', '" + enrollment_date + "');";


                int result = statement.executeUpdate(insert);
                if (result > 0) { // if data was manipulated
                    System.out.println("Student added successfully!");
                }
                else{ // if nothing was changed
                    System.out.println("Failed to add student ");
                }

            }

            connection.close();
        }

        catch(Exception e){
            System.out.println("Error in addStudent() function: " + e);
        }
    }



    //3. update a students email
    public static void updateStudentEmail(int student_id, String new_email){
        try {
            //connect to our Db connection class
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

            //email update query
            // find student with parameters StudentID, set email
            String update = "UPDATE students SET email =" + "'" + new_email + "' WHERE student_id = " + student_id;


            int result = statement.executeUpdate(update);
            if (result > 0) { // if data was manipulated
                System.out.println("Student email updated successfully!");
            }
            else{ // if nothing was manipulated - returns 0
                System.out.println("Failed to update student's email <> make sure student id is a valid one!" + "\n");
            }

            connection.close();
        }

        catch(Exception e){
            System.out.println("Error in updateStudentEmail() func: " + e);
        }
    }



    //4. Delete studnet by id
    public static void deleteStudent(int student_id){

        try {
            //connect to our Db connection class
            Connection connection = DatabaseConnection.getConnection();
            Statement statement = connection.createStatement();

            // delete student query
            // delete student with parameters StudentID
            String delete = "DELETE FROM students WHERE student_id = " + student_id;

            int result = statement.executeUpdate(delete);
            if (result > 0) { // if data was manipulated
                System.out.println("Student deleted successfully!");
            }
            else{ // if nothing was manipulated
                System.out.println("Couldn't delete student - make sure id exists in student table!" + "\n");
            }

            connection.close();
        }
        catch(Exception e){
            System.out.println("Error in deleteStudent() func: " + e);
        }
    }



    //test functions
    public static void main(String[] args){
        System.out.println("====================================================================" +  "\n" + "                             START " +  "\n" + "====================================================================" +  "\n");

        // 1. Print ALL STUDENTS AND ATTRIBUTES
        //System.out.println("Printing All Students" + "\n");
        //getAllStudents();


        //2. ADD STUDENT:
        //add new student
        //addStudent("Shauna", "Quinn", "shaunaquinn@email.com", "2025-11-05");
        //System.out.println('\n');

        //add student that already exists
        //addStudent("Jim", "Beam", "jim.beam@example.com",	"2023-09-02");


        // reprint all students to check if add student worked
        //System.out.println("All students after adding student: " + "\n");
        //getAllStudents();


        //3. UPDATE STUDENT EMAIL
        //update student with valid ID
        //updateStudentEmail(1, "johnNEW@email.com");

        //update student with invalid ID
        //updateStudentEmail(100, "Email@email.com");

        //print all student again to see update
        //System.out.println("After updating email:") ;
        //getAllStudents();


        //4.DELETE STUDENT
        //Delete student with valid ID
        //deleteStudent(4);


        //Delete student with invalid ID
        //deleteStudent(400);

        // show final result
        //System.out.println("All students after deleting student: " + "\n");
        getAllStudents();

    }
}
