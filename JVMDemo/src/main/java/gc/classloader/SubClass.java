package gc.classloader;

public class SubClass extends SuperClass {
    static {
        System.out.println("child");
    }

    public static void main(String[] args) {
        A[] a = new A[10];
    }

    private class A {
        public int aNum;
    }
}
