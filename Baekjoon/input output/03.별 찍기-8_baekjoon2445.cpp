#include<bits/stdc++.h>
using namespace std;

int main(){
  int n;
  cin>>n;
  
  for(int i=1; i<=n; i++){
    int blank = 2*n - 2*i;
    for(int j=0; j<i; j++) cout<<"*";
    for(int j=0; j<blank; j++) cout<<" ";
    for(int j=0; j<i; j++) cout<<"*";
    cout<<endl;
  }
  
  for(int i=n-1; i>=1; i--){
    int blank = 2*n - 2*i;
    for(int j=0; j<i; j++) cout<<"*";
    for(int j=0; j<blank; j++) cout<<" ";
    for(int j=0; j<i; j++) cout<<"*";
    cout<<endl;
  }
}
