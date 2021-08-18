#include<bits/stdc++.h>
using namespace std;

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  long long n,k;
  cin>>n>>k;
  
  long long f = 1, e = n*n, result, m, sum;
  
  while(f<=e){
    m = (f+e) / 2;
    sum = 0;
    for(long long i = 1; i<n+1; i++)
      sum += min(n, m / i);  //mid보다 작은 j의 수(i * j <= mid)
    if(sum<k) 
      f = m + 1;
    else {
      result = m;
      e = m -1;
    }
  }
  cout<<result;
}
