import edu.duke.*;
import java.io.*;
/**
 * Write a description of ImageInvertor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ImageInvertor {
    public ImageResource invertImage(ImageResource inImage){
        for (Pixel pixel : inImage.pixels()){
            int red = pixel.getRed();
            int green = pixel.getGreen();
            int blue = pixel.getBlue();
                       
            pixel.setRed(255 - red);
            pixel.setGreen(255 - green);
            pixel.setBlue(255 - blue);
        }
        return inImage;
    }
    
    public void testInvertImage(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = invertImage(inImage);
            String inName = inImage.getFileName();
            String outName = "inverted-" + inName;
            outImage.setFileName(outName);
            outImage.draw();
            outImage.save();
        }
    }
}
