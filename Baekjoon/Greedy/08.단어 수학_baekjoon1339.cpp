#include <cstdio>
#include <cstring>
#include <math.h>
#include <algorithm>
#include <functional>

using namespace std;

int main(){
	int n, num = 9;
	int sum = 0;
	int d[26] = {0,};
	char words[11][9];
	
	scanf("%d", &n);
	
	for(int i=0; i<n; i++){
		scanf("%s", words[i]);	 

		int length = strlen(words[i]);
		for(int j=0; j<length; j++){
			int x = words[i][j] - 65;
			d[x] += pow(10,length-j-1);
		}
	}
	
	sort(d, d+26, greater<int>());
	
	for(int i=0; i<10; i++){
		sum += d[i] * num; 
		num--;
	}
    printf("%d", sum);
}
