
public class FlexArray {
    private int capacity; //max amount of integers that can be held in following array
    private int[] array = new int[capacity];
    private int size; //tells how many spots are filled

    public static void main(String[] args) throws ArrayIndexOutOfBoundsException{
        FlexArray a1 = new FlexArray();
        FlexArray a2 = new FlexArray(15);
        FlexArray a3 = new FlexArray(5);
        System.out.println(a1.toString());
        System.out.println(a2.toString());
        System.out.println(a3.toString());
        //a1.add(3);
        //a2.add(3);
        //a3.add(3);

        System.out.println(a1.toString());
        System.out.println(a2.toString());
    }

    public FlexArray(){
        capacity = 10;
        size = 1;
    }
    public FlexArray(int maxEntries){
        if (maxEntries > 0) {
            capacity = maxEntries;
            size = 0;
        }
    }
    public void add(int val) throws ArrayIndexOutOfBoundsException{
        if (size < capacity){
            array[size] = val;
            size++;
        }
        else{
            System.out.println("Array is full, values cannot be added");
        }
    }
    public int set(int val, int location) throws ArrayIndexOutOfBoundsException{
        int temp = array[location];
        array[location] = val;
        return temp;
    }
    public int get(int location) throws ArrayIndexOutOfBoundsException {
        
    	return array[location];
    }
    public String toString() throws ArrayIndexOutOfBoundsException{
        String s = "";
        for(int i = 0; i<size; i++){
            s += array[i] + " / ";
        }
        return s;
    }
}
