using namespace std;

int binary(int n){
    int count = 0;
    while(n){
        count += n % 2;
        n /= 2;
    }
    return count;
}

int solution(int n) {
    int count = binary(n);
    while(++n){
        if(count == binary(n)) return n;
    }
}
