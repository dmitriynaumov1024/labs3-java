# Lab 1

### Usage

1. Clone this repository.
2. Build this project using Ant.
3. Ensure you are in this directory (labs3-java/lab1) and execute ```java -jar dist/lab1.jar task<task number> [additional params]```

### Task list:

1. Write a program to calculate sum, difference, product and quotient of 2 numbers passed as command line arguments. Result should be printed on console.
2. Write a program to calculate factorial of a number passed as command line argument. Result should be printed on console.
3. Write a program to calculate length of hypotenuse of a triangle with known length of cathets passed as command line arguments. Result should be printed on console.
4. Write a program to calculate 2^n using loop.
5. Modify given program to calculate square root of a number passed as command line argument.
``` java
public class Sqrt {
    static void sqrt(long a){
        double b=a;
        int i=0;
        while ((b*b>a)&&(i<200)){
            b=(b+a/b)/2;
            i++;
        }
        System.out.print(b);
    }

    public static void main(String[] args) {
        sqrt(45);
    }
}
```
6. Write a program to calculate factorial of a number n<10 using loop.
7. (?) Implement given examples. 
``` java
public class CalcPi {
    static double pi;

    static void leibnic(){
        for(double i=1;i<100000000;i+=1){
            if (i%2==0){
                pi-=1/(2*i-1);
            }
            else{
                pi+=1/(2*i-1);
            }
        }
        pi*=4;
        System.out.print("\t\tnЧисло Пи (метод Лейбница): "+pi);
    }

    static void vallis(){
        double pi1=1, pi2=1;
        for(double i=2;i<100000000;i+=2){
           pi1*=i/(i+1);
           pi2*=i/(i-1);
        }
        pi=pi1*pi2*2;
        System.out.print("\t\tnЧисло Пи (метод Валлиса): "+pi);
    }

    public static void main(String[] args) {
        leibnic();
        vallis();
    }
}
```
``` java
public class JavaApplication2 {

    public static void work(){
        long x=0;
        for (int i=1;i<10;i++){
            for (int j=0;j<10;j++){
                for (int k=0;k<10;k++){
                    x=i*100+j*10+k;
                    if (x==(Math.pow(i,3)+Math.pow(j, 3) + Math.pow(k,3))){
                        System.out.println(i+"^3+"+j+"^3+"+k+"^3+"+" = "+x);
                    }
                }
            }
        }
    }
    public static void main(String[] args) {
        work();
    }
}
```

8. Write a program to check Ferma theorem a^n + b^n = c^n for numbers in range 1...100 and 2&lt;n&lt;10.
9. Given array A(n), where n is in range 1...25. Write a method to swap min and max element of array.
10. Given array B(n), write a method to sort the array in ascending or descending order.
11. Given array C(n), where n is in range 1...20. Write a method to find the average of array elements. 
12.  Given array D(n), where n is in range 1...30. Write a method to calculate sum of even and odd numbers separately.
13. Write a program to print a 3x5 table of random numbers of 0..<10 range.
14. Given strings s1, s2, s3, s4, s5. Write a method to: if s4 == s5, concat s1 with s2, else concat s1 with s3.
