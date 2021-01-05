//Àç±Í »ç¿ë 

#include <vector>
using namespace std;

int count = 0, w, h;
vector<vector<int> > p;

int calSection(int x, int y, int color){
    if(x<0 || y<0 || x>=w || y>=h || color != p[x][y] || p[x][y] == 0) return 0;
    
    int count = 1;
    p[x][y] = 0;
    
    count += calSection(x, y-1, color);
    count += calSection(x+1, y, color);
    count += calSection(x-1, y, color);
    count += calSection(x, y+1, color);
    return count;
}

vector<int> solution(int m, int n, vector<vector<int>> picture) {
    w = m;
    h = n;
    p = picture;
    
    int result=0, i=0, j=0;
    vector<int> answer(2, 0);
    
    while(i<m){
        if(p[i][j] != 0){
            result = calSection(i,j,p[i][j]);
            if(answer[1] < result) answer[1] = result;
            answer[0]++;
        }
        
        if(j == n-1){ 
            j=0;
            i++;
        }
        else j++; 
    }
    
    return answer;
}
