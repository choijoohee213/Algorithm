#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(){
	int i=0;
	char n[21];
	scanf("%s",&n);
	for(int i=0; n[i]!=NULL; i++){
		printf("\'%c\'\n",n[i]);
	}

	getchar();
	return 0;
}
