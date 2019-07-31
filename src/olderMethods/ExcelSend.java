import java.util.*;
import java.io.*;
public class ExcelSend {
	public static void main(String[] args) {
		long time1 = System.currentTimeMillis();
		
		String fileName = "resultRef v 20";
		File file = new File(fileName);
		read(file);
		
		
		long time2 = System.currentTimeMillis();
		System.out.println("Time taken: "+((time2-time1)/1000.0)+" seconds");
	}
	public static void read(File file) {
		try {
			Scanner sc = new Scanner(file);
			String temp = "";
			String subTemp = "";
			int OccurrenceCounter = -1;
			File OccVavgTime = new File("OccVavgTime");
			File OccVmedianTime = new File("OccVmedianTime");
			File OccVpercentCorrect = new File("OccVpercentCorrect");
			File OccVstdev = new File("OccVstdev");
			clear(OccVavgTime);clear(OccVmedianTime);clear(OccVpercentCorrect);clear(OccVstdev);
			while(sc.hasNextLine()) {
				try {
					temp = sc.nextLine();
					subTemp = temp.substring(0, 5);
					if(subTemp.equals("Occur")) {
						String occur = temp.substring(14);
						temp = sc.nextLine();
						String median = temp.substring(27)+"\n";
						writeToFile(OccVmedianTime,occur+":"+median);
						temp = sc.nextLine();
						String stdev = temp.substring(27)+"\n";
						writeToFile(OccVstdev,occur+":"+stdev);
						temp = sc.nextLine();
						String avgTime = temp.substring(32,46);
						writeToFile(OccVavgTime,occur+":"+avgTime+"\n");
						temp = sc.nextLine();
						String percentCorrect = temp.substring(4,7);
						writeToFile(OccVpercentCorrect,occur+":"+percentCorrect+"\n");
					}
					if((subTemp.equals("First"))||(subTemp.equals("Secon"))||
							(subTemp.equals("Third"))||(subTemp.equals("Fourt"))){
						OccurrenceCounter++;
						if(OccurrenceCounter%2 == 0) {
							if(OccurrenceCounter/2 == 0) {
								writeToFile(OccVavgTime,"First Occurrence\n");
								writeToFile(OccVmedianTime,"First Occurrence\n");
								writeToFile(OccVpercentCorrect,"First Occurrence\n");
								writeToFile(OccVstdev,"First Occurrence\n");
							}
							if(OccurrenceCounter/2 == 1) {
								writeToFile(OccVavgTime,"Second Occurrence\n");
								writeToFile(OccVmedianTime,"Second Occurrence\n");
								writeToFile(OccVpercentCorrect,"Second Occurrence\n");
								writeToFile(OccVstdev,"Second Occurrence\n");
							}
							if(OccurrenceCounter/2 == 2) {
								writeToFile(OccVavgTime,"Third Occurrence\n");
								writeToFile(OccVmedianTime,"Third Occurrence\n");
								writeToFile(OccVpercentCorrect,"Third Occurrence\n");
								writeToFile(OccVstdev,"Third Occurrence\n");
							}
							if(OccurrenceCounter/2 == 3) {
								writeToFile(OccVavgTime,"Fourth Occurrence\n");
								writeToFile(OccVmedianTime,"Fourth Occurrence\n");
								writeToFile(OccVpercentCorrect,"Fourth Occurrence\n");
								writeToFile(OccVstdev,"Fourth Occurrence\n");
							}
						}
						if(OccurrenceCounter%2 == 1) {
							continue;
						}
					}
					else {
						continue;
					}
				}
				catch(StringIndexOutOfBoundsException e) {
					continue;
				}
			}
		}
		catch(IOException e) {
			System.out.println("Error2");
			System.exit(0);
		}
	}
	public static void writeToFile(File file,String input) {
		try {
			BufferedWriter bf= new BufferedWriter(new FileWriter(file, true));
			bf.write(input);
			bf.close();
		}
		catch(IOException e) {
			System.out.println("Error");
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
