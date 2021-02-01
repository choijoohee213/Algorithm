#include <vector>
using namespace std;

int solution(vector<vector<int> > land)
{
    int answer = 0, maxValue = 0;
    for(int i=land.size()-2; i>=0; i--){
        for(int j=0; j<4; j++){
            for(int k=0; k<4; k++){
                if(j!=k && maxValue < land[i+1][k])
                    maxValue = land[i+1][k];
            }
            land[i][j] += maxValue;
            maxValue = 0;
        }
    }
    
    for(int i=0; i<4; i++)
        if(maxValue < land[0][i]) maxValue = land[0][i];
    
    return maxValue;
}
