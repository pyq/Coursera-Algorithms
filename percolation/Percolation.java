public class Percolation {
    //field
   private static final int blocked = 0;
   private static final int open = 1;
    //static final int full = 2;
   private WeightedQuickUnionUF system;
   private WeightedQuickUnionUF bwCheck;
    private int[] status;
    private int gridN = 0;
    private int[] neighbor = new int[4];
    
    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        system = new WeightedQuickUnionUF(N * N + 2); // 0 - n*n + 1
        bwCheck = new WeightedQuickUnionUF(N * N + 1); // for check backwash
        gridN = N;
        //top to 0 bottom to n+1
        //for(int i = 1; i <= gridN; ++i){
         //   system.union(0, i);
          //  bwCheck.union(0, i); 
            //back wash
            //system.union(gridN * gridN - i + 1, gridN * gridN + 1);
        //}
        status = new int[N * N + 2];
        status[0] = open;
        status[N*N+1] = open;
        for(int i = 1; i < N * N + 1; ++i){
            status[i] = blocked;
        }
        
    }
            
    // open site (row i, column j) if it is not already
    public void open(int i, int j) {
    	if (i <= 0 || i > gridN || j <= 0 || j > gridN) 
    		throw new IndexOutOfBoundsException("row index i out of bounds");
        int n = xyTo1D(i , j);
        
        if(status[n] == blocked){
            status[n] = open;
            // use 2 virtual point for system  use 1 for bwCheck
            if(n <= gridN){
            	system.union(0, n);
            	bwCheck.union(0, n);
            }
            if(n > gridN * gridN - gridN)
            	system.union(n, gridN * gridN + 1);
            
            //connected to up bot l r;
            //up(-1, 0) bt(1, 0) l(0, -1) r(0 , 1)
            neighbor[0] = xyTo1D(i-1, j);
            neighbor[1] = xyTo1D(i+1, j);
            neighbor[2] = xyTo1D(i, j-1);
            neighbor[3] = xyTo1D(i, j+1);
            for(int k = 0; k < 4; ++ k){
                if(neighbor[k] >= 1)//mlgb bu yao duo chong pan duan!!!
                    if(status[neighbor[k]] == open){
                    	system.union(n, neighbor[k]);
                    	bwCheck.union(n, neighbor[k]);
                }
            }
        }
    }
    
    // is site (row i, column j) open?
    public boolean isOpen(int i, int j){
    	if (i <= 0 || i > gridN || j <= 0 || j > gridN) 
    		throw new IndexOutOfBoundsException("row index i out of bounds");
        return status[xyTo1D(i,j)] == open;
    }
    
    // is site (row i, column j) full?
    public boolean isFull(int i, int j){
    	if (i <= 0 || i > gridN || j <= 0 || j > gridN) 
    		throw new IndexOutOfBoundsException("row index i out of bounds");
        int n = xyTo1D(i,j);
        if(system.connected(0, n) && bwCheck.connected(0, n))
        	return true;
        else
        	return false;
        //return system[xyTo1D(i,j)]] == full;
    }

    // does the system percolate?
    public boolean percolates(){
    	//backwash method
    	/* for(int i = 1; i <= gridN; ++i){
             system.union(gridN * gridN - i + 1, gridN * gridN + 1);
         }*/
        return system.connected(0, gridN*gridN+1);
    	// regular
    	/*for (int j = 1; j <= gridN; ++j) {
    		int n = xyTo1D(gridN, j);
    		if(status[n] == open && system.connected(0, n))
    			return true;
    	}
    	return false;*/
    }
            
    // convert 2d to 1d   
    private int xyTo1D(int x, int y){
        if(x >= 1 && x <= gridN && y >= 1 && y <= gridN ){
            return  gridN * (x - 1) + y;
         }
        else
        	return 0;
        	//throw new IndexOutOfBoundsException("row index i out of bounds");  bu neng tou lan a 
    }
}