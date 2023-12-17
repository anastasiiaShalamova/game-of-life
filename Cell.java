package game_of_life;

class Cell {
	
    int row;
    int col;
    int state; 

    public Cell(int row, int col, int state) {
    	
        this.row = row;
        this.col = col;
        this.state = state;
    }
}