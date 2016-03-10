import java.sql.*;

/**
 * Created by Stan on 22-2-2016.
 */

public class DBConnect {

    //Properties
    //Connection Strings (host, username, password) have been left out for safety reasons.
    private static final String host = "";
    private static final String username =  """;
    private static final String password = "";
    private static Connection con;
    private static Statement statement;
    private static final String query = "SELECT * FROM Test";
    private static ResultSet data;

    public static void main(String[] args) {

        try {

            con = null;

            con = createConnection();

            data = getAllRows(con);

            System.out.println("Amount of rows retrieved: " + getRowCount(data));

        }

        catch (SQLException ex) {

            System.out.println(ex.getMessage());

        }

    }

    //Creates and returns a connection.
    private static Connection createConnection() throws SQLException{

        System.out.println("Registering Oracle Database Driver.");

        try {

            //Register the Oracle jdbc Driver.
            Class.forName("oracle.jdbc.driver.OracleDriver");

        }

        catch (ClassNotFoundException ex) {

            System.out.println(ex.getMessage() + "ClassNotFoundException");

        }

        System.out.println("Driver added.");

        //Create the connection.
        con = DriverManager.getConnection(host, username, password);

        System.out.println("Database connection established.");

        return con;

    }

    //Gets and returns all rows from the database.
    private static ResultSet getAllRows(Connection con) throws SQLException {

        //Make sure the ResultSet is scrollable.
        statement = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_READ_ONLY);

        //Execute the SQL query.
        data = statement.executeQuery(query);

        return data;

    }

    //Determines the amount of rows in a ResultSet.
    public static int getRowCount(ResultSet res) {

        int totalRows;

        try {

            //Move to last entry in ResultSet.
            res.last();

            //Get the current row
            totalRows = res.getRow();

            //Move to the first entry in the Resultset
            res.beforeFirst();

        }

        catch(SQLException ex)  {

            return 0;

        }

        return totalRows ;
    }
}
