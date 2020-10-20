#include <stdio.h>

int main(){
	int n,x,y;
	scanf("%d",&n);
	int s[20][20]={};
	for(int i=0; i<n; i++){
		scanf("%d %d",&x,&y);
		s[x][y]++;
	}
	for(int i=1; i<=19; i++){
		for(int j=1; j<=19; j++){
			printf("%d ",s[i][j]);
		}
		printf("\n");
	}
	getchar();
	getchar();
	return 0;
}
