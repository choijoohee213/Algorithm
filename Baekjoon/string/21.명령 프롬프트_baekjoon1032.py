n = int(input())
result = []
for i in range(n) :
    s = input()
    for j in range(len(s)) :
        if i == 0 :
            result.append(s[j])
        if result[j] != s[j] :
            result[j] = "?"
print(''.join(result))