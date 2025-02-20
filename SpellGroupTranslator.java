import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class SpellGroupTranslator
{
    private ArrayList<String> spells;

    public SpellGroupTranslator()
    {
        spells = new ArrayList<String>();
    }

    public void writeFile(String filePath)
    {
        for (String spell : spells) {
            System.out.println(spell);
        }
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "utf-8"))) {
           for(String spell : spells) {
               writer.write(spell);
               writer.newLine();
           }

        } catch (FileNotFoundException e) {
            System.out.println("Could not locate file");
        }
        // Catch block to handle the exception
        catch (IOException e) {
            System.out.println("There are some IOException");
        }
    }

    public void readSpellSection(String filePath, int spellGroups)
    {
        String[] groupNames = new String[spellGroups];
        for(int i = 1; i < spellGroups+1; i++) {
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while((line = reader.readLine()) != null) {

                    if(line.length() > 3 && line.substring(0,2).equals((i)+"."))
                    {
                        groupNames[i - 1] = line.substring(3);
                        System.out.println(line.substring(3));
                        break;
                    }
                }

            } catch (FileNotFoundException e) {
                System.out.println("Could not locate file");
            }
            // Catch block to handle the exception
            catch (IOException e) {
                System.out.println("There are some IOException");
            }
        }
        readSpellGroup(filePath, groupNames);
    }

    public void readSpellGroup(String filePath, String[] groupNames)
    {

        //store the full spell into string
        StringBuilder spell = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            //the current line the reader is on
            String line;

            //checks if the reader is on a spell
            boolean onSpell = false;

            //helps determine what line is which for each specific spell
            String previousLine = "";
            boolean descriptionLine = false;

            //current spellGroup
            String spellGroup = "";

            //going through each line in the text file till it reaches the last line or null
            while((line = reader.readLine()) != null){

                //checks if the reader is on a spell
                if(onSpell) {


                    if(descriptionLine)
                    {
                        if(line.contains("Resist Check:"))
                        {
                            descriptionLine = false;
                            spell.append(";");
                        }
                        else {
                            System.out.println("Descriptions: " + line);
                            spell.append(line);
                        }
                    }

                    switch(previousLine) {
                        case("Time to Cast:"):
                            System.out.println("time to Cast: " + line);
                            spell.append(line).append(";");
                            descriptionLine = true;
                            break;
                        case("Resist Check:"):
                            System.out.println("Resist Check: " + line);
                            spell.append(line).append(";");
                            break;
                        case("Target:"):
                            System.out.println("Target: " + line);
                            spell.append(line).append(";");
                            break;
                        case("Duration:"):
                            System.out.println("Duration: " + line);
                            spell.append(line).append(";");
                            break;
                        case("Area:"):
                            System.out.println("Area: " + line);
                            spell.append(line).append(";");
                            break;
                        case("Effect:"):
                            System.out.println("Effect: " + line);
                            spell.append(line);
                            spells.add(spell.toString());
                            spell = new StringBuilder();
                            onSpell = false;
                            System.out.println(" ");
                            break;
                    }


                }
                // determines the spell group of spell
                if(!onSpell) {
                    for (String groupName : groupNames) {
                        if (line.contains(groupName)) {
                            spellGroup = groupName;
                        }
                    }
                }
                if(!onSpell && line.contains(" – "))
                {
                    onSpell = true;
                    spell.append(spellGroup).append(";");// Change the spell group to update with the current array from groupNames
                    System.out.println("rank: " + line.substring(0, line.indexOf(" ")));
                    spell.append(line.substring(0, line.indexOf(" "))).append(";");
                    System.out.println("Spell name: " + line.substring(2+line.indexOf("– ")));
                    spell.append(line.substring(2 + line.indexOf("– "))).append(";");
                }
                previousLine = line;
            }
        }

        catch(FileNotFoundException e){
            System.out.println("Could not locate file");
        }
        // Catch block to handle the exception
        catch (IOException e) {
            System.out.println("There are some IOException");
        }
    }


    public void readSpellGroup(String filePath)
    {
        //put file pathway into the string to read all the spells
        Scanner in = new Scanner(System.in);
        System.out.println("what spell group is this?");
        String spellGroup = in.nextLine();


        //store the full spell into string
        StringBuilder spell = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            //the current line the reader is on
            String line;

            //checks if the reader is on a spell
            boolean onSpell = false;

            //helps determine what line is which for each specific spell
            String previousLine = "";
            boolean descriptionLine = false;

            //going through each line in the text file till it reaches the last line or null
            while((line = reader.readLine()) != null){

                //checks if the reader is on a spell
                if(onSpell) {

                    if(descriptionLine)
                    {
                        //System.out.println("Descriptions: " + line);
                        spell.append(line).append(";");
                        descriptionLine = false;
                    }
                    switch(previousLine) {
                        case("Time to Cast:"):
                            //System.out.println("time to Cast: " + line);
                            spell.append(line).append(";");
                            descriptionLine = true;
                            break;
                        case("Resist Check:"):
                            //System.out.println("Resist Check: " + line);
                            spell.append(line).append(";");
                            break;
                        case("Target:"):
                            //System.out.println("Target: " + line);
                            spell.append(line).append(";");
                            break;
                        case("Duration:"):
                            //System.out.println("Duration: " + line);
                            spell.append(line).append(";");
                            break;
                        case("Area:"):
                            //System.out.println("Area: " + line);
                            spell.append(line).append(";");
                            break;
                        case("Effect:"):
                            //System.out.println("Effect: " + line);
                            spell.append(line);
                            spells.add(spell.toString());
                            spell = new StringBuilder();
                            onSpell = false;
                            //System.out.println(" ");
                            break;
                    }

                }
                if(!onSpell && line.contains(" – "))
                {
                    onSpell = true;
                    spell.append(spellGroup).append(";");
                    //System.out.println("rank: " + line.substring(0, line.indexOf(" ")));
                    spell.append(line.substring(0, line.indexOf(" "))).append(";");
                    //System.out.println("Spell name: " + line.substring(2+line.indexOf("– ")));
                    spell.append(line.substring(2 + line.indexOf("– "))).append(";");
                }
                previousLine = line;
            }
        }

        catch(FileNotFoundException e){
            System.out.println("Could not locate file");
        }
        // Catch block to handle the exception
        catch (IOException e) {
            System.out.println("There are some IOException");
        }
    }
}
