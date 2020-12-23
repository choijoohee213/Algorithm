#include <stack>
#include <vector>

using namespace std;

stack<int> st;
int answer = 0;

void pushDoll(int doll){
    if(!st.empty() && st.top() == doll){
        st.pop();
        answer += 2;
        return;
    }
    st.push(doll);
}

int solution(vector<vector<int>> board, vector<int> moves) {
    for(int i=0; i<moves.size(); i++){
        int y = moves[i]-1;
        int doll = 0;
        
        for(int j=0; j<board.size(); j++){
            if(board[j][y] == 0) continue;
            doll = board[j][y];
            board[j][y] = 0;
            break;
        }
        
        if(doll != 0) pushDoll(doll);
    }
    return answer;
}
