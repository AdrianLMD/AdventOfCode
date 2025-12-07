import math

data = open("day01_input.txt").read().strip().split("\n")
groups = data

vault = 50
zero_counter = 0

def count_rotations_pos(val: int):
    zeroes = 0
    zeroes += val // 100
    return zeroes
        
        

def count_rotations_neg(val: int, diff: int):
    zeroes = 0
    if val >= diff:
        zeroes += 1 + (val - diff) // 100
    return zeroes


for line in groups:
    if line[0] == "R":
        val = vault + int(line[1:])
        zero_counter += count_rotations_pos(val)
        vault = (vault + int(line[1:])) % 100
    elif line[0] == "L":
        val = int(line[1:])
        if vault == 0:
            diff_to_0 = 100
        else:
            diff_to_0 = vault
        
        zero_counter += count_rotations_neg(val, diff_to_0)
        vault = (vault - val) % 100
    else:
        print(f"Not starting with either R or L")
    print(vault)
    if vault == 0:
       zero_counter += 0

# part 2: 6591 too low, not 7054, not 7163, not 9333, not 6803
print("The combination resulted in 0 a total of " + str(zero_counter) + " time(s).")