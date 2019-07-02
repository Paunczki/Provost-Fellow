import java.util.*;
import java.io.*;
import java.text.DecimalFormat;
// import java.lang.Math;

public class AutomateRewrite {
	/*
	 	What needs to be added when working on these files
	 		Need the 3 directories (not attached on GitHub for privacy issues)
	 		writeReferences5
	 		4 data information files
	 		4 files for temporary holding
	 */

    // This time we are not skipping any of the files

    public static void main(String[] args) {
        long time1 = System.currentTimeMillis();
        run();
        long time2 = System.currentTimeMillis();
        double stopwatch = (time2-time1)/1000.000;
        System.out.println("Time taken: " + stopwatch);
    }

    static File a1 = new File("automatedFirst");
    static File a2 = new File("automatedSecond");
    static File a3 = new File("automatedThird");
    static File a4 = new File("automatedFourth");

    static File s1 = new File("saveFirst");
    static File s2 = new File("saveSecond");
    static File s3 = new File("saveThird");
    static File s4 = new File("saveFourth");

    static Double occurrence;

    static double avgBigTime1; static double percentCalculator1; static double total1;
    static int presentPackets1; static int missingPackets1; static int counter1;
    static double avgBigTime2; static double percentCalculator2; static double total2;
    static int presentPackets2; static int missingPackets2; static int counter2;
    static double avgBigTime3; static double percentCalculator3; static double total3;
    static int presentPackets3; static int missingPackets3; static int counter3;
    static double avgBigTime4; static double percentCalculator4; static double total4;
    static int presentPackets4; static int missingPackets4; static int counter4;

    static double minusOne; static double plusOne;

    public static void run(){
        File directory = new File("10s_website1_copy");
        writeClear(a1); // for first instance
        writeClear(a2); // for second instance
        writeClear(a3); // for third instance
        writeClear(a4); // for fourth instance

        occurHold.clear();
        nextOccurrence();
        // System.out.println(occurHold);
        // allowed to be held constant since reference stays the same
        // if wants to be changed see the createReference() method

        for(int i=0; i<2; i++) {
            if(i == 0) {
                File a = new File("10s_website1 copy");
                minusOne = 9.0; plusOne = 11.0;
                directory = a;
            }
            if(i == 1) {
                File b = new File("20s_website1");
                minusOne = 19.0; plusOne = 21.0;
                directory = b;
            }
			/*
			if(i == 2) {
				File c = new File("result");
				minusOne = 0; plusOne = 1;
				directory = c;
			}
			*/

            String show = "----------------------------------------------------------------------------"
                    + "\n\n" + directory.toString() + "\n"
                    + "----------------------------------------------------------------------------\n";

            writeFile(a1, show);
            writeFile(a2, show);
            writeFile(a3, show);
            writeFile(a4, show);

            // Now do work for all directories
            int instance = 0;
            occurrence = occurHold.get(0);

            while(occurrence != null) {
                createReference(occurrence);
                //System.out.println(reference);
                //System.out.println(occurrence);
                resetValues();

                writeClear(s1);
                writeClear(s2);
                writeClear(s3);
                writeClear(s4);

                try {
                    for(File files: directory.listFiles()) {
                        runEach(files);
                    }
                }
                catch(Exception e) {
                    System.out.println("Error1");
                }

                avgBigTime1 = avgBigTime1 / counter1;
                avgBigTime2 = avgBigTime2 / counter2;
                avgBigTime3 = avgBigTime3 / counter3;
                avgBigTime4 = avgBigTime4 / counter4;

                writeData();
                instance++;
                occurrence = occurHold.get(instance);
            }
        }
    }

