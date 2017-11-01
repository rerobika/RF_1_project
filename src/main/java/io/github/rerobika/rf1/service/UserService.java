package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.User;
import io.github.rerobika.rf1.exception.EmailExistsException;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

/**
 * Created by Nandor Magyar on 9/30/17.
 *
 * Extension of the default Spring security UserDetails service
 *
 */


public interface UserService extends UserDetailsService {

    /**
     * Get every user from the system
     * @return user list
     */
    List<User> getAll();

    /**
     *  Adds a new user to the system
     * @param user user to be added
     * @return true if the transaction was successful
     */
    boolean addUser(User user);

    /**
     * Removes a user from the database
     *
     * @param user user to be removed
     * @return true if the transaction was successful
     */
    boolean removeUser(User user);


    /**
     * Returns a user with the provided id
     *
     * @param id id of the demanded user
     * @return user with the id if exists
     */
    User getUser(long id);


    /**
     * Registers a new user to the database
     *
     * @param user user to be created
     */
    User register(User user) throws EmailExistsException;

    void createVerificationToken(User user, String token);

    String validateVerificationToken(String token);

    User getUser(String verificationToken);

    User getUserByEmail(final String email);
    }
