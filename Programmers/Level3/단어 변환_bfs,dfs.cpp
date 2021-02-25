#include <bits/stdc++.h>
using namespace std;

int wSize;
vector<string> cWords;

int convert(string word, string target, int sum, unordered_map<int, int> uom) {
    int answer = wSize + 2;
    if(word == target) return sum;
    for (int i = 0; i < cWords.size(); i++) {
        if (uom[i]) continue;
        int differ = 0;
        unordered_map<int, int> visited = uom;
        
        for (int j = 0; j <word.size(); j++) {
            if (word[j] != cWords[i][j]) differ++;
            if (differ > 1) break;
        }
        if (differ == 1) {
            visited[i] = 1;
            answer = min(answer, convert(cWords[i], target, sum + 1, visited));
        }
    }
    return answer;
}

int solution(string begin, string target, vector<string> words) {
    int answer = 0;
    if (find(words.begin(), words.end(), target) == words.end()) return 0;
    cWords = words;
    wSize = words.size();
    unordered_map<int, int> uom;
    
    answer = convert(begin, target, 0, uom);
    return answer;
}
