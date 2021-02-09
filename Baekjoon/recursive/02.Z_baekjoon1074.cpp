#include<bits/stdc++.h>
using namespace std;

int recursive(int n,int r,int c){
	if(n==0) return 0;
	int half = 1<<(n-1);
	if(r<half && c<half) return recursive(n-1,r,c);
	if(r<half && c>=half) return half*half + recursive(n-1,r,c-half);
	if(r>=half && c<half) return half*half*2 + recursive(n-1,r-half,c);
	return half*half*3 + recursive(n-1,r-half,c-half); 
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	int n,r,c;
	cin>>n>>r>>c;
	cout<<recursive(n,r,c);
}
