#include <bits/stdc++.h>
using namespace std;

int main(){
	int n;
	cin>>n;
	
	int t1, t2;
	t1=5*n;
	t2=11+3*n;
	
	if(t1<t2){
		cout<<"Local"<<endl;
	}else if(t1>t2){
		cout<<"Luogu"<<endl;
	}
	return 0;
}
