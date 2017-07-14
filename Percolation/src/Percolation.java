import edu.princeton.cs.algs4.QuickFindUF; 

public class Percolation {
	
	private int size;
	private boolean[][] grid;
	private QuickFindUF unionFind;
	private int openNum;
	private int down;
	private int top;
	
	//Create and n-by-n grid, with all sites blocked
	public Percolation(int n) {
		if(n<=0)
			throw new IllegalArgumentException("Grid size should be higher than 0.");
		
		this.size = n;
		this.grid = new boolean[size][size];
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				this.grid[i][j] = false;
			}
		}
		
		int elements = (n*n)+2;
		this.unionFind = new QuickFindUF(elements);
		
		this.openNum = 0;
		this.down = 0;
		this.top = this.unionFind.count()-1;
	}
	
	//Open a site(row, col), if it is not open already.
	public void open(int row, int col) {
		this.checkRowCol(row, col);
		
		if(!this.isOpen(row, col)) {
			this.grid[row-1][col-1] = true;
			this.openNum += 1;
			
			int p = this.calcPos(row, col);
			int q = -1;
			
			if(row==1) {
				this.unionFind.union(p, this.down);
			} else if(this.grid[row-2][col-1]) {
				q = this.calcPos(row-1, col);
				this.unionFind.union(p, q);
			}
			
			if(row==this.size) {
				this.unionFind.union(p, this.top);
			} else if(this.grid[row][col-1]) {
				q = this.calcPos(row+1, col);
				this.unionFind.union(p, q);
			}
			
			if(col>1 && this.grid[row-1][col-2]) {
				q = this.calcPos(row, col-1);
				this.unionFind.union(p, q);
			}
			
			if(col<this.size && this.grid[row-1][col]) {
				q =this.calcPos(row, col+1);
				this.unionFind.union(p, q);
			}
		}
	}
	
	//Check if site(row, col) is open.
	public boolean isOpen(int row, int col) {
		this.checkRowCol(row, col);
		
		return this.grid[row-1][col-1];
	}
	
	//Check if site(row, col) is connected to the top.
	public boolean isFull(int row, int col) {
		this.checkRowCol(row, col);
		
		int p = (this.size*(row-1))+col;
		return this.unionFind.connected(p, this.top);
	}
	
	//Return the number of open sites.
	public int numberOfOpenSites() {
		return this.openNum;
	}
	
	//Check if the system percolates.
	public boolean percolates() {
		return this.unionFind.connected(this.down, this.top);
	}
	
	//Throw an exception if the row or the col are not in the valid range.
	private void checkRowCol(int row, int col) {
		if(row<1 || row>this.size)
			throw new IllegalArgumentException("Row value should be between 1 to n.");
		
		if(col<1 || col>this.size)
			throw new IllegalArgumentException("Col value should be between 1 to n.");
	}
	
	//Calculate the position according to the site(row, col).
	private int calcPos(int row, int col) {
		this.checkRowCol(row, col);
		
		int calcPos = (this.size*(row-1))+col;
		return calcPos;
	}
	
	//Test client.
	public static void main(String[] args) {
		int n = 100;
		Percolation p = new Percolation(n);
		int row, col;
		while(!p.percolates()) {
			row = 1 + (int)(Math.random() * n);
			col = 1 + (int)(Math.random() * n);
			p.open(row, col);
		}
		double prob = (1.0*p.numberOfOpenSites())/(n*n);
		String msg = "Probability p: " + prob;
		System.out.println(msg);
	}
}
