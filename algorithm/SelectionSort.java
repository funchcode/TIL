import java.util.Arrays;
import java.util.Scanner;

// Selection Sort 선택정렬
// 앞에서부터 순차적으로 최댓값을 찾아낸다.
class Main {
    public static void main(String[] args) throws Exception {
        // Input Data
        int[] data = {9,2,3,1,7,4};
        for(int i = data.length-1 ; i >= 1 ; i--) {
            for(int j = 0 ; j < i ; j++) {
                int max = 0;
                if(data[j]>data[j+1]) {
                    max = data[j];
                    data[j] = data[j+1];
                    data[j+1] = max;
                }else{
                    max = data[j+1];
                }
            }
        }
        System.out.print(Arrays.toString(data));
    }
}