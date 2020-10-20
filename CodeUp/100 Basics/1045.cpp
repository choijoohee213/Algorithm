#include <stdio.h>

int main(){
	unsigned int a,b;
	scanf("%u %u",&a,&b);

	printf("%d\n%d\n%d\n%d\n%d\n%.2f\n",a+b,a-b,a*b,a/b,a%b,(double)a/(double)b);
	getchar();
	getchar();
	return 0;
}
