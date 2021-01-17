#include <vector>
#include <algorithm>

using namespace std;

int solution(vector<vector<int>> board)
{
    int answer = 0, x = board.size(), y = board[0].size();
    
    if(x<=1 || y<=1) return 1;
    for(int i=1; i<x; i++){
        for(int j=1; j<y; j++){
            if(board[i][j] == 1){
                board[i][j] = min(board[i-1][j], 
                    min(board[i][j-1], board[i-1][j-1])) + 1;
                answer = max(board[i][j],answer);
            }
        }
    }
    return answer * answer;
}
