import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;

public class NewWay {
	/*	** Consult google doc to see what patterns seem to emerge **
	 * 	Maybe create a method that will get rid of all common elements 
	 * 		and return what values are left over for each list
	 * 			-- test identity of site
	 * 	Create a method that gets rid of all packets in a provided range
	 * 			-- test to see if the range is a common part of all sites
	 * 	Maybe create a method that takes in argument for a packet size 
	 * 		then it returns how long it took for that packet to be sent
	 * 			-- tests time v web site
	 * 	
	 * 	Need a method now to find where these two web sites are split
	 * 	
	 * 	Maybe do a 3D arrayList so that we could test certain data sets together
	 * 	Might simplify and allow for later to do more
	 */
	public static double sumDiff=0.0;
	public static int counter=0;
	private static String data;
	private static String[] split;
	public static ArrayList<ArrayList<ArrayList<Double>>> time = new ArrayList<ArrayList<ArrayList<Double>>>();
	public static ArrayList<ArrayList<ArrayList<Double>>> packetSize = new ArrayList<ArrayList<ArrayList<Double>>>();
	private static int c = 0;
	
	public static void main(String[] args) throws FileNotFoundException {
		long time1 = System.currentTimeMillis();
//		readData();
//		compareData();
//		System.out.println("\n " + (sumDiff/counter) + "\n");
		readData("youtube.com");		 //0
		readData("amazon.com"); 		 //1
		readData("stackexchange.com");	 //2
		readData("bbc.com");			 //3
		readData("craigslist.org");		 //4
		System.out.println("Hi");
		System.out.println("\n youtube v amazon");
		compareData(0,1);
		System.out.println("\n youtube v stackexchange");
		compareData(0,2);
		System.out.println("\n youtube v bbc");
		compareData(0,3);
		System.out.println("\n youtube v craigslist");
		compareData(0,4);
		System.out.println("\n amazon v stackexchange");
		compareData(1,2);
		System.out.println("\n amazon v bbc");
		compareData(1,3);
		System.out.println("\n amazon v craigslist");
		compareData(1,4);
		System.out.println("\n stackexchange v bbc");
		compareData(2,3);
		System.out.println("\n stackexchange v craigslist");
		compareData(2,4);
		System.out.println("\n bbc v craigslist");
		compareData(3,4);
		long time2 = System.currentTimeMillis();
		double time = (time2-time1)/1000;
		System.out.println("\n\nProgram toook " + time + " seconds");
	}
	
