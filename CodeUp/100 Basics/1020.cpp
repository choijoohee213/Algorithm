#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(){
	char n[30];
	char seps[2]="-";
	scanf("%s",&n);
	
	char* result=strtok(n,seps);
	printf("%06d",atoi(result));
	result = strtok(NULL,seps);
	printf("%07d",atoi(result));

	
	getchar();
	return 0;
}
