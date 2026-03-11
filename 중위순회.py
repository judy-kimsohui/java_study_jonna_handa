from collections import defaultdict

def inorder(node):

    alpha = wordL[node][0]

    if len(tree[node]) >= 1:
        inorder(tree[node][0])

    print(alpha, end="")

    if len(tree[node]) == 2:
        inorder(tree[node][1])


for t in range(1, 11):

    N = int(input())

    tree = defaultdict(list)
    wordL = [[]]

    for _ in range(N):
        line = input().split()
        wordL.append(line[1:])

    for i, node in enumerate(wordL):

        if len(node) > 1:
            for n in node[1:]:
                tree[i].append(int(n))

    print("#" + str(t) + " ", end="")
    inorder(1)
    print()
