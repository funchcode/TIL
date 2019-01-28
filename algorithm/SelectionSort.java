import java.util.Arrays;

public class SelectionSort {
    public static void main(String[] args) {
        // Input Data
        int[] data = {9,2,3,1,7,4};

        for(int i = 0 ; i < data.length-1 ; i++) {
            int indexMin = i;
            for(int j = i+1 ; j < data.length ; j++) {
                if(data[indexMin] > data[j]) {
                    indexMin = j;
                }
            }
            int temp = data[i];
            data[i] = data[indexMin];
            data[indexMin] = temp;
        }
        System.out.println(Arrays.toString(data));
    }
}