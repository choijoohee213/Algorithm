#include<stdio.h>
#include<vector>
#include<algorithm>

int n, c, m;
vector<pair<pair<int,int> > > v;

int main(){
	scanf("%d %d", &n, &c);
	scanf("%d", &m);
	
	for(int i=0; i<m; i++){
		int a, b, c;
		scanf("%d %d %d", &a, &b, &c);
		v.push_back(make_pair(a,make_pair(b,c)));
	}
	 
}
