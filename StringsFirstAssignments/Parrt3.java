
/**
 * Write a description of Parrt3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Parrt3 {
    public Boolean twoOccurrences(String stringa, String stringb) {
        int firstOccur = stringb.indexOf(stringa);
        if (firstOccur == -1) {return false;}
        else{
            String cutStringb = stringb.substring(firstOccur + stringa.length());
            int secondOccur = cutStringb.indexOf(stringa);
            if (secondOccur == -1) {return false;}
            else {return true;}
        }
        
    }
    
    public String lastPart(String stringa, String stringb){
        int occurPoint = stringb.indexOf(stringa);
        if (occurPoint == -1){return stringb;}
        else{return stringb.substring(occurPoint + stringa.length());} 
    }
    
    public void testing(){
        String stringa1 = "a";
        String stringa2 = "by";
        String stringa3 = "is";
        
        String stringb1 = "banana is by the way my favourite fruit";
        String stringb2 = "that is not what it is";
        
        System.out.println(stringa1 + "  " + stringb1 + "  " + twoOccurrences(stringa1,stringb1) + "       " + lastPart(stringa1,stringb1));
        System.out.println(stringa1 + "  " + stringb2 + "  " + twoOccurrences(stringa1,stringb2) + "       " + lastPart(stringa1,stringb2));
        System.out.println(stringa2 + "  " + stringb1 + "  " + twoOccurrences(stringa2,stringb1) + "       " + lastPart(stringa2,stringb1));
        System.out.println(stringa2 + "  " + stringb2 + "  " + twoOccurrences(stringa2,stringb2) + "       " + lastPart(stringa2,stringb2));
        System.out.println(stringa3 + "  " + stringb1 + "  " + twoOccurrences(stringa3,stringb1) + "       " + lastPart(stringa3,stringb1));
        System.out.println(stringa3 + "  " + stringb2 + "  " + twoOccurrences(stringa3,stringb2) + "       " + lastPart(stringa3,stringb2));
    }

}
