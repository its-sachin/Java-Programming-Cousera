/**
 * Print out total number of babies born, as well as for each gender, in a given CSV file of baby name data.
 * 
 * @author Duke Software Team 
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;

public class BabyBirths {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (numBorn <= 100) {
                System.out.println("Name " + rec.get(0) +
                           " Gender " + rec.get(1) +
                           " Num Born " + rec.get(2));
            }
        }
    }

    public void totalBirths (FileResource fr) {
        int totalBirths = 0;
        int totalBoys = 0;
        int boysNames = 0;
        int totalGirls = 0;
        int girlsNames = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            totalBirths += numBorn;
            if (rec.get(1).equals("M")) {
                totalBoys += numBorn;
                boysNames += 1;
            }
            else {
                totalGirls += numBorn;
                girlsNames += 1;
            }
        }
        System.out.println("total births = " + totalBirths);
        System.out.println("female girls = " + totalGirls);
        System.out.println("female names = " + girlsNames);
        System.out.println("male boys = " + totalBoys);
        System.out.println("male names = " + boysNames);
    }
    
    public int getRank(int year, String name, String gender){
        int rank = 0;
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        //FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)){
                rank += 1;
                if (rec.get(0).equals(name)){
                    return rank;
                }
            }
        }
        return -1;
    }
    
    public String getName(int year, int rank, String gender){
        String name = "";
        int currRank = 0;
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)){
                currRank += 1;
                if (rank == currRank){
                    return rec.get(0);
                }
            }
        }
        return "NO NAME";
    }
    
    public String whatIsNameInYear(String name, int year, int newYear, String gender){
        int rank = getRank(year, name, gender);
        String finalName = getName(newYear, rank, gender);
        return finalName;
    }
    
    public int yearOfHighestRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        int maxRank = -1;
        int yearOfMaxRank = -1;
        for (File fr : dr.selectedFiles()){
            String currYearStr = fr.getName().substring(3,7);
            int currYear = Integer.parseInt(currYearStr);
            int currRank = getRank(currYear, name, gender);
            
            if (maxRank == -1 || maxRank > currRank){
                maxRank = currRank;
                yearOfMaxRank = currYear;
            }
        }
        return yearOfMaxRank;
    }
    
    public double getAverageRank(String name, String gender){
        DirectoryResource dr = new DirectoryResource();
        double rankSum = 0.0;
        int count = 0;
        
        for (File fr : dr.selectedFiles()){
            String currYearStr = fr.getName().substring(3,7);
            int currYear = Integer.parseInt(currYearStr);
            int currRank = getRank(currYear, name, gender);
            
            if (currRank != -1){
                rankSum += currRank;
                count += 1;
            }
        
        }
        if (count == 0){
            return -1.0;
        }
        else{
            return rankSum/count;
        }
    }
    
    public int getTotalBirthsRankedHigher(int year, String name, String gender){
        int totalBirths = 0;
        FileResource fr = new FileResource("data/yob" + year + ".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            if (rec.get(1).equals(gender)){
                if (rec.get(0).equals(name)){return totalBirths;}
                int numBorn = Integer.parseInt(rec.get(2));
                totalBirths += numBorn;
            }
        }
        return totalBirths;
    }
    
    
    
    
    
    //Tests
    
    public void testGetTotalBirthsRankedHigher(){
        int year = 1990;
        String name = "Drew";
        System.out.println("Total births ranked higher than " + name + " in the year " + year + " are " + getTotalBirthsRankedHigher(year, name, "M"));
    }
    
    public void testGetAverageRank(){
        String name = "Robert";
        System.out.println("The average rank of " + name + " in selected years is " + getAverageRank(name, "M"));
    }
    
    public void testYearOfHighestRank(){
         String name = "Mich";
         System.out.println("Out of the provided years name " + name + " was used most in year " + yearOfHighestRank(name, "M"));
    }
    
    public void testWhatIsNameInYear(){
        String name = "Owen";
        int year = 1974;
        int newYear = 2014;
        
        System.out.println(name + " born in  " + year + " would be " + whatIsNameInYear(name, year, newYear, "M") + " if he was born in " + newYear);
    }
    
    public void testGetName(){
        int year = 1982;
        int rank = 450;
        System.out.println("The name with rank " + rank + " in the year " + year + " is " + getName(year, rank, "M"));
    }
    
    public void testGetRank(){
        String name = "Frank";
        int year = 1971;
        System.out.println("The rank of name " + name + " in the year " + year + " is "  + getRank(year, name, "M"));
       
    }
    
    public void testTotalBirths () {
        FileResource fr = new FileResource();
        //FileResource fr = new FileResource("data/yob2014.csv");
        totalBirths(fr);
    }
}
