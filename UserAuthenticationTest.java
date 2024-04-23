import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

public class UserAuthenticationTest {
    private static File testFile;
    private static BufferedReader reader;

    @BeforeAll
    public static void setUp() throws IOException {
        reader = new BufferedReader(new FileReader("D:/users.txt"));
   
    }

    @AfterAll
    public static void tearDown() throws IOException {
        reader.close();
    }

    @Test
    public void testAuthenticateValidCredentials() {
        assertTrue(UserAuthentication.authenticate("admin", "password123"));
    }

    @Test
    public void testAuthenticateInvalidUsername() {
        assertFalse(UserAuthentication.authenticate("john_do", "secret321"));
    }

    @Test
    public void testAuthenticateInvalidPassword() {
        assertFalse(UserAuthentication.authenticate("alice", "pass457"));
    }

    @Test
    public void testAuthenticateInvalidCredentials() {
        assertFalse(UserAuthentication.authenticate("art_ninja","paintSplash!"));
    }
}
