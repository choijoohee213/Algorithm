#include <cstdio>
using namespace std;

int n, sum=0;
int d[101];

int main(){
	scanf("%d", &n);
	for(int i=0; i<n; i++){
		scanf("%d", &d[i]);
	}
	
	for(int i=n-2; i>=0; i--){
		if(d[i]>=d[i+1]){
			int a = d[i+1]-1;
			sum += d[i]-a;
			d[i] = a;
		}
	}
	
	printf("%d", sum);
}
