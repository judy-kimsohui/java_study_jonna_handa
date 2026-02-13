package algorithm;

import java.io.*;
import java.util.*;

class 병사관리
{
	private final static int CMD_INIT				= 1;
	private final static int CMD_HIRE				= 2;
	private final static int CMD_FIRE				= 3;
	private final static int CMD_UPDATE_SOLDIER		= 4;
	private final static int CMD_UPDATE_TEAM		= 5;
	private final static int CMD_BEST_SOLDIER		= 6;
	
	private final static UserSolution usersolution = new UserSolution();
	
	private static boolean run(BufferedReader br) throws Exception
	{
		StringTokenizer st;
		
		int numQuery;

		int mID, mTeam, mScore, mChangeScore;
	
		int userAns, ans;
	
		boolean isCorrect = false;

		numQuery = Integer.parseInt(br.readLine());
		
		for (int q = 0; q < numQuery; ++q)
		{
			st = new StringTokenizer(br.readLine(), " ");

			int cmd;
			cmd = Integer.parseInt(st.nextToken());
			
			switch(cmd)
			{
			case CMD_INIT:
				usersolution.init();
				isCorrect = true;
				break;
			case CMD_HIRE:
				mID = Integer.parseInt(st.nextToken());
				mTeam = Integer.parseInt(st.nextToken());
				mScore = Integer.parseInt(st.nextToken());
				usersolution.hire(mID, mTeam, mScore);
				break;
			case CMD_FIRE:
				mID = Integer.parseInt(st.nextToken());
				usersolution.fire(mID);
				break;
			case CMD_UPDATE_SOLDIER:
				mID = Integer.parseInt(st.nextToken());
				mScore = Integer.parseInt(st.nextToken());
				usersolution.updateSoldier(mID, mScore);
				break;
			case CMD_UPDATE_TEAM:
				mTeam = Integer.parseInt(st.nextToken());
				mChangeScore = Integer.parseInt(st.nextToken());
				usersolution.updateTeam(mTeam, mChangeScore);
				break;
			case CMD_BEST_SOLDIER:
				mTeam = Integer.parseInt(st.nextToken());
				userAns = usersolution.bestSoldier(mTeam);
				ans = Integer.parseInt(st.nextToken());
				if (userAns != ans) {
					isCorrect = false;
				}
				break;
			default:
				isCorrect = false;
				break;
			}
		}
		
		return isCorrect;
	}
	
	public static void main(String[] args) throws Exception
	{
		int TC, MARK;
	
		System.setIn(new java.io.FileInputStream("./sample_25_input.txt"));
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		
		TC = Integer.parseInt(st.nextToken());
		MARK = Integer.parseInt(st.nextToken());

		for (int testcase = 1; testcase <= TC; ++testcase)
		{
			int score = run(br) ? MARK : 0;
            System.out.println("#" + testcase + " " + score);
		}

		br.close();
	}
}

class UserSolution
{	
	static class Soldier {
		int mID;
		int mTeam;
		int mScore;

		public Soldier(int mID, int mTeam, int mScore) {
			this.mID = mID;
			this.mTeam = mTeam;
			this.mScore = mScore;
		}
	}	
	static Soldier[] soldierL = new Soldier[100_001];
	static TreeSet<Integer>[][] team = new TreeSet[6][6];
	static int[][] teamRealScore = new int[6][6];
	static int[] teamOffset = new int[6];
	
	
	public void init()
	{		
//		각 테스트 케이스의 처음에 호출된다.
//		테스트 케이스의 시작 시 고용된 병사는 없다.
		
		for (int i = 1; i <= 5; i++) {
	        for (int j = 1; j <= 5; j++) {
	            team[i][j] = new TreeSet<>();
	            teamRealScore[i][j] = j;
	        }
	        teamOffset[i] = 0;
	    }
	}
	
	
	public void hire(int mID, int mTeam, int mScore)
	{				
		// 고유번호가 mID, 소속팀이 mTeam, 평판 점수가 mScore인 병사를 고용한다.
		// 한 테스트 케이스 내에서 동일한 mID를 가진 병사가 여러 번 고용되는 경우는 없음이 보장된다.
		
//		mID : 고유번호 (1 ≤ mID ≤ 100,000)
//		mTeam : 소속팀 (1 ≤ mTeam ≤ 5)
//		mScore : 평판 점수 (1 ≤ mScore ≤ 5)
		
		soldierL[mID] = new Soldier(mID, mTeam, mScore);
	    for (int i = 1; i <= 5; i++) {
	    	if (teamRealScore[mTeam][i] == mScore) {
	    		team[mTeam][i].add(mID);
	    		break;
	    	}
	    }
	}
	
