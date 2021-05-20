/**
 *  Copyright (C) 2021  the original author or authors.
 *
 * 		This program is free software: you can redistribute it and/or modify
 * 		it under the terms of the GNU General Public License as published by
 * 		the Free Software Foundation, either version 3 of the License, or
 * 		(at your option) any later version.
 *
 * 		This program is distributed in the hope that it will be useful,
 * 		but WITHOUT ANY WARRANTY; without even the implied warranty of
 * 		MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * 		GNU General Public License for more details.
 *
 * 		You should have received a copy of the GNU General Public License
 * 		along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package de.hsalbsig.HonigruehrmaschineSteuerung.service;

import de.hsalbsig.HonigruehrmaschineSteuerung.dao.UserRepository;
import de.hsalbsig.HonigruehrmaschineSteuerung.dto.UserDTO;
import de.hsalbsig.HonigruehrmaschineSteuerung.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * @author
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

   @Autowired
   UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findUserByUsername(s);
        optionalUser.orElseThrow( () -> new UsernameNotFoundException("Bad Credentials"));
        return optionalUser.get();
    }

    public void createUser(UserDTO userDTO) {
        User user = new User();
        user.setUsername(userDTO.getUsername().strip().toLowerCase());
        user.setUsername(userDTO.getUsername().replaceAll("\\s+", "").toLowerCase());
        user.setPassword(new BCryptPasswordEncoder().encode(userDTO.getPassword()));
        userRepository.save(user);
    }

    public boolean userExists(String username) {
        return userRepository.existsById(username);
    }

}
