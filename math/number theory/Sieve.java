package math.number_theory;

public class Sieve {

   static boolean[] isPrime;

   /*
   * 1. Sieve to generate primes
   */
   static void sieve(int n){
      isPrime = new boolean[n + 1];

      for(int k = 0; k <= n; k++){
         isPrime[k] = true;
      }

      for(int i = 2; i*i <= n; i++)
      {
         if (isPrime[i])
         {
            for(int j = i*i; j <= n; j += i){
               isPrime[j] = false;
            }
         }
      }

      isPrime[0] = isPrime[1] = false;
   }

   /*
   * 2. Segmented Sieve to generate primes in a range
   * mainly used for primes with large values (values > 1e7)
   * n is the left boundry
   * m is the right boundry
   * after running this method the Array isPrime will indicate
   * if the number is prime or not with an offset n (isPrime[0] == isPrime[n])
   */
   static void segmentedSieve(int n, int m){
      int r = (int) Math.sqrt(m);

      if (r < n) {
         boolean[] tempPrimes = new boolean[r + 1];

         for(int k = 0; k <= r; k++){
            tempPrimes[k] = true;
         }

         for(int i = 2; i*i <= r; i++)
         {
            if (tempPrimes[i])
            {
               for(int j = i*i; j <= r; j += i){
                  tempPrimes[j] = false;
               }
            }
         }

         isPrime = new boolean[m-n+1];

         for(int i = 0; i < m-n+1; i++){
            isPrime[i] = true;
         }

         for(int i = 2; i <= r; i++)
         {
            if (tempPrimes[i])
            {
               int l = (n / i) * i;
               if (l < n){
                  l += i;
               }

               for(;l <= m; l+=i)
               {
                  isPrime[l - n] = false;
               }
            }
         }
      }
      else
      {
         isPrime = new boolean[m+1];

         for(int k = 0; k <= m; k++){
            isPrime[k] = true;
         }

         for(int i = 2; i*i <= m; i++)
         {
            if (isPrime[i])
            {
               for(int j = i*i; j <= m; j += i){
                  isPrime[j] = false;
               }
            }
         }

         isPrime[0] = isPrime[1] = false;
      }
   }
}
