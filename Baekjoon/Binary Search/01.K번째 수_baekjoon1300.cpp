#include<bits/stdc++.h>
using namespace std;

int main(){
  long long n,k;
  cin>>n>>k;
  
  long long f = 1, e = n*n, result;
  while(f<=e){
    long long m = (f+e) / 2;
    long long sum = 0;
    for(long long i = 1; i<n+1; i++){
      sum += min(n, m / i);]
    }
    if(sum<k) f = m -1;
    else {
      result = m;
      e = m -1;
    }
  }
  cout<<result;
}
