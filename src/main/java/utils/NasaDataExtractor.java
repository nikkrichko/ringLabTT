package utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.*;

public class NasaDataExtractor {


    public static ArrayList getNasaPicturesLinks(String link) throws IOException {

        JSONObject jsonSol = JsonReader.readJsonFromUrl(link);

        JSONArray array = (JSONArray) jsonSol.get("photos");

        ArrayList photosLinks = new ArrayList();
        for(int i=0; i < 10; i++){
            Map maps = JsonReader.toMap((JSONObject) array.get(i));
            photosLinks.add(maps.get("img_src"));
        }

        return photosLinks;
    }

    public static Map getcountOfPhotosByCamera(String link) throws IOException {
        JSONObject jsonSol = JsonReader.readJsonFromUrl(link);

        JSONArray array = (JSONArray) jsonSol.get("photos");

        ArrayList photosLinks = new ArrayList();
        for(int i=0; i < array.length(); i++){
            Map photoMaps = JsonReader.toMap((JSONObject) array.get(i));
            Map camerasMaps = (Map) photoMaps.get("camera");
            photosLinks.add(camerasMaps.get("name"));
        }

        Set<String> mySet = new HashSet<String>(photosLinks);
        Map countCamerasPhotos = new HashMap();
        for(String s: mySet){
            countCamerasPhotos.put(s,Collections.frequency(photosLinks,s));
        }

        return countCamerasPhotos;
    }
}
