package mathdemo;

public class MyMath {

    static double L = Math.PI;

    static double Exp(double x, int N) {
        int i;
        double s = 0, q = 1;
        for (i = 0; i < N; i++) {
            s += q;
            q *= x / (i + 1);
        }
        return s + q;
    }
    
    static double Sin(double x, int N) {
        int i;
        double s = 0, q = x;
        for (i = 0; i < N; i++) {
            s += q;
            q *= (-1) * x * x / (2 * i + 2) / (2 * i + 3);
        }
        return s + q;
    }
    
    static double Cos(double x, int N) {
        int i;
        double s = 0, q = 1;
        for (i = 0; i < N; i++) {
            s += q;
            q *= (-1) * x * x / (2 * i + 1) / (2 * i + 2);
        }
        return s + q;
    }
    
    static double BesselJ(double x, int N) {
        int i;
        double s = 0, q = 1;
        for (i = 0; i < N; i++) {
            s += q;
            q *= (-1) * x * x / 4 / (i + 1) / (i + 1);
        }
        return s + q;
    }

    static double FourSin(double x, double[] a) {
        int i, N = a.length;
        double s = 0;
        for (i = 0; i < N; i++) {
            s += a[i] * Math.sin(Math.PI * x * (i + 1) / L);
        }
        return s;
    }

    static double FourCos(double x, double[] a) {
        int i, N = a.length;
        double s = 0;
        for (i = 0; i < N; i++) {
            s += a[i] * Math.cos(Math.PI * x * i / L);
        }
        return s;
    }
    
    // My additions
    
    // 1. Ln(x) - natural logarithm of x
    static double Ln(double x){
        if(x < 0) return Double.NaN;
        if(x == 0) return Double.NEGATIVE_INFINITY;
        
        double initial = 0.0;
        while (x > Math.E){
            x /= Math.E;
            initial += 1.0;
        }
        
        double a = (x - 1.0) / (x + 1.0), delta_a = a * a, result = a;
        
        long n = 1;
               
        while (a > 1.0E-12 || a < -1.0E-12){
            n += 2;
            a *= delta_a;
            result += a / (double)(n);
        }
        
        return initial + result * 2.0;
    }
    
    // 2. Cbrt(x) - cubic root of x
    static double Cbrt(double x){
        double sign = 1.0;
        boolean reverse = false;
        
        if (x < 0){
            x = -x;
            sign = -1.0;
        }
        
        if (x < 1){
            x = 1/x;
            reverse = true;
        }
        
        double left = 0, right = x;
        double epsilon = x / 1.0E+16;
        
        while (left + epsilon < right)
        {
            double mid = (left + right) / 2.0;
            if(mid * mid * mid > x)
                right = mid;
            else 
                left = mid;
        }
        
        double result = sign * (left + right) / 2.0;
        return reverse ? 1.0/result : result;
    }
    
    // 3. Hell(x) - what???
    static int Hell(int x){
        if(x < 0) x = -x;
        
        int iterationCount = 0;
        
        while(x > 1)
        {
            if (x%2==0){
                x = x / 2;
            }
            else {
                x = 3*x + 1;
            }
            
            ++iterationCount;
        }
        
        return iterationCount;
    }
    

}
