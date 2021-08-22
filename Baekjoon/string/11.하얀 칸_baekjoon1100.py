chess = list()
cnt = 0
for i in range(8) :
    chess.append(list(input()))
    
for i in range(8) :
    for j in range(8) :
        if (i % 2 == 0 and j % 2 == 0 and chess[i][j] == 'F') or (i % 2 == 1 and j % 2 == 1 and chess[i][j] == 'F') :
            cnt += 1    
print(cnt)