#include<cstdio>
#include<vector>
#include<algorithm>
using namespace std;

vector<pair<int,int> > v;

bool compare(pair<int,int> a, pair<int,int> b){
	if(a.second == b.second){
		return a.first < b.first;
	}
	else return a.second < b.second;
}


int main(){
	int t;
	scanf("%d", &t);
	
	while(t--){
		int n, m, count = 0;
		bool d[1001] = {false};
		v.clear();
	
		scanf("%d %d", &n, &m);


		for(int i=0; i<m; i++){
			int a,b;
			scanf("%d %d", &a, &b);
			v.push_back(make_pair(a,b));	
		}
		
		sort(v.begin(), v.end(), compare);

		for(int i=0; i<m; i++){
			for(int j=v[i].first; j<=v[i].second; j++){
				if(!d[j]){
					d[j]=true;
					count++;
					break;
				}
			}
		}
		printf("%d\n",count);
	}	
}
