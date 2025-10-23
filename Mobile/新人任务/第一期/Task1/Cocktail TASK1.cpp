#include <stdio.h>
#include <string.h>
#include <stdlib.h>

typedef struct {
    char name[32];
    double baseStrength;
    int baseVolume;
    int nonAlcoholicBeveragesVolume;
} Cocktail;//定义一个Cocktail的结构体，用于储存一杯鸡尾酒的全部信息 

int cmp(const void* a, const void* b) {/*两个通用指针*/ 
    Cocktail* ca = (Cocktail*)a;/*把通用指针转化成鸡尾酒类型的指针，这样就可以访问鸡尾酒的内容了*/ 
    Cocktail* cb = (Cocktail*)b;
    // 无酒精饮料（度数0）排最前
    if (ca->baseStrength == 0 && cb->baseStrength != 0) return -1;
    if (ca->baseStrength != 0 && cb->baseStrength == 0) return 1;
    // 剩下的按度数降序
    if (ca->baseStrength < cb->baseStrength) return 1;
    if (ca->baseStrength > cb->baseStrength) return -1;
    return 0;
}//排序使用的比较函数 

int main() {
    Cocktail cocktails[] = {
        {"Cocktail0", 10.0, 50, 120},
        {"Cocktail1", 30.0, 100, 300},
        {"Cocktail2", 12.8, 50, 150},
        {"Cocktail4", 43.2, 80, 120},
        {"Soda", 0.0, 0, 330}
    };//对鸡尾酒数组初始化 
    int n = sizeof(cocktails) / sizeof(Cocktail);//sizeof(cocktails) 是整个数组的大小（字节数），sizeof(Cocktail) 是一杯鸡尾酒的大小（字节数），两个一除就得到数组里有几杯鸡尾酒

    qsort(cocktails, n, sizeof(Cocktail), cmp);/*cocktails是要排序的数组，n是数组里面元素的个数，sizeof(Cocktail)是一杯鸡尾酒的大小，cmp素之前写的比较函数，告诉qsort按什么规则排序 */

    for (int i = 0; i < n; ++i) {
        printf("Cocktail{name='%s', baseStrength=%.1f, baseVolume=%d, nonAlcoholicBeveragesVolume=%d}\n",
               cocktails[i].name,
               cocktails[i].baseStrength,
               cocktails[i].baseVolume,
               cocktails[i].nonAlcoholicBeveragesVolume);
    }
    return 0;
}
