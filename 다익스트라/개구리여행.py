import heapq

N = int(input())
Map = [[""] * (N+1)]
for _ in range(N):
    Map.append([""] + list(input()))

Q = int(input())
TravelL = []
for _ in range(Q):
    TravelL.append(list(map(int, input().split())))

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

for r1, c1, r2, c2 in TravelL:
    # 최소 시간 배열, 1~5 점프력
    dist = [[[float('inf')]*6 for _ in range(N+1)] for _ in range(N+1)]
    dist[r1][c1][1] = 0

    # heapq: (time, r, c, jump)
    pq = [(0, r1, c1, 1)]
    while pq:
        time, r, c, jump = heapq.heappop(pq)

        if (r, c) == (r2, c2):
            # 최소 시간 확정
            break

        if time > dist[r][c][jump]:
            continue

        # 이동
        for i in range(4):
            nr = r + dr[i]*jump
            nc = c + dc[i]*jump
            if not (0 < nr <= N and 0 < nc <= N):
                continue
            if Map[nr][nc] != '.':
                continue

            # 직선 중간 벽 체크
            can = True
            for step in range(1, jump+1):
                check_r = r + dr[i]*step
                check_c = c + dc[i]*step
                if Map[check_r][check_c] == '#':
                    can = False
                    break
            if can:
                new_time = time + 1
                if new_time < dist[nr][nc][jump]:
                    dist[nr][nc][jump] = new_time
                    heapq.heappush(pq, (new_time, nr, nc, jump))

        # 점프력 증가
        if jump < 5:
            newJump = jump + 1
            new_time = time + newJump*newJump
            if new_time < dist[r][c][newJump]:
                dist[r][c][newJump] = new_time
                heapq.heappush(pq, (new_time, r, c, newJump))

        # 점프력 감소
        for newJump in range(1, jump):
            new_time = time + 1
            if new_time < dist[r][c][newJump]:
                dist[r][c][newJump] = new_time
                heapq.heappush(pq, (new_time, r, c, newJump))

    # 최소 시간 선택
    ans = min(dist[r2][c2])
    print(ans if ans != float('inf') else -1)
