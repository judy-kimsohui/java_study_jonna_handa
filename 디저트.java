
import java.io.*;

import java.util.*;



public class 디저트 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static StringBuilder sb = new StringBuilder();

	static StringTokenizer st;



//	static int[] dr = { -1, 1, 1, -1 };

//	static int[] dc = { 1, -1, 1, -1 };



	public static void main(String[] args) throws Exception {

		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {

			int n = Integer.parseInt(br.readLine());

			int[][] map = new int[n][n];

			for (int i = 0; i < n; i++) {

				st = new StringTokenizer(br.readLine());

				for (int j = 0; j < n; j++) {

					map[i][j] = Integer.parseInt(st.nextToken());

				}

			}



//			임의의 한 카페에서 출발하여 대각선 방향으로 움직이고

//			서로 다른 디저트를 먹으면서 사각형 모양을 그리며 다시 출발점으로 돌아오는 경우,

			// 디저트를 가장 많이 먹을 수 있는 경로를 찾고, 그 때의 디저트 수를 정답으로 출력하는 프로그램을 작성하라.



			// 출발점 (r, c)



			int ans = -1;

			for (int r = 1; r < n-1; r++) {

				for (int c = 0; c < n-2; c++) {

					for (int x=0; x<n;x++) {

						for (int y=0;y<n;y++) {

														

							//사각형 안되는 경우 continue

							if (map[r][c] == map[x][y]) continue;

							if (c == y) continue;

							if (r == x && c == y) continue;

							if (r == x && Math.abs(c-y) == 1) continue; // 옆에 붙어있을 경우

							if (c == y && Math.abs(r-x) == 1) continue; 

//							

							if (c > y) continue; // 1번이 2번보다 오른쪽에 있을 경우 (두 점 순서 지켜야함)

//							if (((y-c) % 2 + (x-r) %2 ) %2 != 0) continue; // 두 점을 1, -1 라인으로 접근 가능해야함

							if (c-y != 0 && Math.abs((r-x)/(c-y)) >= 1) continue; // 두 점 사이 기울기가 1, -1이 되면 안됨

//							System.out.println(r + " " + c + " " + x + " " + y + " ");

							int up = 0, down = 0;							

							int sum = 0;

							boolean succ = true;

							

							boolean[] visitedL = new boolean[101];

							visitedL[map[r][c]] = true;

							sum += 1;

							

							// 시작 = 다음

							

							// 1

							int j = c;

//							System.out.println(1);

							for (int i = r-1; i >= 0; i--) {

								j++;

//								System.out.println(i + " " + j);

								if (i < 0 || i >= n || j < 0 || j >= n) {

									succ = false;

									break;

								}

								int number = map[i][j];

								if (!visitedL[number]) {										

									sum += 1;

									visitedL[number] = true;

								}

								else {

									succ = false;

									break;

								}							

								

								// 가로, 세로 

								if (y-j != 0 && Math.abs((x-i)/(y-j)) == 1) {

//									System.out.println("iooii");

									up = Math.abs(i-r);

									down = Math.abs(y-j);

//									System.out.println(up + " " + down);

//									System.out.println(r + " " + c + " " + x + " " + y + " " + i + " " + j);

									

									break;

								}								

							}

							

							// 2

							if (succ) {

//								System.out.println(2);

								for (int i = r-up+1; i <= x; i++) {

									j++;

									if (i < 0 || i >= n || j < 0 || j >= n) {

										succ = false;

										break;

									}

//									System.out.println(i + " " + j);

									int number = map[i][j];

									if (!visitedL[number]) {										

										sum += 1;

										visitedL[number] = true;

									}

									else {

										succ = false;

										break;

									}

								}

							}

							

							// 3

							if (succ) {

//								System.out.println(3);



								for (int i = x+1; i <= x+up; i++) {

									j--;

									if (i < 0 || i >= n || j < 0 || j >= n) {

										succ = false;

										break;

									}

//									System.out.println(i + " " + j);

									int number = map[i][j];

									if (!visitedL[number]) {										

										sum += 1;

										visitedL[number] = true;

									}

									else {

										succ = false;

										break;

									}

								}								

							}

							

							// 4

							if (succ) {

//								System.out.println(4);



								for (int i = x+up-1; i <= r; i--) {

									j--;

									if (i == r && j == c) break; // 도착점 도달

									if (i < 0 || i >= n || j < 0 || j >= n) {

										succ = false;

										break;

									}

//									System.out.println(i + " " + j);

									int number = map[i][j];

									if (!visitedL[number]) {										

										sum += 1;

										visitedL[number] = true;

									}

									else {

										succ = false;

										break;

									}

								}								

							}

							

							

							if (succ) {

								System.out.println("hey");

								System.out.println(r + " " + c + " " + x + " " + y + " " + up + " " + down);

								ans = Math.max(ans, sum);

							}



						}

					}					

				}

			}

			

			sb.append("#" + tc + " " + ans + "\n");

		}

		

		System.out.println(sb);



	}



}
