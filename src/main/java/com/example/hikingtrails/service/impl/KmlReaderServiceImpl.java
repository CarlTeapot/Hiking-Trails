package com.example.hikingtrails.service.impl;

import com.example.hikingtrails.model.Description;
import com.example.hikingtrails.model.Landmark;
import com.example.hikingtrails.model.Trail;
import com.example.hikingtrails.repository.DescriptionRepository;
import com.example.hikingtrails.repository.LandmarkRepository;
import com.example.hikingtrails.repository.TrailRepository;
import com.example.hikingtrails.service.KmlReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Qualifier("impl1")
@Service
@RequiredArgsConstructor
public class KmlReaderServiceImpl implements KmlReaderService {
   private final TrailRepository repository;
   private final DescriptionRepository descriptionRepository;
   private final LandmarkRepository landmarkRepository;
    @Override
    public void read() {
        try {
            File kmlFile = new File("trails.kml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(kmlFile);

            doc.getDocumentElement().normalize();

            // Assuming Placemark is the element containing geometry information
            NodeList folderList = doc.getElementsByTagName("Folder");

            for (int i = 0; i < folderList.getLength(); i++) {

                Element folderNode = (Element) folderList.item(i);
                NodeList placemarkList = folderNode.getElementsByTagName("Placemark");
                NodeList nameList = folderNode.getElementsByTagName("name");
                String region = nameList.item(0).getTextContent();
                for (int j = 0; j < placemarkList.getLength(); j++) {
                    Element placemarkNode = (Element) placemarkList.item(j);
                    NodeList names = placemarkNode.getElementsByTagName("name");
                    String trailName = names.item(0).getTextContent();
                    NodeList dataList = placemarkNode.getElementsByTagName("value");
                    NodeList coordinatesList = placemarkNode.getElementsByTagName("coordinates");
                    String coordinates = coordinatesList.item(0).getTextContent();
                    repository.save(createTrail("Georgia", region, trailName, dataList, coordinates));
                }

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private Trail createTrail(String country, String region, String trailName, NodeList dataList, String coordinates) {
        Trail trail = new Trail();
        trail.setCountry(country);
        trail.setRegion(region);
        trail.setTrailName(trailName);
        trail.setTrailCode(getData(dataList,0));
        trail.setRecommended(getData(dataList,1));
        trail.setMunicipality(getData(dataList,2));
        trail.setType(getData(dataList,3));
        trail.setTransportation(getData(dataList,4));
        trail.setCategory(getData(dataList,5));
        trail.setDifficulty(getData(dataList,6));
        trail.setSeason(getData(dataList,7));
        trail.setLength(getData(dataList,8));
        trail.setDuration(getData(dataList,9));
        trail.setElevation(getData(dataList,10));

        addLandMark(getData(dataList,11),trail);

        trail.setInfrastructure(getData(dataList,12));
        trail.setMarked(getData(dataList,13));
        trail.setCrossesAgency(getData(dataList,14));
        trail.setOrganization(getData(dataList,15));

        Description description = new Description();
        description.setText(getData(dataList,16));
        descriptionRepository.save(description);
        trail.setDescription(description);
        System.out.println(coordinates);
        trail.setCoordinates(coordinates);
        return trail;
    }
    private void addLandMark(String landmarks, Trail trail) {
        String[] landMarks;
        if (landmarks.contains(";"))
             landMarks = landmarks.split(";");
        else
            landMarks = landmarks.split(",");

        Set<Landmark> landmarkSet = new HashSet<>();
        for (String landmark : landMarks) {
            Landmark landmark1;
            Optional<Landmark> optional= landmarkRepository.getLandmarkByName(landmark);
            landmark1 = optional.orElseGet(() -> new Landmark(landmark));
            Set<Trail> temp = landmark1.getTrails();
            temp.add(trail);
            landmark1.setTrails(temp);
            landmarkSet.add(landmark1);
            landmarkRepository.save(landmark1);
        }
        trail.setLandmarks(landmarkSet);
    }
    private String getData(NodeList node, int i) {

        Optional<Node> item = Optional.ofNullable(node.item(i));
        if (item.isPresent())
            return item.get().getTextContent();
        return "xd";
    }
}
