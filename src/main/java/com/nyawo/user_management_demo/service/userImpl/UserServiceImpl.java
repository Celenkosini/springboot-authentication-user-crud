package com.nyawo.user_management_demo.service.userImpl;

import org.springframework.beans.factory.annotation.Autowired;

import com.nyawo.user_management_demo.repository.UserRepository;
import com.nyawo.user_management_demo.service.UserService;
import com.nyawo.user_management_demo.DTO.UserDTO;
import com.nyawo.user_management_demo.model.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    //constructor injection
    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    //convert User to UserDTO
    public UserDTO convertToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setUserName(user.getUserName());
        userDTO.setEmail(user.getEmail());
        return userDTO;
   }
//create a user

public String createUser(User user) {
    //check if user already exists
    if (userRepository.findByEmail(user.getEmail()) != null) {
        return "user already exists";
    }else{
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        logger.info("creating user with username: " + user.getUserName() + " and email: " + user.getEmail());
        userRepository.save(user);
        return "user created";
    }
   
}

    //get all users
    public  List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO).collect(Collectors.toList());
    }
    //get a user by id
    public  UserDTO getUserById(Long id) {
        User user = userRepository.findById(id).orElse(null);
        return user != null ? convertToDTO(user) : null;
    }
    //get a user by email
    public  UserDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null ? convertToDTO(user) : null;
    }
    //get a user by username
    public  UserDTO getUserByUsername(String username) {
        User user = userRepository.findByUserName(username);
        return user != null ? convertToDTO(user) : null;
    }
    //update a user
    public  UserDTO updateUser(Long id, User user) {
        User existingUser = userRepository.findById(id).orElse(null);
        if (existingUser != null) {
            existingUser.setUserName(user.getUserName());
            existingUser.setEmail(user.getEmail());
            existingUser.setPassword(user.getPassword());
            return convertToDTO(userRepository.save(existingUser));
        }
        return null;
    }
    //delete a user
    public String deleteUser(Long id) {
        userRepository.deleteById(id);
        return "user deleted";
    }

}
