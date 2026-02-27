# 1. 택배 투입
from _cffi_backend import CType

N, M = map(int, input().split())

map2L = [[0] * N for _ in range(N)]
heightL = [N-1] * N
Box = [[0] * 4 for _ in range(101)]
print(len(Box))

for _ in range(M):
    # 번호, 세로, 가로, 좌측좌표(1>0변환)
    K, H, W, C = map(int, input().split())
    C -= 1
    R = N-1
    for c in range(C, C+W):
        R = min(R, heightL[c])
    for r in range(R, R-H, -1):
        for c in range(C, C + W):
            map2L[r][c] = K
            heightL[c] = R-H
    Box[K] = [H, W, C, R-H+1]

for _ in range(N):
    print(*map2L[_])
# print(heightL)

Print = ""
BoxOut = [False] * 101
# Box = set(i for i in range(1, M+1))
# 택배를 모두 하차할 때까지 반복
Count = M
while Count > 0:
    # 2. 택배 하차 (좌측)
    BoxNum = 100
    for r in range(N):
        for c in range(N):
            if map2L[r][c] != 0:
                BoxNum = min(BoxNum, map2L[r][c])
                break
    BoxOut[BoxNum] = True
    [H, W, C, R] = Box[BoxNum]
    for r in range(R, R + H):
        for c in range(C, C + W):
            map2L[r][c] = 0
    Count -= 1
    Print += str(BoxNum) + "\n"

    if Count <= 0:
        break

    print()
    for _ in range(N):
        print(*map2L[_])

    # 중력 (BoxOut 제외하고 덮어쓰기)
    map2L = [[0] * N for _ in range(N)]
    heightL = [N - 1] * N
    for K in range(1, M+1):
        # 번호, 세로, 가로, 좌측좌표(1>0변환)
        if not BoxOut[K]:
            H, W, C, P = Box[K]
            C -= 1
            R = N - 1
            for c in range(C, C + W):
                R = min(R, heightL[c])
            for r in range(R, R - H, -1):
                for c in range(C, C + W):
                    map2L[r][c] = K
                    heightL[c] = R - H
            Box[K] = [H, W, C, R - H + 1]

    # 3. 택배 하차 (우측)
    BoxNum = 100
    for r in range(N):
        for c in range(N-1, 0, -1):
            if map2L[r][c] != 0:
                BoxNum = min(BoxNum, map2L[r][c])
                break
    BoxOut[BoxNum] = True
    [H, W, C, R] = Box[BoxNum]
    for r in range(R, R + H):
        for c in range(C, C + W):
            map2L[r][c] = 0
    Count -= 1
    Print += str(BoxNum) + "\n"

    # 중력 (BoxOut 제외하고 덮어쓰기)
    map2L = [[0] * N for _ in range(N)]
    heightL = [N - 1] * N
    for K in range(1, M + 1):
        # 번호, 세로, 가로, 좌측좌표(1>0변환)
        if not BoxOut[K]:
            H, W, C, P = Box[K]
            C -= 1
            R = N - 1
            for c in range(C, C + W):
                R = min(R, heightL[c])
            for r in range(R, R - H, -1):
                for c in range(C, C + W):
                    map2L[r][c] = K
                    heightL[c] = R - H
            Box[K] = [H, W, C, R - H + 1]

    print()
    for _ in range(N):
        print(*map2L[_])

# 하차되는 택배의 번호를 순서대로 출력
print(Print)
