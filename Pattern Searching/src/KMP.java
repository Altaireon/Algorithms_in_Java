public class KMP {

	public static void main(String[] args) {
		
		int x[]=KMPSearch("AABAACAADAABAAABAA", "AABA");
		for(int i = 0;i<x.length;i++) 
		   System.out.print(x[i]+" ");
	}
    static int[] KMPSearch(String s,String p)
    {
    	int ls=s.length(),lp=p.length(),j=0;
    	int ans[]=new int[ls],prefixArr[]=prefixArray(p);
    	for(int i = 0;i<ls;)
    	{
    		if(p.charAt(j)==s.charAt(i))
    		{
    			j++;
    			i++;
    		}
    		else
    		{
    			if(j!=0)
    				j=prefixArr[j-1];
    			else i++;
    		}
    		if(j==lp)
    		{
    			ans[i-lp]=1;
    			j=prefixArr[j-1];
    		}
    	}
    	return ans;
    }
    static int[] prefixArray(String s)
    {
    	int ls=s.length(),j=0;
    	int prefixArr[]=new int[ls];
    	for(int i = 1;i<ls;)
    	{
    		if(s.charAt(j)==s.charAt(i)){
    			j++;
    			prefixArr[i]=j;
    			i++;
    		}
    		else
    		{
    			if(j!=0)
    			{
    				j=prefixArr[j-1];
    			}
    			else
    			{
    				prefixArr[i]=j;
    			    i++;
    			}
    		}
    	}
    	return prefixArr;
    }
}
