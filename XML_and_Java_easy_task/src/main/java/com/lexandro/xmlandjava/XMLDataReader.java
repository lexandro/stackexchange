package com.lexandro.xmlandjava;

import com.lexandro.xmlandjava.domain.Location;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XMLDataReader {

    public static Map<String, Location> readLocations(String pathToFile) throws JDOMException, IOException {
        Element root = prepareXmlDatasource(pathToFile);
        return loadData(root);
    }

    private static Element prepareXmlDatasource(String pathToFile) throws JDOMException, IOException {
        SAXBuilder jdomBuilder = new SAXBuilder();
        Document jdomDocument = jdomBuilder.build(pathToFile);
        return jdomDocument.getRootElement();
    }

    private static Map<String, Location> loadData(Element root) {
        Map<String, Location> result = new HashMap<>();
        List<Element> locationProperties = root.getChildren("LOCATION_PROPERTIES");
        //
        loadLocations(result, locationProperties);
        //
        for (Element locationProperty : locationProperties) {
            List<Element> segmentProperties = locationProperty.getChildren("SEGMENT_PROPERTIES");
            Location location = result.get(locationProperty.getAttributeValue("NAME"));

            for (Element seg : segmentProperties) {
                List<Element> nextLocationElements = seg.getChildren("NEXT_LOCATION");
                for (Element next : nextLocationElements) {
                    String arrivalName = next.getAttributeValue("NAME");
                    Location nextLocation = result.get(arrivalName);
                    if (nextLocation != null) {
                        nextLocation.setLength(Integer.parseInt(next.getAttributeValue("LENGTH")));
                        nextLocation.setSpeed(Integer.parseInt(next.getAttributeValue("SPEED")));
                        location.addNextLocation(nextLocation);
                    }
//                    int L = Integer.parseInt(next.getAttributeValue("LENGTH"));
//                    int S = Integer.parseInt(next.getAttributeValue("SPEED"));
//                    timeonestep = L / S;
//                    System.out.println("  ---  Time to go : " + timeonestep + " seconds");
                }
            }


        }
        return result;
    }

    private static void loadLocations(Map<String, Location> result, List<Element> locationProperties) {
        for (Element locationProperty : locationProperties) {
            Location location = new Location();
            location.setName(locationProperty.getAttributeValue("NAME"));
            result.put(location.getName(), location);
        }
    }
}
