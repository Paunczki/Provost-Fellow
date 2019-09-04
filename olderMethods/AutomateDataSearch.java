import java.util.*;
import java.io.*;
import java.text.DecimalFormat;

public class AutomateDataSearch {
	public static double sumDifference = 0.0;
	public static double bigCounter = 0.0;
	public static int smallCounter = 1;
	public static int largeCounter = 1;
	public static double avgBigTime;
	public static double percentCalculator = 0.0;
	public static double totalCalculator = 0.0;
	public static int total;
	public static int missingPackets;
	public static int presentPackets;
	public static int counteros = 0;
	public static int errorCounter=0;
	public static double occurrenceInitial = 500;
	public static double occurrenceNext = 500;
	public static boolean pizzas = false;
	public static int counterChange = 0;
	public static int referencNumber = 0;
	public static int referenceCounter = 0;
	public static void main(String[] args) throws FileNotFoundException {
		long time5 = System.currentTimeMillis();
		fileDataWritesClear();
		for(int i=0; i<4; i++) {
			if(referenceCounter == 0) {
				fileReferenceWrite("----------------------------------------------------------------------------"
						+ "\n\nFirst Positive Packet Size\n"
						+ "----------------------------------------------------------------------------\n");
			}  
			if(referenceCounter == 1) {
				fileReferenceWrite("----------------------------------------------------------------------------"
						+ "\n\nSecond Positive Packet Size\n"
						+ "----------------------------------------------------------------------------\n");
			}
			if(referenceCounter == 2) {
				fileReferenceWrite("----------------------------------------------------------------------------"
						+ "\n\nThird Positive Packet Size\n"
						+ "----------------------------------------------------------------------------\n");
			}
			if(referenceCounter == 3) {
				fileReferenceWrite("----------------------------------------------------------------------------"
						+ "\n\nFourth Positive Packet Size\n"
						+ "----------------------------------------------------------------------------\n");
			}
			occurrenceInitial = 500;
			occurrenceNext = 500;
			counterChange = 0;
			while(changeFind()){
				counterChange++;
				if(!(counterChange >= 40)) {
					continue;
				}
				counterChange = 0;
				sumDifference = 0;bigCounter = 0;smallCounter = 1;largeCounter = 1;
				percentCalculator = 0.0;totalCalculator = 0.0;counteros = 0;errorCounter = 0;
				total = 0;missingPackets = 0; presentPackets = 0; 
				reference.clear();
				saveReference();
				fileWritesClear();
				readData();
				avgBigTime = sumDifference / bigCounter;
				writeData();
				if (avgBigTime < 0.1){
					break;
				}
			}
			referenceCounter++;
		}
		long time6 = System.currentTimeMillis();
		double timeMinutes = (Math.round(((time6-time5) / 1000.0) / 60.0))-1;
		double timeSeconds = ((time6-time5)/1000.0) - (timeMinutes*60.0);
		System.out.println("\n\n\n****************************************************************");
		System.out.println("Program took " + timeMinutes + " minutes and " + timeSeconds + " seconds");
		System.out.println("****************************************************************\n");
	}
	public static void writeData() throws FileNotFoundException {
		fileDataWrites("Occurrence >= " + occurrenceNext + "\n");
		MedianFinder f = new MedianFinder();
		fileDataWrites("  - Average time throughout was " + avgBigTime + " seconds\n");
		fileDataWrites("  - " + (percentCalculator / totalCalculator)*100.0 + "% correct\n");
		fileDataWrites("  - Total Packets tested... " + total + "\n");
		fileDataWrites("  - Amount of positive packets found... " + presentPackets + "\n");
		fileDataWrites("  - Amount of missing packets... " + missingPackets + "\n");
	}
	public static void readData() throws FileNotFoundException{
		try {
			File dir = new File("10s_website1 copy");
			int counter = 0;
			for (File fileA : dir.listFiles()) {
				readData(fileA);
				System.out.println(counter++);
			}
		}
		catch (Exception e) {
			errorCounter++;
			e.printStackTrace();
		}
	}
	public static void readData(File a) throws FileNotFoundException{
		fileWrites(a + "\n");
		Scanner scannerA = new Scanner(a);
		String dataA1;
		String[] splitA1;
		HashMap<Double, Double> unique = new HashMap<Double,Double>();
		double sumDiff=0.0;
		double counter=0.0;
		int z = 1;
		while(scannerA.hasNextLine()) {
			dataA1 = scannerA.nextLine();
			String[] singlesA1 = dataA1.split(" ");
			unique.clear();
			double timeInitial = 0;
			try {
				for(int i=2; i<singlesA1.length; i++) {
					splitA1 = singlesA1[i].split(":");
					if(i==2) {
						timeInitial = Double.parseDouble(splitA1[0]);
						continue;
					}
					if(!reference.containsKey(Double.parseDouble(splitA1[1]))) {
						unique.put(Double.parseDouble(splitA1[0]),Double.parseDouble(splitA1[1]));
					}
				}
			}
			catch(Exception IndexOutOfBoundsException) {
				break;
			}
			double earliest1 = Double.MAX_VALUE;
			double earliest2 = Double.MAX_VALUE;
			double earliest3 = Double.MAX_VALUE;
			double earliest4 = Double.MAX_VALUE;
			double finalTime = 0;
			boolean pizza = true;
			for(Double key: unique.keySet()) {
				if(unique.get(key) > 0) {
					if(key<earliest1) {
						earliest4 = earliest3;
						earliest3 = earliest2;
						earliest2 = earliest1;
						earliest1 = key;
					}
					if((key>earliest1) && key<earliest2) {
						earliest4 = earliest3;
						earliest3 = earliest2;
						earliest2 = key;
					}
					if((key>earliest2) && (key<earliest3)){
						earliest4 = earliest3;
						earliest3 = key;
					}
					if((key>earliest3) && (key<earliest4)) {
						earliest4 = key;
					}
				}
			}
			if(referenceCounter == 0) {
				finalTime = earliest1;
				if(earliest1 != Double.MAX_VALUE) {
					pizza = false;
				}
			}
			if(referenceCounter == 1) {
				finalTime = earliest2;
				if(earliest2 != Double.MAX_VALUE) {
					pizza = false;
				}
			}
			if(referenceCounter == 2) {
				finalTime = earliest3;
				if(earliest3 != Double.MAX_VALUE) {
					pizza = false;
				}
			}
			if(referenceCounter == 3) {
				finalTime = earliest4;
				if(earliest4 != Double.MAX_VALUE) {
					pizza = false;
				}
			}
			if(referenceCounter>3) {
				break;
			}
			if(pizza == false) {
				DecimalFormat df = new DecimalFormat("#");
				df.setMaximumFractionDigits(6);
				fileWrites("line " +z+","+df.format(timeInitial)+","+df.format(finalTime)+"\n");
				double timeDiff = finalTime-timeInitial;
				sumDiff+= timeDiff;
				counter++;
				if((9<timeDiff) && (timeDiff<11)){
					percentCalculator++;
				}
				presentPackets++;
			}
			if(pizza == true) {
				missingPackets++;
			}
			totalCalculator++;
			total++;z++;
		}
		if(!(counter==0)) {
			sumDifference += sumDiff;
			bigCounter += counter;
			double diffAvg = sumDiff/counter;
			fileWrites("Average time of this session: " + diffAvg + " seconds\n");
			fileWrites("*************************************************************\n");
		}
	}
	public static boolean changeFind() {
		try {
			File file = new File("writeReferences5");
			Scanner scanner = new Scanner(file);
			String temp = "";
			double min = 10000000.0;
			while(scanner.hasNextLine()) {
				temp = scanner.nextLine();
				String[] split = temp.split("  |  ");
				if((Double.parseDouble(split[2]) > occurrenceInitial)&&(Double.parseDouble(split[2]) < min)) {
					min = Double.parseDouble(split[2]);
				}
			}
			if(min > occurrenceInitial) { // work on error checking this
				occurrenceInitial = occurrenceNext;
				occurrenceNext = min;
				scanner.close();
				//counterChange++;
				return true;
			}
			scanner.close();
			return false;
			}
		catch(Exception FileNotFoundException){
			System.out.println("File Not Found... Exiting");
			return false;
		}
	}
	public static HashMap<Double,Integer> reference = new HashMap<Double,Integer>();
	public static void saveReference() throws FileNotFoundException {
		File file = new File("writeReferences5");
		Scanner scanner = new Scanner(file);
		String data = "";
		while(scanner.hasNextLine()) {
			data = scanner.nextLine();
			String[] split = data.split("  |  ");
			if(Double.parseDouble(split[2]) >= occurrenceNext) {
				reference.put(Double.parseDouble(split[0]),Integer.parseInt(split[2]));
			}
		}
		System.out.println("Reference map completed!");
		scanner.close();
	}
	public static void fileDataWrites(String input) {
		BufferedWriter bf;
		try {
			bf = new BufferedWriter(new FileWriter("automatedData", true));
			bf.write(input);
			bf.close();
		}
		catch(IOException e) {
			System.out.println("Error2");
			System.exit(0);
		}
	}
	public static void fileReferenceWrite(String input) {
		BufferedWriter bf;
		try {
			bf = new BufferedWriter(new FileWriter("automatedData", true));
			bf.write(input);
			bf.write(input);
			bf.close();
		}
		catch(IOException e) {
			System.out.println("Error3");
			System.exit(0);
		}
	}
	public static void fileWrites(String input) {
		BufferedWriter bf;
		try{
			bf = new BufferedWriter(new FileWriter("saveFileName6", true));
			bf.write(input);
			bf.close();
		}
		catch(IOException e){
			System.out.println("Error");
			System.exit(0);
			e.printStackTrace();
		}
	}
	public static void fileDataWritesClear() {
		BufferedWriter bf;
		try{
			bf = new BufferedWriter(new FileWriter("automatedData", false));
			bf.write("");
			bf.close();
		}
		catch(IOException e){
			System.out.println("Error");
			System.exit(0);
			e.printStackTrace();
		}
	}
	public static void fileWritesClear() {
		BufferedWriter bf;
		try{
			bf = new BufferedWriter(new FileWriter("saveFileName6", false));
			bf.write("");
			bf.close();
		}
		catch(IOException e){
			System.out.println("Error");
			System.exit(0);
			e.printStackTrace();
		}
	}

}
