#include <vector>
#include <algorithm>
using namespace std;

vector<int> solution(vector<int> answers) {
    int case1[] = {1,2,3,4,5};
    int case2[] = {2,1,2,3,2,4,2,5};
    int case3[] = {3,3,1,1,2,2,4,4,5,5};

    vector<int> answer;
    int d[3] = {0,};

    for (int i = 0; i < answers.size(); i++) {
        int a = answers[i];
        if (case1[i % (sizeof(case1)/sizeof(case1[0]))] == a) d[0]++;
        if (case2[i % (sizeof(case2)/sizeof(case2[0]))] == a) d[1]++;
        if (case3[i % (sizeof(case3)/sizeof(case3[0]))] == a) d[2]++;
    }

    int maxValue = max(max(d[0], d[1]), d[2]);
    for (int i = 0; i < 3; i++) {
        if (maxValue == d[i]) {
            answer.push_back(i+1);
        }
    }
    return answer;
}
