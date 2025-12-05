grid = open("day05_input.txt").read().strip().split("\n\n")
ranges_raw = grid[0].split("\n")
ids = grid[1].split("\n")

ranges = []
for line in ranges_raw:
    first, last = map(int, line.split("-"))
    ranges.append((first, last))

ranges.sort()
merged_ranges = []
# für Marc zur Erklärung: if ranges ist quasi if ranges.length > 0
if ranges:
    start, end = ranges[0]
    for i in range(1,len(ranges)):
        next_start, next_end = ranges[i]
        if next_start <= end + 1:
            end = max(end, next_end)
        else:
            merged_ranges.append((start, end))
            start, end = next_start, next_end
    merged_ranges.append((start, end))

fresh = 0
fresh2 = 0
fresh_ids = []
for start, end in merged_ranges:
    count = (end-start+1)
    fresh += count
print(f"total frehs: {fresh}")
