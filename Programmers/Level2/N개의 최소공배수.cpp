//https://codingwell.tistory.com/

#include <vector>
using namespace std;

int gcd(int a, int b)
{
	int c;
	while (b != 0)
	{
		c = a % b;
		a = b;
		b = c;
	}
	return a;
}

int lcm(int a, int b)
{
    return a * b / gcd(a, b);
}

int solution(vector<int> arr) {
    int n = arr[0];
    
    for(int i=1; i<arr.size(); i++)
        n = lcm(n, arr[i]);
    return n;
}
