//최장증가수열
 
#include <iostream>
#include <vector>
using namespace std;

int n,llo;
int a[40001];
vector<int> v;

int main(){
	int res=0;
	cin>>n;
	for(int i=0; i<n; i++){
		cin>>a[i];
	}
	
	for(int i=0; i<n; i++){
		llo=lower_bound(v.begin(),v.end(),a[i])-
		v.begin();
		if(llo==v.size()) v.push_back(a[i]);
		else v[llo]=a[i];
	}
	
	res=v.size();
	cout<<res;
	getchar();
	getchar();
	return 0;
} 
