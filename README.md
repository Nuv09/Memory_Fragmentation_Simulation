# Memory-Fragmentation-Simulation

## Overview
This Java program simulates memory allocation using three different strategies:
- **First-Fit**: Allocates the first available block that fits the process.
- **Best-Fit**: Allocates the smallest available block that fits the process.
- **Worst-Fit**: Allocates the largest available block that fits the process.

The program allows users to create memory blocks, allocate processes using the chosen strategy, deallocate processes, and view the current memory status.

## Features
- Users can define memory blocks with custom sizes.
- Supports First-Fit, Best-Fit, and Worst-Fit allocation strategies.
- Displays memory status, including fragmentation details.
- Allows deallocation of processes.

## How to Run
1. Compile the Java file:
   ```sh
   javac MemorySimulation.java
   ```
2. Run the program:
   ```sh
   java MemorySimulation
   ```
3. Follow the on-screen instructions to:
   - Enter the number and sizes of memory blocks.
   - Select an allocation strategy.
   - Allocate and deallocate processes.
   - View the current memory status.

## Example Output
```
Enter the total number of blocks: 4
Enter the size of each block in KB: 300 200 100 400
Enter allocation strategy (1 for first-fit, 2 for best-fit, 3 for worst-fit): 2
Memory blocks are created…
==========================================================
Block#  Size   Start-End   Status     ProcessID   Fragmentation
==========================================================
Block0  300KB  0-299      free       Null        0KB
Block1  200KB  300-499    free       Null        0KB
Block2  100KB  500-599    free       Null        0KB
Block3  400KB  600-999    free       Null        0KB
==========================================================
1) Allocate memory block
2) Deallocate memory block
3) Print memory status
4) Exit
Enter your choice: 1
Enter Process ID and size: P1 60
P1 allocated at address 500, and the internal fragmentation is 40
```

## Code Structure
- **MemoryBlock Class**: Represents an individual memory block.
- **MemorySimulation Class**: Handles memory allocation, deallocation, and status display.
- **Main Method**: Runs the interactive simulation.

## Future Improvements
- Implement compaction to reduce fragmentation.
- Allow dynamic memory resizing.
- Enhance user interface for better interaction.

## Author
This project was developed as part of an Operating Systems assignment on memory fragmentation simulation.

