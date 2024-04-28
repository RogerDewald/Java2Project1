import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Convert {
    public static void convertFile(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename.replace(".gpx", ".csv")));

        writer.write("Time,Latitude,Longitude\n");

        String singleLine;
        int outputTime = 0;
        while ((singleLine = reader.readLine()) != null) {
            if (singleLine.trim().startsWith("<trkpt")) {
                String[] parts = singleLine.split("\"");
                String lat = parts[1].replace("?", "").replace(" ", "").replace("\t", "");
                String lon = parts[3].replace("?", "").replace(" ", "").replace("\t", "");

                writer.write(outputTime + "," + lat + "," + lon + "\n");
                outputTime += 5;
            }
        }

        reader.close();
        writer.close();
    }
}