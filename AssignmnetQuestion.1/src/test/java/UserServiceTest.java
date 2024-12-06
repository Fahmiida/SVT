import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
//QuestionNo.8
class UserServiceTest {
    @Test
    void testFindUserById() {

        UserRepository userRepositoryMock = mock(UserRepository.class);
        User mockUser = new User(1, "Fahmida");
        when(userRepositoryMock.findById(1)).thenReturn(mockUser);


        UserService userService = new UserService(userRepositoryMock);
        User result = userService.findUserById(1);
        assertNotNull(result);
        assertEquals("Fahmida", result.getName());


        verify(userRepositoryMock).findById(1);
    }
}

