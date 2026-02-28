def solve():
    N, X, M = map(int, input().split())

    hamsterL = [[-1] * (N + 1) for _ in range(N + 1)]
    for _ in range(M):
        a, b, c = map(int, input().split())
        hamsterL[a][b] = c

    best = None
    best_sum = -1

    def check(arr):
        prefix = [0] * (N + 1)
        for i in range(N):
            prefix[i + 1] = prefix[i] + arr[i]

        for a in range(1, N + 1):
            for b in range(a, N + 1):
                if hamsterL[a][b] != -1:
                    if prefix[b] - prefix[a - 1] != hamsterL[a][b]:
                        return False
        return True

    def dfs(idx, arr):
        nonlocal best, best_sum

        if idx == N:
            if check(arr):
                s = sum(arr)
                if s > best_sum or (s == best_sum and (best is None or arr < best)):
                    best_sum = s
                    best = arr[:]
            return

        for v in range(X + 1):
            arr.append(v)
            dfs(idx + 1, arr)
            arr.pop()

    dfs(0, [])

    if best is None:
        print(-1)
    else:
        print(*best)


T = int(input())
for _ in range(T):
    solve()
