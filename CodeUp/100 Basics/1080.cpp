#include <stdio.h>

int main(){
	int n,sum=0,i=0;
	scanf("%d",&n);
	while(sum<n){
		i++;
		sum+=i;
	}
	printf("%d",i);
	getchar();
	getchar();
	return 0;
}
