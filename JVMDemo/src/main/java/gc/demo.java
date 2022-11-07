package gc;

/**
 * @ClassName Dessert
 * @Description 将JDK换成1.8，会显示堆的内存信息
 * <p>PSYoungGen是新生代, ParOldGen是老年代, Metaspace是元空间(对应JDK1.7之前的永久代)</>
 * <p>新声明的内存变量，会按照上面的顺序一次放入，直到下一次变量声明之后，
 * 新生一代堆内存不够，之前最先放入的变量就会被转移到Survivor空间，或者老年代(分配担保机制)</>
 * <p>大对象(大型字符串，大型数组)、长期存活的对象直接进入老年代</>
 * @Author QKS
 * @Version v1.0
 * @Create 2022-07-18 13:02
 */
public class demo {
    public static void main(String[] args) {
        byte[] allocation1, allocation2;
        allocation1 = new byte[60900*1024];
        allocation2 = new byte[9000*1024];
    }
}
