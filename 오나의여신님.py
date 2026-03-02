
# 현재 수연이의 위치는 ‘S’, 여신의 공간은 ‘D’, 돌의 위치는 ‘X’, 악마는 ‘*’로 주어진다.

# "악마의 손아귀" 스킬은 매 초마다 상하좌우 인접해있는 영역들을 부식시키며 확장되어 간다.
# 수연이는 돌이 있는 위치로는 이동할 수 없다. 또, “악마의 손아귀”는 돌이 있는 곳에도 확장되지 않는다.

# 수연이가 “악마의 손아귀”를 벗어나면서 안전 지역에 가기 위한 최소 시간을 출력한다.

from collections import deque

T = int(input())
for t in range(1, T+1):
    
    # 입력
    N, M = map(int, input().split())
    MapL = []
    for _ in range(N):
        MapL.append(list(input()))

    devilL = []
    start, end = (0, 0), (0, 0)
    for r in range(N):
        for c in range(M):
            if MapL[r][c] == "*":
                devilL.append((r, c))
            elif MapL[r][c] == "S":
                start = (r, c)
            elif MapL[r][c] == "D":
                end = (r, c)
    # MapL[start[0]][start[1]] = "."
    
    MinTime = float('inf')
    visitedL = [[False] * M for _ in range(N)]
    visitedL[start[0]][start[1]] = True
    
    def dfs(r, c, time):
        global MinTime, devilL        
        MapL[r][c] = "."

        if (r, c) == end:
            MinTime = min(MinTime, time)
            return

        # 악마의 손아귀 확장
        devilSpreadL = []
        devilPrevL = [[""] * M for _ in range(N)]
        for (devilR, devilC) in devilL:
            for dr, dc in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
                nr, nc = devilR + dr, devilC + dc
                if 0 <= nr < N and 0 <= nc < M and (MapL[nr][nc] == "." or MapL[nr][nc] == "S"):
                    devilSpreadL.append((nr, nc))
                    devilPrevL[nr][nc] = MapL[nr][nc]
                    MapL[nr][nc] = "*"
        devilL.extend(devilSpreadL)
        
        # 수연이 이동
        for dr, dc in [(-1, 0), (1, 0), (0, -1), (0, 1)]:
            nr, nc = r + dr, c + dc
            if 0 <= nr < N and 0 <= nc < M and MapL[nr][nc] != "*" and MapL[nr][nc] != "X" and not visitedL[nr][nc]:
                visitedL[nr][nc] = True
                MapL[nr][nc] = "S"
                dfs(nr, nc, time+1)
                visitedL[nr][nc] = False

        # 원상복구
        devilL = devilL[:-len(devilSpreadL)]
        for (devilR, devilC) in devilSpreadL:
            MapL[devilR][devilC] = devilPrevL[devilR][devilC]
        

    dfs(start[0], start[1], 0)
    print("#" + str(t) + " ", end="")
    print(MinTime if MinTime != float('inf') else "GAME OVER")



    
    
