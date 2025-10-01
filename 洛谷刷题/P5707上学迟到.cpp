#include<bits/stdc++.h>
using namespace std;

int main(){
	double s,v;//输入时间和速度 ,注意变量为double 否则 s与v除不尽时，如果定义为整数类型，结果就会直接向下取整了，所以才会输出07:49。
	int total,time,hour,min;//total为从上一天的0点开始计算，time是需要时间 
	
	cin>>s>>v;
	total=8*60+24*60;//总时间 
	time=ceil(s/v)+10;
	time=total-time;
	//优化代码格式 
	/*if (time>=24*60){
		min=time%60;
		hour=(time-24*60)/60; 
		if(hour<10){
			if(min<10){
			cout<<"0"<<hour<<":0"<<min<<endl;
			} else cout<<"0"<<hour<<":"<<min<<endl;
		} else {
			if(min<10){
			cout<<hour<<":0"<<min<<endl;
			} else cout<<hour<<":"<<min<<endl;
		}
	} else{
		min=time%60;
		hour=time/60;
		if(hour<10){
			if(min<10){
			cout<<"0"<<hour<<":0"<<min<<endl;
			} else cout<<"0"<<hour<<":"<<min<<endl;
		} else {
			if(min<10){
			cout<<hour<<":0"<<min<<endl;
			} else cout<<hour<<":"<<min<<endl;
		}
	}*/
	if (time>=24*60) time-=24*60;
	hour=time/60;
	min=time%60;
	
	if (hour<10) {
	if (min<10) cout<<"0"<<hour<<":0"<<min<<endl;
	else cout<<"0"<<hour<<":"<<min<<endl;
} 
	else{
		if(min<10) cout<<hour<<":0"<<min<<endl;
		else cout<<hour<<":"<<min<<endl;
}
	return 0;	
}
