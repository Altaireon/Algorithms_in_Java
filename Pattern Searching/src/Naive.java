import java.io.*;
public class Naive {
public static void main(String args[])throws IOException
   {
	   int x[]=naiveSearch("AABAACAADAABAAABAA", "AABA");
	   for(int i = 0;i<x.length;i++)
	   System.out.print(x[i]+" ");
   }
   static int[] naiveSearch(String s,String p)
   {
	   int j,i,ls,lp;
	   ls=s.length();
	   lp=p.length();
	   int ans[]=new int[ls];
	   for(i=0;i<=ls-lp;i++)
	   {
		   boolean flag=true;
		   for(j=i;j<i+lp;j++)
		   {
			   if(s.charAt(j)!=p.charAt(j-i))
			   {
				   flag=false;
				   break;
			   }
		   }
		   if(flag)
			   ans[i]=1;
		   else
			   ans[i]=0;
	   }
	   return ans; 
   }
}
