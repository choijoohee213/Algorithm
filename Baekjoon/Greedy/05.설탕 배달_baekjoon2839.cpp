#include <iostream>

using namespace std;

int main(){
	int n;
	cin >> n;

	for(int i=n/5; i>=0; i--){
		for(int j=0; j<=n/3; j++){
			int sum = 5*i + 3*j;
			if(sum == n){
				cout << i+j;
				return 0;
			}
			else if(sum > n) break;
		}
	}
	
	cout << -1;
	return 0;
}
