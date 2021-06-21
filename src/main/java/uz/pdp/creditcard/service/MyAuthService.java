package uz.pdp.creditcard.service;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserData;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
//import uz.pdp.creditcard.entity.UserData;
//import uz.pdp.creditcard.repository.UserRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MyAuthService implements UserDetailsService {

    @Autowired
    PasswordEncoder passwordEncoder;

//    @Autowired
//    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        UserData userData = userRepository.findByUsername(username);

        List<User> users = new ArrayList<>(
                Arrays.asList(
                        new User("hello1", passwordEncoder.encode("hi1"), new ArrayList<>()),
                        new User("hello2", passwordEncoder.encode("hi2"), new ArrayList<>()),
                        new User("hello3", passwordEncoder.encode("hi3"), new ArrayList<>())
                )
        );

        for (User user : users) {

            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        throw new UsernameNotFoundException("user not found");
    }

}
