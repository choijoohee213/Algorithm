using namespace std;

int solution(int n) {
    int answer = 0, i = 1, num = 1, sum = 0;
    while(i<n){
        if(sum + i == n || sum + i > n) {
            if(sum+i == n) answer++;
            num++;
            i = num;
            sum = 0;
        }
        sum += i;
        i++;
    }
    return answer+1;
}
