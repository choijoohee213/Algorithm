import java.text.*;
import java.util.*;

class Solution {
	public String solution(String m, String[] musicinfos) throws Exception {
		String answer = "(None)";
		int maxTime = 0;
		HashMap<String, String> hm = new HashMap<String, String>() {{
			put("A#", "G"); put("C#", "H"); put("D#", "I"); put("F#", "J"); put("G#", "K");
		}};
		SimpleDateFormat f = new SimpleDateFormat("HH:mm");

		for(String st : hm.keySet()) {
			m = m.replaceAll(st, hm.get(st));
		}

		for(String info : musicinfos) {
			String[] infos = info.split(",");
			long diff = (f.parse(infos[1]).getTime() - f.parse(infos[0]).getTime()) / 60000;
			for(String st : hm.keySet()) {
				infos[2] = infos[2].replaceAll(st, hm.get(st));
				infos[3] = infos[3].replaceAll(st, hm.get(st));
			}

			String melody = infos[3];
			int i = infos[3].length();
			if(i > diff) {
				i = 0;
				melody = melody.substring(0, (int)diff);
			}
			boolean have = false;
			while(i <= diff && melody.length() <= (3 * m.length())) {
				melody += melody;
				i += infos[3].length();
			}
			int len = (int)diff;
			if(len > melody.length()) len = melody.length();
			if(melody.substring(0,len).contains(m)) {
				have = true;
			}
			if(have && maxTime < diff) {
				maxTime = (int)diff;
				answer = infos[2];
			}
		}
		return answer;
	}
}