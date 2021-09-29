package mathdemo;

public class MathDemo {

    public static void main(String[] args) {
        System.out.println("Примеры вызова функций:");
        // Вычисление экспоненты:
        System.out.println("exp(1) = " + MyMath.Exp(1, 30));
        // Вычисление синуса:
        System.out.println("sin(pi) = " + MyMath.Sin(Math.PI, 100));
        // Вычисление косинуса:
        System.out.println("cos(pi / 2) = " + MyMath.Cos(Math.PI / 2, 100));
        // Вычисление функции Бесселя:
        System.out.println("J0(mu1) = " + MyMath.BesselJ(2.404825558, 100));
        
        // Заполнение массивов коэффициентов рядов Фурье для функции y(x)=x:
        int m = 1000;
        double[] a = new double[m];
        double[] b = new double[m + 1];
        b[0] = MyMath.L / 2;
        for (int i = 1; i <= m; i++) {
            a[i - 1] = (2 * (i % 2) - 1) * 2 * MyMath.L / Math.PI / i;
            b[i] = -4 * (i % 2) * MyMath.L / Math.pow(Math.PI * i, 2);
        }
        // Вычисление функции y(x)=x через синус-ряд Фурье:
        System.out.println("2.0->" + MyMath.FourSin(2.0, a));
        // Вычисление функции y(x)=x через косинус-ряд Фурье:
        System.out.println("2.0->" + MyMath.FourCos(2.0, b));
        
        // My additions
        
        System.out.printf("\n My additions \n\n");
        
        // Ln(x) demo
        System.out.printf("Ln(%f) = %f \n", 4.0, MyMath.Ln(4.0));
        System.out.printf("Ln(E) = %f \n", MyMath.Ln(Math.E));
        System.out.printf("Ln(1/E) = %f \n", MyMath.Ln(1.0/Math.E));
        
        // Cbrt(x) demo
        System.out.printf("Cbrt(%f) = %f \n", 1000.0, MyMath.Cbrt(1000.0));
        System.out.printf("Cbrt(%f) = %f \n", 76893.56, MyMath.Cbrt(76893.56));
        
        double[] testData = new double[1000000];
        for(int i=0; i<testData.length; i++){
            testData[i] = Math.random() * Math.random() * 1.6101292E+8;
        }
        
        long startTime = System.currentTimeMillis();
        for(int i=0; i<testData.length; i++){
            double tmp = MyMath.Cbrt(testData[i]);
        }
        System.out.printf("Calculated %d cubic roots in %d ms \n", 
                          testData.length, System.currentTimeMillis() - startTime);
        
        // Hell(x) demo
        for (int i=1; i<100; i++){
            System.out.printf("Hell(%d) = %d \n", i, MyMath.Hell(i));
        }
        
    }
    
}
