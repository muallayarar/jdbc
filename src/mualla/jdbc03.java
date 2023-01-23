package mualla;
import  java.sql.*;
public class jdbc03 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //DQL
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "hr");
        Statement st= con.createStatement();

        //Find the name and salary of the worker salary is the second highest salary from the workers table
        String sql1="SELECT name, salary FROM workers ORDER BY salary DESC OFFSET 1 ROW FETCH NEXT 1 ROW ONLY";
        ResultSet result1=st.executeQuery(sql1);
        while(result1.next()) {
            System.out.println(result1.getString(1)+ "-->"+ result1.getInt(2));
        }

        //2.way:
        String sql2="SELECT company, number_of_employees FROM companies WHERE number_of_employees=(SELECT MAX(number_of_employees) FROM companies WHERE salary <(SELECT MAX(number_of_employees FROM companies))";
        ResultSet result2 = st.executeQuery(sql2);

        while(result2.next()) {
            System.out.println(result2.getString(1)+ "-->"+ result2.getInt(2));
        }

        //2.example: Find the company names and number of employees whose number of employees is less than the average number of employees
        String sql3="SELECT compamy , number_of_employees FROM companies WHERE number_of_employees< (SELECT AVG(number_of_employees) FROM companies)";
        ResultSet result3=st.executeQuery(sql3);

        while(result3.next()) {
            System.out.println(result3.getString("company_name")+ "-->"+ result3.getInt("number_of_employees"));
        }


        st.close();
        con.close();
        result1.close();
        result2.close();
        result3.close();


    }


}