    public static void writeData() {
        try {
            String occurrenceInput = "Occurrence >= " + occurrence + "\n";

            // For first positive packet
            writeFile(a1, occurrenceInput);
            MedianFinder e = new MedianFinder(s1, a1);
            writeFile(a1, "  - Average time throughout was " + avgBigTime1 + " seconds\n");
            writeFile(a1, "  - " + (percentCalculator1 / total1)*100.0 + "% correct\n");
            writeFile(a1, "  - Total Packets tested... " + total1 + "\n");
            writeFile(a1, "  - Amount of positive packets found... " + presentPackets1 + "\n");
            writeFile(a1, "  - Amount of missing packets... " + missingPackets1 + "\n");

            // For second positive packet
            writeFile(a2, occurrenceInput);
            MedianFinder f = new MedianFinder(s2, a2);
            writeFile(a2, "  - Average time throughout was " + avgBigTime2 + " seconds\n");
            writeFile(a2, "  - " + (percentCalculator2 / total2)*100.0 + "% correct\n");
            writeFile(a2, "  - Total Packets tested... " + total2 + "\n");
            writeFile(a2, "  - Amount of positive packets found... " + presentPackets2 + "\n");
            writeFile(a2, "  - Amount of missing packets... " + missingPackets2 + "\n");

            // For third positive packet
            writeFile(a3, occurrenceInput);
            MedianFinder g = new MedianFinder(s3, a3);
            writeFile(a3, "  - Average time throughout was " + avgBigTime3 + " seconds\n");
            writeFile(a3, "  - " + (percentCalculator3 / total3)*100.0 + "% correct\n");
            writeFile(a3, "  - Total Packets tested... " + total3 + "\n");
            writeFile(a3, "  - Amount of positive packets found... " + presentPackets3 + "\n");
            writeFile(a3, "  - Amount of missing packets... " + missingPackets3 + "\n");

            // For fourth positive packet
            writeFile(a4, occurrenceInput);
            MedianFinder h = new MedianFinder(s4, a4);
            writeFile(a4, "  - Average time throughout was " + avgBigTime4 + " seconds\n");
            writeFile(a4, "  - " + (percentCalculator4 / total4)*100.0 + "% correct\n");
            writeFile(a4, "  - Total Packets tested... " + total4 + "\n");
            writeFile(a4, "  - Amount of positive packets found... " + presentPackets4 + "\n");
            writeFile(a4, "  - Amount of missing packets... " + missingPackets4 + "\n");
        }
        catch(Exception FileNotFoundException) {
            System.out.println("Error6");
        }
    }

    static ArrayList<Double> positivePackets = new ArrayList<>();
    // This is going to store all positive packets in order

    public static void runEach(File file) {
        try{
            Scanner scanner = new Scanner(file);
            String line;
            String[] sections;
            String[] timePacket;
            Double time;
            Double packet;
            int z=1;

            while(scanner.hasNextLine()) {
                positivePackets.clear();
                line = scanner.nextLine();
                sections = line.split(" ");

                // format for data is web-site 0 timestamp1:packetsize1 timestamp2:packetsize2 ...

                Double timeInitial = null;

                for(int i=2; i<sections.length; i++) {
                    timePacket = sections[i].split(":");
                    time = Double.parseDouble(timePacket[0]);
                    packet = Double.parseDouble(timePacket[1]);

                    if(i==2) {
                        timeInitial = time;
                    }
                    if(packet > 0) {
                        if(!(reference.contains(packet))) {
                            positivePackets.add(time);
                        }
                    }
                }

                if(timeInitial == null) {
                    continue;
                }

                Double earliest1;
                Double earliest2;
                Double earliest3;
                Double earliest4;

                DecimalFormat df = new DecimalFormat("#");
                df.setMaximumFractionDigits(6);

                if(!(positivePackets.isEmpty())) {
                    if(positivePackets.get(0) != null) {
                        earliest1 = positivePackets.get(0);
                        String save1 = "line " +z+","+df.format(timeInitial)+","+df.format(earliest1)+"\n";
                        writeFile(s1, save1);
                        if((minusOne <= (timeInitial - earliest1)) && ((timeInitial - earliest1) <= plusOne)) {
                            percentCalculator1++;
                        }
                        avgBigTime1 += (timeInitial - earliest1);
                        presentPackets1++; total1++;
                    }
                    else missingPackets1++;

                    if(positivePackets.get(1) != null) {
                        earliest2 = positivePackets.get(1);
                        String save2 = "line " +z+","+df.format(timeInitial)+","+df.format(earliest2)+"\n";
                        writeFile(s2, save2);
                        if((minusOne <= (timeInitial - earliest2)) && ((timeInitial - earliest2) <= plusOne)) {
                            percentCalculator2++;
                        }
                        avgBigTime2 += (timeInitial - earliest2);
                        presentPackets2++; total2++;
                    }
                    else missingPackets2++;

                    if(positivePackets.get(2) != null) {
                        earliest3 = positivePackets.get(2);
                        String save3 = "line " +z+","+df.format(timeInitial)+","+df.format(earliest3)+"\n";
                        writeFile(s3, save3);
                        if((minusOne <= (timeInitial - earliest3)) && ((timeInitial - earliest3) <= plusOne)) {
                            percentCalculator3++;
                        }
                        avgBigTime3 += (timeInitial - earliest3);
                        presentPackets3++; total3++;
                    }
                    else missingPackets3++;

                    if(positivePackets.get(3) != null) {
                        earliest4 = positivePackets.get(3);
                        String save4 = "line " +z+","+df.format(timeInitial)+","+df.format(earliest4)+"\n";
                        writeFile(s4, save4);
                        if((minusOne <= (timeInitial - earliest4)) && ((timeInitial - earliest4) <= plusOne)) {
                            percentCalculator4++;
                        }
                        avgBigTime4 += (timeInitial - earliest4);
                        presentPackets4++; total4++;
                    }
                    else missingPackets4++;
                }
            }
            // while loop ends

            z++;
            scanner.close();
        }
        catch(Exception e) {
            System.out.println(e);
            System.out.println("Error2");
        }
    }

