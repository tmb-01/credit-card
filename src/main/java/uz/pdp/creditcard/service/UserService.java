package uz.pdp.creditcard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.creditcard.entity.UserData;
import uz.pdp.creditcard.repository.UserRepository;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public List<UserData> getAll() {
        return userRepository.findAll();
    }

    public UserData getById(Long id) {
        return userRepository.findById(id).get();
    }

}
