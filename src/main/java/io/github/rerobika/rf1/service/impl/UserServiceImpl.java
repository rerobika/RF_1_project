package io.github.rerobika.rf1.service.impl;

import org.springframework.stereotype.Service;
import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.repository.UserRepository;
import io.github.rerobika.rf1.service.UserService;

import java.util.List;

@Service
class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return (List<User>) userRepository.findAll();
    }

    @Override
    public boolean addUser(User user) {
        User addedUser = userRepository.save(user);
        boolean successful = false;
        if (addedUser.getId() != 0) {
          successful = true;
        }
        return successful;
    }

    @Override
    public boolean removeUser(User user) {
        userRepository.delete(user);
        return true;
    }

    @Override
    public User getUser(long id) {
        return userRepository.findOne(id);
    }
}