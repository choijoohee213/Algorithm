#include <stdio.h>

int main(){
	int a;
	scanf("%d",&a);
	(a>0)?printf("plus\n"):printf("minus\n");
	(a%2==0)?printf("even\n"):printf("odd\n");
	getchar();
	getchar();
	return 0;
}
