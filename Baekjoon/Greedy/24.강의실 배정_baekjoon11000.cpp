#include<cstdio>
#include<vector>
#include<queue>
#include<algorithm>
using namespace std;

bool compare(pair<int, int> a, pair<int, int> b){
	if(a.first == b.first) return a.second < b.second;
	else return a.first < b.first;
}

int main(){
	int n;
	vector<pair<int,int> > v;
	priority_queue<int, vector<int>, greater<int> > pq;
	scanf("%d", &n);
	
	for(int i=0; i<n; i++){
		int a,b;
		scanf("%d %d", &a,&b);
		v.push_back(make_pair(a,b));	
	}
	
	sort(v.begin(), v.end(), compare);

	pq.push(v[0].second);
	
	for(int i=1; i<n; i++){
		if(pq.top() <= v[i].first)
			pq.pop();
		pq.push(v[i].second);
	}
	
	printf("%d", pq.size());
}
