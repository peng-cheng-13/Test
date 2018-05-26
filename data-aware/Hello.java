import java.io.File;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Hello
{
        public void Hello()
        {   
                System.out.println("hello() constructor run ...");
        }   
        public void print()
        {   
                System.out.println("hello.print() run");
        }   
        public static void StaticFunction()
        {   
                System.out.println("--- static void StaticFunction() run");
        }   
        public static int add(int a,int b)
        {   
                int c=a+b;
                System.out.println("--- static int add(int,int) run ---");
                System.out.println( a + "+"+ b + "=" + c );
                return a + b;
        }   
        public static String getS(String str)
        {   
                System.out.println("--- static int showString(String) run ---");
                System.out.println("static showString() receive -->" + str + " <--");
                return "Done";
        }   
}
