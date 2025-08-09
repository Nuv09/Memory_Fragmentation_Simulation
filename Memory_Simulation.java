

import java.util.*;

// Class representing a memory block
class MemoryBlock {
    int size;
    int startAddress;
    int endAddress;
    boolean isAllocated;
    String processID;
    int internalFragmentation;

    // Constructor to initialize memory block attributes
    public MemoryBlock(int size, int startAddress) {
        this.size = size;
        this.startAddress = startAddress;
        this.endAddress = startAddress + size - 1;
        this.isAllocated = false;
        this.processID = "Null";
        this.internalFragmentation = 0;
    }
}

public class Memory_Simulation {
    private List<MemoryBlock> memoryBlocks;
    private Scanner scanner;
    private int allocationStrategy; // 1 for First-Fit, 2 for Best-Fit, 3 for Worst-Fit

    // Constructor to initialize memory list and scanner
    public Memory_Simulation() {
        memoryBlocks = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    // Method to initialize memory blocks based on user input
    public void initializeMemory() {
        System.out.print("Enter the total number of blocks: ");
        int M = scanner.nextInt();
        System.out.println("Enter the size of each block in KB: ");
        int startAddress = 0;
        
        for (int i = 0; i < M; i++) {
            int size = scanner.nextInt();
            memoryBlocks.add(new MemoryBlock(size, startAddress));
            startAddress += size;
        }
        
        System.out.print("Enter allocation strategy (1 for first-fit, 2 for best-fit, 3 for worst-fit): ");
        allocationStrategy = scanner.nextInt();
        System.out.println("Memory blocks are created…");
        displayMemoryStatus(false);
    }

// Modified displayMemoryStatus to support detailed or simple view
public void displayMemoryStatus(boolean detailed) {
    System.out.println("Memory blocks:");
    if (detailed) {
        System.out.println("==========================================================================================");
        System.out.printf("%-8s %-8s %-15s %-12s %-12s %-20s\n", 
            "Block#", "Size", "Start-End", "Status", "ProcessID", "InternalFragmentation");
        System.out.println("==========================================================================================");
    } else {
        System.out.println("============================================");
        System.out.printf("%-8s %-8s %-15s %-10s\n", 
            "Block#", "Size", "Start-End", "Status");
        System.out.println("============================================");
    }

    int i = 0;
    for (MemoryBlock block : memoryBlocks) {
        if (detailed) {
            System.out.printf("%-8d %-8d %-15s %-12s %-12s %-20d\n", 
                i++, 
                block.size, 
                block.startAddress + "-" + block.endAddress, 
                block.isAllocated ? "allocated" : "free", 
                block.processID, 
                block.internalFragmentation
            );
        } else {
            System.out.printf("%-8d %-8d %-15s %-10s\n", 
                i++, 
                block.size, 
                block.startAddress + "-" + block.endAddress, 
                block.isAllocated ? "allocated" : "free"
            );
        }
    }

    if (detailed) {
        System.out.println("==========================================================================================");
    } else {
        System.out.println("============================================");
    }
}


    // Method to run the simulation with a menu-driven interface
    public void runSimulation() {
        initializeMemory();
        boolean running = true;
        
        while (running) {
            System.out.println("1) Allocate memory block");
            System.out.println("2) Deallocate memory block");
            System.out.println("3) Print memory status");
            System.out.println("4) Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    allocateMemory();
                    break;
                case 2:
                    deallocateMemory();
                    break;
                case 3:
                    displayMemoryStatus(true);
                    break;
                case 4:
                    running = false;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    // Method to allocate memory based on the chosen strategy
    public void allocateMemory() {
        System.out.print("Enter Process ID and size: ");
        String processID = scanner.next();
        int processSize = scanner.nextInt();
        
        MemoryBlock selectedBlock = null;
        
        if (allocationStrategy == 1) { // First-Fit
            for (MemoryBlock block : memoryBlocks) {
                if (!block.isAllocated && block.size >= processSize) {
                    selectedBlock = block;
                    break;
                }
            }
        } else if (allocationStrategy == 2) { // Best-Fit
            int minSize = Integer.MAX_VALUE;
            for (MemoryBlock block : memoryBlocks) {
                if (!block.isAllocated && block.size >= processSize && block.size < minSize) {
                    minSize = block.size;
                    selectedBlock = block;
                }
            }
        } else if (allocationStrategy == 3) { // Worst-Fit
            int maxSize = Integer.MIN_VALUE;
            for (MemoryBlock block : memoryBlocks) {
                if (!block.isAllocated && block.size >= processSize && block.size > maxSize) {
                    maxSize = block.size;
                    selectedBlock = block;
                }
            }
        }
        
        if (selectedBlock != null) {
            selectedBlock.isAllocated = true;
            selectedBlock.processID = processID;
            selectedBlock.internalFragmentation = selectedBlock.size - processSize;
            System.out.println(processID + " allocated at address " + selectedBlock.startAddress + ", and the internal fragmentation is " + selectedBlock.internalFragmentation);
        } else {
            System.out.println("Memory allocation failed: Not enough space.");
        }
    }
    
    // Method to deallocate memory and free up space
    public void deallocateMemory() {
        System.out.print("Enter Process ID to deallocate: ");
        String processID = scanner.next();
        
        for (MemoryBlock block : memoryBlocks) {
            if (block.isAllocated && block.processID.equals(processID)) {
                block.isAllocated = false;
                block.processID = "Null";
                block.internalFragmentation = 0;
                System.out.println(processID + " has been deallocated.");
                return;
            }
        }
        
        System.out.println("Process ID not found.");
    }

    // Main method to start the simulation
    public static void main(String[] args) {
        Memory_Simulation simulation = new Memory_Simulation();
        simulation.runSimulation();
    }
}