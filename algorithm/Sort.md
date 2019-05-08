## "목차" <br>
1. [선택정렬](#선택정렬-)

---
## 선택정렬 <br>

선택정렬("Selection sort")은 리스트의 모든 항목을 살펴보고 가장 많거나 적은 항목을 찾아 기록하는 것을 말한다. <br>
<br>
| 기존항목 | 값 | <> | 정렬항목 | 값 |
|---|---|---|---|---|
|Barcelona|10|  |   |   |   |
|Liverpool|8|   |   |   |   |
|Tottenham|7|   |   |   |   |
|Munich|1|  |   |   |   |
|Pohang|99| |   |   |   |
<br>
가장 많은 항목을 살펴보고 가장 많은 항목을 기록한다.
<br>
| 기존항목 | 값 | <> | 정렬항목 | 값 |
|---|---|---|---|---|
|Barcelona|10|  |   |Pohang|99|
|Liverpool|8|   |   |   |   |
|Tottenham|7|   |   |   |   |
|Munich|1|  |   |   |   |
|   |   |  |   |   |   |
<br>
값이 제일 높은 포항 스틸러스가 기록되었다.
<br>
| 기존항목 | 값 | <> | 정렬항목 | 값 |
|---|---|---|---|---|
|   |   |  |   |Pohang|99|
|Liverpool|8|   |   |Barcelona|10|
|Tottenham|7|   |   |   |   |
|Munich|1|  |   |   |   |
|   |   |  |   |   |   |
<br>
포항 스틸러스를 제외한 나머지 항목 중 가장 큰 값인 바르셀로나가 기록되었다.
<br>
이렇게 순서대로 반복하면 정렬된 목록을 얻을 수 있다. <br>
<br>
빅오 표기법으로 계산해보자면 O(n)시간은 목록의 모든 원소를 한 번씩 건드려야 한다는 의미인데, O(n*n)시간이 걸린다는 것을 알 수 있다. <br>
> 왜 O(n*n)일까. <br>
> 빅오 표기법은 상수와 관련이 있기 때문에 이런 법칙이 생긴건데 빅오 표기법은 상수항을 무시하므로 O(n*n)이라 표기한다.

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