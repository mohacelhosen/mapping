package org.learning.mapping.one2one.controller;

import lombok.AllArgsConstructor;
import org.learning.mapping.one2one.dto.UserDTO;
import org.learning.mapping.one2one.model.User;
import org.learning.mapping.one2one.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserRestController {
    private UserService userService;


    @PostMapping
    public ResponseEntity<UserDTO> saveUser(@RequestBody UserDTO userDTO) {
        UserDTO savedUser = userService.saveUser(userDTO);
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }
    @PostMapping("/user")
    public ResponseEntity<User> saveOnDB(@RequestBody User user) {
        User savedUser = userService.saveOnDB(user);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getUserInfo(@PathVariable long userId) {
        UserDTO savedUser = userService.getUserById(userId);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

    @GetMapping("/address/{addressId}")
    public ResponseEntity<?> getUserAddress(@PathVariable long addressId) {
        User savedUser = userService.getUserByAddressId(addressId);
        return new ResponseEntity<>(savedUser, HttpStatus.OK);
    }

}
