#include <stdio.h>

int main(){
	int n;
	scanf("%d",&n);
	int a[n];
	for(int i=0; i<n; i++) scanf("%d",&a[i]);
	for(int i=0; i<n; i++) printf("%d\n",a[i]);
	getchar();
	getchar();
	return 0;
}
