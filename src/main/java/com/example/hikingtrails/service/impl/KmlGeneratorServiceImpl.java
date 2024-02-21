package com.example.hikingtrails.service.impl;

import com.example.hikingtrails.model.Trail;
import com.example.hikingtrails.repository.DescriptionRepository;
import com.example.hikingtrails.repository.LandmarkRepository;
import com.example.hikingtrails.repository.TrailRepository;
import com.example.hikingtrails.service.KmlGeneratorService;
import lombok.RequiredArgsConstructor;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Qualifier("main")
public class KmlGeneratorServiceImpl implements KmlGeneratorService {
    private final TrailRepository trailRepository;

    @Override
    public File generateKml(String name) {
        if (!trailRepository.getTrailByTrailName(name).isPresent())
            return null;

        Trail trail = trailRepository.getTrailByTrailName(name).get();



        String datapath = "C:\\Users\\u\\Desktop\\Hiking\\";

        String kmlstart = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<kml xmlns=\"http://www.opengis.net/kml/2.2\">\n";

        String body =
                " <Document>\n" +
                        "    <name>" + name + "</name>\n" +
                        "    <Folder>\n" +
                        "      <name>" + trail.getCountry() + "</name>\n" +
                        "      <Placemark>\n" +
                        "        <name>" + name +"</name>\n" +
                        "        <styleUrl>#line-1267FF-5000-nodesc</styleUrl>\n" +
                        "        <LineString>\n" +
                        "          <tessellate>1</tessellate>";

        String coordinates = "\n<coordinates>\n" + trail.getCoordinates() + "\n</coordinates>\n";

        String kmlend = "  </LineString>\n" +
                "      </Placemark>\n" +
                "    </Folder>\n" +
                "  </Document>\n" +
                "</kml>";


        String text = kmlstart + body + coordinates + kmlend;


        File testexists = new File(datapath + "/" + trail.getTrailName() + ".kml");
        Writer fwriter;

        if (!testexists.exists()) {
            try {
                fwriter = new FileWriter(datapath + "/" + trail.getTrailName() + ".kml");
                fwriter.write(text);
                fwriter.flush();
                fwriter.close();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }
        return testexists;
    }

    @Override
    public File generate2(String trailName) throws IOException {
        Optional<Trail> opttrail = trailRepository.getTrailByTrailName(trailName);
        Trail trail = opttrail.get();
        String datapath = "C:\\Users\\u\\Desktop\\Hiking\\";

        // Create JDOM Document
        Document document = new Document();

        // Create root element <kml>
        Element kmlElement = new Element("kml", Namespace.getNamespace("http://www.opengis.net/kml/2.2"));
        document.addContent(kmlElement);

        // Create child element <Document>
        Element documentElement = new Element("Document", trailName);
        kmlElement.addContent(documentElement);

        // Create child element <Placemark>
        Element placemarkElement = new Element("Placemark", trailName);
        documentElement.addContent(placemarkElement);

        // Create child element <Point> with <coordinates>
        Element coordinatesElement = new Element("coordinates", kmlElement.getNamespace());

        // Write KML document to file
        File file = new File(datapath + "/" + trail.getTrailName() + ".kml");
        FileWriter writer = new FileWriter(file);
        new XMLOutputter().output(document, writer);
        writer.close();

        return file;
    }
}
