package org.learning.mapping.util;

import org.learning.mapping.one2one.dto.AddressDTO;
import org.learning.mapping.one2one.dto.UserDTO;
import org.learning.mapping.one2one.model.Address;
import org.learning.mapping.one2one.model.User;

public abstract class Convert {
    public static UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setGender(user.getGender());

        Address address = user.getAddress();
        if (address != null) {
            AddressDTO addressDTO = new AddressDTO();
            addressDTO.setId(address.getId());
            addressDTO.setCountry(address.getCountry());
            addressDTO.setState(address.getState());
            addressDTO.setCity(address.getCity());
            addressDTO.setZipcode(address.getZipcode());

            userDTO.setAddress(addressDTO);
        }

        return userDTO;
    }

    public static User convertToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setGender(userDTO.getGender());

        AddressDTO addressDTO = userDTO.getAddress();
        if (addressDTO != null) {
            Address address = new Address();
            address.setId(addressDTO.getId());
            address.setCountry(addressDTO.getCountry());
            address.setState(addressDTO.getState());
            address.setCity(addressDTO.getCity());
            address.setZipcode(addressDTO.getZipcode());

            user.setAddress(address);
        }

        return user;
    }


}
