public class FA 
{
		public static void main(String[] args) {
			int x[]=search("AABAACAADAABAAABAA", "AABA");
			for(int i = 0;i<x.length;i++) 
			   System.out.print(x[i]+" ");
		}
		static int getNextState(String s, int ls, int state, int x)
		{
		  if(state<ls&&x==s.charAt(state))
			  return state+1;
		  int ns,i;
		  for(ns=state;ns>0;ns--)
		  {
			  if(s.charAt(ns-1)==x)
			  {
				  for(i = 0; i < ns-1; i++)
				  {
					  if(s.charAt(i)!=s.charAt(state-ns+1+i))
					  break;
				  }
				  if (i == ns-1)
					  return ns;
			  }
		  } 
		  return 0;
		}
		static void computeTF(String s,int ls, int TF[][])
		{
		  int state, x;
		  for(state=0;state<=ls;++state)
			  for(x=0;x<260;++x)
				  TF[state][x]=getNextState(s,ls,state,x);
		}
		static int[] search(String s,String p)
		{
		  int ls=s.length(),lp=p.length();
		  int TF[][]=new int[lp+1][260],ans[]=new int[ls];
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
