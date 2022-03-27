class Solution {
	int answer = 0;
	char[] alphabet = {'A','E','I','O','U'};
	boolean flag = false;
	public int solution(String word) {
		makeWord(0, "", word);
		return answer;
	}

	public void makeWord(int cnt, String cur, String target) {
		if(cur.equals(target)) {
			flag = true;
			return;
		}
		if(cnt == 5) {
			return;
		}

		for(int i=0; i<5; i++) {
			if(!flag) {
				makeWord(cnt+1, cur+alphabet[i], target);
				answer++;
			}
		}
	}
}