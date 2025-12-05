grid = open("day04_input.txt").read().strip().split("\n")
input = [list(line) for line in grid]
height = len(input)
width = len(input[0])
round_counter = 0
accessable = 0
while True:
    round_counter += 1
    kill_list = []

    for z in range(height):
        for s in range(width):
            if input[z][s] == "@":
                neighbour_count = 0
                if z > 0:
                    if input[z-1][s] == "@":    # oben mittig
                        neighbour_count += 1
                if z < height-1:        
                    if input[z+1][s] == "@":    # unten mittig
                        neighbour_count += 1
                if s > 0:        
                    if input[z][s-1] == "@":    # mittig links
                        neighbour_count += 1
                if s < width-1:        
                    if input[z][s+1] == "@":    # mittig rechts
                        neighbour_count += 1
                if z > 0 and s > 0:        
                    if input[z-1][s-1] == "@":  # oben links
                        neighbour_count += 1
                if z > 0 and s < width-1:        
                    if input[z-1][s+1] == "@":  # oben rechts
                        neighbour_count += 1
                if z < height-1 and s > 0:        
                    if input[z+1][s-1] == "@":  # unten links
                        neighbour_count += 1
                if z < height-1 and s < width-1:        
                    if input[z+1][s+1] == "@":  # unten rechts
                        neighbour_count += 1
                # print(f"Found @ at ({z},{s}) with {neighbour_count} # neighbours")
                if neighbour_count < 4:
                    kill_list.append((z,s))
                    accessable += 1
    if len(kill_list) == 0:
        print(f"No more kills possible  after {round_counter-1} , exiting loop")
        break
    for (z,s) in kill_list:
        input[z][s] = "."
print(f"Total accessable positions: {accessable}")