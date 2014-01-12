
public class PercolationStats {
	//field
	private double[] percoThresh;
	private double n, t;
	
	// perform T independent computational experiments on an N-by-N grid
	public PercolationStats(int N, int T){
		if(N < 1 || T < 1 )
			throw new IllegalArgumentException();
		n = (double)N;
		t = (double)T;
		percoThresh = new double[T];
		for(int k = 0; k < T; ++k){
			Percolation exp = new Percolation(N);
			double count = 0;
			int i, j;
			while(count <= N*N){
				i = StdRandom.uniform(1, N+1);
				j = StdRandom.uniform(1, N+1);
				if(exp.isOpen(i, j))
					continue;
				else{
					exp.open(i, j);
					count++;
				}
				if(exp.percolates())
					break;
			}
			//System.out.println("count = " + count + "for " + k);
			percoThresh[k] = count / (n*n);
		}
		
	}
	
	// sample mean of percolation threshold
	public double mean(){
		double sum = 0;
		for(int i = 0; i < t; ++i){
			sum += percoThresh[i];
		}
		return sum / t; 
	}
	
	// sample standard deviation of percolation threshold
	public double stddev(){
		double sum = 0;
		double meanThresh = mean();
		for(int i = 0; i < t; ++i){
			sum += Math.pow(percoThresh[i] - meanThresh, 2);
		}
		return Math.sqrt(sum / (t - 1));
	}
	
	// returns lower bound of the 95% confidence interval
	public double confidenceLo(){
		double low;
		low = mean() - 1.96 * stddev() / Math.sqrt(t);
		return low;
	}
	
	// returns upper bound of the 95% confidence interval
	public double confidenceHi(){
		double high;
		high = mean() + 1.96 * stddev() / Math.sqrt(t);
		return high;
	}

	// test client, described below
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		PercolationStats ps = new PercolationStats(200, 100);
		//System.out.println("n = " + ps.n + "t = " + ps.t);
		System.out.println("mean = " + ps.mean());
		System.out.println("stddev = " + ps.stddev());
		System.out.println("95% confidence interval = " + ps.confidenceLo() +", " + ps.confidenceHi());
		
		PercolationStats ps1 = new PercolationStats(2, 10000);
		System.out.println("mean = " + ps1.mean());
		System.out.println("stddev = " + ps1.stddev());
		System.out.println("95% confidence interval = " + ps1.confidenceLo() +", " + ps1.confidenceHi());
		
		PercolationStats ps2 = new PercolationStats(2, 100000);
		System.out.println("mean = " + ps2.mean());
		System.out.println("stddev = " + ps2.stddev());
		System.out.println("95% confidence interval = " + ps2.confidenceLo() +", " + ps2.confidenceHi());
		

	}

}
