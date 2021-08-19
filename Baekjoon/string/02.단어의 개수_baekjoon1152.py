s = input()
cnt = s.count(' ') + 1
if(s[len(s)-1] == ' ') : cnt -= 1
if(s[0] == ' ') : cnt -= 1
print(cnt)

'''
print(len(input().split()))
'''