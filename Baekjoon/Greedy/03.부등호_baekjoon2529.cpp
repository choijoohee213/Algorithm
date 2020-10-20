#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;

int k;
vector<char> sign;
vector<int> res;

bool check(vector<char> &sign, vector<int> &res){
	for(int i=0; i<sign.size(); i++){
		if(sign[i]=='<'){
			if(res[i]>res[i+1]) return false;
		}
		else if(sign[i]=='>'){
			if(res[i]<res[i+1]) return false;
		}
	} 
	
	return true;
}

int main(){
	cin>>k;
	for(int i=0; i<k; i++){
		char x;
		cin>>x;
		sign.push_back(x);
	}
	
	for(int i=0; i<10; i++){
		res.push_back(i);
	}
	
	//내림차순 
	sort(res.begin(),res.end(),greater<int>());
	
	do{
		if(check(sign,res)==true){
			for(int i=0; i<k+1; i++){
				cout<<res[i];
			}
			cout<<endl;
			break;
		}
	}while(prev_permutation(res.begin(),res.end()));
	
	//오름차순 
	sort(res.begin(),res.end()); 
	
	do{
		if(check(sign,res)==true){
			for(int i=0; i<k+1; i++){
				cout<<res[i];
			};
			break;
		}
	}while(next_permutation(res.begin(),res.end()));
	
	getchar();
	getchar();
	return 0;
}
