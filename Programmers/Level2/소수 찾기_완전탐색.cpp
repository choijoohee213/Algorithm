//https://codingwell.tistory.com/32?category=917768

#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(string numbers) {
    int answer = 0, max = 0;
    int d[9999999] = {0,};
    vector<int> n(10,0);
    
    sort(numbers.begin(), numbers.end(), greater<>());
    max = stoi(numbers);
    
    for(int i=2; i<=max; i++){
        if(d[i] == 1) continue;
        for(int j=i+i; j<=max; j+=i){
            d[j] = 1;
        }
    }
    
    for(int i=0; i<numbers.size(); i++){
        n[numbers[i]-'0']++;
    }
    
    for(int i=2; i<=max; i++){
        if(d[i] != 0) continue;
        string a = to_string(i);
        vector<int> nn = n;
        for(int j=0; j<a.size(); j++){
            if(nn[a[j]-'0'] == 0) break;
            nn[a[j]-'0']--;
            if(j == a.size()-1) answer++;
        }
    }
    
    return answer;
}
