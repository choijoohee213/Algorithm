#include<bits/stdc++.h>
using namespace std;

int n, answer=0;
int arr[15];

int main(){
	ios::sync_with_stdio(0);
	cin.tie(0);
	cin>>n;
	for(int i=0; i<n; i++) arr[i] = i;
	do{
		//for(int i=0; i<n; i++) cout<<arr[i]<<' ';
		//cout<<endl;
		bool enable = true;
		for(int i=1; i<n; i++){
			int k = arr[i-1];
			if(arr[i]==k-1 || arr[i]==k+1){
				enable = false;
				break;
			}
		}
		if(enable) {
		answer++; 
		}

	}while(next_permutation(arr,arr+n));
	cout<<answer;
}
