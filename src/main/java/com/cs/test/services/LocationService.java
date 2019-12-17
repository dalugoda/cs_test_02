package com.cs.test.services;

import java.util.Queue;


public interface LocationService {

    void updateLocation(String name, String location);
    Queue getLast5Updates(String name);
}
