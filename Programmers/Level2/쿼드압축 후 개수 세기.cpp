#include <vector>

using namespace std;

vector<int> answer(2);

void check(vector<vector<int>>& arr, int size, int x, int y){
    if(size == 1) {
        answer[arr[x][y]]++;
        return;
    }
    
    int num = arr[x][y];
    
    for(int i=x; i<x+size; i++){
        for(int j=y; j<y+size; j++){
            if(num != arr[i][j]){ 
                size /= 2;
                check(arr, size, x, y);
                check(arr, size, x+size, y);
                check(arr, size, x, y+size);
                check(arr, size, x+size, y+size);
                return;
            }
        }
    }
    answer[num]++;
}

vector<int> solution(vector<vector<int>> arr) {
    check(arr, arr.size(), 0,0);
    return answer;
}
