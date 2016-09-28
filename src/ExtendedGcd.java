public class ExtendedGcd {

	public static long gcd(long a, long b) {
		if (b == 0) {
			return a;
		} else {
			return gcd(b, a % b);
		}
	}

	public static long[] extendedGcd(long a, long b) {
		if (b == 0) {
			if (a > 0) {
				return new long[] { a, 1, 0 };
			} else {
				return new long[] { -a, -1, 0 };
			}
		} else {
			long[] prev = extendedGcd(b, a % b);
			return new long[] { prev[0], prev[2], prev[1] - a / b * prev[2] };
		}
	}

	public static void main(String[] args) {

		System.out.println("gcd of 42 and 35 is " + gcd(42L, 35L));

		long[] ext = extendedGcd(42, 35);
		System.out.println("42*" + ext[1] + " + 35*" + ext[2] + " = " + ext[0]);
		ext = extendedGcd(768, 30);
		System.out.println("768*" + ext[1] + " + 30*" + ext[2] + " = " + ext[0]);

	}
}
