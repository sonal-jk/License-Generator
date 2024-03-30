import java.io.*;

public class Main {

    public static void main(String[] args) {
        String inputFile = "name.txt";
        String outputFile = "newname.txt";
        String searchString = "Sonal";
        String replacement = "Monkey D. Luffy";
        String searchAge="20";
        String replaceAge="25";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the line contains the specific text to replace
                if (line.contains(searchString)) {
                    // Replace the specific text with the new text
                    line = line.replace(searchString, replacement);
                }
                if (line.contains(searchAge)){
                    line=line.replace(searchAge,replaceAge);
                }
                // Write the modified or unmodified line to the output file
                writer.write(line);
                writer.newLine();
            }
            System.out.println("File modification completed successfully.");


//            toPDF.convertpdf(new File("./newFile.txt"));
            toDOCX.convertDOCX(new File("./newFile.txt"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
