#include <stdio.h>

int main(){
	int a,b,c;
	scanf("%d %d %d",&a,&b,&c);

	printf("%d\n%.1f",a+b+c,(double)(a+b+c)/3);
	getchar();
	getchar();
	return 0;
}
