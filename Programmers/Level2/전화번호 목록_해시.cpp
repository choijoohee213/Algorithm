//https://codingwell.tistory.com/34

#include <string>
#include <vector>

using namespace std;

bool solution(vector<string> phone_book) {
    string s = phone_book[0];
    int n = phone_book[0].size(), i = 0, j = 1;

    while(true) {
        if (i!=j && phone_book[j].size() >= n && s == phone_book[j].substr(0, n)) 
            return false;
        if (j == phone_book.size() - 1) {
            if(i+1 == phone_book.size()) break;
            i++;
            j = -1;
            s = phone_book[i];
            n = s.size();
        }
        j++;
    }
    return true;
}
