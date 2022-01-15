import java.util.*;

class Solution {
	public int[] solution(String[] id_list, String[] report, int k) {
		int[] answer = new int[id_list.length];
		Set<String> reportSet = new HashSet<>();
		HashMap<String, Integer> reportCnt = new HashMap<>();
		HashMap<String, List<String>> reportList = new HashMap<>();

		for(String s : report) {
			if(reportSet.contains(s)) continue;
			reportSet.add(s);
			String a = s.split(" ")[0];
			String b = s.split(" ")[1];
			reportCnt.put(b, reportCnt.getOrDefault(b, 0) + 1);
			List<String> newList = reportList.getOrDefault(a, new ArrayList<>());
			if(!newList.contains(b)) newList.add(b);
			reportList.put(a, newList);
		}

		for(int i=0; i< id_list.length; i++) {
			if(!reportList.containsKey(id_list[i])) {
				answer[i] = 0;
				continue;
			}
			int cnt = 0;
			for(String v : reportList.get(id_list[i])) {
				if(reportCnt.getOrDefault(v,0) >= k) {
					cnt++;
				}
			}
			answer[i] = cnt;
		}
		return answer;
	}
}