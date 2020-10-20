#include <cstdio>
#include <vector>
#include <queue>
#include <algorithm>
using namespace std;

int main(){
	int n,k;
	long long int sum=0;
	vector<pair<int,int>> v;
	int d[300001];
	priority_queue<int> pq;
	
	scanf("%d %d", &n, &k);
	for(int i=0; i<n; i++){
		int x,y;
		scanf("%d %d", &x, &y);
		v.push_back(make_pair(x,y));
	}
	
	for(int i=0; i<k; i++){
		scanf("%d", &d[i]);
	}
	
	sort(v.begin(), v.end());
	sort(d, d+k);
	
	int i=0, j=0;
	while(i<k){
		while(j<n && d[i]>=v[j].first) {
			pq.push(v[j++].second);
		}
		if(!pq.empty()){
			sum+=pq.top();
			pq.pop();
		}
		i++;
	}		

	printf("%ld",sum);
}
