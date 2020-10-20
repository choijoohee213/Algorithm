#include <stdio.h>

int main(){
	int n,r;
	scanf("%d",&n);
	int s[n]={};
	for(int i=0; i<n; i++){
		scanf("%d",&r);
		s[i]=r;
	}
	for(int j=n-1; j>=0; j--){
		printf("%d ",s[j]);
	}
	getchar();
	getchar();
	return 0;
}
