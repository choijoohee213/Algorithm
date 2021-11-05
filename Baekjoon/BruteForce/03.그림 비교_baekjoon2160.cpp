#include<bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    int n,result = 36, a, b;
    cin>>n;
    vector<vector<string>> arr(n, vector<string>(5));

    for(int i=0; i<n*5; i++){
        string s;
        cin>>s;
        arr[i/5][i%5] = s;
    }

    for(int i=0; i<n; i++){
        for(int j=i+1; j<n; j++){
            int cnt = 0;
            for(int k=0; k<35; k++){
                if(arr[i][k/7][k%7] != arr[j][k/7][k%7])
                    cnt++;
            }
            if(result > cnt){
                result = cnt;
                a = i;
                b = j;
            }
        }
    }
    cout<<a+1<<' '<<b+1;
}