#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(){
	int i,j=0,p;
	char n[20];
	char seps[2]=".";
	scanf("%s",&n);
	
	char* result=strtok(n,seps);

	printf("%04d",atoi(result));
	while(result!=NULL){
		result=strtok(NULL,seps);
		if(result!=NULL){
			printf(".%02d",atoi(result));
	
		}

	}
	
	getchar();
	return 0;
}
