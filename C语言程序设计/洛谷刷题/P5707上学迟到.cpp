#include<bits/stdc++.h>
using namespace std;

int main(){
	double s,v;//����ʱ����ٶ� ,ע�����Ϊdouble ���� s��v������ʱ���������Ϊ�������ͣ�����ͻ�ֱ������ȡ���ˣ����ԲŻ����07:49��
	int total,time,hour,min;//totalΪ����һ���0�㿪ʼ���㣬time����Ҫʱ�� 
	
	cin>>s>>v;
	total=8*60+24*60;//��ʱ�� 
	time=ceil(s/v)+10;
	time=total-time;
	//�Ż������ʽ 
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
