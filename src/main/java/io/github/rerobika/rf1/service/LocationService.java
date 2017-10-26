package io.github.rerobika.rf1.service;

import io.github.rerobika.rf1.domain.Location;

import java.util.List;

public interface LocationService {
    public List<Location> getAll();
    public void addLocation(Location location);
    public void removeLocation(Location location);
    public  Location getLocation(long id);
}
