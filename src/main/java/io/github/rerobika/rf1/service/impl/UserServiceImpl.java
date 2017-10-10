package io.github.rerobika.rf1.service.impl;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    public User register(User user) {
        user.setRole("ROLE_USER");
        userRepository.save(user);
        return user;
    }

    @Override
    public User getUser(long id) {
        return userRepository.findOne(id);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findByEmail(email);

        if(user == null) {
            return null;
        }

        List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList(user.getRole());

        String password = user.getPassword();

        return new org.springframework.security.core.userdetails.User(email, password, auth) {
        };
    }
}