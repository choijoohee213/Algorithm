#include <stdio.h>

int main(){
	int c,sum=0;
	scanf("%d",&c);
	for(int i=1;  i<=c; i++){
		if(i%2==0) sum+=i;
	}
	printf("%d",sum);
	getchar();
	getchar();
	return 0;
}
