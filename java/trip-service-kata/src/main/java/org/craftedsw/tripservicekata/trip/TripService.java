package org.craftedsw.tripservicekata.trip;

import java.util.Collections;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;

public class TripService {

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {
		User loggedUser = loggedUser();

		if(loggedUser == null) {
			throw new UserNotLoggedInException();
		}

		if(user.isFriendOf(loggedUser)) {
			return tripsBy(user);
		}

		return Collections.emptyList();
	}

	protected List<Trip> tripsBy(User user) {
		return TripDAO.findTripsByUser(user);
	}

	protected User loggedUser() {
		return UserSession.getInstance().getLoggedUser();
	}

}