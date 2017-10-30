package io.github.rerobika.rf1.service.impl;

import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.domain.VerificationToken;
import io.github.rerobika.rf1.exception.EmailExistsException;
import io.github.rerobika.rf1.repository.UserRepository;
import io.github.rerobika.rf1.repository.VerificationTokenRepository;
import io.github.rerobika.rf1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;

@Service
class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private final VerificationTokenRepository tokenRepository;


    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, VerificationTokenRepository tokenRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
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
    public User register(User user) throws EmailExistsException {
        if (mailExists(user.getEmail())) {
            throw new EmailExistsException("There is an account with that email address " + user.getEmail());
        }

        user.setPassword(passwordEncoder.encode(user.getPlainPassword()));
        user.setRole("ROLE_USER");
        user.setEnabled(false);
        userRepository.save(user);
        return user;
    }

    private boolean mailExists(String email) {
        User user = userRepository.findByEmail(email);
        return user!=null;
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

    @Override
    public void createVerificationToken(User user, String token) {
        VerificationToken verificationToken = new VerificationToken(token, user);
        verificationToken.setExpiryDate(verificationToken.calculateExpiryDate());
        tokenRepository.save(verificationToken);
    }

    @Override
    public String validateVerificationToken(String token) {
        final VerificationToken verificationToken = tokenRepository.findByToken(token);
        if (verificationToken == null) {
            return "invalid";
        }

        final User user = verificationToken.getUser();
        final Calendar cal = Calendar.getInstance();
        if ((verificationToken.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            tokenRepository.delete(verificationToken);
            return "expired";
        }

        user.setEnabled(true);
        //tokenRepository.delete(verificationToken);
        userRepository.save(user);
        return "valid";
    }

    @Override
    public User getUser(final String verificationToken) {
        final VerificationToken token = tokenRepository.findByToken(verificationToken);
        if (token != null) {
            return token.getUser();
        }
        return null;
    }

}