#include <bits/stdc++.h>
using namespace std;

int main() {
	int m,t,s,ans;
	cin>>m>>t>>s;
	if(t==0) {
		 ans=0;
	} else if (t!=0) {
		if(s%t==0) {
			 ans=m-s/t;
		} else if (t!=0) {
			 ans=m-(s/t)-1;
		}
	}
	if(ans>0) {
		cout<<ans<<endl;
	} else {
		cout<<"0"<<endl;
	}
	return 0;
}
