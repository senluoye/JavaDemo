package gc;

/**
 * @ClassName FinalizeEscapeGC
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-09-14 16:56
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("finalize method");
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();

        SAVE_HOOK = null;
        System.gc();

        Thread.sleep(500);
        if (SAVE_HOOK != null) SAVE_HOOK.isAlive();
        else System.out.println("dead");

        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if (SAVE_HOOK != null) SAVE_HOOK.isAlive();
        else System.out.println("dead");
    }
}
