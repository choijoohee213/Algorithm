#include <bits/stdc++.h>
using namespace std;

vector<int> _plays;

bool compare1(pair<string,int>& a, pair<string,int>& b){
    return a.second > b.second;
}

bool compare2(int& a, int& b){
    return _plays[a] > _plays[b];
}

vector<int> solution(vector<string> genres, vector<int> plays) {
    vector<int> answer;
    unordered_map<string, int> playGenres; //장르 당 총 재생 횟수
    unordered_map<string ,vector<int>> playList; //장르 당 노래 목록
    
    _plays = plays;
    for(int i=0; i<genres.size(); i++){
        playGenres[genres[i]] += plays[i];
        playList[genres[i]].push_back(i);
    }
    
    vector<pair<string,int>> v1(playGenres.begin(), playGenres.end());
    sort(v1.begin(), v1.end(), compare1);
    
    for(int i=0; i<v1.size(); i++){
        vector<int> v2 = playList[v1[i].first];
        sort(v2.begin(), v2.end(), compare2);
        for(int j=0; j<2 && j<v2.size(); j++)
            answer.push_back(v2[j]);
    }
    return answer;
}
