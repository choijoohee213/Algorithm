using namespace std;

long long solution(int w,int h) {
    long long answer = 1;
    answer = (long long)w * (long long)h - (w+h);
    int c;
    while(h != 0){
        c = w % h;
        w = h;
        h = c;
    }
    answer += w;
    return answer;
}
