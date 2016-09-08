package math;

public class Basics {

   static final double PI = 3.1415926536;

   /*
   * 1. Greatest common divisor
   */
   static int gcd( int a,  int b) {
      return b == 0 ? a : gcd(b, a % b);
   }

   /*
   * 2. least common multiple
   */
   static int lcm(int a,int b) {
      return (a * b) / gcd(a, b);
   }

   /*
   * 3. The number of digits of a number in a given base
   */
   static int DigitCount(long x, int base){
      return (int) (1 + log(x, base));
   }

   /*
   * 4. The nth root of the number
   */
   static double nthRoot(int x, int n){
      return Math.pow(x, 1 / (double) n);
   }

   /*
   * 5. log formula
   */
   static double log(long x, int base){
      return (Math.log10(x) / Math.log10(base));
   }

   /*
   * 6. The sum of the first n terms in an arethmatic progression [a, (a + d), (a + 2 * d), (a + 3 * d), ...]
   */
   static int sumArethmaticProgression(int n, int a, int d){
      return (n / 2) * (2 * a + (n - 1) * d);
   }

   /*
   * 7. The sum of the first n terms in a geometric series [a, (a * r), (a * r ^ 2), (a * r ^ 3), ...]
   */
   static int sumGeometricSeries(int n, int a, int r){
      return a * ((1 - pow(r, n)) / (1 - r));
   }

   /*
   * 8. Double variables comparison
   *
   *   Integers   |       Doubles
   *
   *    a == b    |     Math.abs(a - b) < EPS
   *    a <= b    |     a < b + EPS
   *    a >= b    |     a + EPS > b
   *    a < b     |     a + EPS < b
   *    a > b     |     a > b + EPS
   */
}
