while True :
    s = input()
    if s == '.' :
        break
    arr = []
    balance = True
    for j in s :
        if j == '(' or j == '[' :
            arr.append(j)
        elif j == ')' :
            if not arr or arr[-1] == '[' :
                balance = False
                break
            elif arr[-1] == '(' :
                arr.pop()
        elif j == ']' :
            if not arr or arr[-1] == '(' :
                balance = False
                break
            elif arr[-1] == '[' :
                arr.pop()
    if balance == True and not arr :
        print("yes")
    else : print("no") 