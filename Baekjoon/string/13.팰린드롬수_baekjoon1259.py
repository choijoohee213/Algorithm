while True :
    s = input()
    if s == '0' : break
    if(s == s[::-1]) : print("yes\n")
    else : print("no\n")