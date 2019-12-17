package com.cs.test.services.impl;

import com.cs.test.model.Location;
import com.cs.test.services.LocationService;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.*;

@Service
public class LocationServiceImpl implements LocationService {

    HashMap<String, Queue> vehicles = new HashMap<String, Queue>();
    private static String DIR_LOCATION = "C:\\Users\\Sandupa Dalugoda\\Desktop\\CS_Test\\";


    @Override
    public void updateLocation(String id, String location) {

        if(!vehicles.containsKey(id)){
            Queue<Location> locations = new LinkedList<>();

            locations.add(new Location(location));
            vehicles.put(id, locations);

        } else {
            Queue locations = vehicles.get(id);
            if(locations.size() == 5){
                locations.remove();
            }

            locations.add(new Location(location));
        }

        File file = new File( DIR_LOCATION+ id + ".txt");

        try {

            BufferedWriter out = new BufferedWriter(
                    new FileWriter(file, true));
            out.write(location);
            out.write("\n");
            out.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public Queue getLast5Updates(String id) {

        //Assuming that last status will only be available in the memory (For performance issue)

        File file = new File(DIR_LOCATION+ id + ".txt");

        try {

            if (!file.exists()) {
                throw new Exception("No file found ");
            }

            return vehicles.get(id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }
}
