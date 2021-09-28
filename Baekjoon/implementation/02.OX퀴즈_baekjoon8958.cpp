 #include<bits/stdc++.h>
 using namespace std;
 
 int main(){
    ios::sync_with_stdio(0);
    cin.tie(0); cout.tie(0);
    
    int t;
    string s;
    
    cin>>t;
    while(t--){
      int sum = 0;
      int index = 1;
      cin>>s;
      for(int i=0; i<s.length(); i++){
        if(s[i] == 'O') sum += index++;
        else if(s[i] == 'X') index = 1;
      }
      cout<<sum<<endl;
    }
 } 
