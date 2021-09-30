#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int n,m;
  cin>>n;
  vector<int> v(n+1);
  
  for(int i=1; i<=n; i++)
    cin>>v[i];
  sort(v.begin(), v.end());
  
  cin>>m;
  for(int i=0; i<m; i++){
    int x, left=1, right=n, result=-1;
    cin>>x;

    while(left <= right){
      int mid = (left + right) / 2;
      if(v[mid] < x)
        left = mid+1;
      else if(v[mid] > x)
        right = mid-1;
      else {
        result = mid;
        break;
      }
    }
    if(result != -1) cout<<1<<'\n';
    else cout<<0<<'\n';
  }
}
