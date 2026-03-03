from collections import deque

N = int(input())
MapL = []
for _ in range(N):
    MapL.append(list(map(int, input().split())))

case1 = [(0, -1), (-1, -1)] # 가로
case2 = [(-1, 0), (-1, -1)] # 세로
case3 = [(0, -1), (-1, 0), (-1, -1)] # 대각선
case = case1 + case2 + case3

pipe = [[0] * N for _ in range(N)]
pipe[0][0] = 0
pipe[0][1] = 1

for r in range(N):
    for c in range(N):
        for dr, dc in case1:
            nr, nc = r + dr, c + dc
            if 0 <= nr < N and 0 <= nc < N and MapL[nr][nc] != 1 and c+1 < N and MapL[r][c+1] != 1:
                pipe[r][c+1] += pipe[r][c]
        for dr, dc in case2:
            nr, nc = r + dr, c + dc
            if 0 <= nr < N and 0 <= nc < N and MapL[nr][nc] != 1 and r+1 < N and MapL[r+1][c] != 1:
                pipe[r+1][c] += pipe[r][c]
        for dr, dc in case3:
            nr, nc = r + dr, c + dc
            if 0 <= nr < N and 0 <= nc < N and MapL[nr][nc] != 1 and r+1 < N and c+1 < N and MapL[r+1][c+1] != 1:
                pipe[r+1][c+1] += pipe[r][c]

print(pipe)
print(pipe[N-1][N-1])
