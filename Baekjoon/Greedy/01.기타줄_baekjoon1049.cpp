//6�� ��Ű��, 1���Ǵ±��̻����� ����
#include<stdio.h>
#include <iostream>
using namespace std;
int n,m,t,x,y;

int main(){
	cin>>n>>m;
	int set_min=1000,one_min=1000,price_min=0;
	for(int i=0; i<m; i++){
		cin>>x>>y;
		set_min=min(x,set_min);
		one_min=min(y,one_min);
	}
	
	if(n<6){
		price_min=min(one_min*n,set_min);
	}
	else{
		set_min=min(one_min*6,set_min);
		price_min+=(n/6)*set_min;
		price_min+=min((n%6)*one_min,set_min);
	}

	cout<<price_min;
	return 0;
}
