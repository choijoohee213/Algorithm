#include <stdio.h>
#include <math.h>

int main(){
	int w,h,b;
	scanf("%d %d %d",&w,&h,&b);
	printf("%.2f MB",(w*h*b)/pow(2,23));
	getchar();
	getchar();
	return 0;
}
