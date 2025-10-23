#include <bits/stdc++.h>
using namespace std;

int main(){
	int a,b;
	cin>>a>>b;
	double v = a * b * b * 3.14;
	double n;
	n= 2e4/v;
	int num=ceil(n);
	cout<<num;
	return 0;
}
