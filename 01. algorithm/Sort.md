## "목차" <br>
1. [선택정렬](#선택정렬-)
2. [삽입정렬](#삽입정렬-)
3. [버블정렬](#버블정렬-)

---
## 선택정렬 <br>    

선택정렬("Selection sort")은 리스트의 모든 항목을 살펴보고 가장 많거나 적은 항목을 찾아 기록하는 것을 말한다. <br>
<br>

|기존항목|값||정렬항목|값|
|---|---|---|---|---|
|Barcelona|10||||
|Liverpool|8||||
|Tottenham|7||||
|Munich|1||||
|Pohang|99||||

<br>
가장 많은 항목을 살펴보고 가장 많은 항목을 기록한다.
<br>

|기존항목|값||정렬항목|값|
|---|---|---|---|---|
|Barcelona|10||Pohang|99|
|Liverpool|8||||
|Tottenham|7||||
|Munich|1||||
||||||

<br>
값이 제일 높은 포항 스틸러스가 기록되었다.
<br>

|기존항목|값||정렬항목|값|
|---|---|---|---|---|
||||Pohang|99|
|Liverpool|8||Barcelona|10|
|Tottenham|7||||
|Munich|1||||
||||||

<br>
포항 스틸러스를 제외한 나머지 항목 중 가장 큰 값인 바르셀로나가 기록되었다.
<br>
이렇게 순서대로 반복하면 정렬된 목록을 얻을 수 있다. <br>
<br>
빅오 표기법으로 계산해보자면 O(n)시간은 목록의 모든 원소를 한 번씩 건드려야 한다는 의미인데, O(n^2)시간이 걸린다는 것을 알 수 있다. <br>
> 왜 O(n^2)일까. <br>
> 빅오 표기법은 상수와 관련이 있기 때문에 이런 법칙이 생긴건데 빅오 표기법은 상수항을 무시하므로 O(n^2)이라 표기한다.

```java
public class SelectSort {
    public static void main(String[] args) {
        int[] input = {4,3,2,1};
        int size = input.length;
        int idx, temp;
        for(int i = 0 ; i < size ; i++) {
            idx = i;
            for(int j = i ; j < size ; j++) {
                if(min > input[j]) {
                    min = input[j];
                    idx = j;
                }
            }
            temp = input[i];
            input[i] = input[idx];
            input[idx] = temp;
        }
    }
}
```

---
## 삽입정렬 <br>

삽입정렬("Insertion sort")은 손안의 카드를 정렬하는 방법과 유사하다. <br>
> 한 장씩 뽑아 책상에 특정 순서를 정해서 배치한다고 생각해보면 된다. <br> 한장을 뽑아 책상에 배치해둔 카드로 특정 순서에 맞게 배치한다.

자료배열의 모든 요소를 앞에서부터 차례대로 이미 정렬된 배열 부분과 비교하여 자신의 위치를 찾아 삽입하는 방식으로 정렬이 완료되는 알고리즘이다. <br>
<br>

|기존항목|값||정렬항목|값|
|---|---|---|---|---|
|Barcelona|10||Barcelona|10|
|Liverpool|8||||
|Tottenham|7||||
|Munich|1||||
|Pohang|99||||

<br>
현재 선택 : Liverpool, 정렬항목의 Barcelona와 값을 비교한다. <br>
Barcelona가 값이 더 크므로 자리를 교체한다. <br>
<br>

|기존항목|값||정렬항목|값|
|---|---|---|---|---|
|Barcelona|10||Liverpool|8|
|Liverpool|8||Barcelona|10|
|Tottenham|7||||
|Munich|1||||
|Pohang|99||||

<br>
현재 선택 : Tottenham, 정렬 항목인 Barcelona와 비교한다. <br>
Barcelona가 값이 더 크므로 자리를 교체하고 Liverpool과 비교한다. <br>
Liverpool이 더 값이 크므로 자리를 교체한다. <br>
<br>  

|기존항목|값||정렬항목|값|
|---|---|---|---|---|
|Barcelona|10||Tottenham|7|
|Liverpool|8||Liverpool|8|
|Tottenham|7||Barcelona|10|
|Munich|1||||
|Pohang|99||||

<br>
계속반복해나가면 크기순으로 정렬된다. <br>
<br>

```java
public class InsertionSort {
    public static void main(String[] args) {
        int[] input = new int[]{9,5,6,4,1,3};
        int len = input.length;
        int temp, j;
        for(int i = 1 ; i < len ; i++) {
            for(int j = i-1 ; j >= 0 && temp < input[j] ; j--) {
                input[j+1] = input[j];
            }
            input[j+1] = temp;
        }
    }
}
```

---
## 버블정렬 <br>

버블정렬("bubble sort")는 서로 인접한 두 원소를 검사하는 알고리즘이다. <br>
회전이 끝날 때마다 가장 큰 자료가 (크기-회전)의 맨 뒤로 옮겨진다. <br>
뒤에서부터 큰 값이 고정된다. <br>
<br>

|기존항목|값||정렬항목|값|
|---|---|---|---|---|
|Barcelona|10||||
|Liverpool|8||||
|Tottenham|7||||
|Munich|1||||
|Pohang|99||||

<br>
"1회전일 때" <br>
Barcelona(10)와 Liverpool(8)을 비교 후 왼쪽 값이 큰 값이므로 자리를 교체한다. <br>
교체된 Barcelona(10)과 Tottenham(7)을 비교 후 왼쪽 값이 큰 값이므로 역시 자리를 교체한다. <br>
교체된 Barcelona(10)과 Munich(1)을 비교 후 역시 왼쪽 값이 큰 값이므로 자리를 교체한다. <br>
교체된 Barcelona(10)과 Pohang(99)와 비교한다. 이번에는 오른쪽 값이 크므로 자리를 교체하지 않는다. <br>
<br>

|기존항목|값||정렬항목|값|
|---|---|---|---|---|
|Liverpool|8||||
|Tottenham|7||||
|Munich|1||||
|Barcelona|10||||
||||Pohang|99|

<br>
위의 표는 1회전 결과이다. <br>
"2회전을 한다" <br>
Liverpool(8)과 Tottenham(7)을 비교 후 왼쪽 값이 크므로 자리를 교체한다. <br>
교체된 Liverpool(8)과 Munich(1)과 비교 후 왼쪽 값이 크므로 자리를 교체. <br>
교치된 Liverpool(8)와 Barcelona(10)을 비교한다. 오른 쪽 값이 크니까 교체하지 않는다. <br>
맨 마지막 값은 이미 제일 큰 값이 올라가있으므로 계산할 필요가 없다. <br>
<br>

|기존항목|값||정렬항목|값|
|---|---|---|---|---|
|Tottenham|7||||
|Munich|1||||
|Liverpool|8||||
||||Barcelona|10|
||||Pohang|99|

<br>
위의 표는 2회전 결과이다. <br>
이렇게 마지막까지 반복하면 정렬된 자료를 얻을 수 있다. <br>
<br>

```java
public class BubbleSort {
    public static void main(String[] args) {
        int[] input = new int[]{9,5,6,4,1,3};
        int len = input.length;
        int temp;
        for(int i = 0 ; i < len ; i ++) {
            for(int j = 0 ; j < len - i -1 ; j++) {
                if(input[j] > input[j+1]) {
                    temp = input[j];
                    input[j] = input[j+1];
                    input[j+1] = temp;
                }
            }
        }
    }
}
```

---
## 퀵 정렬 <br>

퀵 정렬("Quick sort")는 분할 정복 알고리즘으로 매우 빠른 수행 속도를 자랑한다. <br>
리스트를 불균형하게 분할한다. <br>
문제를 2개의 작은 문제로 분리하고 각각을 해결한 다음 결과를 모아서 원래의 문제를 해결하는 전략이다. <br>
분할 정복 방법은 대게 순환 호출을 이용하여 구현한다. <br>
리스트 안에 있는 한 요소를 구한다. 이를 피벗(Pivot)이라고 한다. <br>
피벗(Pivot)을 기준으로 피벗보다 작은 요소들을 피벗의 왼쪽, 피벗보다 큰 요소들을 피벗의 오른쪽으로 옮겨진다. <br>
피벗을 제외한 오른쪽, 왼쪽 리스트를 다시 정렬한다. <br>
부분 리스트들이 더 이상 분할이 불가능(리스트의 크기가 0 또는 1) 할때까지 반복한다. <br>
<br>
퀵 정렬은 분할, 정복, 결합으로 이루어져있다. <br>
순환 호출이 한번 진행될 때마다 최소한 하나의 원소는 최종적인 위치가 결정된다. <br>
그러므로 이 알고리즘은 반드시 끝난다는 걸 보장할 수 있다. <br>


```java
public class QuickSort {
    public static void sort(int[] data, int l, int r) {
        int left = l;
        int right = r;
        int pivot = data[(l+r)/2];
        int temp;
        do {
            while(data[left] < pivot) left++;
            while(data[right] > pivot) right--;
            if(left <= right) {
                temp = data[left];
                data[left] = data[right];
                data[right] = temp;
            }
        } while(left <= right);
        if(l < right) sort(data, l, right);
        if(r > left) sort(data, left, r);
    }

    public static void main(String[] args) {
        int[] input = {11,43,2,85,4,7,53,27,28};
        QuickSort.sort(input, 0, input.length-1);
    }
}
```

---
## 합병정렬 <br>

합병정렬("Merge sort")는 분할 정복 알고리즘 중 하나이다. <br>
문제를 작은 2개의 문제로 분리하고 각각을 해결한 다음 결과를 모아서 원래의 문제를 해결하는 전략이다. <br>
합병정렬은 분할, 정복, 결합의 부분으로 나뉜다. <br>
합병 정렬의 과정에서는 추가적인 리스트가 필요하다. <br>
합병 정렬에서 실제로 정렬이 이루어지는 시점은 결합(합병)하는 단계이다. <br>


```java
public class Merge {
    public static void sort(int[] data, int l, int r) {
        if(l < r) {
            int mid = (l+r)/2;
            sort(data, l, mid);
            sort(data, mid+1, r);
            merge(data, l, mid, r);
        }
    }

    public static void merge(int[] data, int l, int mid, int r) {
        int i = l;
        int j = mid + 1;
        int k = l;
        int[] temp = new int[data.length];

        while(i <= mid && j <= r) {
            if(arr[i] < arr[j]) {
                arr[k++] = arr[i++];
            }else {
                arr[k++] = arr[j++];
            }
        }
        while(i<=mid) arr[k++] = arr[i++];
        while(j<=r) arr[k++] = arr[j++];
        for(int index = l; index < k ;index++) data[index] = temp[index];
    }

    public static void main(String[] args) {
        int[] input = {11,43,2,85,4,7,53,27,28};
        Merge.sort(input, 0, input.length-1);
    }
}
```

---