#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main(){
	int y,m,d;
	int n;
	scanf("%d.%d.%d",&y,&m,&d);
	printf("%02d-%02d-%04d",d,m,y);

	getchar();
	return 0;
}