    static HashSet<Double> reference = new HashSet<>();
    // HashSet since time complexity is lower for .contains() method

    public static void createReference(Double numOcc) {
        // uses the file writeReferences5
        // which has format --> packet-size  |  numberOfOccurrences

		/*
		 	Currently the reference is created by counting all number of occurrences
		 		from the "result" directory
		 	The result directory is the longest running directory
		 	If you want to change the directory used for reference, use HashCount.java
		 		Now can be automated so reference can be used automatically
		 */

        reference.clear();
        try {
            File scanFile = new File("writeReferences5");
            Scanner refScan = new Scanner(scanFile);
            String instance;
            String[] split;
            Double occur;
            Double packet;

			/*
			 	Quick brainstorm about saving reference
			 		Maybe check to only add positive packet-sizes to reference
			 		Then to see if it is in it then we can pass with it
			 		Since we will check the packets in time
			 			They go in order
			 */

            while(refScan.hasNextLine()) {
                instance = refScan.nextLine();
                split = instance.split("  |  ");
                occur = Double.parseDouble(split[2]);
                packet = Double.parseDouble(split[0]);

                if(occur >= numOcc) {
                    reference.add(packet);
                }
            }
            refScan.close();
        }
        catch(Exception FileNotFoundException) {
            System.out.println("Error7");
        }
    }

    static ArrayList<Double> occurHold = new ArrayList<>();
    // This is the getting the values of the occurrences in order

    public static void nextOccurrence() {
        try {
            Scanner scan = new Scanner(new File("writeReferences5"));
            String line;
            String[] split;
            Double occurs;

            while(scan.hasNextLine()) {
                line = scan.nextLine();
                split = line.split("  |  ");
                occurs = Double.parseDouble(split[2]);
                if(!(occurHold.contains(occurs))) {
                    occurHold.add(occurs);
                }
            }
            scan.close();
        }
        catch(Exception e) {
            System.out.println("Error3");
        }

        Collections.sort(occurHold);
    }

    public static void writeFile(File file, String input) {
        // Reference is created from file HashCount.java
        BufferedWriter bf;

        try {
            bf = new BufferedWriter(new FileWriter(file,true));
            bf.write(input);
            bf.close();
        }
        catch(Exception e) {
            System.out.println("Error24");
        }
    }

    public static void writeClear(File file) {
        BufferedWriter bf;
        try {
            bf = new BufferedWriter(new FileWriter(file, false));
            bf.write("");
            bf.close();
        }
        catch(Exception e) {
            System.out.println("Error25");
        }
    }

    public static void resetValues() {
        avgBigTime1 = 0.0; percentCalculator1 = 0.0; total1 = 0.0;
        presentPackets1 = 0; missingPackets1 = 0; counter1 = 0;

        avgBigTime2 = 0.0; percentCalculator2 = 0.0; total2 = 0.0;
        presentPackets2 = 0; missingPackets2 = 0; counter2 = 0;

        avgBigTime3 = 0.0; percentCalculator3 = 0.0; total3 = 0.0;
        presentPackets3 = 0; missingPackets3 = 0; counter3 = 0;

        avgBigTime4 = 0.0; percentCalculator4 = 0.0; total4 = 0.0;
        presentPackets4 = 0; missingPackets4 = 0; counter4 = 0;
    }

}
