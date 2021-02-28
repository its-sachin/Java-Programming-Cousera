
/**
 * Write a description of Part3 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part3 {
    public int findStopCodon(String dna, int startIndex, String stopCodon){
        int currIndex = dna.indexOf(stopCodon, startIndex);
        while (currIndex != -1){
            int diff = currIndex - startIndex;
            if (diff % 3 == 0){
                return currIndex;
            }
            else {
                currIndex = dna.indexOf(stopCodon, currIndex + 3);
            }
        }
        return dna.length();
    }
    
    public void testFindStopCodon(){
        String startCodon = "ATG";
        String stopCodon = "TAA";
        
        String string1 = "ATCGTATGGACTAGAGCTAAGTACGTAAGGC";
        int startIndex1 = string1.indexOf(startCodon);
        System.out.println(findStopCodon(string1, startIndex1, stopCodon));
        
        String string2 = "ATGAATAAGCAGTACGTGTAATGA";
        int startIndex2 = string2.indexOf(startCodon);
        System.out.println(findStopCodon(string2, startIndex2, stopCodon));
        
        
        String string3 = "AATCGATGAGGACATTAGCAAGT";
        int startIndex3 = string3.indexOf(startCodon);
        System.out.println(findStopCodon(string3, startIndex3, stopCodon));
    }
    
    public String testGene(String dna){
        int startIndex = dna.indexOf("ATG");
        while (true){
            if (startIndex == -1) {return "";}
        
            int tgaIndex = findStopCodon(dna, startIndex, "TGA");
            int taaIndex = findStopCodon(dna, startIndex, "TAA");
            int tagIndex = findStopCodon(dna, startIndex, "TAG");
            
            int intermediateIndex = Math.min(tgaIndex, taaIndex);
            int minIndex = Math.min(intermediateIndex, tagIndex);
            
            if (minIndex != dna.length()) {
                return dna.substring(startIndex,minIndex+3);
            }
            
            startIndex = dna.indexOf("ATG",startIndex + 3);
        }        
    }
    
    public void testFindGene(){
        String dna1 = "ATCATCGTAGTCAAGCTAATGT";
        System.out.println(dna1);
        System.out.println(testGene(dna1));
        
        String dna2 = "ATGAATAAGCAGTACGTGTAATGA";
        System.out.println(dna2);
        System.out.println(testGene(dna2));
        
        String dna3 = "ATGTACGAATAAGCATGGCAATGA";
        System.out.println(dna3);
        System.out.println(testGene(dna3));
        
        String dna4 = "ATGGCAAGGCTCATCGTTACTGC";
        System.out.println(dna4);
        System.out.println(testGene(dna4));
        
        String dna5 = "ATGAAGCCTGACCAATCTAAGTATTATTAG";
        System.out.println(dna5);
        System.out.println(testGene(dna5));
    }
    
    public void printAllGenes(String dna){
        int startIndex = 0;
        while(true){
            String currGene = testGene(dna.substring(startIndex));
            if (currGene.isEmpty()){break;}
            System.out.println(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
    }
    
    public int countGenes(String dna){
        int startIndex = 0;
        int count = 0;
        while(true){
            String currGene = testGene(dna.substring(startIndex));
            if (currGene.isEmpty()){break;}
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
            count = count + 1;
        }
        return count;
    }
    
    public void testPrintAllGenes(){
        String dna = "ATGATCTAGCCTGATGCAATCATAAATGTTATAGTAG";
        printAllGenes(dna);
    }
    
    public void testCountGenes(){
        String dna1 = "ATGATCTAGCCTGATGCAATCATAAATGTTATAGTAG";
        System.out.println(countGenes(dna1));
        
        String dna2 = "ATGGCAAGGCTCATCGTTACTGC";
        System.out.println(countGenes(dna2));
        
        String dna3 = "ATCATCGTAGTCAAGCTAATGT";
        System.out.println(countGenes(dna3));
        
        String dna4 = "ATGAATAAGCAGTACGTGTAATGA";
        System.out.println(countGenes(dna4));
        
        String dna5 = "ATGAAGCCTGACCAATCTAAGTATTATTAG";
        System.out.println(countGenes(dna5));
    }

}
