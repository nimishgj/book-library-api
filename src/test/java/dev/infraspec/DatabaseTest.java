package dev.infraspec;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class DatabaseTest {
    @Test
    @DisplayName("Successfully Connected To Database")
    public void testConnectSuccess() throws ClassNotFoundException, SQLException, SQLException {
        Database db = new Database("jdbc:mysql://localhost:3306/library", "root", "1234");
        db.connect();
        assertNotNull(db.getConnection());
    }

    @Test
    @DisplayName("Unsuccessful connection for invalid Credentials")
    public void testConnect_invalidCredentials() {
        assertThrows(SQLException.class, () -> {
            Database db = new Database("jdbc:mysql://localhost:3306/testdb", "invaliduser", "wrongpassword");
            db.connect();
        });
    }

    @Test
    @DisplayName("Get the connection for the Database")
    public void testGetConnection() throws ClassNotFoundException, SQLException {
        Database db = new Database("jdbc:mysql://localhost:3306/library", "root", "1234");
        db.connect();
        Connection connection = db.getConnection();
        assertNotNull(connection);
    }

    @Test
    @DisplayName("Successfully disconnected from the database")
    public void testDisconnect() throws ClassNotFoundException, SQLException {
        Database db = new Database("jdbc:mysql://localhost:3306/library", "root", "1234");
        db.connect();
        db.disconnect();
        assertTrue(db.getConnection().isClosed());
    }

}
