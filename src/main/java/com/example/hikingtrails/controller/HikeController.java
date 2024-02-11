package com.example.hikingtrails.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Optionals;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/hiking")
public class HikeController {
    @GetMapping("/getMapping")
    public void register() {
        try {
            File kmlFile = new File("C:\\Users\\Movedi\\Desktop\\Hiking\\trails.kml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(kmlFile);

            doc.getDocumentElement().normalize();

            // Assuming Placemark is the element containing geometry information
            NodeList placemarkList = doc.getElementsByTagName("Placemark");
            for (int temp = 0; temp < placemarkList.getLength(); temp++) {
                Element placemarkNode = (Element) placemarkList.item(temp);
                NodeList nameList = doc.getElementsByTagName("name");
                NodeList dataList = placemarkNode.getElementsByTagName("value");
                NodeList description = placemarkNode.getElementsByTagName("description");
                // Get geometry information (coordinates)
                NodeList coordinatesList = placemarkNode.getElementsByTagName("coordinates");
                String coordinates = coordinatesList.item(0).getTextContent();
                System.out.println(nameList.item(temp).getTextContent());
                System.out.println(description.item(temp).getTextContent());

//                System.out.println("Coordinates: " + coordinates);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
