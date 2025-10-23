#include <bits/stdc++.h>
using namespace std;

int main(){
	int n;
	cin>>n;
	int isLeapyear;
	if((n%4==0 && n%100!=0)||(n%400==0)){
		isLeapyear=1;
	} else {
		isLeapyear=0;
	}
	cout<<isLeapyear<<endl;
	return 0; 
}
