#include <stdio.h>

int main(){
	char c;
	scanf("%c",&c);
	if(c=='A') printf("best!!!");
	else if(c=='B') printf("good!!");
	else if(c=='C') printf("run!");
	else if(c=='D') printf("slowly~");
	else printf("what?");
	getchar();
	getchar();
	return 0;
}
