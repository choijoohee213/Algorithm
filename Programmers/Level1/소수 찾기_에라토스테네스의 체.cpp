using namespace std;

int solution(int n) {
    int answer = 0;
    int d[1000000] = {0,};
    
    for(int i=2; i<=n; i++){
        if(d[i] == 1) continue;
        for(int j=i+i; j<=n; j+=i){
            d[j] = 1;
        }
    }
    
    for(int i=2; i<=n; i++)
        if(d[i] != 1) answer++;
    
    return answer;
}
