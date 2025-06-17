package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @Test
    void shouldReturnNoTrips_whenUsersAreNotFriends() {
        User loggedUser = new User();

        TripService tripService = new TripService() {
            @Override
            protected User getLoggedUser() {
                return loggedUser;
            }
        };

        User anotherUser = new User();
        anotherUser.addTrip(new Trip());

        List<Trip> trips = tripService.getTripsByUser(anotherUser);

        assertTrue(trips.isEmpty());
    }
}