#include <string>
#include <vector>

using namespace std;

vector<int> solution(int rows, int columns, vector<vector<int>> queries) {
    vector<int> answer;
    vector<vector<int>> d(rows, vector<int>(columns, 0));    
    
    int cnt = 0;
    for(int i=0; i<rows; i++){
        for(int j=0; j<columns; j++){
            d[i][j] = ++cnt;
        }
    }
    
    for(int i=0; i<queries.size(); i++){
        int x1 = queries[i][0] - 1;
        int y1 = queries[i][1] - 1;
        int x2 = queries[i][2] - 1;
        int y2 = queries[i][3] - 1;
        int temp = d[x1][y1];
        cnt = rows * columns;
        cnt = min(cnt, temp);
        
        for(int i=x1; i<x2; i++){
            d[i][y1] = d[i+1][y1];
            cnt = min(cnt, d[i][y1]); 
        }
        
        for(int i=y1; i<y2; i++){
            d[x2][i] = d[x2][i+1];
            cnt = min(cnt, d[x2][i]);
        }
        
        for(int i=x2; i>x1; i--){
            d[i][y2] = d[i-1][y2];
            cnt = min(cnt, d[i][y2]);
        }
        
        for(int i=y2; i>y1+1; i--){
            d[x1][i] = d[x1][i-1];
            cnt = min(cnt, d[x1][i]);
        }
        d[x1][y1+1] = temp;
        answer.push_back(cnt);
    }
    return answer;
}
