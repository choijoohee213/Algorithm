import java.time.*;
import java.util.*;

class Solution {
	static Comparator<String> comparator = String::compareTo;
	static HashMap<String, String> carIn = new HashMap<>();
	static Map<String, Long> parkingTime = new TreeMap<>(comparator);

	public static int[] solution(int[] fees, String[] records) {
		int[] answer;

		for (String record : records) {
			String[] info = record.split(" ");
			if (info[2].equals("IN")) {
				carIn.put(info[1], info[0]);
			} else if (info[2].equals("OUT")) {
				calParkingTime(info[1], info[0], true);
			}
		}

		for (String s : carIn.keySet()) {
			calParkingTime(s, "23:59", false);
		}

		answer = new int[parkingTime.keySet().size()];

		//주차요금 계산
		int i = 0;
		for (String key : parkingTime.keySet()) {
			Long totalTime = parkingTime.get(key);
			int totalFee = 0;
			totalFee += fees[1];
			if (totalTime > fees[0]) {
				if ((totalTime - fees[0]) % fees[2] != 0) {
					totalFee += ((totalTime - fees[0]) / fees[2] + 1) * fees[3];
				} else {
					totalFee += ((totalTime - fees[0]) / fees[2]) * fees[3];
				}
			}
			answer[i++] = totalFee;
		}
		return answer;
	}

	private static void calParkingTime(String key, String time, boolean flag) {
		String[] t1 = carIn.get(key).split(":");
		String[] t2 = time.split(":");
		LocalTime inTime = LocalTime.of(Integer.parseInt(t1[0]), Integer.parseInt(t1[1]));
		LocalTime outTime = LocalTime.of(Integer.parseInt(t2[0]), Integer.parseInt(t2[1]));
		parkingTime.put(key, parkingTime.getOrDefault(key, 0L)
			+ Duration.between(inTime, outTime).getSeconds() / 60L);
		if(flag) carIn.remove(key);
	}
}