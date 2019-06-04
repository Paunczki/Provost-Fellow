import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
public class UniversalReference{
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
	public static ArrayList<Integer> combinations = new ArrayList<Integer>();
	public static void main(String[] args) throws FileNotFoundException {
		long time5 = System.currentTimeMillis();
		fileWritesClear();
		readData();
		long time6 = System.currentTimeMillis();
		double timeMinutes = (Math.round(((time6-time5) / 1000.0) / 60.0))-1;
		double timeSeconds = ((time6-time5)/1000.0) - (timeMinutes*60.0);
		avgBigTime = sumDifference / bigCounter;
		System.out.println("\n\n\n****************************************************************");
		System.out.println("Program took " + timeMinutes + " minutes and " + timeSeconds + " seconds");
		System.out.println("****************************************************************\n");
		MedianFinder f = new MedianFinder();
		System.out.println("Average time throughout was " + avgBigTime + " seconds");
		System.out.println((percentCalculator / totalCalculator)*100.0 + "% correct");
		System.out.println("Total Lines tested... " + total);
		System.out.println("Amount of positive packets found... " + presentPackets);
		System.out.println("Amount of missing packets... " + missingPackets);
		fileWrites("Total Packets tested... " + total + "\n");
		fileWrites("Amount of positive packets found... " + presentPackets + "\n");
		fileWrites("Amount of missing packets... " + missingPackets + "\n");
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
	public static HashMap<Double,Integer> reference = new HashMap<Double,Integer>();
	//public static ArrayList<Double> reference = new ArrayList<Double>();
	public static void saveReference() throws FileNotFoundException {
		File file = new File("writeReferences5");
		Scanner scanner = new Scanner(file);
		String data = "";
		while(scanner.hasNextLine()) {
			data = scanner.nextLine();
			String[] split = data.split("  |  ");
			if(Double.parseDouble(split[2]) > 1000) {
				reference.put(Double.parseDouble(split[0]),Integer.parseInt(split[2]));
			}
		}
		System.out.println("Reference map completed!");
	}
	public static void readData() throws FileNotFoundException{
		try {
			File dir = new File("20s_website1");
			saveReference();
			int counter =0;
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
		int k = 0;
		File fileA = a;
		Scanner scannerA = new Scanner(fileA);
		String dataA;
		String[] splitA;
		ArrayList<ArrayList<Double>> timeA = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> packetSizeA = new ArrayList<ArrayList<Double>>();
		int lineCounter = 0;
		while(scannerA.hasNextLine()) {
			dataA = scannerA.nextLine();
			lineCounter++;
			String[] singlesA = dataA.split(" ");
			for (int i=2; i<singlesA.length; i++) {
				splitA = singlesA[i].split(":");
				timeA.add(new ArrayList<Double>());
				packetSizeA.add(new ArrayList<Double>());
				timeA.get(k).add(Double.parseDouble(splitA[0]));
				packetSizeA.get(k).add(Double.parseDouble(splitA[1]));
			}k++;
		}scannerA.close();
		
//		ArrayList<Integer> L1 = new ArrayList<Integer>();
//		for(int i=0; i<timeA.size(); i++) {
//			for(int z=0; z<timeA.get(i).size(); z++) {
//				if(((timeA.get(i).get(0))+10)<(timeA.get(i).get(z))){
//					L1.add(z);
//					break;
//				}
//			}
//		}
		
		ArrayList<ArrayList<Double>> unique1 = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> uniqueTime1 = new ArrayList<ArrayList<Double>>();
		double sumDiff=0.0;
		double counter=0.0;
		for(int z=0; z<lineCounter; z++) {
			try {
			unique1.add(new ArrayList<Double>());
			uniqueTime1.add(new ArrayList<Double>());
			try {
			for(int y=0; y<(packetSizeA.get(z).size()); y++) {
				if(!reference.containsKey(packetSizeA.get(z).get(y))) {
					unique1.get(z).add(packetSizeA.get(z).get(y));
					uniqueTime1.get(z).add(timeA.get(z).get(y));
				}
			}
			}
			catch(Exception e) {
				errorCounter++;
				break;
			}
			boolean pizza = true;
			int counterZ = 0;
			for(int i=0; i<unique1.get(z).size(); i++) {
				if(((unique1.get(z).get(i))>0)) {
					if(counterZ == 0) {
						DecimalFormat df = new DecimalFormat("#");
						df.setMaximumFractionDigits(6);
						fileWrites("line " +(z+1)+","+df.format(timeA.get(z).get(0))+","+df.format(uniqueTime1.get(z).get(i))+"\n");
						sumDiff+=uniqueTime1.get(z).get(i)-timeA.get(z).get(0);
						counter++;
						if(19<(uniqueTime1.get(z).get(i)-timeA.get(z).get(0)) && (uniqueTime1.get(z).get(i)-timeA.get(z).get(0))<21){
							percentCalculator++;
						}
						presentPackets++;
						pizza = false;
						break;
					}
					counterZ++;
				}
			}
			if(pizza == true) {
				missingPackets++;
			}
			totalCalculator++;
			total++;
			}
			catch(Exception e){
				errorCounter++;
				break;
			}
		}
		if(!(counter==0)) {
			sumDifference += sumDiff;
			bigCounter += counter;
			double diffAvg = sumDiff/counter;
			fileWrites("Average time of this session: " + diffAvg + " seconds\n");
			fileWrites("*************************************************************\n");
		}
	}
}