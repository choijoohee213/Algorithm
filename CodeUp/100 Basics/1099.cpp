#include <stdio.h>
int main(){
	int x=2,y=2;
	int s[11][11]={};
	for(int i=1; i<=10; i++){
		for(int j=1; j<=10; j++){
			scanf("%d",&s[i][j]);
		}
	}
	
	s[x][y]=9;
	while((s[x][y+1]!=1||s[x+1][y]!=1)){
		if(s[x][y+1]==0){  //오른쪽이 갈수있는곳이라면 
			if(s[x][y+1]==2){
			   s[x][++y]=9;
			   break;
			}  //먹이에 도착하면 종료 
			else s[x][++y]=9;
		}
		else if(s[x][y+1]==1){ //아니라면 아래로 이동 
		    if(s[x+1][y]==2){
			   s[++x][y]=9;
			   break;
			}  //먹이에 도착하면 종료 
			else s[++x][y]=9;
		}
	}
	
	for(int i=1; i<=10; i++){
		for(int j=1; j<=10; j++){
			printf("%d ",s[i][j]);
		}
		printf("\n");
	} 
	getchar();
	getchar();
	return 0;
}
