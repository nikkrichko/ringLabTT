package functionalTest;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import utils.Log4Test;
import utils.NasaDataExtractor;
import utils.PropertyLoader;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import static utils.PictureHandler.getDifferencePercent;

public class ComparePictures extends MainTest{


    ArrayList photosEarthDateLinks;
    ArrayList photosSolDateLinks;


    @BeforeClass
    public void PrepareJsonMap() throws IOException{
        photosEarthDateLinks = NasaDataExtractor.getNasaPicturesLinks(EarthDateLink);
        photosSolDateLinks = NasaDataExtractor.getNasaPicturesLinks(SolDateLink);
    }


    @Test
    public void compareLinksStrings() throws IOException{
        for(int i = 0; i<10; i++) {
            Assert.assertEquals(photosEarthDateLinks.get(i).toString(),photosSolDateLinks.get(i).toString());
        }
    }


    @Test(dependsOnMethods = { "compareLinksStrings" })
    public void comparePicturesTest() throws IOException {

        for(int i = 0; i<10; i++) {
            URL urlE = new URL(photosEarthDateLinks.get(0).toString());
            URL urlS = new URL(photosSolDateLinks.get(0).toString());
            BufferedImage img0 = ImageIO.read(urlE);
            BufferedImage img1 = ImageIO.read(urlS);
            double p = getDifferencePercent(img1, img0);
            Assert.assertEquals(p, 0.0);
            }
        }





}
