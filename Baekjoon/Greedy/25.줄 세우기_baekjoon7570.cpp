#include<cstdio>

int n, max, d[1000001];

int main(){
	scanf("%d", &n);
	
	for(int i=0; i<n; i++){
		int x;
		scanf("%d", &x);
		d[x] = d[x-1] + 1;
		if(max<d[x]) max = d[x];
	}
	
	printf("%d", n-max);
	return 0;
}
