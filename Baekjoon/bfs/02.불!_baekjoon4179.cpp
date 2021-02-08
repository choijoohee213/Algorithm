#include <bits/stdc++.h>
using namespace std;

int r,c;
string d[1002];
int checkJ[1002][1002];
int checkF[1002][1002];
int dx[4] = {1,0,-1,0};
int dy[4] = {0,1,0,-1};

int main(){
	 ios::sync_with_stdio(0);
	 cin.tie(0);
	 cin>>r>>c;
	 for(int i=0; i<r; i++) cin>>d[i];
	 
	 queue<pair<int,int>> qJ;
	 queue<pair<int,int>> qF; 
	 
	 for(int i=0; i<r; i++){
	 	for(int j=0; j<c; j++){
	 		if(d[i][j] == 'J'){
				qJ.push(make_pair(i,j));
				checkJ[i][j] = -1;
			}	
			else if(d[i][j] == 'F'){
				qF.push(make_pair(i,j));
				checkF[i][j] = -1;
			} 
		 }
	 }
	 
	 //ºÒ 
	  while(!qF.empty()){
	 	pair<int,int> locF = qF.front(); qF.pop();
		
		for(int i=0; i<4; i++){
			int x = locF.first + dx[i];
			int y = locF.second + dy[i];
			
			if(x<0 || x>=r || y<0 || y>=c 
				|| d[x][y] == '#' || checkF[x][y]<0) continue;
			qF.push(make_pair(x,y));
			checkF[x][y] = checkF[locF.first][locF.second]-1;
		}
	 }
	 
	 //ÁöÈÆ 
	 while(!qJ.empty()){
	 	pair<int,int> locJ = qJ.front(); qJ.pop();
		for(int i=0; i<4; i++){
			int x = locJ.first + dx[i];
			int y = locJ.second + dy[i];
			
			if(x<0 || x>=r || y<0 || y>=c) {
				int result = checkJ[locJ.first][locJ.second]*(-1);
				cout<<result;
				return 0;
			}
			if(d[x][y] == '#' || checkJ[x][y]<0) continue;
			if(checkF[x][y]!=0 && checkF[x][y] >= checkJ[locJ.first][locJ.second]-1) continue;
			qJ.push(make_pair(x,y));
			checkJ[x][y] = checkJ[locJ.first][locJ.second] -1;
		}
	 }
	 
	cout<<"IMPOSSIBLE";
	  
}
