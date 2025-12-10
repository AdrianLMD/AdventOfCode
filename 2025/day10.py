import re
from collections import deque
data = open("day10_input.txt").read().strip().split("\n")

def repair_machine(line):
    # regex to find pattern in brackets [...]
    target_match = re.search(r'\[([.#]+)\]', line)
    if not target_match:
        return 0  # No target pattern found
    # sets target_pattern to the pattern found in brackets
    target_pattern = target_match.group(1)
    num_lights = len(target_pattern)

    # convert string to integer bitmask (so i can do bitwise operations)
    # por exemplo: "##..#" is 11001 in binary or 25 in decimal
    # using an xor operator we can simply 1^1 = 0 or 0^1 = 1 to toggle bits
    # index 0 is bit 0
    target_mask = 0
    for i, char in enumerate(target_pattern):
        if char == "#":
            # bit i is set to 1
            # |= is bitwise OR assignment
            # << is left shift
            target_mask |= (1 << i)
    
    # parse buttons
    # aka finding content insice parentheses (...) yay regex again
    # idea: we have 4 lights, button (0,2) toggles lights 0 and 2
    # light0 = 0001 (1 in decimal)
    # light2 = 0100 (4 in decimal)
    # button mask = 0101 (5 in decimal)
    button_match = re.findall(r'\(([\d,]+)\)', line)
    buttons = []
    for b_str in button_match:
        # string "0,2" gets turned into list of integers [0,2]
        indices  = [int(x) for x in b_str.split(",")]
        b_mask = 0
        for idx in indices:
            # take a 1 and shift it left idx-times to the left
            # then OR it to the button mask
            # e.g., idx=2 -> 1 << 2 = 00000100
            # b_mask |= 00000100
            b_mask |= (1 << idx)
        # button is now represented as a bitmask, which we can use to toggle lights
        buttons.append(b_mask)

    # bfs initialization
    queue = deque([(0,0)]) # queue stores (current_light_state, num_of_pressses)
    visited = set() # found out hard way to keep track of visited states (preventing cycles)
    visited.add(0)
    while queue:
        # next state (fifo)
        # oldest state that we have not processed yet
        current_mask, presses = queue.popleft()

        # reached target?
        # is our current light state equal to the target light state?
        if current_mask == target_mask:
            return presses  # yes! and since bfs finds shortest path, this is minimum presses, thanks TheoInfo Prof
        
        # press each button
        for b_mask in buttons:
            new_mask = current_mask ^ b_mask # pressing button toggles bits (XOR operation)

            # did we already visit this state?
            # if so, we skip it
            if new_mask not in visited:
                visited.add(new_mask)
                # if not, we add it to the queue with incremented press count
                queue.append((new_mask, presses + 1))
    return 0  # if no solution found (shoulnt happen, hopefully lol)

total_presses = 0
for line in data:
    if not line.strip(): 
        continue
    presses = repair_machine(line)
    total_presses += presses
print(f"Total button presses needed (hopefully lowest possible): {total_presses}")