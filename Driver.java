public class Driver {

    public static void main(String [] args) {

        UserFile testfile1 = new UserFile("./LCSProject/testfile1.txt");
        System.out.println("---------- FILE 1 ----------");
        System.out.println(testfile1);

        UserFile testfile2 = new UserFile("./LCSProject/testfile2.txt");
        System.out.println("---------- FILE 2 ----------");
        System.out.println(testfile2);

        LCSAlgorithm example = new LCSAlgorithm(testfile1, testfile2);
        System.out.println(example.findLCS());
    }
}