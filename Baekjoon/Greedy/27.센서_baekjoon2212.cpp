#include<stdio.h>
#include<algorithm>
using namespace std;

int n,k, d[10001], start, end;
long long sum, x;

int main(){
	
	scanf("%d %d",&n,&k);
	for(int i=0; i<n; i++) scanf("%d",&d[i]);
	
	sort(d, d+n);
	
	start = d[0];
	end = d[n-1];
	sum = 9223372036854775807;
	
	for(int i=1; i<n-2; i++){
		x = (d[i]-start) + (end-d[i+1]);
		sum = min(sum,x);
	}
	printf("%lld",sum);
}
