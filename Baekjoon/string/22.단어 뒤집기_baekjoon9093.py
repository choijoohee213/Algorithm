t = int(input())
for _ in range(t) :
    s = input()
    l = s.split(' ');
    for i in range(len(l)) :
        print(l[i][::-1], end=' ')
    print()