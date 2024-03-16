package org.learning.mapping.one2one.service;

import lombok.AllArgsConstructor;
import org.learning.mapping.one2one.dto.UserDTO;
import org.learning.mapping.one2one.model.Address;
import org.learning.mapping.one2one.model.User;
import org.learning.mapping.one2one.repository.AddressRepository;
import org.learning.mapping.one2one.repository.UserRepository;
import org.learning.mapping.util.Convert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private AddressRepository addressRepository;
    private final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserDTO saveUser(UserDTO userDTO){
        logger.info("userDTO before setting address:: {}", userDTO);
        logger.info("addressDTO before setting user:: {}", userDTO.getAddress());

        // Convert UserDTO to User
        User user = Convert.convertToUser(userDTO);

        // Set the bidirectional relationship
        Address address = user.getAddress();
        address.setUser(user);

        logger.info("user after setting address:: {}", user);
        logger.info("address after setting user:: {}", user.getAddress());

        // Save the User entity, which will cascade to the associated Address entity
        User savedUser = userRepository.save(user);

        // Convert saved User back to UserDTO and return
        return Convert.convertToUserDTO(savedUser);
    }


    public User saveOnDB(User user){
        logger.info("user:: {}",user);
        user.getAddress().setUser(user);
       return userRepository.save(user);
    }



    public UserDTO getUserById(Long userId) {
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return Convert.convertToUserDTO(user);
        } else {
            // Handle case when user with given ID is not found
            // For example, throw an exception or return null
            return null;
        }
    }

    public User getUserByAddressId(long addressId) {
        Optional<Address> addressOptional = addressRepository.findById(addressId);
        if (addressOptional.isPresent()) {
            Address address = addressOptional.get();
            logger.info("Address:: {}",address);
            return address.getUser();
        } else {
            // Handle case when user with given ID is not found
            // For example, throw an exception or return null
            return null;
        }
    }
}
