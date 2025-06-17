/*
 * Copyright 2025 Smarsh Inc.
 */

package org.craftedsw.tripservicekata.trip;

import org.craftedsw.tripservicekata.user.User;

class UserBuilder {

    private User[] friends = new User[]{};
    private Trip[] trips = new Trip[]{};

    static UserBuilder newUser() {
        return new UserBuilder();
    }

    UserBuilder friendsWith(User... friends) {
        this.friends = friends;
        return this;
    }

    UserBuilder withTrips(Trip... trips) {
        this.trips = trips;
        return this;
    }

    User build() {
        User user = new User();
        addFriendsTo(user);
        addTripsTo(user);
        return user;
    }

    private void addTripsTo(User user) {
        for (Trip trip : trips) {
            user.addTrip(trip);
        }
    }

    private void addFriendsTo(User user) {
        for (User friend : friends) {
            user.addFriend(friend);
        }
    }
}
