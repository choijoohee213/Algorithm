#include <stdio.h>

int main(){
	int n,r;
	scanf("%d",&n);
	int s[n]={};
	int min=n;
	for(int i=0; i<n; i++){
		scanf("%d",&r);
		s[i]=r;
	}
	for(int j=0; j<n; j++){
		if(min>s[j]) min=s[j];
	}
	printf("%d",min);
	getchar();
	getchar();
	return 0;
}
