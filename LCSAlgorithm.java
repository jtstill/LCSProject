//import java.lang.Math;

public class LCSAlgorithm {
    
    protected UserFile f1;
    protected UserFile f2;
    private int [][] t;
    private String [] commonLines;

    public LCSAlgorithm(UserFile file1, UserFile file2){
        f1 = file1;
        f2 = file2;
        t = findLCS();
        commonLines = retrieval(t);
    }

    // Gets the number of lines in the given file
    public int totalLines(UserFile file){
        return file.lines.size();
    }

    public int numOfCommonLines()
    {
        //System.out.println("LCS length = " + t[t.length-1][t[0].length-1]);
        return t[t.length-1][t[0].length-1];
    }

    // the main algorithm
    // public String findLCS(){
    private int [][] findLCS(){
        // build the table with f1 + 1 columns, f2 + 1 rows
        int [][] table = new int[totalLines(f1)+1][totalLines(f2)+1];

        // for each cell, check if that line matches
            // if match, compare left, up, diagonal+1
            // else compare only left and right
        // NOTE: first column and first row are going to be all zeroes
        for (int i = 0; i<table.length; i++){
            for (int j = 0; j<table[i].length; j++){
                // if in first row or first column, the entry is just 0
                if (i==0 || j==0){
                    table[i][j] = 0;
                }
                else{// compare left, up, (diagonal if match)
                    // filler
                    int left = table[i-1][j];
                    int top = table[i][j-1];
                    int biggest = top; // default to top
                    if (left > top)
                        biggest = left; // change to left if larger
                    
                    if (f1.lines.get(i-1).equals(f2.lines.get(j-1))){
                        int diagonal = table[i-1][j-1];
                        if (diagonal+1 >= biggest)
                            biggest = diagonal+1; // change to diagonal+1 if strings are equal AND diagonal+1 is larger
                    }

                    table[i][j] = biggest;

                    //System.out.println(printTable(table));

                }
            } 
        }

        // print here for now
        // System.out.println("Printing table:");
        // System.out.println(printTable(table));

        return table;
    }

    // input: LCS solved table
    // output String Array
    // indexes from end of table
    private String[] retrieval(int[][] table)
    {
        int i = table.length -1;
        int j = table[i].length -1;
        String[] out = new String[table[i][j]];


        // while cell your at does not = 0 (end)
        while(table[i][j] > 0)
        {
            // define values
            int left = table[i-1][j];
            int top = table[i][j-1];
            int diagonal = table[i-1][j-1];

            // if top and left are the same -> check for matching substrings
            if(top == left)
            {
                if (f1.lines.get(i-1).equals(f2.lines.get(j-1)))
                {
                    // diagnonal makes a good index for array placement
                    out[diagonal] = f1.lines.get(i-1);
                    i--;
                    j--;
                }

                else // if equal and not a match -> move up one 
                    i--;
            }
            
            else if (top > left) j--;
            else i--;
        }
        return out;
    }

    
    private String printTable(int [][] table){
        String result = "";

        result += "\t";
        for (int k = 0; k<table[0].length; k++){
            //System.out.print("COL " + (k+1) + "\t");
            if(k>=1) // in column at index 1, print index 0 of lines
                result += f2.lines.get(k-1).substring(0,2) + "\t";
            else
                result += "\t";
        }
        result += "\n";
        for (int i = 0; i<table.length; i++){
            //System.out.print("ROW " + (i+1) + "\t");
            if (i >= 1)
                result += f1.lines.get(i-1).substring(0,2) + "\t";
            else 
                result += "\t";
            for (int j = 0; j<table[i].length; j++){
                result += table[i][j] + "\t";
            }
            result += "\n";
        }
        
        return result;
    }

    // takes file to compare and a file name
    private String feedback(UserFile feedFile, String fileName)
    {
        int fileIndex = 0;
        int stringIndex = 0;
        String feedback = "Feedback for " + fileName + " is: \n" ;
        
        while(fileIndex < totalLines(feedFile))
        {
            // if there are still strings in lCS and file line ==  LCS line
            if (stringIndex < commonLines.length && 
                feedFile.lines.get(fileIndex).equals(commonLines[stringIndex]))
            {
                feedback += feedFile.lines.get(fileIndex) + " | is in both files \n";
                stringIndex++;
            }
            // else it is a unique line
            else
            {
                feedback += feedFile.lines.get(fileIndex) + " | is only in " + fileName + "\n";
            }
            // iterate through file Index every time
            fileIndex++;
        }
        return feedback;
    }

    private String formattedPrint(String [] arr)
    {
        String out = "[";
        for (int i = 0; i < arr.length - 1; i++)
        {
            out += arr[i] + ", ";
        }
        out += arr[arr.length-1] + "]";
        return out;
    }

    // toString method
    @Override
    public String toString(){
        String output = "";
        output += "# of common lines: " + numOfCommonLines() + "\n";
        output += "Common lines are: \n" + formattedPrint(commonLines) + "\n \n";
        
        output += feedback(f1, "file 1") + "\n";
        output += feedback(f2, "file 2") + "\n";

        return output;
    }
}
