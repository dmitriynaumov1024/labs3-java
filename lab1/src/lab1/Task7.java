package lab1;

/**
 * Implement (copypaste) examples given in task list.
 * @author Dmitriy Naumov
 */
public class Task7 implements ITask {
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
    
    public void run(String[] args){
        leibnic();
        vallis();
    }
}
