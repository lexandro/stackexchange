package com.lexandro.xmlandjava;

import com.lexandro.xmlandjava.domain.Location;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Main {

    //    private static final String XML_ROUTES = "XML_and_Java_easy_task/target/classes/routes_small.xml";
    private static final String XML_ROUTES = "XML_and_Java_easy_task/target/classes/routes_full.xml";
    static Element root;

    public static void main(String[] args) throws JDOMException, IOException {

        Map<String, Location> locations = XMLDataReader.readLocations(XML_ROUTES);
        System.out.println(listStationTimes(locations.get("STN10"), new LinkedList<Location>()));
    }

    private static List<Location> listStationTimes(Location location, List<Location> route) {
        route.add(location);
        for (Location nextLocation : location.getNextLocations()) {
            if (nextLocation != null && !route.contains(nextLocation)) {
                listStationTimes(nextLocation, route);
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
