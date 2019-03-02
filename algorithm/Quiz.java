
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


    public static void main(String[] args) {
        System.out.println("Class Test!");
        chartQuiz();
    }
}