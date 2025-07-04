package org.craftedsw.tripservicekata.trip;

import java.util.List;

import org.craftedsw.tripservicekata.exception.CollaboratorCallException;
import org.craftedsw.tripservicekata.user.User;

public class TripDAO {

	public List<Trip> tripsBy(User user) {
		throw new CollaboratorCallException(
			"TripDAO should not be invoked on an unit test.");
	}
	
}