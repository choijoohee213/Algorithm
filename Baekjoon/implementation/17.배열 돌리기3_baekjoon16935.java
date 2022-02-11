import java.util.*;
import java.io.*;

/*
 * 배열에 연산을 하는 문제
 * 1. 배열의 행, 열 크기와 연산의 개수를 입력받는다.
 * 2. 배열의 원소들을 입력받는다.
 * 3. 연산 개수만큼 연산을 입력받고 각 연산을 수행한다.
 * 4. 각 연산 종류마다 메서드를 생성하여 연산 수행
 * 5. 각 메서드는 연산을 수행한 후 결과 배열을 리턴 -> 기존 배열에 저장
 * 6. 최종 결과 배열 출력
 *
 */

class Main {
	static int n,m,r;
	static int[][] arr;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");

		n = Integer.parseInt(st.nextToken());  //배열의 행 크기 입력받기
		m = Integer.parseInt(st.nextToken());  //배열의 열 크기 입력받기
		r = Integer.parseInt(st.nextToken());  //연산의 개수 입력 받기

		//나중에 회전을 할 경우에 행, 열의 크기가 서로 바뀌기때문에 오류가 날 것을 대비하여 행, 열 크기 중 더 큰 값으로 배열을 선언
		int size = Math.max(n,m);
		arr = new int[size][size];

		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=0; j<m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken()); //배열의 원소를 입력받는다.
			}
		}
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<r; i++) {  //연산들을 입력받는다.
			int num = Integer.parseInt(st.nextToken());

			//입력받은 연산 종류에 따라 함수 호출
			//함수는 결과배열을 리턴하기 때문에 기존의 배열에 저장
			if(num == 1) arr = upsideDown();
			else if(num == 2) arr = ReverseLR();
			else if(num == 3) arr = RotateRight();
			else if(num == 4) arr = RotateLeft();
			else if(num == 5) arr = RotateSubarrayRight();
			else if(num == 6) arr = RotateSubarrayLeft();
		}

		//최종 결과 출력
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				sb.append(arr[i][j]).append(" ");
			}
			sb.append("\n");
		}
		System.out.print(sb);
	}

	//상하 반전
	static int[][] upsideDown() {
		int[][] result = new int[n][m];  //행과 열의 크기는 그대로 연산 결과를 넣을 배열 생성
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				//상하 반전이므로 열은 그대로
				//행이 0부터 증가함에 따라 결과 배열은 n-1부터 감소
				result[n-1-i][j] = arr[i][j];
			}
		}
		return result;
	}

	//좌우 반전
	static int[][] ReverseLR() {
		int[][] result = new int[n][m];  //행과 열의 크기는 그대로 연산 결과를 넣을 배열 생성
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				//좌우 반전이므로 행은 그대로
				//열이 0부터 증가함에 따라 결과 배열은 m-1부터 감소
				result[i][m-1-j] = arr[i][j];
			}
		}
		return result;
	}

	//오른쪽으로 90도 회전
	static int[][] RotateRight() {
		int[][] result = new int[m][n];  //배열을 회전시키기 때문에 행과 열의 크기가 서로 뒤바뀌게끔 결과 배열 생성
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				//열이 증가함에 따라 결과 배열의 행이 증가. 기존 배열의 열 == 결과 배열의 행
				//기존 배열의 행이 0부터 증가함에 따라 결과 배열의 열이 n-1부터 감소
				result[j][n-1-i] = arr[i][j];
			}
		}
		//n과 m을 swap
		int tmp = n;
		n = m;
		m = tmp;
		return result;
	}


	//왼쪽으로 90도 회전
	static int[][] RotateLeft() {
		int[][] result = new int[m][n];  //배열을 회전시키기 때문에 행과 열의 크기가 서로 뒤바뀌게끔 결과 배열 생성
		for(int i=0; i<n; i++) {
			for(int j=0; j<m; j++) {
				//기존 배열의 행 == 결과 배열의 열
				//기존 배열의 열이 0부터 증가함에 따라 결과 배열의 행이 m-1부터 감소
				result[m-1-j][i] = arr[i][j];
			}
		}
		//n과 m을 swap
		int tmp = n;
		n = m;
		m = tmp;
		return result;
	}

	//부분배열 오른쪽 회전
	static int[][] RotateSubarrayRight() {
		int[][] result = new int[n][m];  //기존 행과 열의 크기만큼 결과 배열 생성
		int a = 0, b = m/2;  //결과 배열의 행과 열 초깃값
		int x = 0, y = 0;  //기존 배열의 행과 열 초깃값

		for(int k=0; k<4; k++) {  //가로,세로 두번씩 나누기때문에 총 부분배열은 4개
			for(int i=0; i<n/2; i++) {  //맨 왼쪽위 인덱스를 기준으로 행은 n/2, 열은 m/2씩 돌기
				for(int j=0; j<m/2; j++) {
					result[a+i][b+j] = arr[x+i][y+j];
				}
			}
			a+=n/2;  //결과 배열의 기준 인덱스 행 n/2만큼 증가
			if(a>=n){  //행이 n 이상이 되었을 경우 행은 0으로, 열은 m/2만큼 감소
				a = 0;
				b -= m/2;
			}
			y+=m/2;  //원래 배열의 기준 인덱스 열 m/2만큼 증가
			if(y>=m) {  //열이 m 이상이 되었을 경우 열은 0으로, 행은 n/2만큼 증가
				y = 0;
				x+=n/2;
			}
		}
		return result;
	}

	//부분배열 왼쪽 회전
	static int[][] RotateSubarrayLeft() {
		int[][] result = new int[n][m]; //기존 행과 열의 크기만큼 결과 배열 생성
		int a=n/2, b=0; //결과 배열의 행과 열 초깃값
		int x = 0, y = 0; //기존 배열의 행과 열 초깃값

		for(int k=0; k<4; k++) {  //가로,세로 두번씩 나누기때문에 총 부분배열은 4개
			for(int i=0; i<n/2; i++) {  //맨 왼쪽위 인덱스를 기준으로 행은 n/2, 열은 m/2씩 돌기
				for(int j=0; j<m/2; j++) {
					result[a+i][b+j] = arr[x+i][y+j];
				}
			}
			a-=n/2;  //결과 배열의 기준 인덱스 행 n/2만큼 감소
			if(a<0){  //행이 0 미만이 되었을 경우 행은 n/2로, 열은 m/2만큼 증가
				a = n/2;
				b += m/2;
			}
			y+=m/2;  //원래 배열의 기준 인덱스 열 m/2만큼 증가
			if(y>=m) {  //열이 m 이상이 되었을 경우 열은 0으로, 행은 n/2만큼 증가
				y = 0;
				x+=n/2;
			}
		}
		return result;
	}
}