import java.io.*;
import java.util.*;
public class PPATH {
    static int V;
	static int E;
	static ArrayList<Integer> graph[];
	static InputReader in=new InputReader(System.in);
	static PrintWriter out=new PrintWriter(System.out);
	@SuppressWarnings("unchecked")
	public static void main(String args[])
	{
	     int t;
	     int a,b;
	     t=in.readInt();
	     ArrayList<Long> sieve=segmented_sieve(1000L,10000L);
	     graph=new ArrayList[10001];
	     for(int i=0;i<10001;i++)
	    	 graph[i]=new ArrayList<Integer>();
	     int l=sieve.size();
	     for(int i=0;i<l;i++)
	     {
	    	 for(int j=0;j<l;j++)
	    	 {
	    		 int c=0;
	    		 int tp1=sieve.get(i).intValue(),tp2=sieve.get(j).intValue();
	    		 for(int k=0;k<4;k++)
	    		 {
	    			 if(tp1%10!=tp2%10)
	    				 c++;
	    			 tp1/=10;
	    			 tp2/=10;
	    		 }
	    		 if(c==1)
	    		 {
	    			 graph[sieve.get(i).intValue()].add(sieve.get(j).intValue());
	    			 graph[sieve.get(j).intValue()].add(sieve.get(i).intValue());
	    		 }
	    	 }
	     }
	     while(t-->0)
	     {
	    	a=in.readInt();
	    	b=in.readInt();
	    	Queue<Integer> q=new LinkedList<Integer>();
			q.add(a);
			boolean visited[]=new boolean[10001];
			visited[a]=true;
			int ans=0;
			boolean flag=false;
			q.add(-1);
			if(visited[b])
				out.println(0);
			else
			{
				l1:	while(!q.isEmpty())
					{
						int tp=q.remove();
						if(tp==-1){
							ans++;
							q.add(-1);
							continue;
						}
						for(int i:graph[tp])
						{
							if(!visited[i])
							{
								q.add(i);
								visited[i]=true;
							}
						}
						if(visited[b])
						{
							flag=true;
							break l1;
						}
					}
				if(flag)
				    out.println(ans+1);
				else
					out.println("Impossible");
	     	}
	     }
	     out.flush();
	     out.close();
	}
	public static ArrayList<Long> sieve(long n)
    {
    	int i,j;
    	ArrayList<Long> list=new ArrayList<Long>();
    	int n_n=(int)Math.sqrt(n);
    	boolean tp[]=new boolean[10000001];
    	tp[0]=tp[1]=true;
    	for(i=2;i<=n_n;i++)
    	{
    		if(!tp[i])
    		for(j=2*i;j<=n;j+=i)
    		{
    			tp[j]=true;
    		}
    	}
    	for(j=2;j<=n;j++)
    	  if(!tp[j])
    		list.add((long)j);
    	return list;
    }
    public static ArrayList<Long> segmented_sieve(long m,long n)
    {
    	long tp,lb,ub,j;
    	int n_n=(int)Math.sqrt(n);
    	ArrayList<Long> ans=sieve(n_n);
    	if(n_n<m)
    		lb=m;
    	else
    		lb=n_n+1;
    	ub=lb+n_n;
    	ArrayList<Long> ans1=new ArrayList<Long>();
    	while(ub<=n)
    	{
    		boolean mark[]=new boolean[(int)(ub-lb+2)];
    		for(long i:ans)
    		{
    			tp=(long)Math.ceil((double)lb/i)*i;
    			for(j=tp;j<=ub;j+=i)
    				mark[(int)(j-lb)]=true;
    		}
    		for(j=lb;j<=ub;j++)
    			if(!mark[(int)(j-lb)])
    				ans.add(j);
    		lb=ub+1;
    		ub=lb+n_n;
    		if(lb>n)
    			break;
    		if(ub>=n)
    			ub=n;
    	}
    	for(long i:ans)
    		if(i>=m&&i<=n)
    			ans1.add(i);
    	return ans1;
    }
}
/*class InputReader{
	private InputStream stream;
	private byte[] buf = new byte[1024];
	private int curChar;
	private int numChars;
	private SpaceCharFilter filter;
	
	public InputReader(InputStream stream){this.stream = stream;}
	public int read(){
		if (numChars==-1) throw new InputMismatchException();
		if (curChar >= numChars){
			curChar = 0;
			try {numChars = stream.read(buf);}
			catch (IOException e){throw new InputMismatchException();}
			if(numChars <= 0) return -1;
		}
		return buf[curChar++];
	}
 
	public int readInt(){
		int c = read();
		while(isSpaceChar(c)) c = read();
		int sgn = 1;
		if (c == '-') {sgn = -1;c = read();}
		int res = 0;
		do {
			if(c<'0'||c>'9') throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
		}
		while (!isSpaceChar(c)); return res * sgn;
	}
	
	public long readLong() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
        }
		long res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
        }
		while (!isSpaceChar(c));
            return res * sgn;
    }
	
	public double readDouble() {
        int c = read();
        while (isSpaceChar(c))
            c = read();
        int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = read();
        }
		double res = 0;
		while (!isSpaceChar(c) && c != '.') {
			if (c == 'e' || c == 'E')
				return res * Math.pow(10, readInt());
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = read();
        }
		if (c == '.') {
			c = read();
			double m = 1;
			while (!isSpaceChar(c)) {
				if (c == 'e' || c == 'E')
					return res * Math.pow(10, readInt());
				if (c < '0' || c > '9')
					throw new InputMismatchException();
				m /= 10;
				res += (c - '0') * m;
				c = read();
            }
        }
		return res * sgn;
    }
	
	public String readString() {
		int c = read();
		while (isSpaceChar(c))
			c = read();
		StringBuilder res = new StringBuilder();
		do {
			res.appendCodePoint(c);
			c = read();
		} while (!isSpaceChar(c));
		return res.toString();
	}
 
	public boolean isSpaceChar(int c) {
		if (filter != null)
			return filter.isSpaceChar(c);
		return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
		}
 
	public String next() {
		return readString();
	}
	
	public interface SpaceCharFilter {
		public boolean isSpaceChar(int ch);
	}
}*/
