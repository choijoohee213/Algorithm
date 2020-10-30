#include<stdio.h>

int main(){
	char s[1001];
	char ucpc[5] = "UCPC";
	scanf("%[^\n]s", &s);
	
	int i=0, j=0;
	while(s[i] != '\0' && j<4){
		if(s[i] == ucpc[j])
			j++;		
		i++;
	}
	
	j==4? printf("I love UCPC") : printf("I hate UCPC");
}
