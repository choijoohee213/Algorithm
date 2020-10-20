#include <stdio.h>

int main(){
	int c,a=0;
	scanf("%d",&c);
	while(1){
	   printf("%d\n",a++);
	   if(a==c+1) break;
	}
	getchar();
	getchar();
	return 0;
}
