
/**
 * Write a description of Part2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part2 {
    public String findSimpleGene(String dna, String startCodon, String endCodon) {     
        String dnaUpperCase = dna.toUpperCase();
        int startIndex = dnaUpperCase.indexOf(startCodon);        
        int endIndex = dnaUpperCase.indexOf(endCodon);
        
        if (startIndex == -1){return "";}
        if (endIndex == -1){return "";}

        String simpleGene = dnaUpperCase.substring(startIndex, endIndex + 3);
        if (simpleGene.length() % 3 == 0){
            if (dna.toUpperCase() == dna){
                return simpleGene;
            }
            else{
                return simpleGene.toLowerCase();
            }
        }
        else{
            return "";
        }
        
    }
    
    public void testSimpleGene(){
        String startCodon = "ATG";
        String endCodon = "TAA";
        
        String gene1 = "AATCGTAGCTAA";
        System.out.println(findSimpleGene(gene1,startCodon,endCodon));
        
        String gene2 = "ATCGAAGCATGGCTAGCCGGTAATTAGCCAT";
        System.out.println(findSimpleGene(gene2,startCodon,endCodon));
        
        String gene3 = "agcctggaccatggatggaaattagctgaggttctaa";
        System.out.println(findSimpleGene(gene3,startCodon,endCodon));
        
        String gene4 = "ATGGAGGTTAGGATTAGGGCCGCTAA";
        System.out.println(findSimpleGene(gene4,startCodon,endCodon));
        
        String gene5 = "GCTTGAGATTCGCGGCTTAGGACCAGGTAT";
        System.out.println(findSimpleGene(gene5,startCodon,endCodon));
        
        
    }

}
