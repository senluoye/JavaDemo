public class A {
    static class B {
        int b;

        B(int b) {
            this.b = b;
        }
    }

    public B build() {
        return new B(1);
    }

    public static void main(String[] args) {
        A a = new A();
        B bb = new A.B(1);
        System.out.println(bb.b);
    }
}
