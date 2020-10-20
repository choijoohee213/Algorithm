#include <cstdio>

int main(){
	int t;
	int n[3] = {0,};
	
	scanf("%d", &t);
	
	if(t % 10) printf("-1");
	else{
		n[0] += t/300;
		t %= 300;
		n[1] += t/60;
		t %= 60;
		n[2] += t/10;
		t %= 10;
		
		printf("%d %d %d", n[0],n[1],n[2]);
	}
	return 0;
}
