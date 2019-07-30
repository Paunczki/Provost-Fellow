import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Data2 {
	private static String data;
	private static String[] split;
	public static ArrayList<ArrayList<Double>> time = new ArrayList<ArrayList<Double>>();
	public static ArrayList<ArrayList<Double>> packetSize = new ArrayList<ArrayList<Double>>();
	private static int k = 0;
	
	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("w93_merge2_32ins_0.1_overlap_randomAtSecond.csv");
		//File file = new File("smallTest");
		Scanner scanner = new Scanner(file);
		
		while (scanner.hasNextLine()) {
			data = scanner.nextLine();
		  	String[] singles = data.split(",");
		  	for (int i=0; i<singles.length; i++) {
				split = singles[i].split(":");
				time.add(new ArrayList<Double>());
				packetSize.add(new ArrayList<Double>());
				time.get(k).add(Double.parseDouble(split[0]));
				packetSize.get(k).add(Double.parseDouble(split[1]));
		  	}
		  	System.out.println("Loaded " + ((100*k)/2975) + "%");
		  	k++;
		}
		System.out.println("Done\n");
		
		packetAmountThreshold(0, 500);
		packetSizeThreshold(0,500);
		
		scanner.close();
	}

	public static void packetAmountThreshold(int line, double timeSplit){
		timeSplit = timeSplit/1000;
		Double carry = (((time.get(line).get((time.get(line).size())-1))-(time.get(line).get(0)))/timeSplit);
		int use = (carry.intValue())+1;
		int[] bins = new int[use];
		int counter = 0;
		Double timeInitial = time.get(line).get(0);
		Double timeNext = timeInitial+timeSplit;
		for(int i=0; i < time.get(line).size(); i++) {
			if((timeInitial<=time.get(line).get(i))&&(time.get(line).get(i)<timeNext)) {
				bins[counter] = bins[counter] + 1;
			}
			else {
				bins[counter+1] = bins[counter+1] + 1;
				timeInitial = timeNext;
				timeNext+=timeSplit;
				counter++;
			}
		}
		for(int k=0; k<bins.length; k++) {
			System.out.print("/ " + bins[k] + " /");
		}	
	// begin extraction
		Double averageCounter = 0.0;
		for(int j=0; j<(bins.length-1); j++) {
			averageCounter+=Math.abs(bins[j+1] - bins[j]);
		}
		Double averageDelta = averageCounter / (bins.length-1);
	// standard deviation part
		double averageCarryOver = 0.0;
		for(int w=0; w<(bins.length-1); w++) {
			averageCarryOver += Math.pow((Math.abs(bins[w+1] - bins[w])- averageDelta),2);
		}
		double stdev = Math.sqrt(averageCarryOver/(bins.length-2));
	// Check
		for(int u=0; u<bins.length-1; u++) {
			if((Math.abs(bins[u+1] - bins[u])) > (averageDelta + (2*stdev))){
				System.out.println(time.get(line).get(0) + (((u+1) * timeSplit)));
			}
		}
	}
	public static void packetSizeThreshold(int line, double timeSplit) {
		timeSplit = timeSplit/1000;
		Double carry = (((time.get(line).get((time.get(line).size())-1))-(time.get(line).get(0)))/timeSplit);
		int use = (carry.intValue())+1;
		double[] size = new double[use];
		int counter = 0;
		Double timeInitial = time.get(line).get(0);
		Double timeNext = timeInitial+timeSplit;
		for(int i=0; i < time.get(line).size(); i++) {
			if((timeInitial<=time.get(line).get(i))&&(time.get(line).get(i)<timeNext)) {
				size[counter] = size[counter] + Math.abs(packetSize.get(line).get(i));
			}
			else {
				size[counter+1] = size[counter+1] + Math.abs(packetSize.get(line).get(i));
				timeInitial = timeNext;
				timeNext+=timeSplit;
				counter++;
			}
		}
		for(int k=0; k<size.length; k++) {
			System.out.print("/ " + size[k] + " /");
		}
	// begin extraction
		Double averageCounter = 0.0;
		for(int j=0; j<(size.length-1); j++) {
			averageCounter+=Math.abs(size[j+1] - size[j]);
		}
		Double averageDelta = averageCounter / (size.length-1);
	// standard deviation part
		double averageCarryOver = 0.0;
		for(int w=0; w<(size.length-1); w++) {
			averageCarryOver += Math.pow((Math.abs(size[w+1] - size[w])- averageDelta),2);
		}
		double stdev = Math.sqrt(averageCarryOver/(size.length-2));
	// Check
		for(int u=0; u<size.length-1; u++) {
			if((Math.abs(size[u+1] - size[u])) > (averageDelta + (2*stdev))){
				System.out.println(time.get(line).get(0) + (((u+1) * timeSplit)));
			}
		}
	}
}