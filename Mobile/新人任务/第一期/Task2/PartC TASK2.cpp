#include <stdio.h>

// 计算逆序对数（暴力 O(n^2)）
int count_inversions(int a[], int n) {
    int inv = 0;
    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            if (a[i] > a[j]) {
                inv++;
            }
        }
    }
    return inv;
}

int main() {
    int n;
    scanf("%d", &n);
    int a[1005];
    for (int i = 0; i < n; i++) {
        scanf("%d", &a[i]);
    }

    int inv = count_inversions(a, n);

    if (inv == 0) {
        printf("0\n");
        return 0;
    }

    int min_ops = inv; // 初始化为只用技能1的操作次数

    // 枚举所有可能的技能 2 操作
    for (int i = 0; i < n - 2; i++) {
        // 交换 a[i] 和 a[i+2]
        int temp = a[i];
        a[i] = a[i+2];
        a[i+2] = temp;

        int new_inv = count_inversions(a, n);
        int delta = new_inv - inv;
        
        // 使用一次技能2的总操作次数 = 1 + new_inv
        int total_ops = 1 + new_inv;
        if (total_ops < min_ops) {
            min_ops = total_ops;
        }

        // 交换回来
        temp = a[i];
        a[i] = a[i+2];
        a[i+2] = temp;
    }

    printf("%d\n", min_ops);

    return 0;
}/*输入格式：比如：5
					1 2 3 5 4*/ 
