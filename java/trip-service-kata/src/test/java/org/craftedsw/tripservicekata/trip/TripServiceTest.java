package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.craftedsw.tripservicekata.trip.UserBuilder.newUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TripServiceTest {

    private TripDAO tripDAO;

    @BeforeEach
    void setUp() {
        tripDAO = mock(TripDAO.class);
    }

    @Test()
    void shouldThrowAnException_whenUserIsNotLoggedIn() {
        TripService tripServiceWithNullUser = new TripService(tripDAO) {
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

        TripService tripService = new TripService(tripDAO) {
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

        TripService tripService = new TripService(tripDAO) {
            @Override
            protected User loggedInUser() {
                return loggedUser;
            }

        };

        User anotherUser = newUser()
            .friendsWith(loggedUser)
            .withTrips(new Trip(), new Trip())
            .build();

        when(tripDAO.tripsBy(anotherUser)).thenReturn(anotherUser.trips());

        List<Trip> trips = tripService.getTripsByUser(anotherUser);

        assertEquals(2, trips.size());
    }

}