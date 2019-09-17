import java.io.*;
import java.util.*;

public class countLines {
	public static void main(String[] args) {
		try {
			File a = new File("10s_website1 copy");
			File directory = a;
			int c = 0;
			double counter = 0;
			for(File files: directory.listFiles()) {
				if(c<3) {
					Scanner b = new Scanner(files);
					while(b.hasNextLine()) {
						counter++;
						b.nextLine();
					}
					b.close();
				}
				else {
					break;
				}
				c++;
			}
			
			System.out.println(counter/3.00);
		}
		catch(FileNotFoundException e) {
			System.out.println("Error");
		}
	}
}
