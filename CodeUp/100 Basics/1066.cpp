#include <stdio.h>

int main(){
	unsigned int a,b,c;
	scanf("%u %u %u",&a,&b,&c);
	(a%2==0)?printf("even\n"):printf("odd\n");
	(b%2==0)?printf("even\n"):printf("odd\n");
	(c%2==0)?printf("even\n"):printf("odd\n");
	getchar();
	getchar();
	return 0;
}
