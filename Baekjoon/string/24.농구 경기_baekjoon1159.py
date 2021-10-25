n = int(input())
arr = dict()
result = set()

for i in range(n) :
    s = input()
    if s[0] in arr :
        arr[s[0]] = arr[s[0]] + 1
    else :
        arr[s[0]] = 1 
    
    if(arr[s[0]] >= 5) :
        result.add(s[0])

if len(result) <= 0 : print("PREDAJA")
else : 
    for i in sorted(result) :
        print(i, end='')