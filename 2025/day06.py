import math

def part1():
    lines = open("day06_input.txt").read().strip().split("\n")
    numbers = lines[:-1] # all lines except last one, since it contains the operators
    operators = lines[-1] # last line only, since it contains the operators
    operators = operators.split() # split into list of operators, split removing unnecessary spaces

    # ill comment a little more, so marc can understand whats going on
    rows = [list(map(int, line.split()))  for line in numbers] # matrix, split() ignores multiple spaces

    # we want to calculate using the same index collumn of each row, thus, we can transpose to get exactly those
    collums = list(zip(*rows))
    # collums now is a list of tuples, each containing the numbers that need to be calculated
    results = []
    # for each collum, we check the operator at the same index
    for i, collum in enumerate(collums):
        operator = operators[i]
        if operator == "+":
            res = sum(collum) # sum all numbers in the collum
        elif operator == "*":
            res = math.prod(collum) # multiply all numbers in the collum
        else:
            print(f"unknown operator {operator}. which is weird, since I strg+f'd the input and fopund none other")
            res = 0
        results.append(res)
    final_sum = sum(results)
    print(f"final sum is {final_sum}")

def part2():
    lines = open("day06_input.txt").read().strip("\n").split("\n")
    max_len = max(len(line) for line in lines) # finds the largest possible line, needed for padding
    pad_lines = [line.ljust(max_len) for line in lines] # pad lines with spaces to make them all the same length

    # transpose so rows are collums
    collums2 = list(zip(*pad_lines))

    blocks = []
    cblock = []
    for col in collums2:
        # check if all spaces
        if "".join(col).strip() == "":
            if cblock:
                blocks.append(cblock)
                cblock = []
        else:
            cblock.append(col)
    # lmao dont forget the last block
    if cblock:
        blocks.append(cblock)
    total = 0

    for block_index, block in enumerate(reversed(blocks)): #enumerates gives each element (block) an index ,starting at 0
        # thus returns tupel (index, block)
        # needed to work from right to left
        operator = "+"
        for col in block:
            for char in col:
                if char in "*+":
                    operator = char
                    break
        block_numbers = []
        for col in reversed(block):
            # list comprehension
            # input is col, a collumn of numbers or operators or empty
            # for c in col iterates through each char in that collumn
            # if c.isdigit() filters digits, so digits is always only didgts, hence the name lol
            # result c is new list of only digits in that gollumn
            digits = [c for c in col if c.isdigit()]

            if digits:
                # join digits top to bottom and parse
                number = int("".join(digits))
                block_numbers.append(number)
        # block result
        if not block_numbers:
            continue

        # use first number as init val
        block_res = block_numbers[0]

        # apply operator to rest of numbers
        for num in block_numbers[1:]:
            if operator == "+":
                block_res += num
            elif operator == "*":
                block_res *= num
        total += block_res
    print(f"total: {total}")

       
# part1()
part2()