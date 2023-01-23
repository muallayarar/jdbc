package mualla;

import java.sql.*;


public class jdbc02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        //DDL

        Class.forName("oracle.jdbc.driver.OracleDriver");

        //on sql developer by typing "SHOW Connection" you can find the url
        Connection con= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xe", "hr", "hr");

        Statement st= con.createStatement();

        //1.Example: Select the country names whose region id's are 1
        String sql1="SELECT country_name FROM countries WHERE region_id=1";

        //If you use execute() method, you will get just true or false but ı want to see the records

        //boolean r1=st.execute(sql1);
        //System.out.println("execute()method returned "+ r1+ " for select query");

        //to see the records we have another which is "executeQuery()"
        ResultSet result1=st.executeQuery(sql1);
        while(result1.next()) {
            System.out.println(result1.getString("country_name"));

        }

        //2. example:Select the country ids and country names whose region id's are greater than 2
        String sql2="SELECT country_id, country_name FROM countries WHERE region_id > 2";
        ResultSet result2= st.executeQuery(sql2);
        while(result2.next()) {
            System.out.println(result2.getString("country_name")+"---> "+result2.getString("country_id"));
        }

        //3.example: Select the company whose number of employees is the lowest from companies table
        String sql3="SELECT * FROM companies WHERE number_of_employees= (SELECT MIN(number_of_employees) FROM companies)";
        ResultSet result3=st.executeQuery(sql3);

        //Both column names or column indexes can be used inside the get methods
        while(result3.next()) {
            System.out.println(result3.getInt(1)+ " - "+ result3.getString(2)+ " - "+result3.getInt(3) );
        }



        con.close();
        st.close();
        result1.close();

    }
}
