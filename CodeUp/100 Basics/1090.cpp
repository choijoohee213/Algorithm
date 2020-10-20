#include <stdio.h>

int main(){
	int a,r,n,result=1;
	scanf("%d %d %d",&a,&r,&n);
	for(int i=1; i<=n-1; i++){
		result*=r;
	}
    printf("%d",a*result);		
	getchar();
	getchar();
	return 0;
}


