data = open("day11_input.txt").read().strip().split("\n")
# print(data)

dict_connections = {}
for line in data:
    # nodes is now part before :
    rest = line.split(":")
    node = rest[0].strip()
    # rest are the neighbours, seperated by space
    if len(rest) > 1 and rest[1].strip():
        neighbours = rest[1].strip().split()
        dict_connections[node] = neighbours
    else:
        dict_connections[node] = []

# dictionary as memory
memo = {}

def count_paths(node):
    # if we arrive at out, we count it as one path
    if node == "out":
         return 1

    # if we"ve already been here, we can just return our result for that node
    if node in memo:
        return memo[node]

    # not even out, just cul de sac or whatever the english term for sackgasse is
    if node not in dict_connections: 
        return 0

    total_paths = 0

    # paths starting from here is sum of all paths of neighbours
    for neighbour in dict_connections[node]:
        total_paths += count_paths(neighbour)

    memo[node] = total_paths
    return total_paths

def count_paths2(node, target, current_mem):
    # arrived at target
    if node == target:
        return 1
    # analogue to count_paths, we can just return result, if we already have one
    if node in current_mem:
        return current_mem[node]

    # sackgasse or node doesnt exist
    if node not in dict_connections:
        return 0
    
    total = 0

    for neighbour in dict_connections[node]:
        total += count_paths2(neighbour, target, current_mem)

    current_mem[node] = total
    return total

# scenario 1 svr -> dac -> fft -> out
w_svr_dac = count_paths2("svr", "dac", {})
w_dac_fft = count_paths2("dac", "fft", {})
w_fft_out = count_paths2("fft", "out", {})
total_scenario_1 = w_svr_dac * w_dac_fft * w_fft_out

# scenario 2 svr -> fft -> dac -> out
w_svr_fft = count_paths2("svr", "fft", {})
w_fft_dac = count_paths2("fft", "dac", {})
w_dac_out = count_paths2("dac", "out", {})
total_scenario_2 = w_svr_fft * w_fft_dac * w_dac_out

# part 2 both scenarios
part2_result = total_scenario_1 + total_scenario_2


print(f"Part1: {count_paths("you")}")
print(f"Part2: {part2_result}")
# count_paths is now depricated as choosing "you" as start and "out" as target 
# creates full functionality to solve part 1
# will keep it in code, for ducumentation purposes
print(f"Part1, using function of Part2: {count_paths2("you", "out", {})}")