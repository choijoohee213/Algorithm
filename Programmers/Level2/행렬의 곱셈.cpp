#include <vector>

using namespace std;

vector<vector<int>> solution(vector<vector<int>> arr1, vector<vector<int>> arr2) {
    int sum = 0, m = arr1.size(), n = arr2[0].size();
    vector<vector<int>> answer(m, vector<int>(n));
    
    for(int i=0; i<m; i++){
        for(int j=0; j<n; j++){
            for(int k=0; k<arr1[i].size(); k++){
                sum += arr1[i][k] * arr2[k][j];
            }
            answer[i][j] = sum;
            sum = 0;
        }
    }   
    return answer;
}
