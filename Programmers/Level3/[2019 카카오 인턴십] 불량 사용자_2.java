import java.util.*;

class Solution {
	List<String>[] bannedList;
	Set<String> result = new HashSet<>();

	public int solution(String[] user_id, String[] banned_id) {
		bannedList = new List[banned_id.length];

		for (int i=0; i< banned_id.length; i++) {
			bannedList[i] = new ArrayList<>();
			for (String user : user_id) {
				checkBannedId(i, banned_id[i], user);
			}
		}

		permutation(0, new HashSet<String>());
		return result.size();
	}

	private void permutation(int cnt, Set<String> visited) {
		if(cnt == bannedList.length) {
			List<String> visitList = new ArrayList<>(visited);
			visitList.sort(Comparator.naturalOrder());
			StringBuilder sb = new StringBuilder();

			for (String id : visitList) {
				sb.append(id).append(" ");
			}
			result.add(sb.toString());
			return;
		}

		for (int i = 0; i < bannedList[cnt].size(); i++) {
			String id = bannedList[cnt].get(i);
			if(visited.contains(id)) continue;

			visited.add(id);
			permutation(cnt+1, visited);
			visited.remove(id);
		}
	}

	private void checkBannedId(int idx, String banned, String user) {
		if(banned.length() != user.length()) return;
		int len = banned.length();

		for (int i = 0; i < len; i++) {
			if(banned.charAt(i) == '*') {
				continue;
			}

			if(banned.charAt(i) != user.charAt(i)) {
				return;
			}
			/*
			frodo, fradi
			frodo, crodo
			abc123, frodoc
			abc123, frodoc

			- frodo, crodo, abc123, frodoc
			- fradi, frodo, abc123, frodoc
			- fradi, crodo, abc123, frodoc
			 */

		}
		bannedList[idx].add(user);
	}
}