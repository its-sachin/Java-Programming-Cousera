import edu.duke.*;
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
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

    public StorageResource getAllGenes(String dna){
        int startIndex = 0;
        StorageResource genes = new StorageResource();
        while(true){
            String currGene = testGene(dna.substring(startIndex));
            if (currGene.isEmpty()){break;}
            genes.add(currGene);
            startIndex = dna.indexOf(currGene, startIndex) + currGene.length();
        }
        return genes;
    }
    
    public int countCodon(String dna, String codon){
        int count = 0;
        int currIndex = dna.indexOf(codon);
        while(true){
            if (currIndex == -1){break;}
            count = count + 1;
            currIndex = dna.indexOf(codon, currIndex + codon.length());
        }
        return count;
    }
    
    public double cgRatio(String dna){
        int cCount = countCodon(dna, "C");
        int gCount = countCodon(dna, "G");
        double ratio = (double)cCount/gCount;
        return ratio;
    }
    
    public void processGenes(StorageResource sr){
        System.out.println("No. of genes in the dna is : " + sr.size());
        System.out.println("");
        
        
        
        
        int count = 0;
        System.out.println("Genes with lenth more than 9:");
        for (String string : sr.data()){
            if (string .length() > 9) {
                System.out.println(string);
                count = count + 1;
            }  
        }
        System.out.println("No of genes of length more then 9 is : " + count);
        System.out.println("");
        
        count = 0;
        System.out.println("Genes with cg ratio higher than 0.35:");
        for (String string : sr.data()){
            if (cgRatio(string) > 0.35){
                System.out.println(string);
                count = count + 1;
            }
        }
        System.out.println("No. of genes with cg ratio higher than 0.35 is : " + count);
        System.out.println("");
        
        String biggestString = "";        
        for (String string : sr.data()){
            if (string.length() > biggestString.length()) {biggestString = string;}
        }
        System.out.println("Biggest gene is : " + biggestString);
        System.out.println("Its Length is : " + biggestString.length());
        System.out.println("");
        
        count = 0;
        System.out.println("Genes with lenth more than 60:");
        for (String string : sr.data()){
            if (string.length() > 60){
                System.out.println(string);
                count = count + 1;
            }
        }
        System.out.println("No. of genes with length more than 60 is : " + count);
    }
    
    public String mystery(String dna) {
        int pos = dna.indexOf("T");
        int count = 0;
        int startPos = 0;
        String newDna = "";
        if (pos == -1) {
            return dna;
        }
        while (count < 3) {
            count += 1;
            newDna = newDna + dna.substring(startPos,pos);
            startPos = pos+1;
            pos = dna.indexOf("T", startPos);
            if (pos == -1) {
              break;
            }
        }
        newDna = newDna + dna.substring(startPos);
        return newDna;
    }
    
    public void testMystery(){
        String string = "ATGGAATAGVGNSTAVJK";
        System.out.println(mystery(string));
    }
    
    public void testProcessGenes(){
        String dna1 = "ATGAGACTGCATGACTAAATGGTCTGA";
        String dna2 = "ATGTAAGCAAGCCATGGCATGACAAGG";
        String dna3 = "ATGAAGCCTGACCAATCTAAGTATTATTAG";
        String dna4 = "ATGATCTAGCCTGATGCAATCATAAATGTTATAGTAG";
        String dna5 = "ATGAATAAGCAGTACGTGTAATGA";
        
        //processGenes(getAllGenes(dna1));
        //System.out.println("");
        
        //processGenes(getAllGenes(dna2));
        //System.out.println("");
        
        //processGenes(getAllGenes(dna3));
        //System.out.println("");
        
        //processGenes(getAllGenes(dna4));
        //System.out.println("");
        
        //processGenes(getAllGenes(dna5));
        
        FileResource fr = new FileResource("brca1line.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        StorageResource genes = getAllGenes(dna);        
        processGenes(genes);
        //System.out.println(countCodon(dna, "CTG"));
    }
    
    public void testCgRatio(){
        String string1 = "ATGGACTGCATGACTAAATGGTCTGA";
        System.out.println(cgRatio(string1));
        System.out.println(countCodon(string1, "CTG"));
    }
    
    public void testGetAllGenes(){
        String dna1 = "ATGATCTAGCCTGATGCAATCATAAATGTTATAGTAG";
        StorageResource genes = getAllGenes(dna1);
        for (String sr : genes.data()){
            System.out.println(sr );
        }
    }
    
    public void testPrintAllGenes(){
        String dna = "ATGAGACTGCATGACTAAATGGTCTGA";
        printAllGenes(dna);
    }

}
