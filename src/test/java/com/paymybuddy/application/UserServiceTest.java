package com.paymybuddy.application;

import com.paymybuddy.application.DTO.UserDTO;
import com.paymybuddy.application.models.User;
import com.paymybuddy.application.repository.UserRepository;
import com.paymybuddy.application.services.Impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Test
    public void saveUserTest() {
        //GIVEN
        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName("test");
        userDTO.setLastName("test");
        userDTO.setEmail("test@test.test");
        userDTO.setPassword("test");

        //WHEN
        userService.saveUser(userDTO);

        //THEN
        verify(userRepository, times(1)).save(any(User.class));
    }
}
