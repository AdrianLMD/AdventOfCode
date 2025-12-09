import itertools
data = open("day09_input_test.txt").read().strip().split("\n")
points = []
# making tuples of coordinates [(1,2)(3,4),...]
for line in data:
    x, y = map(int, line.split(","))
    points.append((x, y))

# literally bruteforcing all areas and saving the largest
max_area = 0

# itertools.combination gives us automatically
# all unique pairs so if we had A and B, we wont get B and A again
for p1, p2 in itertools.combinations(points, 2):
    x1, y1 = p1
    x2, y2 = p2
    # area is inlcusive of starting and endoints
    area = (abs(x1-x2)+1) * (abs(y1-y2) + 1)
    if area > max_area:
        max_area = area
print(f"Largest found area: {max_area}")
