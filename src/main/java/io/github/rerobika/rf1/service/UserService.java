package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.User;

import java.util.List;

/**
 * Created by Nandor Magyar on 9/30/17.
 */
public interface UserService {
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
}
