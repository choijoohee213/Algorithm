#include <cstdio>
using namespace std;

int n, i=1;
long long int sum=0, min;
long long int distance[100001];
long long int price[100001];

int main(){
	scanf("%d", &n);
	for(int i=0; i<n-1; i++){
		scanf("%lld", &distance[i]);
	}	
	for(int i=0; i<n; i++){
		scanf("%lld", &price[i]);
	}
	
	min = price[0];
	sum = price[0] * distance[0];

	while(i<n-1){
		if(min > price[i]) min = price[i];
		sum += min * distance[i];
		i++;
	}
	
	printf("%lld", sum);
}
