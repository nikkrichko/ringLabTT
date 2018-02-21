package functionalTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.NasaDataExtractor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import static utils.JsonReader.convertToString;
import static utils.NasaDataExtractor.getcountOfPhotosByCamera;

public class CheckPhotoNumbers extends MainTest{

    ArrayList photosEarthDateLinks;


    @BeforeClass
    public void PrepareJsonMap() throws IOException {
        photosEarthDateLinks = NasaDataExtractor.getNasaPicturesLinks(EarthDateLink);
    }



    @Test
    public void compareNumbersOfPhotos() throws IOException{

        Map countCamerasPhotos = getcountOfPhotosByCamera(EarthDateLink);

        String[] listofCameraNames = convertToString(countCamerasPhotos.keySet().toArray()) ;

        for(int i=0; i < listofCameraNames.length-1; i++){

            for(int j=0;j < listofCameraNames.length; j++ ){
                if(listofCameraNames[i] != listofCameraNames[j]) {
                    int firstCameraValue = (int) countCamerasPhotos.get(listofCameraNames[i]);
                    int secondCameraValue = (int) countCamerasPhotos.get(listofCameraNames[j]);

                    boolean comparator = firstCameraValue > secondCameraValue+10;

                    Assert.assertFalse(comparator);

                }

            }
        }
    }


}
