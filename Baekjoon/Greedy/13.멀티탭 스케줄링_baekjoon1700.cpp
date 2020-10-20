#include <cstdio>
#include <vector>

using namespace std;

int n,k,count=0;
int d[102];
vector<int> v;

int main(){
	scanf("%d %d", &n, &k);
	
	for(int i=0; i<k; i++){
		scanf("%d", &d[i]);
	}
		
	for(int i=0; i<k; i++){
		bool change = true;
		for(int j=0; j<v.size(); j++){
			if(v[j]==d[i]){
				change = false;
				break;
			} 
		} 
		
		if(!change) continue;
		if(v.size()<n){
			v.push_back(d[i]);
			continue;
		}
		
		int max=0, index=0;
		for(int x=0; x<v.size(); x++){
			int z = 1e9;
			for(int y=i+1; y<k; y++){
				if(v[x]==d[y]){
					z=y;
					break;		
				}
			}
			
			if(max<z) {
				max = z;
				index = x;
			}
		}
		
		v[index] = d[i];
		count++;	
	}
	
	printf("%d", count);
}
