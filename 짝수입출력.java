package com.ssafy.ws.step1;

import java.util.*;
import java.io.*;

public class EvenSum {

	public static void main(String[] args) throws Exception {

		System.out.print("숫자 입력 >>");

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int Num = Integer.parseInt(br.readLine());

		int EvenNum = 0;
		for (int i=0; i<=Num; i+=2) {
			EvenNum += i;
		}

		System.out.printf("1 부터 %d까지 수 중 짝수의 합 = %d", Num, EvenNum);
		
	}

}
