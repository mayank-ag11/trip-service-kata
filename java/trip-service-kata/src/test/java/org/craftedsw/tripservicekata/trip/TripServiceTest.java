package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TripServiceTest {

    @Test()
    void shouldThrowAnException_whenUserIsNotLoggedIn() {
        TripService tripServiceWithNullUser = new TripService() {
            @Override
            protected User getLoggedUser() {
                return null;
            }
        };

        assertThrows(UserNotLoggedInException.class, () -> {
            tripServiceWithNullUser.getTripsByUser(null);
        });
    }
}