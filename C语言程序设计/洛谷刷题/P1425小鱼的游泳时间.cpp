#include <bits/stdc++.h>
using namespace std;

int main(){
	int a,b,c,d;
	cin>>a>>b>>c>>d;
	
	int t1,t2;
	t1=a*60+b;
	t2=c*60+d;
	
	int t3=t2-t1;
	if(t3<60){
		cout<<0<<" "<<t3<<endl;
	}else{
		int hour=t3/60;
		int min=t3%60;
		cout<<hour<<" "<<min;
	}
	
	return 0;
}
