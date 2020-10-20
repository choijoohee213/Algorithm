#include <stdio.h>

int main(){
	while(1){
		char a;
		scanf("%c",&a);
	    if(a!=' ') printf("%c\n",a);
	    if(a=='q') break;
	}
	getchar();
	getchar();
	return 0;
}
