//https://codingwell.tistory.com/31?category=917768

#include <string>
#include <vector>
#include <algorithm>

using namespace std;

vector<string> solution(int n, vector<int> arr1, vector<int> arr2) {
    vector<string> answer;  
    
    for(int i=0; i<n; i++){
        int a = arr1[i], b = arr2[i];
        string str;
        for(int j=0; j<n-1; j++){
           ((a % 2) || (b % 2))? str += "#" : str += " ";
           a /= 2;
           b /= 2;
        }
        (a || b) ? str += "#" : str += " ";
        reverse(str.begin(), str.end());
        answer.push_back(str);
    }

    return answer;
}
