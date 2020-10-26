#include<cstdio>
#include<queue>
using namespace std;

int main(){
	int n, m;
	long long int sum=0;
	priority_queue<long long int, vector<long long int>, greater<long long int> > pq;
	scanf("%d %d", &n, &m);
	
	for(int i=0; i<n; i++){
		int a;
		scanf("%d", &a);
		pq.push(a);
	}
	
	for(int i=0; i<m; i++){
		long long int x = pq.top();
		pq.pop();
		long long int y = pq.top();
		pq.pop();
		
		pq.push(x+y);
		pq.push(x+y);
	}
	
	for(int i=0; i<n; i++){
		sum += pq.top();
		pq.pop();
	}
	
	printf("%ld", sum);
}
