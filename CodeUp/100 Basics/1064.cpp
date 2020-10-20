#include <stdio.h>

int main(){
	int a,b,c;
	scanf("%d %d %d",&a,&b,&c);
	(a>b)?(b>c)?printf("%d",c):printf("%d",b):(a>c)?printf("%d",c):printf("%d",a);
	getchar();
	getchar();
	return 0;
}
