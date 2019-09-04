import java.util.*;
import java.io.*;

public class CountFiles {
    public static void main(String[] args) {
        File directory = new File("result");
        int counter = 0;
        for(File website: directory.listFiles()) {
            counter++;
        }
        System.out.println(counter);
    }
}
