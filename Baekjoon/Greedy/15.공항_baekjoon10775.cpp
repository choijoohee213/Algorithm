#include <cstdio>
using namespace std;

int g,p, count=0;
int d[100001];

int find(int x){
	if(d[x]==x) return x;
	return d[x]=find(d[x]);
}

void merge(int x, int y){
	x = find(x);
	y = find(y);
	d[x] = y;
}

int main(){
	scanf("%d %d", &g, &p);
	for(int i=0; i<=g; i++){
		d[i] = i;
	}
	
	int *gi = new int[p];
	for(int i=0; i<p; i++){
		scanf("%d", &gi[i]);
	}

	for(int i=0; i<p; i++){		
		int docking = find(gi[i]);
		if(docking!=0){
			count++;
			merge(docking, docking-1);		
		}
		else break;
	}
	
	delete[] gi;
	printf("%d", count);
}
