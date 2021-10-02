#include<bits/stdc++.h>
using namespace std;

int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);

    int n,m,cnt=1;
    cin>>n>>m;
    vector<int> arr[257];
    vector<int> indegree(n+1, 0);
    vector<int> result(n+1, 0);
    vector<int> order(n+1, 0);
    set<int> nums;

    for(int i=0; i<m; i++){
        int a,b;
        cin>>a>>b;
        arr[a].push_back(b);
        nums.insert(a);
        nums.insert(b);
        indegree[b]++;
    }
    if(nums.size() != n){
        cout<<-1;
        return 0;
    }

    queue<int> q;
    for(int i=1; i<=n; i++){
        if(indegree[i] == 0) {
            q.push(i);
            result[i] = 1;
            cnt++;
        }
    }
    order[1] = cnt-1;

    while(!q.empty()){
        int x = q.front(); q.pop();
        int count = 0;

        for(int i=0; i<arr[x].size(); i++){
            int nx = arr[x][i];
            indegree[nx]--;
            if(indegree[nx] == 0){ 
                q.push(nx);
                result[nx] = cnt;
                count++;
            }
        }
        
        if(count != 0) order[cnt] = cnt + count - 1;
        cnt += count;
    }

    for(int i=1; i<=n; i++){
        if(indegree[i]>0 || result[i] == 0 || order[result[i]] == 0){
            cout<<-1;
            return 0;
        }
        cout<<result[i]<<" "<<order[result[i]]<<endl;
    }
}