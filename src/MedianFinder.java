import java.util.*;
import java.io.*;
import java.lang.Math;
public class MedianFinder {
	private static ArrayList<Double> avgTimes = new ArrayList<Double>();
	public static void main(String[] args) throws FileNotFoundException {
		System.out.println(worker(new File("saveFirst")));
	}
	public MedianFinder() throws FileNotFoundException {
		fileDataWrites("  - Median time taken was: " + work("saveFileName6") + "\n");
	}
	public MedianFinder(File savedFile, File writeFile) throws FileNotFoundException {
		fileData(writeFile, "  - Median time taken was: " + worker(savedFile) + "\n");
	}
	public static String worker(File saveFile) throws FileNotFoundException {
		Scanner scanner = new Scanner(saveFile);
		double mean = 0;
		while(scanner.hasNextLine()) {
			String data = scanner.nextLine();
			String[] apples;
			try {
				apples = data.split(",");
				Double difference = Double.parseDouble(apples[2]) - Double.parseDouble(apples[1]);
				System.out.println(Double.parseDouble(apples[2]) + "  -  " + Double.parseDouble(apples[1]));
				mean += difference;
				avgTimes.add(difference);
			}
			catch (Exception e) {
				continue;
			}
		}
		mean = mean/(avgTimes.size());
		scanner.close();
		//sort(avgTimes);
		Collections.sort(avgTimes);
		Double summed = 0.0;
		for(int i=0; i<avgTimes.size(); i++) {
			summed+=Math.pow((avgTimes.get(i) - mean),2);
		}
		double stdev = Math.sqrt((summed/avgTimes.size()));
		int temp = ((avgTimes.size())/2);
		return avgTimes.get(temp) + "\n" +
				"  - Standard Deviation was " + stdev;
	}

	public static void fileData(File file, String input) {
		BufferedWriter bf;
		try {
			bf = new BufferedWriter(new FileWriter(file, true));
			bf.write(input);
			bf.close();
		}
		catch(IOException e) {
			System.out.println("Error2");
			System.exit(0);
		}
	}

	public static String work(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		Scanner scanner = new Scanner(file);
		double mean = 0;;
		while(scanner.hasNextLine()) {
			String data = scanner.nextLine();
			String[] apples;
			try {
				apples = data.split(",");
				Double difference = Double.parseDouble(apples[2]) - Double.parseDouble(apples[1]);
				mean += difference;
				avgTimes.add(difference);
			}
			catch (Exception e) {
				continue;
			}
		}
		mean = mean/(avgTimes.size());
		scanner.close();
		//sort(avgTimes);
		Collections.sort(avgTimes);
		Double summed = 0.0;
		for(int i=0; i<avgTimes.size(); i++) {
			summed+=Math.pow((avgTimes.get(i) - mean),2);
		}
		double stdev = Math.sqrt((summed/avgTimes.size()));
		int temp = ((avgTimes.size())/2);
		return avgTimes.get(temp) + "\n" +
				"  - Standard Deviation was " + stdev;
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
}
