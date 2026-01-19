package com.ssafy.ws.step3;

import java.util.*;
import java.io.*;

/**
 * 가위,바위,보 게임을 하는 클래스
 */
public class GameTest {

	public static void main(String[] args) throws Exception {

		String Quest = """
			가위바위보 게임을 시작합니다. 아래 보기 중 하나를 고르세요.
			
				1. 5판 3승
				2. 3판 2승
				3. 1판 1승 \n	
		""";
		
		System.out.println("번호를 입력하세요.");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));		
		int Number = Integer.parseInt(br.readLine());
		
		for (int i=0; i<Number; i++) {
			System.out.println("가위바위보 중 하나 입력: ");
			String RSP = br.readLine();
			
			int me = switch (RSP) {
				case "가위" -> 1;
				case "바위" -> 2;
				case "보" -> 3;
				default -> 0;
			};
			
			int WinM = 0, WinC = 0;
			int Computer = (int)(Math.random()*3)+1;
			if (me == 0) {
				System.out.println("잘못 입력했습니다.");
			}
			else if (Computer-me == 0) { // 비긴 경우
				System.out.println("이겼습니다!!");
				WinM += 1;
			}
			else if (Computer == 1 && me == 2) {
				System.out.println("이겼습니다!!");
				WinM += 1;
			}
			else if (Computer == 2 && me == 3) {
				System.out.println("이겼습니다!!");
				WinM += 1;
			}
			else if (Computer == 3 && me == 1) {
				System.out.println("이겼습니다!!");
				WinM += 1;
			}
			else {
				System.out.println("졌습니다!!");
				WinC += 1;
			}
			
			if (WinM >= WinC) { System.out.print("#### 승리!!"); }
			else { System.out.println("#### 컴퓨터 승!!"); }
			
		}
		
		
		
		
		
		
		
		
	}
}
