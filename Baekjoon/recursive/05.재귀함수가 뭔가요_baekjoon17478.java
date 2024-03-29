import java.util.*;
import java.io.*;

public class Main {

	static int N;
	
	public static void main(String[] args) throws Exception{
		//System.setIn(new FileInputStream("res/input_bj_17478.txt"));
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		System.out.println("어느 한 컴퓨터공학과 학생이 유명한 교수님을 찾아가 물었다.");
		respond(0);
	}
	
	static void respond(int cnt) {
		if(cnt == N+1) return;
		String s = "";
		for(int i=1; i<=cnt*4; i++) {
			s += '_';
		}
		System.out.printf("%s\"재귀함수가 뭔가요?\"\n", s);
		if(cnt == N)
			System.out.printf("%s\"재귀함수는 자기 자신을 호출하는 함수라네\"\n", s);
		else {
			System.out.printf("%s\"잘 들어보게. 옛날옛날 한 산 꼭대기에 이세상 모든 지식을 통달한 선인이 있었어.\n" + 
				"%s마을 사람들은 모두 그 선인에게 수많은 질문을 했고, 모두 지혜롭게 대답해 주었지.\n" + 
				"%s그의 답은 대부분 옳았다고 하네. 그런데 어느 날, 그 선인에게 한 선비가 찾아와서 물었어.\"\n", s, s, s, s);
		}
		respond(cnt+1);
		System.out.printf("%s라고 답변하였지.\n", s);
	}
}
