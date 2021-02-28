/**
 * Reads a chosen CSV file of our preferences and prints each field.
 * 
 * @author Duke Software Team
 */
import edu.duke.*;
import org.apache.commons.csv.*;

public class FirstCSVExample {
    public void readFood() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord record : parser){
            System.out.print(record.get("Name") + " ");
            System.out.print(record.get("Favorite Color") + " ");
            System.out.println(record.get("Favorite Food"));
        }
    }
    
    public String countryInfo(CSVParser parser, String country){
        for (CSVRecord record : parser){
           String currCountry = record.get("Country");
           if (currCountry.equals(country)){
               String output = country + " : " + record.get("Exports") + " : " + record.get("Value (dollars)");
               return output;               
           }           
        }
        return "NOT FOUND";
    }
    
    public void listExportersTwoProducts(CSVParser parser, String exporter1, String exporter2){
        for (CSVRecord record : parser){
            String export = record.get("Exports");
            if (export.contains(exporter1) && export.contains(exporter2)){
                System.out.println(record.get("Country"));
            }    
        }
    }
    
    public int numberOfExporters(CSVParser parser, String exportItem){
        int count = 0;
        for (CSVRecord record : parser){
            if (record.get("Exports").contains(exportItem)){
                count = count + 1;
            }
        }
        return count;
    }
    
    public void bigExporters(CSVParser parser, String value){
        for (CSVRecord record : parser){
            if (record.get("Value (dollars)").length() > value.length()){
                System.out.println(record.get("Country") + " ; " + record.get("Value (dollars)"));
            }
        }
    }
    
    public void tester(){
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //System.out.println(countryInfo(parser,"Nauru"));
        //listExportersTwoProducts(parser, "cotton", "flowers");
        //System.out.println(numberOfExporters(parser, "cocoa"));
        bigExporters(parser, "$999,999,999,999");
    }
}
