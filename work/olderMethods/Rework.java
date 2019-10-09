import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
public class Rework {
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
	public static ArrayList<Integer> combinations = new ArrayList<Integer>();
	public static void main(String[] args) throws FileNotFoundException {
		long time5 = System.currentTimeMillis();
		readData();
		long time6 = System.currentTimeMillis();
		double timeMinutes = (Math.round(((time6-time5) / 1000.0) / 60.0))-1;
		double timeSeconds = ((time6-time5)/1000.0) - (timeMinutes*60.0);
		avgBigTime = sumDifference / bigCounter;
		System.out.println("\n\n\n****************************************************************");
		System.out.println("Program took " + timeMinutes + " minutes and " + timeSeconds + " seconds");
		System.out.println("****************************************************************\n");
		System.out.println("Average time throughout was " + avgBigTime + " seconds");
		System.out.println((percentCalculator / totalCalculator)*100.0 + "% correct");
		System.out.println("Total Packets tested... " + total);
		System.out.println("Amount of positive packets found... " + presentPackets);
		System.out.println("Amount of missing packets... " + missingPackets);
		fileWrites("Total Packets tested... " + total + "\n");
		fileWrites("Amount of positive packets found... " + presentPackets + "\n");
		fileWrites("Amount of missing packets... " + missingPackets + "\n");
	}
	public static void fileWrites(String input) {
		BufferedWriter bf;
		try{
			bf = new BufferedWriter(new FileWriter("saveFileName4", true));
			bf.write(input);
			bf.close();
		}
		catch(IOException e){
			System.out.println("Error");
			System.exit(0);
			e.printStackTrace();
		}
	}
	public static void fileWritesReference(String input) {
		BufferedWriter bf;
		try{
			bf = new BufferedWriter(new FileWriter("writeReferences2", true));
			bf.write(input);
			bf.close();
		}
		catch(IOException e){
			System.out.println("Error");
			System.exit(0);
			e.printStackTrace();
		}
	}
	public static void readData() throws FileNotFoundException{
		try {
			int pCounter=0;
			int fCounter=0;
			File dir = new File("10s_website1 copy");
			for (File fileA : dir.listFiles()) {
				long time3 = System.currentTimeMillis();
				pCounter = 0;
				for (File fileB : dir.listFiles()) {
					//if(combinationChecker() == true) {
						long time1 = System.currentTimeMillis();
						readData(fileA, fileB);
						pCounter++;
						long time2 = System.currentTimeMillis();
						double time = (time2-time1)/1000.0;
						System.out.println("Program took " + time + " seconds");
						System.out.println(((pCounter / 1.12)) + "% completed" + " | " + fCounter + "/112 times\n");
					//}
					//else {
					//	continue;
					//}
				}
				fCounter++;
				long time4 = System.currentTimeMillis();
				double time = (time4-time3)/1000.0;
				System.out.println("****************************************************************");
				System.out.println("Program took " + time);
				avgBigTime = sumDifference / bigCounter;
				System.out.println("Average time throughout was " + avgBigTime + " seconds");
				System.out.println("****************************************************************\n");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void readData(File a, File b) throws FileNotFoundException{
		fileWrites(a + " vs. " + b + "\n");
		int k = 0;
		File fileA = a;
		Scanner scannerA = new Scanner(fileA);
		String dataA;
		String[] splitA;
		ArrayList<ArrayList<Double>> timeA = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> packetSizeA = new ArrayList<ArrayList<Double>>();
		while(scannerA.hasNextLine()) {
			dataA = scannerA.nextLine();
			String[] singlesA = dataA.split(" ");
			for (int i=2; i<singlesA.length; i++) {
				splitA = singlesA[i].split(":");
				timeA.add(new ArrayList<Double>());
				packetSizeA.add(new ArrayList<Double>());
				timeA.get(k).add(Double.parseDouble(splitA[0]));
				packetSizeA.get(k).add(Double.parseDouble(splitA[1]));
			}k++;
		}scannerA.close();
		
		int j = 0;
		File fileB = b;
		Scanner scannerB = new Scanner(fileB);
		String dataB;
		String[] splitB;
		ArrayList<ArrayList<Double>> timeB = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> packetSizeB = new ArrayList<ArrayList<Double>>();
		while(scannerB.hasNextLine()) {
			dataB = scannerB.nextLine();
			String[] singlesB = dataB.split(" ");
			for (int i=2; i<singlesB.length; i++) {
				splitB = singlesB[i].split(":");
				timeB.add(new ArrayList<Double>());
				packetSizeB.add(new ArrayList<Double>());
				timeB.get(j).add(Double.parseDouble(splitB[0]));
				packetSizeB.get(j).add(Double.parseDouble(splitB[1]));
			}j++;
		}scannerB.close();
		
		ArrayList<Integer> L1 = new ArrayList<Integer>();
		ArrayList<Integer> L2 = new ArrayList<Integer>();
		for(int i=0; i<timeA.size(); i++) {
			for(int z=0; z<timeA.get(i).size(); z++) {
				if(((timeA.get(i).get(0))+10)<(timeA.get(i).get(z))){
					L1.add(z);
					break;
				}
			}
		}
		for(int i=0; i<timeB.size(); i++) {
			for(int z=0; z<timeB.get(i).size(); z++) {
				if(((timeB.get(i).get(0))+10)<(timeB.get(i).get(z))){
					L2.add(z);
					break;
				}
			}
		}
		int countero = 0;
		ArrayList<ArrayList<Double>> L2sData = new ArrayList<ArrayList<Double>>();
		for(int w=0; w<L2.size(); w++) {
			String references = "";
			L2sData.add(new ArrayList<Double>());
			for(int x=0; x<L2.get(w); x++) {
				L2sData.get(w).add(packetSizeB.get(w).get(x));
				references += L2sData.get(w).get(x) + (",");
			}
			if((countero < 1)&&(counteros < 113)) {
				fileWritesReference(references + "\n");
			}
			countero++;
		}
		counteros++;
		
		
		ArrayList<ArrayList<Double>> unique1 = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> unique2 = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> uniqueTime1 = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> uniqueTime2 = new ArrayList<ArrayList<Double>>();
		double sumDiff=0.0;
		double counter=0.0;
		for(int z=0; z<100; z++) {
			try {
			unique1.add(new ArrayList<Double>());
			unique2.add(new ArrayList<Double>());
			uniqueTime1.add(new ArrayList<Double>());
			uniqueTime2.add(new ArrayList<Double>());
			// Here write a for loop if want to check one line against all others
			/*
		ArrayList<ArrayList<ArrayList<Double>>> unique1 = new ArrayList<ArrayList<ArrayList<Double>>>();
		ArrayList<ArrayList<ArrayList<Double>>> unique2 = new ArrayList<ArrayList<ArrayList<Double>>>();
		for(int z=0; z<100; z++) {
			try {
			unique1.add(new ArrayList<ArrayList<Double>>());
			unique2.add(new ArrayList<ArrayList<Double>>());	
			for(int c=0; c<L2.size(); c++){
				unique1.get(z).add(new ArrayList<Double>());
				unique2.get(z).add(new ArrayList<Double>());
			 	for(int y=0; y<L1.get(z); y++) {
					if(!L2sData.get(c).contains(packetSizeA.get(z).get(c).get(y))) {
						unique1.get(z).get(c).add(packetSizeA.get(z).get(c).get(y));
					}
				}
				for(int y=0; y<L2.get(z); y++) {
					try {
						if(!(packetSizeA.get(c)).contains(L2sData.get(z).get(c).get(y))) {
							unique2.get(z).get(c).add(L2sData.get(z).get(c).get(y));
						}
					}
					catch(Exception e) {
						break;
					}
				}
				for(int i=0; i<unique1.get(z).size(); i++) {
					if((unique1.get(z).get(c).get(i))>0) {
						int temp = L1.get(i);
						fileWrites((timeA.get(z).get(c).get(temp)-timeA.get(z).get(c).get(0)) + ",");
						sumDiff+=timeA.get(z).get(c).get(temp)-timeA.get(z).get(c).get(0);
						counter++;
						break;
					}
				}
				fileWrites("\n");
				for(int i=0; i<unique2.get(z).size(); i++) {
					if((unique2.get(z).get(c).get(i))>0) {
						int temp = L2.get(i);
						fileWrites((timeB.get(z).get(c).get(temp)-timeB.get(z).get(c).get(0)) + ",");
						sumDiff+=timeB.get(z).get(c).get(temp)-timeB.get(z).get(c).get(0);
						counter++;
						break;
					}
				}
				fileWrites("\n");
			 	}
			 	}
			 	catch(Exception e){
					break;
				}
			}
			if(!(counter==0)) {
				sumDifference += sumDiff;
				bigCounter += counter;
				double diffAvg = sumDiff/counter;
				System.out.println("Average time of this session: " + diffAvg + "seconds");
				fileWrites("Average time of this session: " + diffAvg + "seconds\n");
			}
			}
		}
			 */
			for(int y=0; y<L1.get(z); y++) {
				if(!L2sData.get(z).contains(packetSizeA.get(z).get(y))) {
					unique1.get(z).add(packetSizeA.get(z).get(y));
					uniqueTime1.get(z).add(timeA.get(z).get(y));
				}
			}
			for(int y=0; y<L2.get(z); y++) {
				try {
				if(!(packetSizeA.get(z)).contains(L2sData.get(z).get(y))) {
					unique2.get(z).add(L2sData.get(z).get(y));
					uniqueTime2.get(z).add(timeB.get(z).get(y));
				}
				}
				catch(Exception e) {
					break;
				}
			}
			boolean pizza = true;
			int counterZ = 0;
			for(int i=0; i<unique1.get(z).size(); i++) {
				if(((unique1.get(z).get(i))>0)) {
					if(counterZ == 3) {
						DecimalFormat df = new DecimalFormat("#");
						df.setMaximumFractionDigits(6);
						fileWrites("line " +(z+1)+","+df.format(timeA.get(z).get(0))+","+df.format(uniqueTime1.get(z).get(i))+"\n");
						//DecimalFormat df = new DecimalFormat("#");
				        //df.setMaximumFractionDigits(8);
						//fileWrites((timeA.get(z).get(temp)-timeA.get(z).get(0)) + ",");
						sumDiff+=uniqueTime1.get(z).get(i)-timeA.get(z).get(0);
						counter++;
						if(9<(uniqueTime1.get(z).get(i)-timeA.get(z).get(0)) && (uniqueTime1.get(z).get(i)-timeA.get(z).get(0))<11){
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
			
//			for(int i=0; i<unique2.get(z).size(); i++) {
//				//totalCalculator++;
//				if((unique2.get(z).get(i))>0) {
//					//int temp = L2.get(i);
//					//fileWrites("line " +z+","+timeB.get(z).get(0)+","+timeB.get(z).get(temp)+"\n");
//					//fileWrites((timeB.get(z).get(temp)-timeB.get(z).get(0)) + ",");
//					sumDiff+=uniqueTime2.get(z).get(i)-timeB.get(z).get(0);
//					counter++;
//					//if(9<(timeB.get(z).get(temp)-timeB.get(z).get(0)) && (timeB.get(z).get(temp)-timeB.get(z).get(0))<11){
//						//percentCalculator++;
//					//}
//					break;
//				}
//			}
			}
			catch(Exception e){
				break;
			}
		}
		if(!(counter==0)) {
			sumDifference += sumDiff;
			bigCounter += counter;
			double diffAvg = sumDiff/counter;
			System.out.println("Average time of this session: " + diffAvg + "seconds");
			fileWrites("Average time of this session: " + diffAvg + "seconds\n");
			fileWrites("*************************************************************\n");
		}
	}
	public static boolean combinationChecker() {
		if(smallCounter>112) {
			smallCounter = 1;
			largeCounter++;
		}
		int adder = (largeCounter*1000) + smallCounter;
		int adders = (smallCounter*1000) + largeCounter;
		if((combinations.contains(adder))){
			smallCounter++;
			return false;
		}
		if((!(combinations.contains(adder)))) {
			combinations.add(adder);
			combinations.add(adders);
			smallCounter++;
			return true;
		}
		else {
			System.out.println(smallCounter + "  " + largeCounter);
			System.out.println("\n`````````````````````````````````````````````````````````");
			System.out.println("Error Occured, exiting");
			System.out.println("`````````````````````````````````````````````````````````\n");
			System.exit(0);
			return false;
		}
	}
	/*
	 * Here psuedocode a way to collect with what percent can we get this to work
	 * 	First thing is to make decision of what range we want to be accurate
	 * 	From here have a counter to count how many iterations were done
	 * 	Have another counter to see how many times our guess is within the data range
	 * 	divide correct/total * 100% and see results.
	 */
	
}