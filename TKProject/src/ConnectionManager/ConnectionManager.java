package ConnectionManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

// TODO : Add comment
public class ConnectionManager {

    private static ConnectionManager connectionManager;

    private static final String URL = "jdbc:mysql://localhost:3306/emp_sys_db?useSSL=false";

    private static final String ID = "TKProject";

    private static final String PASSWORD = "TKProject";

    private Statement statement;

    private Connection connection;

    /**
     *
     * singleton pattern.
     *
     * NOTE :
     * This method just return <code>ConnectionManager</code> instance,
     * so calling this method doesn't ensure <code>ConnectionManager</code>
     * instance's member variable <code>statement</code> and <code>connection</code>
     * have it's instances.
     * If you want to get Connection or Statement instance, call
     * <code>getConnection</code> or <code>getConnectionStatement</code> method.
     *
     * @return ConnectionManager :
     * Returned instance ConnectionManager's member variable
     * <code>statement</code> and <code>connection</code> might be NULL.
     */
    public static synchronized ConnectionManager getInstance() {

        if (connectionManager == null) {

            connectionManager = new ConnectionManager();

        }

        return connectionManager;

    }

    // TODO : Add comment
    public Connection getConnection() throws Exception {

        if (connection != null && !connection.isClosed()) {

            return connection;

        }

        try {

            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {

            System.out.println("There is no DRIVER " + e.getMessage());

        }

        try {

            if(connection == null || connection.isClosed()) {

                connection = DriverManager.getConnection(URL, ID, PASSWORD);

            }

        } catch(Exception e) {

            e.printStackTrace();

            connection = null;

            throw e;

        }

        return connection;

    }

    // TODO : Add Comment
    public Statement getConnectionStatement() throws Exception {

        if ( !(statement == null || statement.isClosed()) ) {

            return statement;

        }

        statement = ConnectionManager.getInstance().getConnection()
                .createStatement();

        return statement;

    }

    // TODO : Add Comment
    public static void invalidate() throws SQLException {

        if (connectionManager != null) {

            if (connectionManager.connection != null) {

                connectionManager.connection.close();

            }
            if (connectionManager.statement != null) {

                connectionManager.statement.close();

            }

        }

    }

}

