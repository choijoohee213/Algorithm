#include <stdio.h>

int main(){
	char c,a='a';
	scanf("%c",&c);
	while(1){
	   printf("%c ",a++);
	   if(a==c+1) break;
	}
	getchar();
	getchar();
	return 0;
}
