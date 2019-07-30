import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Data {
	private static String data;
	private static String[] split;
	public static ArrayList<ArrayList<Double>> time = new ArrayList<ArrayList<Double>>();
	public static ArrayList<ArrayList<Double>> packetSize = new ArrayList<ArrayList<Double>>();
	private static int k = 0;
	
	public static void main(String[] args) throws FileNotFoundException {
		long time1 = System.currentTimeMillis();
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
		System.out.println("");
		packetSizeThreshold(0,500);
		System.out.println("");
		packetAmountThreshold(1, 500);
		System.out.println("");
		packetSizeThreshold(1,500);
		scanner.close();
		long time2 = System.currentTimeMillis();
		System.out.println("\n\nprogram took  " + (time2-time1) + "  milliseconds");
	}

	public static void packetAmountThreshold(int line, double timeSplit){
		timeSplit = timeSplit/1000;
		Double carry = (((time.get(line).get((time.get(line).size())-1))-(time.get(line).get(0)))/(timeSplit));
		int use = (carry.intValue())+1;
		System.out.println(use);
		int[] bins = new int[use];
		int counter = 0;
		Double timeInitial = time.get(line).get(0);
		Double timeNext = timeInitial+timeSplit;
		for(int i=0; i < time.get(line).size(); i++) {
			if((timeInitial<=time.get(line).get(i))&&(time.get(line).get(i)<timeNext)) {
				bins[counter] = bins[counter] + 1;
			}
			if((time.get(line).get(i))>=timeNext) {
				Double v = (((time.get(line).get(i)) - timeNext)/timeSplit);
				int u = v.intValue()+1;
				bins[counter+u] = bins[counter+u] + 1;
				timeInitial += (timeSplit*u);
				timeNext+=(timeSplit*u);
				counter+=u;
			}
		}
		for(int k=0; k<bins.length; k++) {
			System.out.print("/ " + bins[k] + " /");
		}	
	// begin extraction
		Double averageCounter = 0.0;
		for(int j=0; j<bins.length; j++) {
			averageCounter+=bins[j];
		}
		Double averageDelta = averageCounter / bins.length;
	// standard deviation part
		double averageCarryOver = 0.0;
		for(int w=0; w<bins.length; w++) {
			averageCarryOver += Math.pow((bins[w]- averageDelta),2);
		}
		double stdev = Math.sqrt(averageCarryOver/(bins.length-1));
	// Check
		for(int u=0; u<bins.length; u++) {
			if((Math.abs(bins[u])) > (averageDelta + (2*stdev))){
				//System.out.println(time.get(line).get(0) + (((u) * timeSplit)));
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
			if((time.get(line).get(i))>=timeNext) {
				Double v = (((time.get(line).get(i)) - timeNext)/timeSplit);
				int u = v.intValue()+1;
				size[counter+u] = size[counter+u] + Math.abs(packetSize.get(line).get(i));
				timeInitial += (timeSplit*u);
				timeNext+=(timeSplit*u);
				counter+=u;
			}	
		}
		for(int k=0; k<size.length; k++) {
			System.out.print("/ " + size[k] + " /");
		}
	// begin extraction
		Double averageCounter = 0.0;
		for(int j=0; j<size.length; j++) {
			averageCounter+=size[j];
		}
		Double averageDelta = averageCounter / size.length;
	// standard deviation part
		double averageCarryOver = 0.0;
		for(int w=0; w<size.length; w++) {
			averageCarryOver += Math.pow((size[w]- averageDelta),2);
		}
		double stdev = Math.sqrt(averageCarryOver/(size.length-1));
	// Check
		for(int u=0; u<size.length; u++) {
			if((Math.abs(size[u])) > (averageDelta + (2*stdev))){
				//System.out.println(time.get(line).get(0) + (((u) * timeSplit)));
			}
		}
	}
}