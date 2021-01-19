#include <vector>
#include <unordered_map>

using namespace std;

int solution(vector<int> nums)
{
    int n = nums.size();
    unordered_map<int,int> uom;
    for(int i=0; i<n; i++) uom[nums[i]]++;
    
    return uom.size() > n/2 ? n/2 : uom.size();  
}
