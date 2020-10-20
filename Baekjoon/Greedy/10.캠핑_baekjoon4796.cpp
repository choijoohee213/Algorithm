#include <cstdio>
#include <vector>

using namespace std;

int main(){
	int i=1;
	while(1){
		int l,p,v;
		scanf("%d %d %d", &l,&p,&v);
		if(!(1<l && l<p && p<v)) break; 
	
		int result = (v/p) * l + min(l,v%p);
		printf("Case %d: %d\n", i , result);
		i++;
	}
}
