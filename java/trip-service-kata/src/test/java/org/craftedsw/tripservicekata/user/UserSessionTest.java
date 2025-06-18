package org.craftedsw.tripservicekata.user;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserSessionTest {
    @Test
    void shouldThrowException_whileFindingLoggedInUser() {
        UserSession userSession = UserSession.getInstance();

        assertThrows(CollaboratorCallException.class, userSession::loggedInUser);
    }
}