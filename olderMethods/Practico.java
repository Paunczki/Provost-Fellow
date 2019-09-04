import java.io.*;
import java.util.*;
public class Practico {
	public static double sumDifference = 0.0;
	public static double bigCounter = 0.0;
	public static int smallCounter = 1;
	public static int largeCounter = 1;
	public static double avgBigTime;
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
	}
	public static void fileWrites(String input) {
		FileWriter fw;
		try{
			fw = new FileWriter(new File("saveFileName"));
			fw.write(input +",");
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	public static void readDatas(File a, File b) throws FileNotFoundException{
		int k =0;
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
		
		int j =0;
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
		
		// real quickly have to find where the 10 second mark is
		ArrayList<Integer> tenA = new ArrayList<Integer>();
		ArrayList<Integer> tenB = new ArrayList<Integer>();
		for(int l=0; l<timeA.size(); l++) {
			for(int m=0; m<timeA.get(l).size(); m++) {
				if((timeA.get(l).get(m)) > (timeA.get(l).get(0)+10)) {
					tenA.add(m);break;
				}
			}
		}
		for(int l=0; l<timeB.size(); l++) {
			for(int m=0; m<timeB.get(l).size(); m++) {
				if((timeB.get(l).get(m)) > (timeB.get(l).get(0)+10)) {
					tenB.add(m);break;
				}
			}
		}
		double sumDiff=0.0;
		int counter=0;
		for(int i=0; i<packetSizeA.size(); i++) {
			for(int m=tenA.get(i); m<packetSizeA.get(i).size(); m++){
				for(int s=0; s<tenB.size(); s++) {
					for(int n=tenB.get(s); n<packetSizeB.get(i).size(); n++){
						if(!((packetSizeA.get(i).get(m)).equals(packetSizeB.get(i).get(n)))){
							if(packetSizeA.get(i).get(m)>0) {
								double time = timeA.get(i).get(m) - timeA.get(i).get(0);
								sumDiff+=time;
								counter++;
								System.out.println(time);
								System.out.println(i);
								System.out.println(m);
								System.out.println(n);
								System.out.println("");
								break;
							}
						}
					}
				}
			}
		}
		sumDifference+=sumDiff;
		bigCounter+=counter;
		double averageTime = sumDiff/counter;
		System.out.println(averageTime);
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
					if(combinationChecker() == true) {
						long time1 = System.currentTimeMillis();
						readData(fileA, fileB);
						pCounter++;
						long time2 = System.currentTimeMillis();
						double time = (time2-time1)/1000.0;
						System.out.println("Program took " + time + " seconds");
						System.out.println(((pCounter / 1.12)) + "% completed" + " | " + fCounter + "/112 times\n");
					}
					else {
						continue;
					}
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
		fileWrites(a + " v " + b + ",");
		int k =0;
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
		
		int j =0;
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
//		ArrayList<Integer> X1 = new ArrayList<Integer>();
//		ArrayList<Integer> X2 = new ArrayList<Integer>();
//		for(int i=0; i<timeA.size(); i++) {
//			for(int z=0; z<timeA.get(i).size(); z++) {
//				if(((timeA.get(i).get(0))+15)<(timeA.get(i).get(z))){
//					X1.add(z);
//					break;
//				}
//			}
//		}
//		for(int i=0; i<timeB.size(); i++) {
//			for(int z=0; z<timeB.get(i).size(); z++) {
//				if(((timeB.get(i).get(0))+15)<(timeB.get(i).get(z))){
//					X2.add(z);
//					break; 
//				}
//			}
//		}
		ArrayList<ArrayList<Double>> L2sData = new ArrayList<ArrayList<Double>>();
		for(int w=0; w<L2.size(); w++) {
			for(int x=0; x<L2.get(w); x++) {
				L2sData.add(new ArrayList<Double>());
				L2sData.get(w).add(packetSizeB.get(w).get(x));
			}
		}
//		ArrayList<ArrayList<Double>> X2sData = new ArrayList<ArrayList<Double>>();
//		for(int w=0; w<X2.size(); w++) {
//			for(int x=L2.get(w); x<X2.get(w); x++) {
//				X2sData.add(new ArrayList<Double>());
//				X2sData.get(w).add(packetSizeB.get(w).get(x));
//			}
//		}
		ArrayList<ArrayList<Double>> unique1 = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> unique2 = new ArrayList<ArrayList<Double>>();
		double sumDiff=0.0;
		int counter=0;
		for(int z=0; z<100; z++) {
			try {
			unique1.add(new ArrayList<Double>());
			unique2.add(new ArrayList<Double>());
			for(int y=0; y<L1.get(z); y++) {
				if(!L2sData.get(z).contains(packetSizeA.get(z).get(y))) {
					unique1.get(z).add(packetSizeA.get(z).get(y));
				}
			}
			for(int y=0; y<L2.get(z); y++) {
				try {
				if(!(packetSizeA.get(z)).contains(L2sData.get(z).get(y))) {
					unique2.get(z).add(L2sData.get(z).get(y));
				}
				}
				catch(Exception e) {
					break;
				}
			}
			for(int i=0; i<unique1.get(z).size(); i++) {
				if((unique1.get(z).get(i))>0) {
					int temp = L1.get(i);
					fileWrites((timeA.get(z).get(temp)-timeA.get(z).get(0)) + ",");
					sumDiff+=timeA.get(z).get(temp)-timeA.get(z).get(0);
					counter++;
					break;
				}
			}
			fileWrites("\n");
			for(int i=0; i<unique2.get(z).size(); i++) {
				if((unique2.get(z).get(i))>0) {
					int temp = L2.get(i);
					fileWrites((timeB.get(z).get(temp)-timeB.get(z).get(0)) + ",");
					sumDiff+=timeB.get(z).get(temp)-timeB.get(z).get(0);
					counter++;
					break;
				}
			}
			fileWrites("\n");
			}
			catch(Exception e){
				break;
			}
		}
//		ArrayList<ArrayList<Double>> uniqueX1 = new ArrayList<ArrayList<Double>>();
//		ArrayList<ArrayList<Double>> uniqueX2 = new ArrayList<ArrayList<Double>>();
//		
//		for(int z=0; z<100; z++) {
//			try {
//			uniqueX1.add(new ArrayList<Double>());
//			uniqueX2.add(new ArrayList<Double>());
//			for(int y=L1.get(z); y<X1.get(z); y++) {
//				if(!X2sData.get(z).contains(packetSizeA.get(z).get(y))) {
//					uniqueX1.get(z).add(packetSizeA.get(z).get(y));
//				}
//			}
//			for(int y=0; y<(X2.get(z) - L2.get(z)); y++) {
//				if(!packetSizeA.get(z).contains(X2sData.get(z).get(y))) {
//					uniqueX2.get(z).add(X2sData.get(z).get(y));
//				}
//			}
//			
//			for(int i=0; i<unique1.get(z).size(); i++) {
//				if((unique1.get(z).get(i))>0) {
//					int temp = L1.get(i);
//					sumDiff+=timeA.get(z).get(temp)-timeA.get(z).get(0);
//					counter++;
//					break;
//				}
//			}
//			for(int i=0; i<unique2.get(z).size(); i++) {
//				if((unique2.get(z).get(i))>0) {
//					int temp = L2.get(i);
//					sumDiff+=timeB.get(z).get(temp)-timeB.get(z).get(0);
//					counter++;
//					break;
//				}
//			}
//			}
//			catch(Exception e){
//				break;
//			}
//		}
		if(!(counter==0)) {
			sumDifference += sumDiff;
			bigCounter += counter;
			double diffAvg = sumDiff/counter;
			System.out.println("Average time of this session: " + diffAvg + "seconds");
			fileWrites("Average time of this session: " + diffAvg + "seconds\n");
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
}