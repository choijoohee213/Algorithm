#include <stdio.h>

int main(){
	int n,x,y;
	int s[20][20]={};
	for(int i=1; i<=19; i++){
		for(int j=1; j<=19; j++){
			scanf("%d ",&s[i][j]);
		}
	}
	scanf("%d",&n);
	for(int i=0; i<n; i++){
	    scanf("%d %d",&x,&y);
		for(int j=1;j<=19;j++){
			if(s[x][j]==0) s[x][j]=1;
			else s[x][j]=0;
		}
		for(int j=1;j<=19;j++){
			if(s[j][y]==0) s[j][y]=1;
			else s[j][y]=0;
		}
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
