T = int(input())

dr = [-1, 1, 0, 0]
dc = [0, 0, -1, 1]

def canConnect(r, c, d):
    nr = r + dr[d]
    nc = c + dc[d]
    length = 0

    while 0 <= nr < N and 0 <= nc < N:
        if MapL[nr][nc] != 0:
            return 0
        nr += dr[d]
        nc += dc[d]
        length += 1

    return length


def setLine(r, c, d, val):
    nr = r + dr[d]
    nc = c + dc[d]

    while 0 <= nr < N and 0 <= nc < N:
        MapL[nr][nc] = val
        nr += dr[d]
        nc += dc[d]


def dfs(coreIndex, connected, length):
    global maxCore, minLen

    # 모든 코어 처리
    if coreIndex == len(cores):
        if connected > maxCore:
            maxCore = connected
            minLen = length
        elif connected == maxCore:
            minLen = min(minLen, length)
        return

    # 가지치기
    if connected + (len(cores) - coreIndex) < maxCore:
        return

    r, c = cores[coreIndex]

    # 4방향 연결 시도
    for d in range(4):
        lineLen = canConnect(r, c, d)

        if lineLen == 0:
            continue

        setLine(r, c, d, 2)
        dfs(coreIndex + 1, connected + 1, length + lineLen)
        setLine(r, c, d, 0)

    # 연결 안 하는 경우
    dfs(coreIndex + 1, connected, length)


for t in range(1, T + 1):

    N = int(input())

    MapL = []
    cores = []

    for r in range(N):
        row = list(map(int, input().split()))
        MapL.append(row)

        for c in range(N):
            if row[c] == 1:
                
                # 가장자리 제외
                if r != 0 and r != N-1 and c != 0 and c != N-1:
                    cores.append((r, c))

    maxCore = 0
    minLen = float('inf')

    dfs(0, 0, 0)

    print(f"#{t} {minLen}")
