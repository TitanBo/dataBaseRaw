import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataBaseModules
{
    private static final String server = "jdbc:mysql://";
    private static final String host = "localhost";
    private static final String port = "3306";
    private static final String username = "root";
    private static final String password = "";;
    static Connection conn;
    static Statement stmt;

    public void connectDB()
    {
        try
        {
            // Verbindung aufbauen
            conn = DriverManager.getConnection(server + host + "/char_s", username, password);
            System.out.println("Verbindung erfolgreich hergestellt");
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void closeDB() throws SQLException
    {

        conn.close();
        System.out.println("Verbindung erfolgreich geschlossen");
    }
    public void sendCommand(String command)
    {
        try
        {
            stmt = conn.createStatement();
            stmt.execute(command);
            System.out.println(command);
        }
        catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public boolean createDatabase(String command) throws Exception
    {
        Statement st;
        Connection con = DriverManager.getConnection(server + host, username, password);
        st = con.createStatement();
        // Then database created
        if (st.executeUpdate(command) == 1)
        {
            System.out.println("database created");
            return true;
        }
        System.out.println("database exists");
        return false;
    }
}
