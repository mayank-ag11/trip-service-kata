package org.craftedsw.tripservicekata.user;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class UserTest {
    @Test
    void shouldReturnTrue_whenUserIsAFriend() {
        User user = new User();
        User anotherUser = new User();
        user.addFriend(anotherUser);

        assertTrue(user.isFriendOf(anotherUser));
    }

    @Test
    void shouldReturnFalse_whenUserIsNotAFriend() {
        User user = new User();
        User anotherUser = new User();

        assertFalse(user.isFriendOf(anotherUser));
    }
}
