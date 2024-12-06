import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
//QuestionNo.1
public class UserManagerTest {

    private UserService mockUserService;
    private UserManager userManager;

    @BeforeEach
    public void initialize() {
        mockUserService = mock(UserService.class);
        userManager = new UserManager(mockUserService);
    }

   @Test
    @DisplayName("Returns true for valid username and successful save")
    public void shouldRegisterValidUser() {
        String username = "TestUser";
        String password = "securePass";

        when(mockUserService.usernameExists(username)).thenReturn(false);
        when(mockUserService.saveUser(username, password)).thenReturn(true);

        boolean isRegistered = userManager.registerUser(username, password);

        assertTrue(isRegistered, "The registration should succeed for valid inputs.");
        verify(mockUserService).usernameExists(username);
        verify(mockUserService).saveUser(username, password);
    }

    @Test
    @DisplayName("Returns false for a duplicate username")
    public void shouldFailForDuplicateUsername() {
        String username = "DuplicateUser";
        String password = "password123";

        when(mockUserService.usernameExists(username)).thenReturn(true);

        boolean isRegistered = userManager.registerUser(username, password);

        assertFalse(isRegistered, "The registration should fail for an already existing username.");
        verify(mockUserService).usernameExists(username);
        verify(mockUserService, never()).saveUser(anyString(), anyString());
    }

    @Test
    @DisplayName("Returns false when save operation fails")
    public void shouldFailIfSaveFails() {
        String username = "FailUser";
        String password = "passFail";

        when(mockUserService.usernameExists(username)).thenReturn(false);
        when(mockUserService.saveUser(username, password)).thenReturn(false);

        boolean isRegistered = userManager.registerUser(username, password);

        assertFalse(isRegistered, "The registration should fail if the save operation fails.");
        verify(mockUserService).usernameExists(username);
        verify(mockUserService).saveUser(username, password);
    }
}
