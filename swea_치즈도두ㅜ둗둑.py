# BFS
from collections import deque

T = int(input())
for t in range(1, T+1):

    # 입력
    N = int(input())
    cheeseL = []
    fairyL = [[] for i in range(101)]
    maxNum = 0
    for r in range(N):
        cheeseL.append(list(map(int, input().split())))
        for c in range(N):
            number = cheeseL[r][c]
            fairyL[number].append((r, c))
            maxNum = max(maxNum, number)

    # 100일 중에서 치즈덩어리가 가장 많을 때의 덩어리 개수
    maxCount = 0
    for time in range(1, maxNum+1):
        for r, c in fairyL[time]:
            cheeseL[r][c] = 0

        # BFS로 덩어리 개수 세기
        count = 0
        visitedL = [[False] * N for _ in range(N)]
        for cr in range(N):
            for cc in range(N):
                if cheeseL[cr][cc] != 0 and not visitedL[cr][cc]:
                    Q = deque([(cr, cc)])
                    count += 1
                    while Q:
                        (r, c) = Q.popleft()
                        visitedL[r][c] = True
                        for dr, dc in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                            nr, nc = r + dr, c + dc
                            if 0 <= nr < N and 0 <= nc < N and cheeseL[nr][nc] != 0 and not visitedL[nr][nc]:
                                Q.append((nr, nc))

        maxCount = max(maxCount, count)

    print("#" + str(t) + " " + str(maxCount))
