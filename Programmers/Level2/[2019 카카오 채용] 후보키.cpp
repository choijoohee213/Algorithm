#include <string>
#include <vector>
#include <unordered_map>
#include <math.h>
#include <algorithm>

using namespace std;

int solution(vector<vector<string>> relation) {
    int answer = 1, rSize = relation.size(), cSize = relation[0].size(), m = pow(2, cSize);
    vector<vector<string>> v(cSize);
    vector<string> vv;
    vector<string> keys;
    unordered_map<string,int> cKeys;

    //비트 연산
    for (int i = 1; i < m; i++) {
        int count = -1;
        string s = "";
        int a = i;
        for (int j = 0; j < cSize; j++) {
            if (a <= 0 || a % 2 == 0) s = '0' + s;
            else {
                s = '1' + s;
                count++;
            }
            a /= 2;
        }
        v[count].push_back(s);
    }

    for (int i = 0; i < v.size(); i++) {
        for (int j = 0; j < v[i].size(); j++)
            vv.push_back(v[i][j]);
    }

    //유일성 체크
    for (int i = 0; i < vv.size(); i++) {
        string str = vv[i], ss = "";
        bool isCkey = true;
        unordered_map<string, int> uom;

        for (int j = 0; j < rSize; j++) {
            ss = "";
            for (int k = 0; k < cSize; k++) {
                if (str[k] == '1'){
                    ss += relation[j][k];
                }
            }
            if (uom[ss]++) {
                isCkey = false;
                break;
            }
        }
        if (isCkey) {
            ss = "";
            for (int k = 0; k < cSize; k++) {
                if (str[k] == '0') continue;
                ss += to_string(k);
            }
            if(ss!="") keys.push_back(ss);
        }
    }
    
    //최소성 체크
    cKeys[keys[0]]=1;
    for(int i=1; i<keys.size(); i++){
        string s = keys[i];
        bool have = true;
        for(pair<string,int> p : cKeys){
            for(int j=0; j<p.first.size(); j++){
                auto it = find(s.begin(),s.end(),p.first[j]);
                if(it == s.end()){ //없다면
                    have = false;
                    break;
                }
                else have = true;
            }
            if(have) break;
        }
        if(!have){
            answer++;
            cKeys[s]++;
        }
    }

    return answer;
}
