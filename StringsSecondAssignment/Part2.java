
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public int howMany(String stringa, String stringb){
        int count = 0;
        int occurIndex = stringb.indexOf(stringa);
        String stringbLeft = stringb;
        while (occurIndex != -1){
            stringbLeft = stringbLeft.substring(occurIndex + stringa.length());
            occurIndex = stringbLeft.indexOf(stringa);
            count = count + 1;
        }
        return count;
    }
    
    public void testHowMany(){
        String stringa1 = "an";
        String stringb1 = "banana";
        System.out.println(howMany(stringa1,stringb1));
        
        String stringa2 = "aa";
        String stringb2 = "aaaaaaa";
        System.out.println(howMany(stringa2,stringb2));
        
        String stringa3 = "der";
        String stringb3 = "i am a rider provider";
        System.out.println(howMany(stringa3,stringb3));
        
        String stringa4 = "lol";
        String stringb4 = "sachin is my name";
        System.out.println(howMany(stringa4,stringb4));
    }

}
