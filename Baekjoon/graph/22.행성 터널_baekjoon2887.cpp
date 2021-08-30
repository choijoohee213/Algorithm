#include <bits/stdc++.h>
using namespace std;
#define INF 987654321

int parent[100001];

int getParent(int x){
  if(x == parent[x]) return x;
  else return parent[x] = getParent(parent[x]);
}

bool sameParent(int a, int b){
  return getParent(a) == getParent(b);
}

void Union(int a, int b){
  a = getParent(a);
  b = getParent(b);
  if(a<b) parent[b] = a;
  else if(a>b) parent[a] = b;
}

int main() {
	ios::sync_with_stdio(0);
	cin.tie(0);
	cout.tie(0);

	int n;
	long long result=0;
	cin >>n;

	vector<pair<int, int>> nodeX;
	vector<pair<int, int>> nodeY;
	vector<pair<int, int>> nodeZ;

	vector<pair<long long, pair<int, int>>> d;
	
	for (int i = 0; i < n; i++) {
		int a, b, c;
		cin >> a >> b >> c;
		nodeX.push_back({a,i});
		nodeY.push_back({b,i});
		nodeZ.push_back({c,i});
	}
	
	sort(nodeX.begin(), nodeX.end());
	sort(nodeY.begin(), nodeY.end());
	sort(nodeZ.begin(), nodeZ.end());

	for(int i=0; i<n-1; i++){
	  d.push_back({abs(nodeX[i].first - nodeX[i+1].first), {nodeX[i].second, nodeX[i+1].second}});
	  d.push_back({abs(nodeY[i].first - nodeY[i+1].first), {nodeY[i].second, nodeY[i+1].second}});
	  d.push_back({abs(nodeZ[i].first - nodeZ[i+1].first), {nodeZ[i].second, nodeZ[i+1].second}});
  }
	
	for(int i=0; i<n; i++) parent[i] = i;
	
	sort(d.begin(), d.end());
	int cnt = 0;
	
	for(int i=0; i<d.size(); i++){
	  int a = d[i].second.first;
	  int b = d[i].second.second;
	  long long c = d[i].first;
	  
	  if(!sameParent(a,b)){
	    Union(a,b);
	    cnt++;
	    result += c;
	    if(cnt == n-1) break;
	  }
	}
	cout<<result;
}
