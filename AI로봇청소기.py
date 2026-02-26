from collections import defaultdict, deque

N, K, L = map(int, input().split(" "))
Map2L = [[0] * (N + 1)]
for _ in range(N):
    Map2L.append([-1] + list(map(int, input().split(" "))))

RobotL = []
Robot2L = [[-1] * (N + 1) for _ in range(N + 1)]
for k in range(K):
    r, c = map(int, input().split(" "))
    RobotL.append((r, c))
    Robot2L[r][c] = k

for l in range(L):

    # 1. 청소기 이동
    dr = [-1, 0, 0, 1]
    dc = [0, 1, -1, 0]
    for k, (r, c) in enumerate(RobotL):
        if Map2L[r][c] > 0:
            continue
        visitedL = [[False] * (N + 1) for _ in range(N + 1)]
        Q = deque([(r, c)])
        visitedL[r][c] = True
        checkL = []
        find = False
        while Q and not find:
            length = len(Q)
            for _ in range(length):
                (cr, cc) = Q.popleft()
                for i in range(4):
                    nr = cr + dr[i]
                    nc = cc + dc[i]
                    if 0 < nr <= N and 0 < nc <= N and not visitedL[nr][nc] and not Map2L[nr][nc] == -1 and Robot2L[nr][nc] == -1:
                        visitedL[nr][nc] = True
                        if Map2L[nr][nc] > 0:
                            checkL.append((nr, nc))
                            find = True
                        else:
                            Q.append((nr, nc))
        checkL = sorted(checkL, key=lambda x: (x[0], x[1]))
        if len(checkL) > 0:
            Robot2L[r][c] = -1
            nr, nc = checkL[0]
            Robot2L[nr][nc] = k
            RobotL[k] = (nr, nc)
        else:
            Robot2L[r][c] = k

    # 2. 청소
    xr = [0, -1, 0, 1]
    xc = [-1, 0, 1, 0]
    for k, (r, c) in enumerate(RobotL):
        index = 0
        maxClean = 0
        for x in range(4):
            # init
            clean = Map2L[r][c]
            if Map2L[r][c] > 20:
                clean = 20
            # 네 방향
            for i in range(4):
                nr = r + dr[i]
                nc = c + dc[i]
                if dr[i] == xr[x] and dc[i] == xc[x]:
                    continue
                if 0 < nr <= N and 0 < nc <= N:
                    if Map2L[nr][nc] > 20:
                        clean += 20
                    elif Map2L[nr][nc] > 0:
                        clean += Map2L[nr][nc]

            if maxClean < clean:
                maxClean = clean
                index = x

        # 청소 진행
        # init
        if Map2L[r][c] > 20:
            Map2L[r][c] -= 20
        else:
            Map2L[r][c] = 0
        for i in range(4):
            nr = r + dr[i]
            nc = c + dc[i]
            if dr[i] == xr[index] and dc[i] == xc[index]:
                continue
            if 0 < nr <= N and 0 < nc <= N:
                if Map2L[nr][nc] > 20:
                    Map2L[nr][nc] -= 20
                elif Map2L[nr][nc] > 0:
                    Map2L[nr][nc] = 0

    # 3. 먼지 축적
    for r in range(1, N + 1):
        for c in range(1, N + 1):
            if Map2L[r][c] > 0:
                Map2L[r][c] += 5

    # 4. 먼지 확산
    NewMap2L = [[0] * (N + 1) for _ in range(N + 1)]
    for r in range(1, N + 1):
        for c in range(1, N + 1):
            if Map2L[r][c] == 0:
                for i in range(4):
                    nr = r + dr[i]
                    nc = c + dc[i]
                    if 0 < nr <= N and 0 < nc <= N and Map2L[nr][nc] > 0:
                        NewMap2L[r][c] += Map2L[nr][nc]
                        # print(r, c, Map2L[nr][nc], NewMap2L[r][c])

                # if NewMap2L[r][c] > 0:
                #     print(r, c, NewMap2L[r][c])
                NewMap2L[r][c] //= 10

    # 먼지 확산 나머지 + sum 출력
    Sum = 0
    for r in range(1, N + 1):
        for c in range(1, N + 1):
            if Map2L[r][c] == 0:
                Map2L[r][c] = NewMap2L[r][c]
            if Map2L[r][c] > 0:
                Sum += Map2L[r][c]

    print(Sum, end='')
    if l < L - 1:
        print()
