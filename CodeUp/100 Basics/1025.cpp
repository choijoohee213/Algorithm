#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(){
	int i=0,j=10000,result;
	int n;
	scanf("%d",&n);
	for(int i=0; i<5; i++){
		result= (n/j)*j;
		printf("[%d]\n",result);
		n%=j;
		j=j/10;
	}

	getchar();
	return 0;
}
