package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.craftedsw.tripservicekata.trip.UserBuilder.newUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TripServiceTest {

    @Test()
    void shouldThrowAnException_whenUserIsNotLoggedIn() {
        TripService tripServiceWithNullUser = new TripService() {
            @Override
            protected User loggedInUser() {
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
            protected User loggedInUser() {
                return loggedUser;
            }
        };

        User anotherUser = newUser()
            .friendsWith(new User())
            .withTrips(new Trip())
            .build();

        List<Trip> trips = tripService.getTripsByUser(anotherUser);

        assertTrue(trips.isEmpty());
    }

    @Test
    void shouldReturnTrips_whenUsersAreFriends() {
        User loggedUser = new User();

        TripService tripService = new TripService() {
            @Override
            protected User loggedInUser() {
                return loggedUser;
            }

            @Override
            protected List<Trip> tripsBy(User user) {
                return user.trips();
            }
        };

        User anotherUser = newUser()
            .friendsWith(loggedUser)
            .withTrips(new Trip(), new Trip())
            .build();

        List<Trip> trips = tripService.getTripsByUser(anotherUser);

        assertEquals(2, trips.size());
    }

}