#include <bits/stdc++.h>
using namespace std;

int main(){
	int pro;
	cin>>pro;
	if(pro==1){
		cout<<"I love Luogu!"<<endl;
	} else if(pro==2){
		cout<<6<<" "<<4<<endl; 
	} else if(pro==3){
		cout<<14/4<<endl;
		cout<<(14/4)*4<<endl;
		cout<<14-((14/4)*4)<<endl;
	} else if(pro==4){
		double ml;
		ml=500.0/3;
		printf("%.3f",ml);
	} else if(pro==5){
		cout<<(260+220)/(20+12)<<endl;
	} else if(pro==6){
		cout<<sqrt(6*6+9*9)<<endl;
	} else if (pro==7){
		int a=100;
		a+=10;  //存了10元
		cout<<a<<endl;
		a-=20;  //花了20元
		cout<<a<<endl;
		a=0;  //全部取出
		cout<<a<<endl;

	} else if(pro==8){
		int r=5;
		double pi=3.141593;
		cout<<2*pi*r<<endl;
		cout<<r*r*pi<<endl;
		cout<<4.0/3*pi*r*r*r<<endl;
	} else if(pro==9){
		cout<<(((1+1)*2+1)*2+1)*2<<endl;	
	} else if(pro==10){
		cout<<9<<endl;
	} else if(pro==11){
		cout<<100.0/(8-5)<<endl;
	} else if(pro==12){
		cout<<'M'-'A'+1<<endl;
		cout<<char('A'-1+18)<<endl;//一定注意char 

	} else if(pro==13){
		double v1=4.0/3*3.141593*4*4*4;
		double v2=4.0/3*3.141593*10*10*10;
		double v3=v1+v2;
		v3=pow(v3,1.0/3);
		printf("%.01f\n",v3);
	} else if(pro==14){
		cout<<50<<endl;
	}
	return 0;
}
