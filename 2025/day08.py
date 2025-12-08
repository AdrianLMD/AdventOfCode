import math
data = open("2025/day08_input.txt").read().strip().split("\n")
points = []
for line in data:
    coords = [int (i) for i in line.split(",")]
    points.append(coords)
limit = 1000

def part2():
    pairs = []
    point_count = len(points)

    for i in range(point_count):
        for j in range(i+1,point_count): #i+1 so we dont check A-B as B-A again
            p1 = points[i]
            p2 = points[j]

            # eucledian distance
            distance = math.dist(p1,p2)
            pairs.append((distance, i, j))

    # sort by distance, smallest fist
    pairs.sort()

    # in the beninging, every point is its own circuit
    circuits = []
    for i in range(point_count):
        circuits.append({i})

    for distance, index_a, index_b in pairs:

        buck_a = None
        buck_b = None

        for c in circuits:
            if index_a in c:
                buck_a = c
            if index_b in c:
                buck_b = c
            # if we found both, we can stop looking
            if buck_a is not None and buck_b is not None:
                break   
        # if in different buckets -> connect
        if buck_a != buck_b:
            buck_a.update(buck_b)
            circuits.remove(buck_b)

            # literally the only new thing lmao
            # do we have only one big circuit?
            if len(circuits) == 1:
                p1 = points[index_a]
                p2 = points[index_b]

                res = p1[0] * p2[0]
                print(f"multiplied x-coords of furthest points: {res}")


def part1(int: limit):
    pairs = []
    point_count = len(points)

    for i in range(point_count):
        for j in range(i+1,point_count): #i+1 so we dont check A-B as B-A again
            p1 = points[i]
            p2 = points[j]

            # eucledian distance
            distance = math.dist(p1,p2)
            pairs.append((distance, i, j))

    # sort by distance, smallest fist
    pairs.sort()

    # in the beninging, every point is its own circuit
    circuits = []
    for i in range(point_count):
        circuits.append({i})

    # connect
    connections_made = 0

    for distance, index_a, index_b in pairs:
        if connections_made >= limit:
            break

        # in which circuit is A and B currently
        buck_a = None
        buck_b = None

        # search in list of circuits
        for c in circuits:
            if index_a in c:
                buck_a = c
            if index_b in c:
                buck_b = c
        
        # if in different buckets -> connect
        # print for debugging reasons, as I was having some issues 
        print(f"step {connections_made+1}: connect {index_a} abd {index_b} (Dist: {distance:.2f})")
        if buck_a != buck_b:
            # everything from B in A
            buck_a.update(buck_b)
            # delete old bucket B 
            circuits.remove(buck_b)
            print("MERGE")
        else:
            print("NO MERGE (already merged)")
        
        connections_made += 1

    sizes = []
    for c in circuits:
        sizes.append(len(c))
    # sort, biggest first
    sizes.sort(reverse=True)
    # mult top 3 
    result = 1
    for s in sizes[:3]:
        result *= s
    print(f"Largest 3 ciruits, multiplied: {result}")

# part1(limit)
part2()