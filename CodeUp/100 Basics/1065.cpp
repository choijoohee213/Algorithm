#include <stdio.h>

int main(){
	unsigned int a,b,c;
	scanf("%u %u %u",&a,&b,&c);
	if(a%2==0) printf("%d\n",a);
	if(b%2==0) printf("%d\n",b);
	if(c%2==0) printf("%d\n",c);
	getchar();
	getchar();
	return 0;
}
