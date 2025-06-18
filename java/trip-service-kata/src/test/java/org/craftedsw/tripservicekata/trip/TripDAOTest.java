package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class TripDAOTest {
    @Test
    void shouldThrowException_whileFindingUserTrips() {
        assertThrows(CollaboratorCallException.class, () -> {
           new TripDAO().tripsBy(new User());
        });
    }
}
