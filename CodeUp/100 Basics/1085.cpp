#include <stdio.h>
#include <math.h>

int main(){
	int h,b,c,s;
	double storage;
	scanf("%d %d %d %d",&h,&b,&c,&s);
	printf("%.1f MB",(h*b*c*s)/pow(2,23));
	getchar();
	getchar();
	return 0;
}
