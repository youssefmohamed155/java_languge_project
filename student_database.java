import java.util.Scanner;

/* 
 * File Name: StudentDataBase.java
 * 
 * Author: Youssef Mohammed
 * Date: 21/11/2024 
 * 
 * This program handles student data, including name, ID, age, fee, and grade.
 */

/* Student_Data class represents a student's information */


class Student_Data {

   String name;  /* The name of the student */
   int id;       /* The student's ID */
   int age;      /* The student's age */
   double fee;   /* The student's fee */
   double grade; /* The student's grade */

   /* Constructor to initialize student data */
   public Student_Data(String name, int id, int age, double fee, double grade) {
      this.name = name;  /* Set the student's name */
      this.id = id;      /* Set the student's ID */
      this.age = age;    /* Set the student's age */
      this.fee = fee;    /* Set the student's fee */
      this.grade = grade; /* Set the student's grade */
   }

   /* Override toString() to provide a formatted string of the student's data */
   @Override
   public String toString() {
      return "Student Name: " + name + ", ID: " + id + ", Age: " + age + ", Grade: " + grade + ", Fee: " + fee;
   }
}

/* StudentInput class handles getting input from the user */
class StudentInput {

   /* Method to get student data from user input */
   public Student_Data getStudentData(Scanner inputScanner, int studentNumber) {
      // Get student name
      System.out.printf("Enter Name of Student %d: ", studentNumber);
      String name = inputScanner.nextLine();

      // Get student ID
      System.out.print("Enter ID of Student: ");
      int id = inputScanner.nextInt();

      // Get student age
      System.out.print("Enter Age of Student: ");
      int age = inputScanner.nextInt();

      // Get student fee
      System.out.print("Enter Fee of Student: ");
      double fee = inputScanner.nextDouble();

      // Get student grade
      System.out.print("Enter Grade of Student: ");
      double grade = inputScanner.nextDouble();
      inputScanner.nextLine();  // Consume the newline

      // Return a new Student_Data object with the collected data
      return new Student_Data(name, id, age, fee, grade);
   }
}

/* Print_Data class handles printing student data */
class Print_Data {

   /* Method to print the student data */
   public void printData(Student_Data[] students, int numStudents) {
      System.out.println("Student Data:");
      for (int i = 0; i < numStudents; i++) {
         if (students[i] != null) {
            System.out.println(students[i]);
         }
      }
   }

   /* Method to print all student IDs */
   public void printAllIDs(Student_Data[] students, int numStudents) {
      System.out.println("Student IDs:");
      for (int i = 0; i < numStudents; i++) {
         if (students[i] != null) {
            System.out.println(students[i].id);
         }
      }
   }
}

public class Main {

   /* The maximum number of students we can handle */
   public static int SIZE = 10;

   public static void main(String[] args) {
      /* Create an array to store student data objects */
      Student_Data[] students = new Student_Data[SIZE];
      Scanner inputScanner = new Scanner(System.in); /* Scanner for user input */
      int numStudents = 0; // Keeps track of the number of students in the database
      StudentInput studentInput = new StudentInput();
      Print_Data printer = new Print_Data();
      
      System.out.println(" ***********************************************");
      System.out.println(" ************** Student_Data_Base **************");
      System.out.println(" ***********************************************\n");

      while (true) {
         System.out.printf("(#1) - To add entry\n");
         System.out.printf("(#2) - To get used size in database\n");
         System.out.printf("(#3) - To read student data\n");
         System.out.printf("(#4) - To get the list of all student IDs\n");
         System.out.printf("(#5) - To check if ID is existed\n");
         System.out.printf("(#6) - To delete student data\n");
         System.out.printf("(#7) - To check if database is full\n");
         System.out.printf("(#0) - To exit\n\n");

         System.out.print("Enter the number you want to choose: ");
         int num_choose = inputScanner.nextInt();
         inputScanner.nextLine(); // Consume the newline character

         switch (num_choose) {

            case 0:
               System.out.println("Exiting program.");
               inputScanner.close();
               return;  // Exit the program

            case 1: // Add student data
               if (numStudents < SIZE) {
                  students[numStudents] = studentInput.getStudentData(inputScanner, numStudents + 1);
                  numStudents++;
                  System.out.println("Student added successfully.\n");
               } else {
                  System.out.println("Database is full. Cannot add more students.\n");
               }
               break;

            case 2: // Get the used size in the database
               System.out.println("Number of students in database: " + numStudents + "\n");
               break;

            case 3: // Read student data
               if (numStudents == 0) {
                  System.out.println("No students to display.\n");
               } else {
                  printer.printData(students, numStudents);
               }
               break;

            case 4: // Get all student IDs
               if (numStudents == 0) {
                  System.out.println("No students in the database.\n");
               } else {
                  printer.printAllIDs(students, numStudents);
               }
               break;

            case 5: // Check if a student ID exists
               System.out.print("Enter the ID to search: ");
               int searchID = inputScanner.nextInt();
               boolean found = false;
               for (int i = 0; i < numStudents; i++) {
                  if (students[i].id == searchID) {
                     System.out.println("Student found: " + students[i]);
                     found = true;
                     break;
                  }
               }
               if (!found) {
                  System.out.println("Student ID not found.\n");
               }
               break;

            case 6: // Delete student data by ID
               System.out.print("Enter the ID of the student to delete: ");
               int deleteID = inputScanner.nextInt();
               boolean deleted = false;
               for (int i = 0; i < numStudents; i++) {
                  if (students[i].id == deleteID) {
                     students[i] = null; // Remove student from array
                     System.out.println("Student deleted successfully.\n");
                     deleted = true;
                     break;
                  }
               }
               if (!deleted) {
                  System.out.println("Student ID not found.\n");
               }
               break;

            case 7: // Check if database is full
               if (numStudents >= SIZE) {
                  System.out.println("Database is full.\n");
               } else {
                  System.out.println("Database has space available.\n");
               }
               break;

            default:
               System.out.println("Invalid choice. Please try again.\n");
         }
      }
   }
}
