a = input().split(' ')
result = len(a[1])
for i in range(len(a[1]) - len(a[0]) + 1) :
    cnt = 0
    for j in range(len(a[0])) :
        if a[0][j] != a[1][i+j] : cnt += 1
    result = min(result, cnt)
print(result)