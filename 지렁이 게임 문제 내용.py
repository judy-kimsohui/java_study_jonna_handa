from typing import List

BoardL = []
WormInfo = []
AliveWorms = []
currTime = 0
Size = 0

class RESULT:    
    def __init__(self):
        self.cnt = 0
        self.IDs = [0, 0, 0, 0, 0]

def init(N : int) -> None:
    global BoardL, WormInfo, AliveWorms, currTime, Size

    # 지렁이 시뮬레이션 보드    
    BoardL = [[0] * N for _ in range(N)] 
    WormInfo = [] * (1000000001)
    AliveWorms = []
    currTime = 0
    Size = N
    pass

def join(mTime : int, mID : int, mX : int, mY : int, mLength : int) -> None:    
    global BoardL, WormInfo, AliveWorms

    # 지렁이 : 지렁이 머리 좌표, 방향, 꺾임 유무, 꺾임 좌표, 테일 좌표, 길이, 성장잠재력
    WormInfo[mID] = [True, (mY, mX), (-1, 0), False, (mY, mX), (mY+mLength-1, mX), mLength, 0]
    AliveWorms.apend([mID] + WormInfo[mID])
    
    # 새로운 지렁이를 보드에 그리기
    for i in range(mLength):
        BoardL[mY+i][mX] = mID    
    
    # 지렁이 이동 시뮬레이션
    moveWorm(mTime)
    pass

# 지렁이 이동
dir = [(-1, 0), (0, 1), (1, 0), (0, -1)]
def moveWorm(mTime):
    global BoardL, WormInfo, AliveWorms, currTime
    
    for t in range(currTime+1, mTime+1):
        newBoard = [[[] for _ in range(N)] for _ in range(N)] 
        OhNo = set()
        
        for worm in AliveWorms:
            [mID, alive, (wR, wC), (dr, dc), curveB, (cR, cC), (tR, tC), mLength, strength] = worm
            
            # 한칸 전진
            head = (wR+dr, wC+dc)
            curve = (cR, cC)
            tail = (tR+dr, tC+dc)

            # 테일 : 꺾는점이 있다면 시계 반대 방향으로 돌아, 테일 계산한다.            
            if curveB:                
                ndr, ndc = dir[dir.index((dr, dc)) - 1]
                tail = (tR+ndr, tC+ndc)                

            # 헤드 : 일직선이었다면, 한칸 꺾는다.
            if not curveB:
                r, c = head
                ndr1, ndc1 = dir[dir.index((dr, dc)) + 1]
                ndr2, ndc2 = dir[dir.index((ndr1, ndc1)) + 1]                
                head = (r+ndr1+ndr2, c+ndc1+ndc2)
                curveB = True
                (cR, cC) = (wR, wC)
                (dr, dc) = (ndr1, ndc1)

            # 헤드가 격자 밖으로 나간다면 아웃
            if not (0 <= head[0] < Size and 0 <= head[1] < Size):
                alive = False
            
            # 성장 잠재력이 0 이상인 경우
            # 지렁이 길이가 1 성장하고, 성장 잠재력은 1 감소한다.
            if strength > 0:
                tail = (tR, tC)
                mLength += 1
                strength -= 1
                
            # 테일 좌표가 꺾임 좌표와 같아진다면, 꺾임 유무 False 로 업데이트
            if tail == curve:
                curveB = False
                        
            # 지렁이를 보드에 그리기
            wormDrawL = []
            graph = defaultdict(list)
            if curveB:
                c = 0
                ndr, ndc = dir[dir.index((dr, dc)) - 1]
                for i in range(mLength):
                    r, c = [head[0]+dr*i, head[1]+dc*i]
                    if (r, c) == curve:
                        c = i
                    if c > 0:
                        r, c = [curve[0]+ndr*(i-c), curve[1]+ndc*(i-c)]
                    wormDrawL.append((r, c))
            
            for r, c in wormDrawL:                
                if 0 <= r < Size and 0 <= r < Size:                    

                    # 지렁이 그리기
                    if (r, c) == head:
                        newBoard[r][c].append((mID, "head"))
                    else:
                        newBoard[r][c].append((mID, "body"))
                    
                    # 겹치는 좌표가 있을 경우 graph, OhNo에 저장
                    if len(newBoard[r][c]) > 1:                        
                        for (id, What) in newBoard[r][c]: # 중복 처리도 필요                            
                            
                            # 내가 헤드일 경우
                            if (r, c) == head: 
                                if What == "head":   (바디 + 헤드)
                                    graph[mID].append(id)
                                elif What == "body": (바디 + 헤드)
                                    graph[id].append(mID)

                            # 내가 바디일 경우 (바디 + 헤드)
                            else:
                                if What == "head":
                                    graph[id].append(mID)
                                
                                
                        else:
                            OhNo.append((mID, "body"))
                            newBoard[r][c].append(mID)
                    else:
                        newBoard[r][c].append(mID)
                    # 
                    # 그래프[몸통].append(머리)

            
            
            
                

    
        # 끝나고 계산
        # 둘다 머리라면??
        # A 지렁이가 B 지렁이에 충돌한 경우,
            # A 지렁이는 소멸하며 B 지렁이는 A 지렁이의 길이만큼 성장 잠재력이 증가한다.
    
    currTime = mTime
   

def top5(mTime : int) -> RESULT:
    moveWorm
    ret = RESULT()
    return ret
