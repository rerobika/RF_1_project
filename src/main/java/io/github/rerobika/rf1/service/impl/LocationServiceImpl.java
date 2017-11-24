package io.github.rerobika.rf1.service.impl;

import io.github.rerobika.rf1.domain.Location;
import io.github.rerobika.rf1.repository.LocationRepository;
import io.github.rerobika.rf1.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    LocationRepository locationRepository;
    @Override
    public List<Location> getAll() {
        return null;
    }

    @Override
    public void addLocation(Location location) {
        locationRepository.save(location);
    }

    @Override
    public void removeLocation(Location location) {

    }

    @Override
    public Location getLocation(long id) {
        return null;
    }

    @Override
    public Location getLocationByName(String name) {
        return locationRepository.findByName(name);
    }
}
