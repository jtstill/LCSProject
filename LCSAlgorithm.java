public class LCSAlgorithm {
    
    protected UserFile f1;
    protected UserFile f2;

    public LCSAlgorithm(UserFile file1, UserFile file2){
        f1 = file1;
        f2 = file2;
    }

    // Gets the number of lines in the given file
    public int totalLines(UserFile file){
        return file.lines.size();
    }

    // the main algorithm
    public String findLCS(){
        // build the table with f1 + 1 columns, f2 + 1 rows
        int [][] table = new int[totalLines(f1)+1][totalLines(f2)+1];
        System.out.println("Dimensions are " + table.length + " and " + table[0].length);
        // [rows][columns]

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
                    table[i][j] = 5;
                }
            } 
        }

        // print here for now
        System.out.println("Printing table:");
        System.out.println(printTable(table));

        // recover information, starting at last cell
            // if left or up is equal to current cell, don't add and move (tiebreaker defaults to up)
            // if left or up are both less than current cell, go diagonal AND add to beginning of LCS
            // go until you hit 0
        
        return "";
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

    // toString method
    @Override
    public String toString(){
        return "";
    }
}
