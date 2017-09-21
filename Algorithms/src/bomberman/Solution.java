package bomberman;

public class Solution {

	public static void main(String[] args) throws Exception {
		String inputFile = "/home/mparra/git/Algorithms/Algorithms/src/bomberman/input.txt";
		String outputFile = "/home/mparra/git/Algorithms/Algorithms/src/bomberman/output.txt";
		
		ReadFile reader = new ReadFile(inputFile);
		String inputStr = reader.getContent();
		
		reader = new ReadFile(outputFile);
		String outputStr = reader.getContent();
		
		// Load initial parameters.
		String[] initState = inputStr.split("\n");
		String[] param = initState[0].split(" ");
        int row = Integer.parseInt(param[0]);
        int col = Integer.parseInt(param[1]);
        int n = Integer.parseInt(param[2]);
        
        
        // Structure to store the grid state.
        int[][] grid =  new int[row][col];
        
        // Point 1.
        for(int i = 0; i < row; i++) {
            String line = initState[i+1];
            for(int j = 0; j < col; j++) {
                if(line.substring(j, j + 1).equals(".")) 
                    grid[i][j] = -1;
                if(line.substring(j, j + 1).equals("O")) 
                    grid[i][j] = 3;
            }
        }
        
        // Point 2.
        for(int i = 0; i < row; i++) {
        	for(int j = 0; j < col; j++) {
        		if(grid[i][j] > 0)
        			grid[i][j] = grid[i][j] - 1;
        	}
        }
        
        int step = 0;
        if(n > 1)
            step = (n -1) % 4 == 0 ? 4 : (n -1) % 4;
        
        for(int k = 1; k <= step; k++) {
        	if(k % 2 != 0) {
        		// Point 3.
        		for(int i = 0; i < row; i++) {
        			for(int j = 0; j < col; j++) {
        				if(grid[i][j] > 0)
        					grid[i][j] = grid[i][j] - 1;
        				else
        					grid[i][j] = 3;
        			}
        		}
        	} else {
        		// Point 4.
                for(int i = 0; i < row; i++) {
                    for(int j = 0; j < col; j++) {
                        if(grid[i][j] > 0)
                            grid[i][j] = grid[i][j] - 1;
                        if(grid[i][j] == 0) {
                            grid[i][j] = -1;
                            if(i > 0) grid[i-1][j] = -1;
                            if(j > 0) grid[i][j-1] = -1;
                            if(i + 1 < row && grid[i+1][j] != 1) grid[i+1][j] = -1;
                            if(j + 1 < col && grid[i][j+1] != 1) grid[i][j+1] = -1;
                        }
                    }
                }
        	}
        }
        
        // Print answer.
        String ans = "";
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == -1)
                    ans = ans + ".";
                else
                    ans = ans + "O";
            }
            ans = ans + "\n";
        }
        
        if(ans.equals(outputStr))
        	System.out.println("C O R R E C T");
        else
        	System.out.println("I N C O R R E C T");
	}

}
