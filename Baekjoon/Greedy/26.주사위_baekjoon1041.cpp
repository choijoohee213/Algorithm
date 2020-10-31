#include<stdio.h>
#include<algorithm>
using namespace std;

int d[6], min1, min2, min3;
long long n, sum=0, n1, n2, n3;

int main(){
	scanf("%lld",&n);
	for(int i=0; i<6; i++)	scanf("%d", &d[i]);	
	
	if(n==1){
		sort(d,d+6);
		for(int i=0; i<5; i++) sum+=d[i];
	}
	else{
		d[0] = min(d[0],d[5]);
		d[1] = min(d[1],d[4]);
		d[2] = min(d[2],d[3]);

		sort(d,d+3);
		min1 = d[0];
		min2 = d[0]+d[1];
		min3 = d[0]+d[1]+d[2];
		
		n1 = 4*(n-1)*(n-2)+(n-2)*(n-2);
		n2 = 4*(n-1)+4*(n-2);
		n3 = 4;

		sum = n1*min1 + n2*min2 + n3*min3;
	}
	printf("%lld", sum);
}
