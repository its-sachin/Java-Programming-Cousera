import edu.duke.*;
import java.io.File;
import org.apache.commons.csv.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    public CSVRecord coldestOfTwo(CSVRecord currHour, CSVRecord coldestHour){
        String currTempStr = currHour.get("TemperatureF");
        double currTemp = Double.parseDouble(currTempStr);
        
        String coldestTempStr = coldestHour.get("TemperatureF");
        double coldestTemp = Double.parseDouble(coldestTempStr);
        if (coldestTemp > currTemp && currTemp > -900){
            coldestHour= currHour;
        }
        return coldestHour;
    }
    
    public CSVRecord lowestOfTwo(CSVRecord currCSV, CSVRecord lowestCSV, String toFind, int constant){
        String currValueStr = currCSV.get(toFind);
        double currValue = Double.parseDouble(currValueStr);
        
        String lowestValueStr = lowestCSV.get(toFind);
        double lowestValue = Double.parseDouble(lowestValueStr);
        
      
        
        if (lowestValue > currValue && currValue > constant){
            lowestCSV = currCSV;
        }
        return lowestCSV;
    }
    
    public CSVRecord coldestHourInFile(CSVParser parser){
        CSVRecord coldestHour = null;
        for (CSVRecord currHour : parser){                        
            if (coldestHour == null){coldestHour= currHour;}
            else{
                 coldestHour = lowestOfTwo(currHour, coldestHour, "TemperatureF", -900);
                }
            }
        return coldestHour;
    }
    
    public File fileWithColdestTemperature(){
        DirectoryResource dr = new DirectoryResource();
        File coldestDay = null;
        CSVRecord coldestHour = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);
            CSVRecord currHour = coldestHourInFile(fr.getCSVParser());
            
            if (coldestDay == null) {
                coldestDay = f;
                coldestHour = currHour;
            }
            
            else{
                coldestHour= lowestOfTwo(currHour, coldestHour, "TemperatureF", -900);
                if (coldestHour == currHour) {coldestDay = f;}
            }
        }
        return coldestDay;
    }
    
    public CSVRecord lowestHumidInFile(CSVParser parser){
        CSVRecord leastHumidHour = null;
        for (CSVRecord currHour : parser){                        
            if (leastHumidHour == null){leastHumidHour= currHour;}
            else if (currHour.get("Humidity").equals("N/A" )) {
            }
            else{
                leastHumidHour = lowestOfTwo(currHour, leastHumidHour, "Humidity", 0);
            }
        }
        return leastHumidHour;
    }
    
    public CSVRecord lowestHumidityInManyFiles(){
        DirectoryResource dr = new DirectoryResource();
        CSVRecord leastHumidHour = null;
        for (File f : dr.selectedFiles()){
            FileResource fr = new FileResource(f);    
            CSVRecord currHour = lowestHumidInFile(fr.getCSVParser());
            
            
            if (leastHumidHour == null){leastHumidHour = currHour;}
            else if (currHour.get("Humidity").equals("N/A" )) {
            }
            else{
                leastHumidHour = lowestOfTwo(currHour, leastHumidHour, "Humidity", 0);
            }
        }
        return leastHumidHour;
    }
    
    public double averageTemperatureInFile(CSVParser parser){
        double totalTemp = 0.0;
        int count = 0;
        for (CSVRecord currHour : parser){
            String currTempStr = currHour.get("TemperatureF");
            double currTemp = Double.parseDouble(currTempStr);
            
            totalTemp = totalTemp + currTemp;
            count = count + 1;
        }
        return totalTemp/count;
    }
    
    public double averageTemperatureWithHighHumidityInFile(CSVParser parser, int value){
        double totalTemp = 0.0;
        int count = 0;
        
        for (CSVRecord currHour : parser){
            String currTempStr = currHour.get("TemperatureF");
            double currTemp = Double.parseDouble(currTempStr);
            
            String currHumidityStr = currHour.get("Humidity");
            double currHumidity = Double.parseDouble(currHumidityStr);
            
            if (currHumidity >= value){
                totalTemp = totalTemp + currTemp;
                count = count + 1;
            }
        }
        
        if (count == 0) {
            return 0.0;
        }
        else{
            return totalTemp/count;
        }
    }
        
    
    
    
    
    
    
    
    
    //Testers
    public void testAverageTemperatureWithHighHumidityInFile(){
        FileResource fr = new FileResource();
        double avgTempWithHighHumidity = averageTemperatureWithHighHumidityInFile(fr.getCSVParser(),80);
        if (avgTempWithHighHumidity == 0.0){System.out.println("No temperature with that humidity");}
        else{
            System.out.println("Average temerature when high humidity is " + avgTempWithHighHumidity);
        }
    }
    
    public void testAverageTemperatureInFile(){
        FileResource fr = new FileResource();
        double avgTemp = averageTemperatureInFile(fr.getCSVParser());
        System.out.println("Average Temperature in file is " + avgTemp);
    }
    
    public void testLowestHumidInManyFiles(){
        CSVRecord leastHumidHour = lowestHumidityInManyFiles();
        System.out.println("Lowest Humidity was " + leastHumidHour.get("Humidity") + " at " + leastHumidHour.get("DateUTC"));
    }
    
    public void testLowestHumdityInFile(){
        FileResource fr = new FileResource();
        CSVRecord leastHumidHour = lowestHumidInFile(fr.getCSVParser());
        System.out.println("Lowest Humidity was " + leastHumidHour.get("Humidity") + " at " + leastHumidHour.get("DateUTC"));
    }
    
    public void testFileWithColdestTemperature(){
        File coldestDayPath = fileWithColdestTemperature();
        String coldestDay = coldestDayPath.getName();
        FileResource coldestDayFile = new FileResource(coldestDayPath);
        CSVRecord coldestTemp = coldestHourInFile(coldestDayFile.getCSVParser());
        
        System.out.println("Coldest was in file " + coldestDay);
        System.out.println("Coldest Temperature on the day was " + coldestTemp.get("TemperatureF"));
        System.out.println("All the temperatures on the coldest day were: " );
        for (CSVRecord currTime : coldestDayFile.getCSVParser()){
            System.out.println(currTime.get("DateUTC") + ": " + currTime.get("TemperatureF"));
        }
    }
    
    public void testColdestHourInFile(){
        FileResource fr = new FileResource();
        CSVRecord coldestHour = coldestHourInFile(fr.getCSVParser());
        System.out.println("Coldest Temperature of the day was " + coldestHour.get("TemperatureF") + " deg F and at time = " + coldestHour.get("TimeEDT"));
    }
}
