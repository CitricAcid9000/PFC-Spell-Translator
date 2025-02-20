// Java program to read content from one file
// and write it into another file

// Custom paths for this program 
// Reading from - gfgInput.txt
// Writing to - gfgOutput.txt

// Importing input output classes
import java.util.Scanner;

// Class
class Main {

    // Main driver method
    public static void main(String[] args)
    {


        Scanner in = new Scanner(System.in);
        //System.out.println("what is the file pathway?");
        //String pathway = in.nextLine();
        String pathway = "C:/Users/seans/OneDrive/Desktop/spellTest.txt";

        //System.out.println("what is the destination file pathway?");
        //String destinationPathway = in.nextLine();
        String destinationPathway = "D://Spell Files/Water Magic.txt";

        // ask is a spell or incant for formating
        boolean waitingResponse = true;
        boolean isSpell = true;
        while(waitingResponse) {
            System.out.println("is this a spell?(Y or N)");
            String type = in.nextLine();
            if (type.equalsIgnoreCase("y")) {
                waitingResponse = false;
            } else if (type.equalsIgnoreCase("n")) {
                isSpell = false;
                waitingResponse = false;
            } else {
                System.out.println("answer invalid");
            }
        }

        if(isSpell) {
            SpellGroupTranslator spellGroup = new SpellGroupTranslator();

            System.out.println("how many spell groups do you know in the document?(0 if psionic)");
            int spellGroupNumber = in.nextInt();
            if (spellGroupNumber > 0) {
                spellGroup.readSpellSection(pathway, spellGroupNumber);
            } else {
                spellGroup.readSpellGroup(pathway);
            }
            spellGroup.writeFile(destinationPathway);
        }else{
            IncantTranslator incantTranslator = new IncantTranslator();

            incantTranslator.readIncant(pathway);
            incantTranslator.writeIncant(destinationPathway);
        }
    }


}
