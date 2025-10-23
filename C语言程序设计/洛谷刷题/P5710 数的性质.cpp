#include <bits/stdc++.h>
using namespace std;

int main(){
	int x;
	cin>>x;
	
	bool con1 = x%2 ==0, con2 = x>4&&x<=12;
	cout<<(con1&&con2)<<" "<<(con1||con2)<<" "<<(con1!=con2)<<" "<<(!con1&&!con2)<<endl;
	return 0;
}
/*逻辑运算
&&与  ||或  ！非：！false=true
！=不等价于  ==同或*/
//con！=con2代表有且只有一个满足 