	public void fire(int mID)
	{			
//		고유번호가 mID인 병사를 해고한다.
//		fire() 함수 호출 시, 고유번호가 mID인 병사가 고용되어 있음이 보장된다.
		
		// 병사 해고
		Soldier s = soldierL[mID];
	    int mTeam = s.mTeam;

	    for (int i = 1; i <= 5; i++) {
	        if (team[mTeam][i].contains(mID)) {
	            team[mTeam][i].remove(mID);
	            break;
	        }
	    }
	}

	public void updateSoldier(int mID, int mScore)
	{		
//		고유번호가 mID인 병사의 평판 점수를 mScore로 변경한다.
//		고유번호가 mID인 병사가 고용되어 있음이 보장된다.
		
		// 병사 평판 점수 변경
		Soldier s = soldierL[mID];
	    int mTeam = s.mTeam;

	    // 현재 들어있는 칸 찾기
	    for (int i = 1; i <= 5; i++) {
	        if (team[mTeam][i].contains(mID)) {
	            team[mTeam][i].remove(mID);
	            break;
	        }
	    }

	    // 새 점수 칸 찾기
	    for (int i = 1; i <= 5; i++) {
	        if (teamRealScore[mTeam][i] == mScore) {
	            team[mTeam][i].add(mID);
	            break;
	        }
	    }
	}

	public void updateTeam(int mTeam, int mChangeScore)
	{
		
//		소속팀이 mTeam인 병사들의 평판 점수를 모두 변경한다.
//		소속팀이 mTeam인 병사가 한 명 이상 고용되어 있음이 보장된다.
//
//		updateTeam() 함수에서의 평판 점수 변경은 아래의 규칙에 따른다.
//		‘변경 전 평판 점수 + mChangeScore’가 5보다 클 경우, 평판 점수를 5로 변경한다.
//		‘변경 전 평판 점수 + mChangeScore’가 1보다 작을 경우, 평판 점수를 1로 변경한다.
//		그 외의 경우, 평판 점수를 ‘변경 전 평판 점수 + mChangeScore’로 변경한다.
		
		for (int i = 1; i <= 5; i++) {	
			// Real Score, 점수 분산 가능성 있음
			teamRealScore[mTeam][i] = Math.max(1, Math.min(5, teamRealScore[mTeam][i]+ mChangeScore));
	    }
	}
	
	public int bestSoldier(int mTeam)
	{
		
//		소속팀이 mTeam인 병사들 중 평판 점수가 가장 높은 병사의 고유번호를 반환한다.
//		평판 점수가 가장 높은 병사가 여러 명일 경우, 고유번호가 가장 큰 병사의 고유번호를 반환한다.
//		소속팀이 mTeam인 병사가 한 명 이상 고용되어 있음이 보장된다.
		
		int bestScore = 0;

	    // 현재 팀의 최고 실제 점수 찾기
	    for (int i = 1; i <= 5; i++) {
	        if (!team[mTeam][i].isEmpty()) {
	            bestScore = Math.max(bestScore, teamRealScore[mTeam][i]);
	        }	        
	    }

	    int answer = -1;

	    // 최고 점수를 가진 모든 칸 확인
	    for (int i = 1; i <= 5; i++) {
	        if (teamRealScore[mTeam][i] == bestScore) {
	            if (!team[mTeam][i].isEmpty()) {
	                answer = Math.max(answer, team[mTeam][i].last());
	            }
	        }
	    }

	    return answer;
	}
}
