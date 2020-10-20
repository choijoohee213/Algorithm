#include <cstdio>
#include <stack>
using namespace std;

char map[10001][501];
int r, c, count=0;
stack<pair<int,int> > s;

int findPath(int row, int col){
	if(col==c) return 1;  //»§Áý¿¡ µµÂø 
	if(row<0 || col<0 || row>r-1) return 0;
	
	if(map[row][col]=='x') return 0;
	
	if(map[row][col]=='.'){
		bool isFind = false; 
		for(int p=-1; p<=1; p++){
			if(findPath(row+p,col+1)) {
				isFind = true;
				break;
			}
		}	
		if(isFind) s.push(make_pair(row,col));
		else map[row][col] = 'x';
			
		return isFind;
	}
}


int main() {
	scanf("%d %d", &r, &c);
	for (int i = 0; i < r; i++) 
		scanf("%s", &map[i]);	
	
	for(int i=0; i<r; i++){
		if(!findPath(i,0)) continue;
		while(!s.empty()){
			map[s.top().first][s.top().second] = 'x';
			s.pop();
		}
		count++;
	}
	
	printf("%d",count);
}
