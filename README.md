Java 17 Spring boot 3.2.2 project for finding information about Hiking trails in Georgia.
It has several features: it can read kml files (with a specific design that contains 17 fields of intormation about the trails)
it can generate kml files for specific trails, which you have to import in your Google My Maps project yourself. (Currently working to automate the process and directly give a link to the client)
It can also filter the hiking trails by following criteria: Difficulty, duration, the Region and the Country. 
"trails.kml" file contains the necesarry information, send the http request to this path http://localhost:8081/hiking/readFile after running the project. this will fill the database with entries.

if it cannot find the file, just copy the path of the file and replace "trails.kml" in the following code in KmlReaderServiceImpl class on line 35:
 File kmlFile = new File("trails.kml");

 
run this sql commands immediately after filling the database with entries:
ALTER TABLE trails ALTER COLUMN coordinates TYPE TEXT;
ALTER TABLE description ALTER COLUMN text TYPE TEXT;

Used Technologies:
Spring boot,
Git,
Hibernate,
Gradle,

