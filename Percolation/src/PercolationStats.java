import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private double[] probs;
	private int samples;
	private double z;
	
	//Perform trials independent experiments on an n-by-n grid.
	public PercolationStats(int n, int trials) {
		if(n<=0)
			throw new IllegalArgumentException("Grid size should be greater than 0.");
		if(trials<=0)
			throw new IllegalArgumentException("The trails should be greate than 0.");
		
		this.samples = trials;
		this.z = 1.96;
		this.probs = new double[trials];
		for(int i=0; i<trials; i++) {
			Percolation percolation = new Percolation(n);
			int row, col;
			while(!percolation.percolates()) {
				row = StdRandom.uniform(1, n+1);
				col = StdRandom.uniform(1, n+1);
				percolation.open(row, col);
			}
			this.probs[i] = (1.0*percolation.numberOfOpenSites())/(n*n);
		}
	}
	
	//Sample mean of percolation threshold.
	public double mean() {
		return StdStats.mean(this.probs);
	}
	
	//Sample standard deviation of percolation threshold.
	public double stddev() {
		return StdStats.stddev(this.probs);
	}
	
	//Low  endpoint of 95% confidence interval.
	public double confidenceLo() {
		double mean = this.mean();
		double stddev = this.stddev();
		double low = mean - (this.z*(stddev/Math.sqrt(this.samples)));
		return low;
	}
	
	//High endpoint of 95% confidence interval.
	public double confidenceHi() {
		double mean = this.mean();
		double stddev = this.stddev();
		double high = mean + (this.z*(stddev/Math.sqrt(this.samples)));
		return high;
	}
	
	//Test client (described below).
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		
		PercolationStats ps = new PercolationStats(n, T);
		String msg = "% java PercolationStats " + T + " " + n;
		msg = msg + "\nmean                    = " + ps.mean();
		msg = msg + "\nstddev                  = " + ps.stddev();
		msg = msg + "\n95% confidence interval = [" + ps.confidenceLo();
		msg = msg + ", " + ps.confidenceHi() + "]";
		System.out.println(msg);
	}
}
