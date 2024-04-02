//Implemented by Garick Mendez Padilla

package rationalnumbers;

public class RationalNumberImpl_Mendez implements RationalNumber 
{
	//Set up private variables
	private int r1;
	private int r2;
	//Examples of Rational Numbers:
	// 0/4
	// 2/2
	// -2/4 = -1/2
	// 5/3
	// -2/-4 = 1/2
	// 2/-4 =  -1/2
	// 4/2 .getNumerator is 2
	public RationalNumberImpl_Mendez(int a, int b)
	{
		//pre: denominator can't be zero
		assert b !=0 : "Denominator can't be 0";
		
		//set greatest common denominator	
		int gcd = 0;
		
		boolean numIsNeg = false;
		boolean denomIsNeg = false;
		
		if(a==0)
		{
			this.r1=0;
			this.r2=1;
		}
		else
		{
			// find Greatest Common Denominator
			if(a<0)
			{
				a= a*(-1);
				numIsNeg = true;
			}
			if(b<0)
			{
				b = b*(-1);
				denomIsNeg = true;
			}
			for(int i = 1; i <= a && i <= b; i++)
			{
				if(a % i==0 && b % i==0)
					gcd = i;
			}
			//check for negatives
			if(numIsNeg && denomIsNeg)
			{
				this.r1 = a/gcd;
				this.r2 = b/gcd;
			}
			else if(numIsNeg && !denomIsNeg)
			{
				this.r1 =-(a)/gcd;
				this.r2= b/gcd;
			}
			else if(!numIsNeg && denomIsNeg)
			{
				this.r1 = -(a)/gcd; //keep the number with negative sign
				this.r2 = b/gcd;
			}
			else if(!numIsNeg && !denomIsNeg)
			{
				this.r1 = a/gcd;
				this.r2 = b/gcd;
			}
		}
				
		
	}
	public int getNumerator()
	{
		return this.r1;
	}
	public int getDenominator()
	{
		assert r2>0;
		
		return this.r2;
	}
	public double getValue()
	{
		return (double)this.r1 /(double)this.r2;
	}
	public String toString()
	{
		String fraction = r1 + "/" + r2;
		return fraction;
	}
	
}