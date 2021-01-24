#include <string>
#include <vector>
#include <algorithm>

using namespace std;

int solution(int cacheSize, vector<string> cities) {
    if(cacheSize == 0) return 5 * cities.size();

    int answer = 5;
    string s = "";
    vector<string> v;
    vector<string>::iterator it;
    
    for(int i=0; i<cities.size(); i++){
        transform(cities[i].begin(), cities[i].end(), cities[i].begin(), 
              [](unsigned char c){ return toupper(c);});
    }
    v.push_back(cities[0]);
    
    for(int i=1; i<cities.size(); i++){
        s = cities[i];
        it = find(v.begin(), v.end(), s);
        if(it != v.end()){  //같은 값이 있으면
            answer += 1;
            v.erase(it);
            v.push_back(s);
        }
        else { //없으면
            answer += 5;
            if(v.size() == cacheSize) v.erase(v.begin());
            v.push_back(s);
        }
    }
    return answer;
}
