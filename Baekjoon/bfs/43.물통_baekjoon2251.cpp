#include<bits/stdc++.h>
using namespace std;

int check(int& x, int& y) {
  if(x == 0){
    if(y == 1) return 2;
    else return 1;
  }
  else if(x == 1){
    if(y == 0) return 2;
    else return 0;
  }
  else if(y == 1) return 0;
  else return 1;
}

int main(){
  ios::sync_with_stdio(0);
  cin.tie(0); cout.tie(0);
  
  int a,b,c; 
  cin>>a>>b>>c;
  bool visit[202][202] = {false,};
  vector<int> v;
  queue<pair<pair<int,int>,int>> q;
  q.push({{0,0},c});
  
  while(!q.empty()){
    int water[3];
    water[0] = q.front().first.first;
    water[1] = q.front().first.second;
    water[2] = q.front().second;
    q.pop();
    
    if(visit[water[0]][water[1]]) continue;
    visit[water[0]][water[1]] = true;
    
    if(water[0] == 0) v.push_back(water[2]);
    
    // a -> b
		if (water[0] + water[1] > b)
			q.push({{water[0] + water[1] - b, b}, water[2]});
		else
			q.push({{0, water[0] + water[1]}, water[2]});
		
		// a -> c
		if (water[0] + water[2] > c)
			q.push({{water[0] + water[2] - c, water[1]}, c});
		else
			q.push({{0, water[1]}, water[0] + water[2]});
			
		// b -> a
		if (water[1] + water[0] > a)
			q.push({{a, water[1] + water[0] - a}, water[2]});
		else
			q.push({{water[1] + water[0], 0}, water[2]});
			
		// b -> c
		if (water[1] + water[2] > c)
			q.push({{water[0], water[1] + water[2] - c}, c});
		else
			q.push({{water[0], 0}, water[1] + water[2]});
			
		// c -> a
		if (water[2] + water[0] > a)
			q.push({{a, water[1]}, water[2] + water[0] - a});
		else
			q.push({{water[2] + water[0], water[1]}, 0});
			
		// c -> b
		if (water[2] + water[1] > b)
			q.push({{water[0], b}, water[2] + water[1] - b});
		else
			q.push({{water[0], water[2] + water[1]}, 0});
  }
  
  sort(v.begin(), v.end());
  for(int i=0; i<v.size(); i++)
    cout<<v[i]<<" ";
}
