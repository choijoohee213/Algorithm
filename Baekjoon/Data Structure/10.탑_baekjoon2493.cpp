#include<bits/stdc++.h>
using namespace std;

int main(){
	ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    int n,x;
	stack<pair<int,int>> st;
	cin>>n;

    for(int i=1; i<=n; i++){
        cin>>x;
        int index = 0;

        while(!st.empty()){
            if(st.top().first < x) 
            	st.pop();
            else { 
            	index = st.top().second;
                break;
            }
        }
        cout<<index<<" "; 
        st.push({x, i});
    }	
}