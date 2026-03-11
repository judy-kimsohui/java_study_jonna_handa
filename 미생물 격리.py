dir = [(-1,0),(1,0),(0,-1),(0,1)]
rev = [1,0,3,2]

T = int(input())

for t in range(1,T+1):

    N, M, K = map(int,input().split())

    virus = []

    for _ in range(K):
        r, c, s, d = map(int,input().split())
        virus.append([r, c, s, d-1])

    for _ in range(M):

        temp = {}

        for r, c, s, d in virus:

            if s == 0:
                continue

            nr = r + dir[d][0]
            nc = c + dir[d][1]

            if nr == 0 or nr == N-1 or nc == 0 or nc == N-1:
                s//=2
                d = rev[d]

            if s == 0:
                continue

            if (nr, nc) not in temp:
                temp[(nr, nc)] = [s, d, s]  
                # total, direction, max

            else:
                temp[(nr, nc)][0] += s

                if temp[(nr, nc)][2] < s:
                    temp[(nr, nc)][1] = d
                    temp[(nr, nc)][2] = s

        virus = []

        for (r, c), (total, d, mx) in temp.items():
            virus.append([r, c, total, d])

    result = sum(v[2] for v in virus)

    print(f"#{t} {result}")
