#include <cstdio>
#include <algorithm>

using namespace std;

int main(){
	int n,l,tape=0, k=0;
	int d[1001];
	
	scanf("%d %d",&n,&l);
	for(int i=0; i<n; i++){
		scanf("%d", &d[i]);
	}
	
	sort(d, d+n);
		
	if(l==1) tape = n;
	while(k<n && l!=1){
		int max = l-1+d[k];
		tape++;
		while(1){
			if(k==n || max<d[k]) break;
			k++;
		}
	}
	
	printf("%d", tape);
}
