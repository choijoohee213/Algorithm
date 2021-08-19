s = input().upper()
arr = list(set(s))
result = []

for i in arr :
    result.append(s.count(i))

if result.count(max(result)) > 1 : 
    print("?")
else :
    print(arr[result.index(max(result))])