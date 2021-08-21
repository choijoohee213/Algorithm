word = input()
arr = []
for i in word :
    arr.append(int(i))
arr.sort(reverse=True)
for i in arr:
    print(i, end='')