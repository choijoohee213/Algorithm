#include <stdio.h>

int main(){
	int n,r;
	scanf("%d",&n);
	int s[24]={};
	for(int i=0; i<n; i++){
		scanf("%d",&r);
		s[r]++;
	}
	for(int j=1; j<=23; j++){
		printf("%d ",s[j]);
	}
	getchar();
	getchar();
	return 0;
}
