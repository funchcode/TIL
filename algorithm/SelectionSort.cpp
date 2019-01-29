#include <stdio.h>
#include <array>

void main()
{
    /* Input Data */
    int data[] = {9,2,3,1,7,4};

    for(int i = 0 ; i < sizeof(data)-1 ; i ++) {
        int indexMin = i;
        for(int j = i+1 ; j< sizeof(data) ; j ++) {
            if(data[indexMin] > data[j]) {
                indexMin = j;
            }
        }
        int temp = data[i];
        data[i] = data[indexMin];
        data[indexMin] = temp;
    }

    for(int k = 0 ; k < sizeof(data) ; k++) {
        printf("\t%d", data[k]);
    }
}
