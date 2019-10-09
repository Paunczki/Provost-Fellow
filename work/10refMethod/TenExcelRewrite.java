import java.util.*;
import java.io.*;

public class TenExcelRewrite {
    static File a1 = new File("10vautomatedFirst");
    static File a2 = new File("10vautomatedSecond");
    static File a3 = new File("10vautomatedThird");
    static File a4 = new File("10vautomatedFourth");

    static File avg10 = new File("10v10occAverageTime");
    static File median10 = new File("10v10occMedianTime");
    static File percent10 = new File("10v10occPercentCorrect");
    static File stdev10 = new File("10v10occStandardDeviation");

    static File avg20 = new File("10v20occAverageTime");
    static File median20 = new File("10v20occMedianTime");
    static File percent20 = new File("10v20occPercentCorrect");
    static File stdev20 = new File("10v20occStandardDeviation");

    static Scanner scanner;
    
    public static void main(String[] args){
        long time1 = System.currentTimeMillis();

        clear(avg10); clear(median10); clear(percent10); clear(stdev10);
        clear(avg20); clear(median20); clear(percent20); clear(stdev20);

        try {
            for (int i = 0; i < 4; i++) {
                if (i == 0) {
                    scanner = new Scanner(a1);
                    work();
                    writeToFile(avg10, "\n\n\n\n");
                    writeToFile(median10, "\n\n\n\n");
                    writeToFile(percent10, "\n\n\n\n");
                    writeToFile(stdev10, "\n\n\n\n");
                    writeToFile(avg20, "\n\n\n\n");
                    writeToFile(median20, "\n\n\n\n");
                    writeToFile(percent20, "\n\n\n\n");
                    writeToFile(stdev20, "\n\n\n\n");
                }
                if (i == 1) {
                    scanner = new Scanner(a2);
                    work();
                    writeToFile(avg10, "\n\n\n\n");
                    writeToFile(median10, "\n\n\n\n");
                    writeToFile(percent10, "\n\n\n\n");
                    writeToFile(stdev10, "\n\n\n\n");
                    writeToFile(avg20, "\n\n\n\n");
                    writeToFile(median20, "\n\n\n\n");
                    writeToFile(percent20, "\n\n\n\n");
                    writeToFile(stdev20, "\n\n\n\n");
                }
                if (i == 2) {
                    scanner = new Scanner(a3);
                    work();
                    writeToFile(avg10, "\n\n\n\n");
                    writeToFile(median10, "\n\n\n\n");
                    writeToFile(percent10, "\n\n\n\n");
                    writeToFile(stdev10, "\n\n\n\n");
                    writeToFile(avg20, "\n\n\n\n");
                    writeToFile(median20, "\n\n\n\n");
                    writeToFile(percent20, "\n\n\n\n");
                    writeToFile(stdev20, "\n\n\n\n");
                }
                if (i == 3) {
                    scanner = new Scanner(a4);
                    work();
                    writeToFile(avg10, "\n\n\n\n");
                    writeToFile(median10, "\n\n\n\n");
                    writeToFile(percent10, "\n\n\n\n");
                    writeToFile(stdev10, "\n\n\n\n");
                    writeToFile(avg20, "\n\n\n\n");
                    writeToFile(median20, "\n\n\n\n");
                    writeToFile(percent20, "\n\n\n\n");
                    writeToFile(stdev20, "\n\n\n\n");
                }
            }
        }
        catch(FileNotFoundException e){
            System.out.println("Error4");
        }

        long time2 = System.currentTimeMillis();
        System.out.println("Time taken: " + ((time2-time1)/1000.00) + " seconds");
    }

    public static void work(){
        File OccVavgTime = avg10;
        File OccVmedianTime = median10;
        File OccVpercentCorrect = percent10;
        File OccVstdev = stdev10;
        String temp = "";
        String subTemp = "";
        while(scanner.hasNextLine()){
            try{
                temp = scanner.nextLine();
				subTemp = temp.substring(0, 5);
				if(subTemp.equals("Occur")) {
					String occur = temp.substring(14);
					temp = scanner.nextLine();
					String median = temp.substring(27)+"\n";
					writeToFile(OccVmedianTime, occur+":"+median);
					temp = scanner.nextLine();
					String stdev = temp.substring(27)+"\n";
					writeToFile(OccVstdev, occur+":"+stdev);
					temp = scanner.nextLine();
					String avgTime = temp.substring(32,46);
					writeToFile(OccVavgTime, occur+":"+avgTime+"\n");
					temp = scanner.nextLine();
					String percentCorrect = temp.substring(4,7);
					writeToFile(OccVpercentCorrect, occur+":"+percentCorrect+"\n");
                }
                if(subTemp.equals("20s_w")){
                    OccVavgTime = avg20;
                    OccVmedianTime = median20;
                    OccVpercentCorrect = percent20;
                    OccVstdev = stdev20;
                }
            }
            catch(StringIndexOutOfBoundsException e){
                continue;
            }
        }
    }

    public static void writeToFile(File file, String input){
        try{
            BufferedWriter bf = new BufferedWriter(new FileWriter(file, true));
            bf.write(input);
            bf.close();
        }
        catch(IOException e){
            System.out.println("Error1");
            System.exit(0);
        }
    }

    public static void clear(File file) {
		try {
			BufferedWriter bf = new BufferedWriter(new FileWriter(file, false));
			bf.write("");
			bf.close();
		}
		catch(IOException e) {
			System.out.println("Error2");
			System.exit(0);
		}
	}
}
