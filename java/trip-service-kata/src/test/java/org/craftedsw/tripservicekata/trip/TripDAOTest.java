package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class TripDAOTest {
    @Test
    void shouldFail_whileFindingUserTrips() {
        User user = new User();
        TripDAO tripDAO = new TripDAO();
        assertThrows(CollaboratorCallException.class, () -> {
           tripDAO.tripsBy(user);
        });
    }
}
