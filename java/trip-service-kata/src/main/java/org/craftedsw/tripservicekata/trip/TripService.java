package org.craftedsw.tripservicekata.trip;

import java.util.Collections;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	private final TripDAO tripDAO;

	public TripService(TripDAO tripDAO) {
		this.tripDAO = tripDAO;
	}

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {

        if(loggedInUser() == null) {
			throw new UserNotLoggedInException();
		}

		if(user.isFriendOf(loggedInUser())) {
			return tripDAO.tripsBy(user);
		}

		return Collections.emptyList();
	}

	protected User loggedInUser() {
		return UserSession.getInstance().getLoggedUser();
	}

}