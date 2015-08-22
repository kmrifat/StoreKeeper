/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package custom;

/**
 *
 * @author rifat
 */
public class RandomIdGenarator {
    
   public static String randomstring(int lo, int hi){
                  int n = rand(lo, hi);
                  byte b[] = new byte[n];
                  for (int i = 0; i < n; i++)
                          b[i] = (byte)rand('0', '9');
                  return new String(b, 0);
          }

          private static int rand(int lo, int hi){
                      java.util.Random rn = new java.util.Random();
                  int n = hi - lo + 1;
                  int i = rn.nextInt(n);
                  if (i < 0)
                          i = -i;
                  return lo + i;
          }
          public static String randomstring(){
                  return randomstring(5, 5);
          }
    
}
