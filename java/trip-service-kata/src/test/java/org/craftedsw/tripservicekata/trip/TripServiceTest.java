package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.craftedsw.tripservicekata.trip.UserBuilder.newUser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TripServiceTest {
    @Test()
    void shouldFail_whenUserIsNotLoggedIn() {
        UserSession userSession = mock(UserSession.class);
        TripService tripService = new TripService(mock(TripDAO.class), userSession);

        when(userSession.loggedInUser()).thenReturn(null);

        assertThrows(UserNotLoggedInException.class, () -> {
            tripService.getTripsByUser(null);
        });
    }

    @Test
    void shouldReturnNoTrips_whenUsersAreNotFriends() {
        UserSession userSession = mock(UserSession.class);
        TripService tripService = new TripService(mock(TripDAO.class), userSession);

        User loggedInUser = new User();
        when(userSession.loggedInUser()).thenReturn(loggedInUser);

        User anotherUser = newUser()
            .friendsWith(new User())
            .withTrips(new Trip())
            .build();

        List<Trip> trips = tripService.getTripsByUser(anotherUser);

        assertTrue(trips.isEmpty());
    }

    @Test
    void shouldReturnTrips_whenUsersAreFriends() {
        TripDAO tripDAO = mock(TripDAO.class);
        UserSession userSession = mock(UserSession.class);
        TripService tripService = new TripService(tripDAO, userSession);

        User loggedInUser = new User();
        User anotherUser = newUser()
            .friendsWith(loggedInUser)
            .withTrips(new Trip(), new Trip())
            .build();

        when(userSession.loggedInUser()).thenReturn(loggedInUser);
        when(tripDAO.tripsBy(anotherUser)).thenReturn(anotherUser.trips());

        List<Trip> trips = tripService.getTripsByUser(anotherUser);

        assertEquals(2, trips.size());
    }

}
