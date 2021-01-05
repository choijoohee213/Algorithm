#include <vector>

using namespace std;

vector<int> solution(int n) {
    vector<int> answer;
    int m = 0, num = 1,x = 0, y = 0;
    int d[1000][1000] = {0,};

    for(int i=0; i<n; i++, m++){
        if(m == 0){
            for(int j=i; j<n; j++)
                d[x++][y] = num++;
            x--;
            y++;
        }
        
        else if(m == 1) {
            for(int j=i; j<n; j++)
                d[x][y++] = num++;
            x--;
            y-=2;   
        }
        
        else{
            for(int j=i; j<n; j++)
                d[x--][y--] = num++;
            x+=2;
            y++;
            m = -1;
        }
    }
    
    for(int i=0; i<n; i++){
        for(int j=0; j<=i; j++){
            answer.push_back(d[i][j]);   
        }
    }
    return answer;
}
