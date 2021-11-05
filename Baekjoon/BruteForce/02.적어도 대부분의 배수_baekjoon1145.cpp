#include<bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    int index = 101;
    int arr[5];

    for(int i=0; i<5; i++){
        cin>>arr[i];
        index = min(index, arr[i]);
    }

    while(true){
        int cnt = 0;
        for(int i=0; i<5; i++){
            if(index % arr[i] == 0){
                cnt++;
                if(cnt>=3){
                    cout<<index;
                    return 0;
                }
            }
        }
        index++;
    }
}