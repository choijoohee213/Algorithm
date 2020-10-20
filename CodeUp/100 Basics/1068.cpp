#include <stdio.h>

int main(){
	int a;
	scanf("%d",&a);
	if(a>=90 && a<=100) printf("A");
	else if(a<90 && a>=70) printf("B");
	else if(a<70 && a>=40) printf("C");
	else if(a<40 && a>=0) printf("D");
	getchar();
	getchar();
	return 0;
}
