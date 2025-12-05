groups = open("day03_input_test.txt").read().strip().split("\n")
sum = 0
x = 12

def largest_num(digits, x):
    pos = 0
    result = []
    length = len(digits)

    for i in range(x):
        reserve = (x-1)-i
        limit = length - reserve
        fitting_area = digits[pos:limit]
        max1 = max(fitting_area)
        index1 = fitting_area.index(max1)
        index2 = pos + index1

        result.append(str(max1))
        pos = index2 +1
    return int("".join(result))

for g in groups:
    digits = [int(c) for c in g]

    if len(digits) >= x:
        val = largest_num(digits, x)
        sum += val

print(sum)