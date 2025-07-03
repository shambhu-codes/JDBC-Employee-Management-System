import java.sql.*;
import java.util.Scanner;

public class JDBC {
    static Connection con;
    static Scanner sc = new Scanner(System.in);

    public static void dataBaseConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        System.out.print("Enter MySQL Database Name Already Exist in DataBase:(e.g., company): ");
        String dbname = sc.nextLine();
        String URL = "jdbc:mysql://localhost:3306/" + dbname;
        String userName = "root";
        System.out.print("Enter MySQL Password for 'root': ");
        String password = sc.nextLine();

        con = DriverManager.getConnection(URL, userName, password);
        if (con.isClosed()) {
            System.out.println("Database Connection Failed");
        } else {
            System.out.println("Database Connection Created!");
        }
    }

    public static void createTable() throws SQLException {
        String q = "CREATE TABLE IF NOT EXISTS employee(empid INT PRIMARY KEY AUTO_INCREMENT, empname VARCHAR(30), salary INT)";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(q);
        System.out.println("Table Created or Already Exists.");
    }

    public static void insertDataTable() throws SQLException {
        String q = "INSERT INTO employee(empname, salary) VALUES(?, ?)";
        sc.nextLine(); // Clear buffer
        System.out.print("Enter Employee Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Salary of Employee: ");
        int sal = sc.nextInt();

        PreparedStatement pstmt = con.prepareStatement(q);
        pstmt.setString(1, name);
        pstmt.setInt(2, sal);
        int c = pstmt.executeUpdate();

        if (c > 0) {
            System.out.println("Data Inserted");
        } else {
            System.out.println("Data Not Inserted");
        }
    }

    public static void updateDataTable() throws SQLException {
        String q = "UPDATE employee SET empname=? WHERE empid=?";
        System.out.print("Enter the Employee ID to Update: ");
        int id = sc.nextInt();
        sc.nextLine(); // Clear buffer
        System.out.print("Enter New Employee Name: ");
        String name = sc.nextLine();

        PreparedStatement pstmt = con.prepareStatement(q);
        pstmt.setString(1, name);
        pstmt.setInt(2, id);
        int c = pstmt.executeUpdate();

        if (c > 0) {
            System.out.println("Updated Successfully!");
        } else {
            System.out.println("No Employee Found with Given ID");
        }
    }

    public static void selectDataTable() throws SQLException {
        String q = "SELECT * FROM employee";
        Statement stmt = con.createStatement();
        ResultSet set = stmt.executeQuery(q);

        System.out.printf("%-6s | %-20s | %-10s\n", "EmpID", "EmpName", "Salary");
        System.out.println("-----------------------------------------------------");

        while (set.next()) {
            int id = set.getInt("empid");
            String name = set.getString("empname");
            int sal = set.getInt("salary");
            System.out.printf("%-6d | %-20s | %-10d\n", id, name, sal);
        }
    }

    public static void deleteDataTable() throws SQLException {
        String q = "DELETE FROM employee WHERE empid=?";
        System.out.print("Enter the Employee ID to Delete: ");
        int id = sc.nextInt();

        PreparedStatement pstmt = con.prepareStatement(q);
        pstmt.setInt(1, id);
        int deleted = pstmt.executeUpdate();

        if (deleted > 0) {
            System.out.println("Employee Deleted Successfully");
        } else {
            System.out.println(" No Employee Found with Given ID");
        }
    }

    public static void dropTable() throws SQLException {
        String q = "DROP TABLE IF EXISTS employee";
        Statement stmt = con.createStatement();
        stmt.executeUpdate(q);
        System.out.println("Table Dropped Successfully.");
    }

    public static void main(String args[]) {
        try {
            dataBaseConnection();
            int op;

            do {
                System.out.println("\n * * * DATABASE MANAGEMENT MENU * * *");
                System.out.println("1. Create Table");
                System.out.println("2. Insert Data into Table");
                System.out.println("3. Update Data in Table");
                System.out.println("4. View Data from Table");
                System.out.println("5. Delete Data from Table");
                System.out.println("6. Drop Table");
                System.out.println("7. Exit");
                System.out.print("Choose an Option (1-7): ");
                op = sc.nextInt();

                switch (op) {
                    case 1:
                        createTable();
                        break;
                    case 2:
                        insertDataTable();
                        break;
                    case 3:
                        updateDataTable();
                        break;
                    case 4:
                        selectDataTable();
                        break;
                    case 5:
                        deleteDataTable();
                        break;
                    case 6:
                        dropTable();
                        break;
                    case 7:
                        System.out.println(" Thank you for using the database.");
                        break;
                    default:
                        System.out.println(" Invalid Option. Choose between 1-7.");
                }

            } while (op != 7);

            con.close();
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }
}
