import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
//QuestionNo.3
public class LoginManagerTest {


    private AuthenticationService mockAuthService;
    private LoginManager loginHandler;

    @BeforeEach
    void initialize() {
        mockAuthService = mock(AuthenticationService.class);
        loginHandler = new LoginManager(mockAuthService);
    }

    @Test
    @DisplayName("Login succeeds for valid username and password")
    void shouldLoginWithValidCredentials() throws Exception {
        String username = "valid_user";
        String password = "password123";

        when(mockAuthService.authenticate(username, password)).thenReturn(true);

        boolean isLoggedIn = loginHandler.login(username, password);

        assertTrue(isLoggedIn, "The login should be successful with correct credentials.");
        verify(mockAuthService).authenticate(username, password);
    }

    @Test
    @DisplayName("Login fails for incorrect username or password")
    void shouldFailForInvalidCredentials() throws Exception {
        String username = "wrong_user";
        String password = "wrong_pass";

        when(mockAuthService.authenticate(username, password)).thenReturn(false);

        boolean isLoggedIn = loginHandler.login(username, password);

        assertFalse(isLoggedIn, "The login should fail with incorrect credentials.");
        verify(mockAuthService).authenticate(username, password);
    }

    @Test
    @DisplayName("Throws exception when username or password is null")
    void shouldThrowExceptionForNullCredentials() {
        Exception usernameException = assertThrows(Exception.class,
                () -> loginHandler.login(null, "pass"),
                "Expected an exception for null username.");
        assertEquals("Username and password cannot be null", usernameException.getMessage());

        Exception passwordException = assertThrows(Exception.class,
                () -> loginHandler.login("user", null),
                "Expected an exception for null password.");
        assertEquals("Username and password cannot be null", passwordException.getMessage());
    }
}
