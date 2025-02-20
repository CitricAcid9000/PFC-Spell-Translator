import java.io.*;
import java.util.ArrayList;

public class IncantTranslator
{

    ArrayList<String> incants;

    public static void main(String[] args)
    {


    }

    public IncantTranslator()
    {
        incants = new ArrayList<String>();
    }

    public void writeIncant(String filePath)
    {
        for(int i = 0; i < incants.size(); i++)
        {
            System.out.println(incants.get(i));
        }

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath), "utf-8"))) {
            for(String incant : incants) {
                writer.write(incant);
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

    public void readIncant(String filePath)
    {

        int lineNum = 1;

        //store the full spell into string
        StringBuilder incant = new StringBuilder();

        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {

            //the current line the reader is on
            String line;

            //checks if the reader is on a spell
            boolean onIncant = false;

            //helps determine what line is which for each specific spell
            String previousLine = "";

            boolean descriptionLine = false;

            //going through each line in the text file till it reaches the last line or null
            while((line = reader.readLine()) != null){

                //checks if the reader is on a spell
                if(onIncant) {

                    if(descriptionLine)
                    {
                        //System.out.println("Descriptions: " + line);
                        incant.append(line).append(";");
                        descriptionLine = false;
                    }
                    else if(line.contains("Secondary Effect"))
                    {
                        incant.append(line).append(";");
                    }
                    switch(previousLine) {
                        // add name
                        case("Type:"):
                            //System.out.println("time to Cast: " + line);
                            incant.append(line).append(";");
                            descriptionLine = true;
                            break;
                        case("Base Cost:"):
                            //System.out.println("Resist Check: " + line);
                            incant.append(line).append(";");
                            break;
                        case("Charge Cost:"):
                            //System.out.println("Target: " + line);
                            incant.append(line).append(";");
                            break;
                        case("Specialization:"):
                            //System.out.println("Duration: " + line);
                            incant.append(line).append(";");
                            break;
                        case("Special Ingredients:"):
                            //System.out.println("Area: " + line);
                            incant.append(line).append(";");
                            incants.add(incant.toString());
                            incant = new StringBuilder();
                            onIncant = false;
                            break;
                    }

                }

                if(!onIncant && line.contains("Type"))
                {
                    onIncant = true;
                    incant.append(readSpecificLine(filePath, lineNum-3)).append(";");
                }

                previousLine = line;
                lineNum += 1;
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

    public static String readSpecificLine(String filePath, int lineNumber) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int currentLine = 1;
            while ((line = reader.readLine()) != null) {
                if (currentLine == lineNumber) {
                    return line;
                }
                currentLine++;
            }
            return null; // Line number not found
        }
    }

}
