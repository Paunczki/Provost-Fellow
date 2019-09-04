import java.util.*;
import java.io.*;
public class ReferenceChecker {
	public static void main(String[] args) throws FileNotFoundException {
		referenceCounter();
	}
	public static ArrayList<ArrayList<Double>> references = new ArrayList<ArrayList<Double>>();
	public static void referenceCounter() throws FileNotFoundException{
		File a = new File("writeReferences2");
		Scanner scanner  = new Scanner(a);
		String data = "";
		while(scanner.hasNextLine()) {
			data = scanner.nextLine();
			String[] split = data.split(",");
			for(int j=0; j<split.length; j++) {
				boolean pizza = true;
				for(int x=0; x<references.size(); x++) {
					if(references.get(x).get(0) == Double.parseDouble(split[j])) {
						pizza = false;
						double temp = (references.get(x).get(1))+1.0;
						references.get(x).remove(1);
						references.get(x).add(temp);
						break;
					}
				}
				if(pizza == true) {
					references.add(new ArrayList<Double>());
					references.get(references.size()-1).add(Double.parseDouble(split[j]));
					references.get(references.size()-1).add(1.0);
				}
			}
		}
		String input = "";
		for(int i=0; i<references.size();i++) {
			if(references.get(i).get(1) > 15.0) {
				input += references.get(i).get(0) + " | " + references.get(i).get(1) + "\n";
				writeReference(input);
				input = "";
			}
		}
	}
	public static void writeReference(String input) {
		BufferedWriter bf;
		try{
			bf = new BufferedWriter(new FileWriter("writeReferences3", true));
			bf.write(input);
			bf.close();
		}
		catch(IOException e){
			System.out.println("Error");
			System.exit(0);
		}
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
