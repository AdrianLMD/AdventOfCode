ranges = [tuple(map(int, r.split('-'))) for r in open("day02_input.txt").read().strip().split(',')]
sum = 0

def check_rep(i: int):
    num = str(i)
    length = len(num)

    for l in range(1, length // 2 + 1):
        if length % l == 0:
            pattern = num[:l]
            reps = length // l
            
            if pattern * reps == num:
                return i
    return 0

for r in ranges:
    for i in range(r[0],r[1]+1):
        sum += check_rep(i)
        
print(sum)