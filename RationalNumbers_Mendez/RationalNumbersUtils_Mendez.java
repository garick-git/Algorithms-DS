//Implemented by Garick Mendez Padilla

package rationalnumbers;
import rationalnumbers.RationalNumberImpl_Mendez;

public class RationalNumbersUtils_Mendez 
{

		//rv = r1+r2 
		public static RationalNumber add(RationalNumber r1, RationalNumber r2)
		{
			assert r1 != null: "r1 is null!";
			assert r2 != null: "r2 is null!";
			int commonDenominator = r1.getDenominator() * r2.getDenominator();
			int num1 = r1.getNumerator() * r2.getDenominator();
			int num2 = r2.getNumerator() * r1.getDenominator();
			int sum = num1 + num2;

			RationalNumber rationalnum = new RationalNumberImpl_Mendez(sum, commonDenominator);
			return rationalnum;
			
		}
		//rv = r1 - r2
		public static RationalNumber subtract(RationalNumber r1, RationalNumber r2)
		{
			assert r1 != null: "r1 is null!";
			assert r2 != null: "r2 is null!";
			int commonDenominator = r1.getDenominator() * r2.getDenominator();
			int num1 = r1.getNumerator() * r2.getDenominator();
			int num2 = r2.getNumerator() * r1.getDenominator();
			int difference = num1 - num2;
			
			return new RationalNumberImpl_Mendez(difference,commonDenominator);
		}
		// rv = r1*r2
		public static RationalNumber multiply(RationalNumber r1, RationalNumber r2)
		{
			assert r1 != null: "r1 is null!";
			assert r2 != null: "r2 is null!";
			int numerator = r1.getNumerator() * r2.getNumerator();
			int denominator = r1.getDenominator() * r2.getDenominator();
			
			return new RationalNumberImpl_Mendez(numerator,denominator);
		}
		//rv =  r1/r2 reverse num and denom for r2 then multiply 
		public static RationalNumber divide(RationalNumber r1, RationalNumber r2)
		{
			assert r1 != null: "r1 is null!";
			assert r2 != null: "r2 is null!";
			assert r1.getNumerator() != 0: "Numerator with 0 can't be divided";
			assert r2.getNumerator() != 0: "Numerator with 0 can't be divided";
			
			int numerator2 = r2.getDenominator();
			int denominator2 = r2.getNumerator();
			
			int numerator3 = r1.getNumerator() * numerator2;
			int denominator3 = r1.getDenominator() * denominator2;
			
			return new RationalNumberImpl_Mendez(numerator3,denominator3);
		}
		// rv = -r1
		public static RationalNumber negate(RationalNumber r1)
		{
			assert r1 != null: "r1 is null!";
			int numerator3 = -(r1.getNumerator());
			int denominator3 = (r1.getDenominator());
			return new RationalNumberImpl_Mendez(numerator3,denominator3);
		}

		
}