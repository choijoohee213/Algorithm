#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(){
	char n[2001];
	fgets(n,2000,stdin);
	printf("%s",n);
	getchar();
	return 0;
}
