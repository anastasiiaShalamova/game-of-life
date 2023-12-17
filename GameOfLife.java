package game_of_life;

public class GameOfLife {
	
    private int size;
    private Cell[][] grid;
    private CircularQueue queue;

    public GameOfLife(int size) {
    	
        this.size = size;
        this.grid = new Cell[size][size];
        this.queue = new CircularQueue(size * size);
        initializeGrid();
    }
    
    // initializes the grid with random states
    private void initializeGrid() {
    	
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int state = Math.random() < 0.5 ? 0 : 1;
                grid[i][j] = new Cell(i, j, state);
            }
        }
    }
    
    // prints the current state of the grid
    public void printGrid() {
    	
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j].state == 1 ? "*" : " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    // updates the grid based on the rules of the game of Life
    public void update() {
    	
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int liveNeighbors = countLiveNeighbors(i, j);
                applyRules(grid[i][j], liveNeighbors);
            }
        }

        while (!queue.isEmpty()) {
            Cell cell = queue.dequeue();
            grid[cell.row][cell.col].state = cell.state;
        }
    }
    
    // counts the number of live neighbors around a cell
    private int countLiveNeighbors(int row, int col) {
    	
        int liveNeighbors = 0;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int neighborRow = (row + i + size) % size;
                int neighborCol = (col + j + size) % size;

                if (!(i == 0 && j == 0) && grid[neighborRow][neighborCol].state == 1) {
                    liveNeighbors++;
                }
            }
        }

        return liveNeighbors;
    }
    
    // applies the rules of the game
    private void applyRules(Cell cell, int liveNeighbors) {
    	
        if (cell.state == 1 && (liveNeighbors < 2 || liveNeighbors > 3)) {
            queue.enqueue(new Cell(cell.row, cell.col, 0));
        } 
        else if (cell.state == 0 && liveNeighbors == 3) {
            queue.enqueue(new Cell(cell.row, cell.col, 1));
        }
    }

    public static void main(String[] args) {
    	
        int gridSize = 4;
        GameOfLife game = new GameOfLife(gridSize);

        System.out.println("Initial State:");
        game.printGrid();

        for (int i = 0; i < 2; i++) {
            game.update();
            System.out.println("After " + (i + 1) + " iteration:");
            game.printGrid();
        }
    }
}