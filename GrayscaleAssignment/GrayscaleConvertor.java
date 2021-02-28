import edu.duke.*;
import java.io.*;
import java.awt.image.BufferedImage;
/**
 * Write a description of GrayscaleConvertor here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class GrayscaleConvertor {
    public ImageResource makeGray(ImageResource inImage){
        for (Pixel pixel : inImage.pixels()){
            int red = pixel.getRed();
            int green = pixel.getGreen();
            int blue = pixel.getBlue();
            
            int average = (red + green + blue)/3;
            
            pixel.setRed(average);
            pixel.setGreen(average);
            pixel.setBlue(average);
        }
        return inImage;
    }
    
    public BufferedImage cropImage(BufferedImage src , int width, int height){
        BufferedImage out = src.getSubimage(0,0,width,height);
        return out;
    }
    
    
    public void testMakeGray(){
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            ImageResource outImage = makeGray(inImage);
            String inName = inImage.getFileName();
            String outName = "gray-" + inName;
            outImage.setFileName(outName);
            outImage.draw();
            outImage.save();
        }
    
    }
    
}
