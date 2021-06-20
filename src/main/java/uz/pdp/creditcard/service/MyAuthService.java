package uz.pdp.creditcard.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserData;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.pdp.creditcard.entity.UserData;
import uz.pdp.creditcard.repository.UserRepository;

import java.util.ArrayList;

@Service
public class MyAuthService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        UserData userData = userRepository.findByUsername(username);
        if (userData != null) {
            return new User(userData.getUsername(), passwordEncoder.encode(userData.getPassword()), new ArrayList<>());
        }

        throw new UsernameNotFoundException("userData not found");
    }

}
