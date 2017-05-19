
public class FA_Updated {

	public static void main(String[] args) {
		int x[]=search("AABAACAADAABAAABAA", "AABA");
		for(int i = 0;i<x.length;i++) 
		   System.out.print(x[i]+" ");
	}
	static void computeTF(String s,int ls, int TF[][])
	{
		int i,lps=0,x;
	    for(x=0;x<256;x++)
	       TF[0][x] = 0;
	    TF[0][(int)s.charAt(0)] = 1;
	    for(i=1;i<=ls;i++)
	    {
	        for(x=0;x<256;x++)
	            TF[i][x] = TF[lps][x];
	        if(i<ls)
	        {
	        	 TF[i][(int)s.charAt(i)]=i+1;
	          lps=TF[lps][(int)s.charAt(i)];
	        }
	    }
	}
	static int[] search(String s,String p)
	{
	  int ls=s.length(),lp=p.length();
	  int TF[][]=new int[lp+1][256],ans[]=new int[ls];
	  computeTF(p,lp,TF);
	  int i, state=0;
	  for (i = 0; i < ls; i++)
	  {
		  state = TF[state][(int)s.charAt(i)];
		  if(state==lp)
			  ans[i-lp+1]=1;
	  }
	  return ans;
	}
}
