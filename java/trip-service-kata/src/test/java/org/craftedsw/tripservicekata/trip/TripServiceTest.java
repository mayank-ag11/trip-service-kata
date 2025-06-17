package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TripServiceTest {

    @Test()
    void shouldThrowAnException_whenUserIsNotLoggedIn() {
        TripService tripServiceWithNullUser = new TripService() {
            @Override
            protected User loggedUser() {
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
            protected User loggedUser() {
                return loggedUser;
            }
        };

        User anotherUser = new User();
        anotherUser.addFriend(new User());
        anotherUser.addTrip(new Trip());

        List<Trip> trips = tripService.getTripsByUser(anotherUser);

        assertTrue(trips.isEmpty());
    }

    @Test
    void shouldReturnTrips_whenUsersAreFriends() {
        User loggedUser = new User();

        TripService tripService = new TripService() {
            @Override
            protected User loggedUser() {
                return loggedUser;
            }

            @Override
            protected List<Trip> tripsBy(User user) {
                return user.trips();
            }
        };

        User anotherUser = new User();
        anotherUser.addFriend(loggedUser);
        anotherUser.addTrip(new Trip());
        anotherUser.addTrip(new Trip());

        List<Trip> trips = tripService.getTripsByUser(anotherUser);

        assertEquals(2, trips.size());
    }
}