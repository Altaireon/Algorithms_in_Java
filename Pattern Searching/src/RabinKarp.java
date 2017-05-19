
public class RabinKarp {

	public static void main(String[] args) {
		int x[]=rabinkarpSearch("AABAACAADAABAAABAA", "AABA",256L);
		for(int i = 0;i<x.length;i++) 
		   System.out.print(x[i]+" ");

	}
	static int[] rabinkarpSearch(String s,String p,long key)
	{
		int ls=s.length(),lp=p.length(),i,j;
		long mod=1000000007,hashp=hash(p,0,lp-1,key,mod),hashs=hash(s, 0,lp-1, key, mod),del=power(key,lp-1,mod);
		int ans[]=new int[ls];
		for(i=0;i<=ls-lp;i++)
	    {
	        if(hashp==hashs)
	        {
	            for(j=0;j<lp;j++)
	            {
	                if(s.charAt(i+j)!=p.charAt(j))
	                    break;
	            }
	            if(j==lp)
	                ans[i]=1;
	        }
	        if(i<ls-lp)
	        {
	            hashs=(key*(hashs-s.charAt(i)*del)+s.charAt(i+lp))%mod;
	            if(hashs<0)
	            hashs=(hashs+mod);
	        }
	    }
		return ans;
	}
	static long hash(String s,int start,int end,long key,long mod)
	{
		long h=0;
		for(int i=start;i<=end;i++)
		{
			h=(h*key+(int)s.charAt(i))%mod;
		}
		return h;
	}
	static long power(long n,long p,long mod)
	{
		if(p==0)
			return 1;
		long temp=power(n,p/2,mod);
		if(p%2==0)
			return (temp*temp)%mod;
		else
			return (((n*temp)%mod)*temp)%mod;
	}
}
