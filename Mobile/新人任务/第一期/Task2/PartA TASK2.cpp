#include <stdio.h>

int main() {
    int n;
    scanf("%d", &n);
    int a[n];//定义一个长度为5的整型数组a，用来存放所有的数字 
    for (int i = 0; i < n; ++i) scanf("%d", &a[i]);//用一个循环，从0到n-1，每次输入一个数字，存到数组a中 
	 

    // 统计逆序对数量
    int count = 0;
    for (int i = 0; i < n; ++i) {//外循环i从0到n-1 
        for (int j = i + 1; j < n; ++j) {//内循环j从i+1到n-1 
            if (a[i] > a[j]) count++;//每次比较a[i],a[j] 
        }
    }
    printf("%d\n", count);
    return 0;
    //总体思路：计算逆序对的数量 
}
