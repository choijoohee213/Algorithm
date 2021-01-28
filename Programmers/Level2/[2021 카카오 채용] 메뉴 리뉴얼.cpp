#include <string>
#include <vector>
#include <unordered_map>
#include <algorithm>
#include <math.h>

using namespace std;

unordered_map<string,int> uom;
int d[11] = {0,};

void check(int n, string& s, vector<int>& course ){
    string str = "";
    for(int i=s.size()-1; i>=0; i--){
        if(n <=0 ) break;
        if(n % 2) str = s[i] + str;
        n /= 2;
    }
    
    int sSize = str.size();
    for(int i=0; i<course.size(); i++){
        if(course[i] == sSize){
            int count = uom[str]++;
            d[sSize] = max(d[sSize],count+1);
            break;
        }
        else if(course[i] > sSize) break;
    } 
}

vector<string> solution(vector<string> orders, vector<int> course) {
    vector<string> answer;
    int maxN = course[course.size()-1];
    
    for(int i=0; i<orders.size(); i++){
        string s = orders[i];
        int n = pow(2,s.size());

        sort(s.begin(),s.end());
        for(int j=3; j<n; j++) check(j,s,course);
    }
    
    for(pair<string, int> atom : uom) {
        string s = atom.first;
        int n = atom.second;
        if(d[s.size()] == n && d[s.size()] > 1) 
            answer.push_back(s);
    }
    sort(answer.begin(), answer.end());
    return answer;
}
