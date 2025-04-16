package com.nyawo.user_management_demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;



import com.nyawo.user_management_demo.DTO.UserDTO;
import com.nyawo.user_management_demo.model.User;
import com.nyawo.user_management_demo.service.userImpl.UserServiceImpl;
import org.mockito.junit.jupiter.MockitoExtension;      
import com.nyawo.user_management_demo.repository.UserRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.password.PasswordEncoder;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

  @InjectMocks
  UserServiceImpl userService;

  @Mock
    PasswordEncoder passwordEncoder;

  
    @Test
    void testConvertToDTO() {
        // Arrange
        User user = new User();
        UserDTO userDTO = new UserDTO();
        user.setUserName("testuser"); 
        user.setEmail("userEmail"); 
        user.setPassword("userPassword");

        // Act
        userDTO = userService.convertToDTO(user);

        // Assert
        assertEquals("testuser", userDTO.getUserName());
        assertEquals("userEmail", userDTO.getEmail());
    }

    @Test
    void testCreateUser() {
        // Arrange
        User user = new User();
        user.setUserName("testuser"); 
        user.setEmail("userEmail"); 
        user.setPassword("userPassword");

        when(passwordEncoder.encode("userPassword")).thenReturn("encodedPassword");


        //Act
        String result = userService.createUser(user);

        // Assert
        assertEquals("user created", result);

    }

    @Test
    void testDeleteUser() {
        // Arrange
        User user = new User();
        user.setUserName("testuser"); 
        user.setEmail("userEmail"); 
        user.setPassword("userPassword");

        // Act
        String result = userService.createUser(user);
        // Assert
        assertEquals("user created ", result);
        result = userService.deleteUser(user.getId());   
        assertEquals("user deleted", result);


    }

    @Test
    void testGetAllUsers() {
        // Arrange
        User user1 = new User();
        user1.setId(1L);
        user1.setUserName("user1");
        user1.setEmail("user1@example.com");

        User user2 = new User();
        user2.setId(2L);
        user2.setUserName("user2");
        user2.setEmail("user2@example.com");

         List<User> users = List.of(user1, user2);
        when(userRepository.findAll()).thenReturn(users);

        // Act
        List<UserDTO> userDTOs = userService.getAllUsers();

        // Assert
        assertEquals(2, userDTOs.size());
        assertEquals("user1", userDTOs.get(0).getUserName());
        assertEquals("user2", userDTOs.get(1).getUserName());

    }

    @Test
    void testGetUserByEmail() {
         // Arrange
         String email = "test@example.com";
         User user = new User();
         user.setId(1L);
         user.setUserName("testuser");
         user.setEmail(email);
         when(userRepository.findByEmail(email)).thenReturn(user);
 
         // Act
         UserDTO userDTO = userService.getUserByEmail(email);
 
         // Assert
         assertEquals(1L, userDTO.getId());
         assertEquals("testuser", userDTO.getUserName());
         assertEquals(email, userDTO.getEmail());

    }

    @Test
    void testGetUserById() {
        // Arrange
        long userId = 1L;
        User user = new User();
        user.setId(userId);
        user.setUserName("testuser");
        user.setEmail("userEmail");
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        // Act
        UserDTO userDTO = userService.getUserById(userId);

        
         // Assert
         assertEquals(userId, userDTO.getId());
         assertEquals("testuser", userDTO.getUserName());
         assertEquals("userEmail", userDTO.getEmail());


    }

    @Test
    void testGetUserById_NonExistingUser() {
        // Arrange
        long userId = 1L;
        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        // Act
        UserDTO userDTO = userService.getUserById(userId);

        // Assert
        assertEquals(null, userDTO);
    }

    @Test
    void testGetUserByUsername() {
         // Arrange
         String username = "testuser";
         User user = new User();
         user.setId(1L);
         user.setUserName(username);
         user.setEmail("test@example.com");
         when(userRepository.findByUserName(username)).thenReturn(user);
 
         // Act
         UserDTO userDTO = userService.getUserByUsername(username);
 
         // Assert
         assertEquals(1L, userDTO.getId());
         assertEquals(username, userDTO.getUserName());
         assertEquals("test@example.com", userDTO.getEmail());

    }

    @Test
    void testUpdateUser() {
         // Arrange
         long userId = 1L;
         User existingUser = new User();
         existingUser.setId(userId);
         existingUser.setUserName("olduser");
         existingUser.setEmail("old@example.com");
         existingUser.setPassword("oldpassword");
 
         User updatedUser = new User();
         updatedUser.setUserName("newuser");
         updatedUser.setEmail("new@example.com");
         updatedUser.setPassword("newpassword");

         when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser));
        when(userRepository.save(existingUser)).thenReturn(updatedUser); // Mock the save and return the updated user

        // Act
        UserDTO resultDTO = userService.updateUser(userId, updatedUser);

        // Assert
        assertEquals("newuser", resultDTO.getUserName());
        assertEquals("new@example.com", resultDTO.getEmail());
        // Password should not be in the DTO, so we don't assert it here.
        // Verify that save was called
        // Mockito.verify(userRepository).save(existingUser);

    }
}
