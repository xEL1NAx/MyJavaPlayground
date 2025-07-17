package School.tEsT;

public class test_001 {
   public static void main (String args[]) {
       int alter=Integer.parseInt(args[0]);
       if (alter < 18)
           System.out.println ("Du bis noch nicht volljährig");
       else
           System.out.println ("Du darfs rein");
     }
}