word = input()
for i in ["c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="] :
    word = word.replace(i, "*")
print(len(word))