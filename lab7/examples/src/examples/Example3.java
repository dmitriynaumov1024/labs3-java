package examples;

// Пример демонстрации использования абстрактного класса
abstract class AA {

    abstract void callme();
    
    // конкретный метод может оставаться доступным в абстрактном классе
    void callmetoo() {
        System.out.println("This is a concrete method.");
    }
}

class BB extends AA {

    void callme() {
        System.out.println("B's implementation of callme.");
    }
}

class Example3 {

    public static void main(String args[]) {
        BB b = new BB();
        b.callme();
        b.callmetoo();
    }
}
