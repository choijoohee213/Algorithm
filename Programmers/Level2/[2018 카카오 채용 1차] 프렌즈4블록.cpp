#include <string>
#include <vector>

using namespace std;

int findSameBlock(vector<vector<char>>& blocks, vector<vector<int>>& d){
    int count = 0;
    
    //2*2블럭을 찾아 해당 인덱스를 벡터에 저장
    for(int i=1; i<blocks.size(); i++){
        for(int j=1; j<blocks[i].size(); j++){
            char c = blocks[i][j];
            if(c == 'a') continue;
            if(c == blocks[i-1][j-1] && c == blocks[i][j-1] && c == blocks[i-1][j]){
                d[i][j] = 1;
                d[i-1][j-1] = 1;
                d[i-1][j] = 1;
                d[i][j-1] = 1;
            }
        }
    }
    
    //벡터에 저장된 지워야할 블럭을 지우고 빈 공간은 a를 넣기
    for(int i=0; i<d.size(); i++){
        for(int j=d[i].size()-1; j>=0; j--){
            if(d[i][j]){
                blocks[i].erase(blocks[i].begin()+j, blocks[i].begin()+j+1);
                blocks[i].push_back('a');
                d[i][j] = 0;
                count++;
            }
        }
    }
    return count;
}

int solution(int m, int n, vector<string> board) {
    int answer = 0;
    int x = board.size(), y = board[0].size();
    vector<vector<int> > d(y, vector<int>(x));
    vector<vector<char> > blocks(y, vector<char>(x));

    for(int i=0; i<y; i++){
        for(int j=x-1; j>=0; j--){
            blocks[i][x-1-j] = board[j][i];
        }
    }

    while(true){
        int count = findSameBlock(blocks, d);
        if(count == 0) return answer;
        answer += count;
    }
}
