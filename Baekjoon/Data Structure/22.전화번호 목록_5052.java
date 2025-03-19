import java.io.*;
import java.util.*;

class Main {
    static Set<String> hashSet;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while(t-- > 0) {
            int n = Integer.parseInt(br.readLine());
            hashSet = new HashSet<>();
            boolean isConsistent = true;
            String[] strArr = new String[n];

            for (int i = 0; i < n; i++) {
                strArr[i] = br.readLine();
                hashSet.add(strArr[i]);
            }

            //Arrays.sort(strArr, Comparator.comparingInt(String::length));

            for (int i = 0; i < n; i++) {
                boolean success = isInPhoneNumberList(strArr[i]);

                if(!success) {
                    isConsistent = false;
                    break;
                }
            }

            if(isConsistent) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }

    }

    static boolean isInPhoneNumberList(String str) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < str.length()-1; j++) {
            sb.append(str.charAt(j));
            if (hashSet.contains(sb.toString())) {
                return false;
            }
        }
        return true;
    }
}