#include <bits/stdc++.h>
using namespace std;

struct bead{ 
	int rx, ry; //���� ���� ��ǥ
	int bx, by;  //�Ķ� ���� ��ǥ 
	int d;  //����� Ƚ�� 
};

int n,m;
char board[10][10];
bool visited[10][10][10][10];  //�湮 ���� 
queue<bead> q;
 
int dx[] = {0,1,0,-1};
int dy[] = {1,0,-1,0};

void move(int& x, int& y, int& c, int& i){
	while(board[x+dx[i]][y+dy[i]] != '#' && board[x][y] != 'O'){
		x += dx[i];
		y += dy[i];
		c += 1; 
	}
}

void bfs(){
	while(!q.empty()){
		int rx = q.front().rx;
		int ry = q.front().ry;
		int bx = q.front().bx;
		int by = q.front().by;
		int d = q.front().d;
		q.pop();
		
		if(d>=10) break;
		
		for(int i=0; i<4; i++){
			int nrx=rx, nry=ry, nbx=bx, nby=by;
			int rc=0, bc=0, nd=d+1;
			
			move(nrx, nry, rc, i);
			move(nbx, nby, bc, i);
			
			if(board[nbx][nby] == 'O') continue;
			if(board[nrx][nry] == 'O'){
				cout<<nd;
				return;
			}
			
			//������ ������ �� 
			if(nrx == nbx && nry == nby){
				//���� ��, �̵��Ÿ��� �� �� ������ ��ġ�� ��ĭ �ڷ� ����
			    if(rc > bc) nrx -= dx[i], nry -= dy[i];
				else nbx -= dx[i], nby -= dy[i];
			}
			
			if(visited[nrx][nry][nbx][nby]) continue;
			visited[nrx][nry][nbx][nby] = true;
			q.push({nrx, nry, nbx, nby, nd});
		}
	}
	cout<<-1;
}

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	
	int rx=0, ry=0, bx=0, by=0;
	cin>>n>>m;
	
	for(int i=0; i<n; i++){
		string s;
		cin>>s;	
		for(int j=0; j<m; j++) {
			board[i][j] = s[j];
			if(s[j] == 'R') rx = i, ry = j;
			else if(s[j] == 'B') bx = i, by = j;
		}
	}
	
	q.push({rx, ry, bx, by, 0});
	visited[rx][ry][bx][by] = true;
	bfs();
}