	public static void readData() throws FileNotFoundException{
		try {
			File dir = new File("10s_website1 copy");
			for (File file : dir.listFiles()) {
				int k = 0;
				Scanner d = new Scanner(file);
				System.out.println(file);
				while (d.hasNextLine()) {
					data = d.nextLine();
				  	String[] singles = data.split(" ");
				  	for (int i=2; i<singles.length; i++) {
						split = singles[i].split(":");
						time.add(new ArrayList<ArrayList<Double>>());
						packetSize.add(new ArrayList<ArrayList<Double>>());
						time.get(c).add(new ArrayList<Double>());
						packetSize.get(c).add(new ArrayList<Double>());
						time.get(c).get(k).add(Double.parseDouble(split[0]));
						packetSize.get(c).get(k).add(Double.parseDouble(split[1]));
				  	}
				  	k++;
				}c++;
				d.close();
				System.out.println(c);
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
//	public static void readData(String a, String b) throws FileNotFoundException{
//		int k = 0;
//		File fileA = new File(a);
//		File fileB = new File(b);
//		Scanner scannerA = new Scanner(fileA);
//		Scanner scannerB = new Scanner(fileB);
//		while (scannerA.hasNextLine()) {
//			data = scannerA.nextLine();
//		  	String[] singles = data.split(" ");
//		  	for (int i=2; i<singles.length; i++) {
//				split = singles[i].split(":");
//				time.add(new ArrayList<ArrayList<Double>>());
//				packetSize.add(new ArrayList<ArrayList<Double>>());
//				time.get(c).add(new ArrayList<Double>());
//				packetSize.get(c).add(new ArrayList<Double>());
//				time.get(c).get(k).add(Double.parseDouble(split[0]));
//				packetSize.get(c).get(k).add(Double.parseDouble(split[1]));
//		  	}
//		  	k++;
//		}c++;
//		while (scannerB.hasNextLine()) {
//			data = scannerB.nextLine();
//		  	String[] singles = data.split(" ");
//		  	for (int i=2; i<singles.length; i++) {
//				split = singles[i].split(":");
//				time.add(new ArrayList<ArrayList<Double>>());
//				packetSize.add(new ArrayList<ArrayList<Double>>());
//				time.get(c).add(new ArrayList<Double>());
//				packetSize.get(c).add(new ArrayList<Double>());
//				time.get(c).get(k).add(Double.parseDouble(split[0]));
//				packetSize.get(c).get(k).add(Double.parseDouble(split[1]));
//		  	}
//		  	k++;
//		}c++;
//		scannerA.close();
//		scannerB.close();
//	}
	
	public static void readData(String a) throws FileNotFoundException{
		int k = 0;
		File file = new File(a);
		Scanner scanner = new Scanner(file);
		while (scanner.hasNextLine()) {
			data = scanner.nextLine();
		  	String[] singles = data.split(" ");
		  	for (int i=2; i<singles.length; i++) {
				split = singles[i].split(":");
				time.add(new ArrayList<ArrayList<Double>>());
				packetSize.add(new ArrayList<ArrayList<Double>>());
				time.get(c).add(new ArrayList<Double>());
				packetSize.get(c).add(new ArrayList<Double>());
				time.get(c).get(k).add(Double.parseDouble(split[0]));
				packetSize.get(c).get(k).add(Double.parseDouble(split[1]));
		  	}
		  	k++;
		}c++;
		scanner.close();
	}
	public static void compareData() {
		/*
		 * Here it might be great to call other compareData method, 
		 * But just iterates through everything else
		 */
		for(int k=0; k<=c; k++) {
			for(int j=0; j<c; j++) {
				compareData(k,j);
			}k++;
		}
	}
	public static void compareData(int c1, int c2) {
		/*	here need a way to split the first ten seconds which can be edited for later
		 * 		It will needed to be erased but to see what I want I need for now
		 * 	after you only have the first 10s of data
		 * 		run a compares method that erases any common numbers
		 * 	prints the values that were not common in each
		 * 	It should not matter on line number, it should maybe check the time left over
		 */
		ArrayList<Integer> L1 = new ArrayList<Integer>();
		ArrayList<Integer> L2 = new ArrayList<Integer>();
		//	here to find index of where 10 seconds passes
		for(int i=0; i<time.get(c1).size(); i++) {
			for(int j=0; j<time.get(c1).get(i).size(); j++) {
				if(((time.get(c1).get(i).get(0))+10)<(time.get(c1).get(i).get(j))){
					L1.add(j);
					break;
				}
			}
		}
		for(int i=0; i<time.get(c2).size(); i++) {
			for(int j=0; j<time.get(c2).get(i).size(); j++) {
				if(((time.get(c2).get(i).get(0))+10)<(time.get(c2).get(i).get(j))){
					L2.add(j);
					break;
				}
			}
		}
		// 	end the 10 seconds
		
		//	start 10-15 seconds
		ArrayList<Integer> X1 = new ArrayList<Integer>();
		ArrayList<Integer> X2 = new ArrayList<Integer>();
		for(int i=0; i<time.get(c1).size(); i++) {
			for(int j=0; j<time.get(c1).get(i).size(); j++) {
				if(((time.get(c1).get(i).get(0))+15)<(time.get(c1).get(i).get(j))){
					X1.add(j);
					break;
				}
			}
		}
		for(int i=0; i<time.get(c2).size(); i++) {
			for(int j=0; j<time.get(c2).get(i).size(); j++) {
				if(((time.get(c2).get(i).get(0))+15)<(time.get(c2).get(i).get(j))){
					X2.add(j);
					break; 
				}
			}
		}
		ArrayList<ArrayList<Double>> L2sData = new ArrayList<ArrayList<Double>>();
		for(int w=0; w<L2.size(); w++) {
			for(int x=0; x<L2.get(w); x++) {
				L2sData.add(new ArrayList<Double>());
				L2sData.get(w).add(packetSize.get(c2).get(w).get(x));
			}
		}
		ArrayList<ArrayList<Double>> X2sData = new ArrayList<ArrayList<Double>>();
		for(int w=0; w<X2.size(); w++) {
			for(int x=L2.get(w); x<X2.get(w); x++) {
				X2sData.add(new ArrayList<Double>());
				X2sData.get(w).add(packetSize.get(c2).get(w).get(x));
			}
		}
		/*	Here we will just remove the elements of the packetSize that appear in both
		 * 	Then just print out a few of the lines for each and see how similar
		 */
		ArrayList<ArrayList<Double>> unique1 = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> unique2 = new ArrayList<ArrayList<Double>>();
		for(int j=0; j<100; j++) {
			unique1.add(new ArrayList<Double>());
			unique2.add(new ArrayList<Double>());
			for(int k=0; k<L1.get(j); k++) {
				//	problem here is I wont be able to check the elements of the other 
				//		unless I simplify it to be within 10 sec
				if(!L2sData.get(j).contains(packetSize.get(c1).get(j).get(k))) {
					unique1.get(j).add(packetSize.get(c1).get(j).get(k));
				}
			}
			for(int k=0; k<L2.get(j); k++) {
				try {
				if(!(packetSize.get(c1).get(j)).contains(L2sData.get(j).get(k))) {
					unique2.get(j).add(L2sData.get(j).get(k));
				}
				}
				catch(Exception e) {
					break;
				}
			}
		}
		ArrayList<ArrayList<Double>> uniqueX1 = new ArrayList<ArrayList<Double>>();
		ArrayList<ArrayList<Double>> uniqueX2 = new ArrayList<ArrayList<Double>>();
		for(int j=0; j<100; j++) {
			uniqueX1.add(new ArrayList<Double>());
			uniqueX2.add(new ArrayList<Double>());
			for(int k=L1.get(j); k<X1.get(j); k++) {
				if(!X2sData.get(j).contains(packetSize.get(c1).get(j).get(k))) {
					//if(uniqueX1.get(j).isEmpty()) {
					//	System.out.println((time.get(c1).get(j).get(k))-(time.get(c1).get(j).get(0)));
					//	uniqueX1.get(j).add(packetSize.get(c1).get(j).get(k));
					//	continue;
					//}
					uniqueX1.get(j).add(packetSize.get(c1).get(j).get(k));
				}
			}
			for(int k=0; k<(X2.get(j) - L2.get(j)); k++) {
				if(!packetSize.get(c1).get(j).contains(X2sData.get(j).get(k))) {
					uniqueX2.get(j).add(X2sData.get(j).get(k));
				}
			}
			
			for(int i=0; i<unique1.get(j).size(); i++) {
				if((unique1.get(j).get(i))>0) {
					int temp = L1.get(i);
					sumDiff+=time.get(c1).get(j).get(temp)-time.get(c1).get(j).get(0);
					counter++;
					System.out.println(time.get(c1).get(j).get(temp)-time.get(c1).get(j).get(0));
					break;
				}
			}
			for(int i=0; i<unique2.get(j).size(); i++) {
				if((unique2.get(j).get(i))>0) {
					int temp = L2.get(i);
					sumDiff+=time.get(c2).get(j).get(temp)-time.get(c2).get(j).get(0);
					counter++;
					System.out.println(time.get(c2).get(j).get(temp)-time.get(c2).get(j).get(0));
					break;
				}
			}

		}
		System.out.println("	10 seconds");
		System.out.println(unique1.get(0));
		System.out.println(uniqueX1.get(0));
		System.out.println("	10-15 seconds");
		System.out.println(unique2.get(0));
		System.out.println(uniqueX2.get(0));
		
	}
	
//	import java.util.HashMap;
//	HashMap<Double, Integer> reference = new HashMap<Double, Integer>();
//	reference.put(packetSize, 1);
//	if (reference.containsKey(newPacketSize)) {
//		int count = reference.get(newPacketSize);
//		count++;
//		reference.put(newPacketSize, count);
//	} else {
//		reference.put(packetSize, 1);
//	}
	
	
}
