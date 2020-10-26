#include<cstdio>
#include<iostream>
#include<algorithm>
using namespace std;

int main(){
	string s;
	cin >> s;
	
	int a[2] = {0,};
	char c = s[0];
	a[c-'0']++;
	for(int i=0; i<s.length(); i++){
		if(c != s[i]){
			c = s[i];
			a[c-'0']++;
		}
	}
	
	printf("%d", min(a[0],a[1])); 
}

