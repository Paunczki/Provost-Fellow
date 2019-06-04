import java.util.*;
import java.io.*;
public class HashCount {
	static HashMap<Double, Integer> map = new HashMap<Double, Integer>();
	public static void main(String[] args) throws FileNotFoundException{
		long time1 = System.currentTimeMillis();
		File dir = new File("result");
		int counter = 0;
		for (File fileA : dir.listFiles()) {
			countThem(fileA);
			System.out.println(counter++);
		}
		getKeys();
		long time2 = System.currentTimeMillis();
		System.out.println("\n\nTime taken: " + ((time2-time1)/1000) + " seconds");
	}
	public static void writeReference(String input) {
		BufferedWriter bf;
		try{
			bf = new BufferedWriter(new FileWriter("writeReferences5", true));
			bf.write(input);
			bf.close();
		}
		catch(IOException e){
			System.out.println("Error");
			System.exit(0);
		}
	}
	public static void countThem(File file) throws FileNotFoundException {
		/*
		 * Need to access every line of each file, so need a loop
		 */
		Scanner scanner = new Scanner(file);
		while(scanner.hasNextLine()) {
			// make sure to skip the first two instances since its name 0 ...
			try {
			String data = scanner.nextLine();
			String[] splits = data.split(" ");
			String[] time1 = splits[2].split(":");
			Double timeInitial = Double.parseDouble(time1[0]);
			for(int i=2; i<splits.length; i++) {
				String[] splits2 = splits[i].split(":");
//				if(Double.parseDouble(splits2[0]) > (timeInitial+20.0)) {
//					break;
//				}
				if(!map.containsKey(Double.parseDouble(splits2[1]))) {
					map.put(Double.parseDouble(splits2[1]), 1);
				}
				else {
					int temp = map.get(Double.parseDouble(splits2[1]));
					map.put(Double.parseDouble(splits2[1]), temp+1);
				}
			}
			}
			catch(Exception e) {
				continue;
			}
//			for(Double key: map.keySet()) {
//				if(map.get(key) >= 1) {
//					writeReference(key + "  |  " + map.get(key) + "\n");
//				}
//			}
		}
	}
	public static void getKeys() {
		for(Double key: map.keySet()) {
			if(map.get(key) >= 1) {
				writeReference(key + "  |  " + map.get(key) + "\n");
			}
		}
	}
}
