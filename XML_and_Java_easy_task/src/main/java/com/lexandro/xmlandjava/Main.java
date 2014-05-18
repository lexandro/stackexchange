package com.lexandro.xmlandjava;

import com.lexandro.xmlandjava.domain.Location;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {

    //    private static final String XML_ROUTES = "XML_and_Java_easy_task/target/classes/routes_small.xml";
    private static final String XML_ROUTES = "XML_and_Java_easy_task/target/classes/routes_full.xml";
    static Element root;

    public static void main(String[] args) throws JDOMException, IOException {

        Map<String, Location> locations = XMLDataReader.readLocations(XML_ROUTES);
        // do it for one location
//        printRouteFor(locations, "STN10");
        // do it for all locations
        for (Location location : locations.values()) {
            printRouteFor(locations, location.getName());
        }

    }

    private static void printRouteFor(Map<String, Location> locations, String stationName) {
        // build up the list of stations by one route
        List<Location> oneRoute = listStationTimes(locations.get(stationName), new LinkedList<Location>());

        if (!oneRoute.isEmpty()) {


            Iterator<Location> iterator = oneRoute.iterator();
            Location routeItem;
            // get the first item
            routeItem = iterator.next();
            //
            int allTime = 0;
            int lastTime = routeItem.getTime();
            allTime += lastTime;
            System.out.println("Departure station: " + routeItem.getName());
            // remaining items
            int count = 1;
            while (iterator.hasNext()) {
                routeItem = iterator.next();
                //
                if (count < oneRoute.size() - 1) {
                    System.out.println("Arrival: " + routeItem.getName() + " --- " + "Time to go : " + lastTime);
                }
                lastTime = routeItem.getTime();
                allTime += lastTime;
                //
                count++;
            }
            // last item
            System.out.println("Arrival: " + routeItem.getName() + " --- " + " Total time : " + allTime);
        }
    }

    private static List<Location> listStationTimes(Location location, List<Location> route) {
        route.add(location);
        if (location.getNextLocations() != null) {
            for (Location nextLocation : location.getNextLocations()) {
                if (nextLocation != null && !route.contains(nextLocation)) {
                    listStationTimes(nextLocation, route);
                }
            }
        }
        return route;
    }


    public static int onestep4all() {
        //int cpt = 0;
        int timeonestep = 0;

        List<Element> location_properties = root.getChildren("LOCATION_PROPERTIES");
        for (Element loc : location_properties) {
            if (loc.getAttributeValue("NAME").startsWith("STN")) {
                System.out.println("-------------------------------------------------");
                System.out.println("            Departure Station : " + loc.getAttributeValue("NAME"));
                List<Element> segment_properties = loc.getChildren("SEGMENT_PROPERTIES");
                for (Element seg : segment_properties) {
                    List<Element> nextLocationElements = seg.getChildren("NEXT_LOCATION");
                    for (Element nextLocationElement : nextLocationElements) {
                        System.out.print("Arrival : " + nextLocationElement.getAttributeValue("NAME"));
                        int L = Integer.parseInt(nextLocationElement.getAttributeValue("LENGTH"));
                        int S = Integer.parseInt(nextLocationElement.getAttributeValue("SPEED"));
                        timeonestep = L / S;
                        System.out.println("  ---  Time to go : " + timeonestep + " seconds");
                    }
                }
            }
        }
        return timeonestep;
    }

}
