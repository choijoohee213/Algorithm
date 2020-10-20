#include <stdio.h>
int main(){
	int h,w,n,l,d,x,y;
	scanf("%d %d",&h,&w);
	int s[h+1][w+1]={};
	scanf("%d",&n);
	for(int i=0; i<n; i++){
		scanf("%d %d %d %d",&l,&d,&x,&y);
		if(d==0){
			for(;l>0;l--){
				s[x][y]=1;
				y++;
			}
		}
		else if(d==1){
			for(;l>0;l--){
				s[x][y]=1;
				x++;
			}
		}
	}
	
	for(int i=1; i<=h; i++){
		for(int j=1; j<=w; j++){
			printf("%d ",s[i][j]);
		}
		printf("\n");
	}
	
	getchar();
	getchar();
	return 0;
}
