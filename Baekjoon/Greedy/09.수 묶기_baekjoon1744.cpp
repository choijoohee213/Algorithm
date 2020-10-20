#include <cstdio>
#include <vector>
#include <algorithm>
using namespace std;

int main(){
	int n, sum=0, left=0, right;
	vector<int> v;
	scanf("%d", &n);
	
	for(int i=0; i<n; i++){
		int a;
		scanf("%d", &a);
		v.push_back(a);
	}
	
	right = n-1;
	sort(v.begin(), v.end());
	
	for(; left<right; left+=2){
		if(v[left]<1 && v[left+1]<1)
			sum += v[left]*v[left+1];
		else break;
	}
	
	for(; right>0; right-=2){		
		if(v[right]>1 && v[right-1]>1)
			sum += v[right]*v[right-1];
		else break;
	}
	
	for(; left<=right; left++)
		sum += v[left];
		
	printf("%d", sum);
	return 0;
}
