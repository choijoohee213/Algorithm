#include<stdio.h>

int main(){
	int n, count=0;
	char s[51];
	
	scanf("%d", &n);
	scanf("%s", s);

	for(int i=0; i<n; i++){
		if(s[i]=='L')
			count++;
	}
	
	printf("%d", count>2? n-count/2+1 : n);
}
