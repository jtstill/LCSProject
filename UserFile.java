import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class UserFile {
    
    protected ArrayList<String> lines;


    public UserFile(String filename){
        lines = new ArrayList<String>();
        try {
            File file = new File(filename);
            Scanner scan = new Scanner(file);
            while (scan.hasNextLine()) {
                lines.add(scan.nextLine());
            }
            scan.close();
          } catch (FileNotFoundException fnfe) {
                System.out.println("File not found");
                fnfe.printStackTrace();
          }
    }


    @Override
    public String toString(){
        String result = "";
        int i = 1;
        for (String line : lines){
            result += "Line " + i + ": " + line + "\n";
            i++;
        }
        return result;
    }
}
