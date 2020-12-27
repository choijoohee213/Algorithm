#include <vector>
#include <algorithm>
#include <iostream>

using namespace std;

int d[502] = {0,};

bool compare(pair<int, double>& a, pair<int, double>& b){
    if(a.second == b.second)
        return a.first < b.first;
    return a.second > b.second;
}

vector<int> solution(int N, vector<int> stages) {
    vector<int> answer;
    vector<pair<int, double> > v;
    
    for(int i=0; i<stages.size(); i++)
        d[stages[i]]++;     
    
    int num = d[N+1];
    double p = 0;
    for(int i=N; i>=1; i--){
        num += d[i];
        if(num == 0 && d[i] == 0) p = 0;
        else p = (double)d[i] / (double)num;
        v.push_back(make_pair(i,p));
    }
    
    sort(v.begin(), v.end(), compare);
    
    for(int i=0; i<v.size(); i++)
        answer.push_back(v[i].first);
    
    return answer;
}
