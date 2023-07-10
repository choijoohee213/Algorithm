class Solution {
	public String solution(String play_time, String adv_time, String[] logs) {
		int playTime = convert(play_time);
		int advTime = convert(adv_time);
		long[] arr = new long[playTime+1];

		for (String log : logs) {
			String[] splited = log.split("-");
			int startTime = convert(splited[0]);
			int endTime = convert(splited[1]);
			arr[startTime]++;
			arr[endTime]--;
		}

		for (int i = 1; i <= playTime; i++) {
			arr[i] += arr[i-1];
		}

		long result = 0, prev = 0;
		for (int i = 0; i < advTime; i++) {
			result += arr[i];
		}

		prev = result;
		int resultStart = 0;
		for (int i = advTime; i < playTime; i++) {
			long sum = prev;
			sum -= arr[i-advTime];
			sum += arr[i];

			if(sum > result) {
				result = sum;
				resultStart = i-advTime+1;
			}
			prev = sum;
		}

		return String.format("%02d:%02d:%02d", resultStart/(3600), resultStart/60%60, resultStart%60);
	}

	int convert(String time) {
		String[] splited = time.split(":");
		return 3600 * Integer.parseInt(splited[0]) + 60 * Integer.parseInt(splited[1])
				+ Integer.parseInt(splited[2]);
	}
}