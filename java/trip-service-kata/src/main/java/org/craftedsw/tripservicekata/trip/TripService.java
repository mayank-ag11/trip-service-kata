package org.craftedsw.tripservicekata.trip;

import java.util.Collections;
import java.util.List;

import org.craftedsw.tripservicekata.exception.UserNotLoggedInException;
import org.craftedsw.tripservicekata.user.User;
import org.craftedsw.tripservicekata.user.UserSession;
import org.springframework.beans.factory.annotation.Autowired;

public class TripService {

	private final TripDAO tripDAO;
	private final UserSession userSession;

	public TripService() {
		this.tripDAO = new TripDAO();
		this.userSession = UserSession.getInstance();
	}

	@Autowired
	public TripService(TripDAO tripDAO, UserSession userSession) {
		this.tripDAO = tripDAO;
		this.userSession = userSession;
	}

	public List<Trip> getTripsByUser(User user) throws UserNotLoggedInException {

        if(loggedInUser() == null) {
			throw new UserNotLoggedInException();
		}

		// doubt, which is more readable, this or ternary?
		if(user.isFriendOf(loggedInUser())) {
			return tripDAO.tripsBy(user);
		}

		return Collections.emptyList();
	}

	private User loggedInUser() {
		return userSession.loggedInUser();
	}

}