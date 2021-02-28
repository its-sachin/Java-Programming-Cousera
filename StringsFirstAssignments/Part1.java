
/**
 * Write a description of Part1 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Part1 {
    
    public String findSimpleGene(String dna) {        
        int startIndex = dna.indexOf("ATG");        
        int endIndex = dna.indexOf("TAA");
        
        if (startIndex == -1){return "No ATG here.";}
        
        if (endIndex == -1){return "No TAA here";}

        String simpleGene = dna.substring(startIndex, endIndex + 3);
        if (simpleGene.length() % 3 == 0){return simpleGene;}
        else{
            return "No Gene here";
        }
        
    }
    
    public void testSimpleGene(){
        String gene1 = "AATCGTAGCTAA";
        System.out.println(findSimpleGene(gene1));
        
        String gene2 = "ATCGAAGCATGGCTAGCCGGTAATTAGCCAT";
        System.out.println(findSimpleGene(gene2));
        
        String gene3 = "AGCCTGATGGCTAGCTGAGGATT";
        System.out.println(findSimpleGene(gene3));
        
        String gene4 = "ATGGAGGTTAGGATTAGGGCCGCTAA";
        System.out.println(findSimpleGene(gene4));
        
        String gene5 = "GCTTGAGATTCGCGGCTTAGGACCAGGTAT";
        System.out.println(findSimpleGene(gene5));
        
        
    }

}
