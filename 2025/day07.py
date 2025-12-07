from collections import deque

data = open("day07_input.txt").read().strip().split("\n")
grid = [list(line) for line in data]
height = len(grid)
width = len(grid[0])

def part2():
    start = None
    for h in range(height):
        for w in range(width):
            if grid[h][w] == "S":
                start = (h,w)
                break

    # dict to store subproblems
    mem = {}
    def count_paths(h,w):
        # leaving bounds still is valid path
        if not (0 <= h < height and 0 <= w < width):
            return 1
        # we check, if we already were at this position
        if (h,w) in mem:
            return mem[(h,w)]
        char = grid[h][w]
        paths = 0
        if char == "^":
            # splits timeline
            # one going left, one right
            paths = count_paths(h, w-1) + count_paths(h,w+1)
        else:
            # cont timeline downwards
            paths = count_paths(h+1, w)
        # we save our result from this position, so if we ever arrrvie here agai
        # we dont have to count again and can just look it up
        mem[(h,w)] = paths
        return paths
    final_count_paths = count_paths(*start)
    print(f"The final count of paths is: {final_count_paths}")



def part1():
    start = None
    for h in range(height):
        for w in range(width):
            if grid[h][w] == "S":
                start = (h,w)
                break

    # queue for bfs, stores row,col (pos) of active beams
    queue = deque([start])

    # a set of beams, so we can keep track of merging beams/ prevent duplicates
    seen_beams = set()
    splitters_hit = set()

    while queue:
        h,w = queue.popleft()
        # boundry check
        if not (0 <= h < height and 0 <= w < width):
            continue

        # check whether we already procesed beam at that location
        if (h,w) in seen_beams:
            continue
        seen_beams.add((h,w))

        char = grid[h][w]

        if char == "^":
            splitters_hit.add((h,w))
            # if splitter is hit, we split beam
            queue.append((h, w-1))
            queue.append((h, w+1))
        else:
            # move further down
            queue.append((h+1, w))
    split_count = len(splitters_hit)
    print(f"The Tachyon-beam was split {split_count} times")

# part1()
part2()