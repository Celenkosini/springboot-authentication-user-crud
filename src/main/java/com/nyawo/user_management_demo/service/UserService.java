package com.nyawo.user_management_demo.service;

import java.util.List;

import com.nyawo.user_management_demo.DTO.UserDTO;
import com.nyawo.user_management_demo.model.User;

public interface UserService {
    //convert User to UserDTO
   public UserDTO convertToDTO(User user);

    //get all users
   public List<UserDTO> getAllUsers();
    //create a user
    public String createUser(User user);
    //update a user
    public UserDTO updateUser(Long id, User user);
    //delete a user
    public String deleteUser(Long id);
    //get a user by id
    public UserDTO getUserById(Long id);
    //get a user by email
    public UserDTO getUserByEmail(String email);
    //get a user by username
    public UserDTO getUserByUsername(String username);
    
     


}
