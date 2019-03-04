import java.util.Arrays;

public class Quiz {
    // Q1. 문자열에 포함된 문자(ASCII)들이 전부 유일한지를 검사하는 알고리즘을 구현하라.
    // 다른 자료구조를 사용할 수 없는 상황이라면 어떻게 하겠는가 ?
        public static void chartQuiz() {
        String dummy = "RepublicOfKorea";
        boolean[] equalArray = new boolean[256];
        Boolean isEual = (dummy.length() > 256 ? false : true);
        
        for(int i = 0 ; i < dummy.length() ; i++) {
            int val = dummy.charAt(i);
            if(equalArray[val]) {
                equalArray[val] = false;
                isEual = false;
            }
            equalArray[val] = true;
        }
        
        System.out.println(!isEual);
    }

    // Q2. 널 문자로 끝나는 문자열을 뒤집는 reverse(char str)함수를 만들어라.
    public static void reverseQuiz() {
        String input = "Republic Of Korea\0"; // 아스키 코드에서 \0은 'null'이다.
        String output = "";
        if(input.length() == 0) System.out.println(input);

        for(int i = 0 ; i < input.length() ; i++) {
            char str = input.charAt(i);
            if(str == '\0') System.out.println(output);
            output = str + output;
        }
    }    

    // Q3. 문자열 두 개를 입력으로 받아 그중 하나가 다른 하나의 순열인지 판별하는 메서드를 작성하라.
    public static void permutationQuiz() {
        String input1 = "abc";
        String input2 = "cabcabcabcabcabcab";

        
    }

    // Q4. 주어진 문자열 내의 모든 공백을 '%20'으로 바꾸는 메서드를 작성하라.
    // 문자열 끝에 추가로 필요한 문자들을 더할 수 있는 충분한 공간이 있다고 가정하라.
    // 그리고 공백을 포함하는 문자열의 길이도 함께 주어진다고 가정하라.
    // 문자 배열을 사용하여 필요한 연산을 각 문자에 바로 적용할 수 있도록.
    // 예) 입력 : "Mr John Smith    , 13" // 출력 : "Mr%20John%20Smith"
    public static void changeBlank() {
        String input = "Republic Of Korea     , 17";

        if(input.length() == 0) System.out.println("비어있는 문자열입니다. 종료합니다.");
        
        int size = Integer.parseInt(input.substring(input.lastIndexOf(',') + 1).trim());
        String temp = input.substring(0, size);
        temp = temp.replaceAll(" ", "%20");

        System.out.println(temp);
    }

    // Q5. 같은 문자가 연속으로 반복될 경우, 그 횟수를 사용해 문자열을 압축하는 메서드를 구현하라.
    // 예) 입력 : "aabccccccccaaa" // 출력 : "a2b1c8a3"
    // 결과가 입력의 길이와 같을 경우 원래 문자열을 그대로 반환해야한다.

    // Q6. 이미지를 표현하는 N*N행렬이 있다.
    // 이미지의 각 픽셀은 4바이트로 표현된다.
    // 이때, 이미지를 90도로 회전시키는 메서드를 작성하라
    // Upgrade) 행렬을 사용하지 않고 작성하라.

    // Q7. M*N행렬을 순회하면서 0인 원소를 발견하면, 해당 원소가 속한 행과 열의 모든 원소를 0으로 설정하는 알고리즘을 작성하라.

    // Q8. 한 단어가 다른 단어에 포함된 문자열인지 판별하는 isSubstring이라는 메서드가 있다고 하자.
    // s1과 s2의 두 문자열이 주어졌을 때, s2가 s1을 회전시킨 결과인지 판별하는 코드를 isSubstring을 한 번만 호출하도록 하여 작성하라.
    // ('waterbottle'은 'erbottlewat'을 회전시켜 얻을 수 있는 문자열이다.)

    public static void main(String[] args) {
        System.out.println("Class Test!");
        //chartQuiz();
        //reverseQuiz();
        changeBlank();
    }
}