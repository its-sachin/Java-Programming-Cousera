import edu.duke.*;
/**
 * Write a description of Part4 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part4 {
    public void youtubeFinder(){
        URLResource ur = new URLResource("https://www.dukelearntoprogram.com/course2/data/manylinks.html");
        String youtube = "youtube.com";
        String quotation = "\"";
        for (String word : ur.words()) {
            String wordLowerCase = word.toLowerCase();
            int youtubeIndex = wordLowerCase.indexOf(youtube);
            if (youtubeIndex != -1){
                int startIndex = word.lastIndexOf(quotation, youtubeIndex);
                int endIndex = word.indexOf(quotation,youtubeIndex+ youtube.length());
                System.out.println(word.substring(startIndex, endIndex+1));
            }
        }
    }
  
}
