#include<stdio.h>
#include<algorithm>
#include<functional>
using namespace std;

int main(){
	int t;
	scanf("%d", &t);

	while(t--){
		int n;
		int d[10001] = {0,};
		
		scanf("%d", &n);
		for(int i=0; i<n; i++) scanf("%d", &d[i]);
		
		sort(d, d+n, greater<int>());
		
		int maxNum = d[0] - d[1];
		for(int i=0; i<n-1; i++){
			if(i==n-2) maxNum = max(maxNum, d[i]-d[i+1]);
			else maxNum = max(maxNum, d[i]-d[i+2]);
		}
		
		printf("%d\n",maxNum);
	} 
}
