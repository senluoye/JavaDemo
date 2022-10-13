import java.util.*;

/**
 * @ClassName SimpleFNV
 * @Description 32位的 FNV 算法实现
 * @Author QKS
 * @Version v1.0
 * @Create 2022-10-12 19:10
 */
public class ConsistentHash {

    /**
     * 物理节点
     */
    private final Set<String> physicalNodes;

    /**
     * 虚拟节点数
     * 也是物理节点映射虚拟节点的倍数
     * 要求为2的倍数
     */
    private final int VIRTUAL_COPIES = 1 << 20;

    /**
     * 哈希值映射到物理节点
     */
    private final TreeMap<Long, String> virtualNodes = new TreeMap<>();

    /**
     * 可以将字符串哈希为32位、64位、128位等等
     * hash的范围是 -2^32 ~ 2^31 - 1，最后进行abs
     */
    private final long HASH = 216613626L;

    /**
     * 初始化方法，根据物理节点构建虚拟节点映射表
     */
    public ConsistentHash() {
        this(new TreeSet<>());
    }

    public ConsistentHash(Set<String> physicalNodes) {
        this.physicalNodes = physicalNodes;
        for (String node : physicalNodes) {
            addPhysicalNode(node);
        }
    }

    /**
     * 32位 FVN 哈希算法简单实现<br/>
     * 计算一个 key 对应的 hash 码
     *
     * @param key 键
     * @return long
     */
    private Long FVNHash(String key) {
        long hash = HASH;

        // p：用于散列的质数
        for (int i = 0, num = key.length(), p = 16777619; i < num; i++) {
            hash = (hash ^ key.charAt(i)) * p;
        }

        hash += hash << 13;
        hash ^= hash >> 7;
        hash += hash << 3;
        hash ^= hash >> 17;
        hash += hash << 5;

        return hash < 0 ? Math.abs(hash) : hash;
    }

    /**
     * 添加物理节点
     *
     * @param nodeIp 节点ip
     */
    public void addPhysicalNode(String nodeIp) {
        physicalNodes.add(nodeIp);

        // 计算该节点的所有虚拟节点
        for (int i = 0; i < VIRTUAL_COPIES; i++) {
            virtualNodes.put(FVNHash(nodeIp + "#" + i), nodeIp);
        }
    }

    /**
     * 删除物理节点
     *
     * @param nodeIp
     */
    public void removePhysicalNode(String nodeIp) {
        physicalNodes.remove(nodeIp);

        for (int i = 0; i < VIRTUAL_COPIES; i++) {
            virtualNodes.remove(FVNHash(nodeIp + "#" + i));
        }
    }

    /**
     * 查找对象映射的节点
     *
     * @param object
     * @return string 对象应当存放的虚拟节点
     */
    public String getObjectNode(String object) {
        long hash = FVNHash(object);
        // 拿到 key 大于或等于 hash 的 节点
        SortedMap<Long, String> tailMap = virtualNodes.tailMap(hash);
        // 拿到第一个节点
        Long key = tailMap.isEmpty() ? virtualNodes.firstKey() : tailMap.firstKey();
        return virtualNodes.get(key);
    }

    /**
     * 统计某个范围内的对象与节点的映射关系
     *
     * @param describe 当前统计描述
     * @param min      对象hash范围最小值
     * @param max      对象hash范围最大值
     */
    public void dumpObjectNodeMap(String describe, int objectMin, int objectMax) {
        // 存储节点映射的对象数
        Map<String, Integer> objectNodeMap = new TreeMap<>();

        for (int object = objectMin; object <= objectMax; ++object) {
            // 获取当前对象映射的节点
            String nodeIp = getObjectNode(Integer.toString(object));
            // 修改次数
            objectNodeMap.put(nodeIp, objectNodeMap.getOrDefault(nodeIp, 0) + 1);
        }

        // 打印
        double totalCount = objectMax - objectMin + 1;
        System.out.println("======== " + describe + " ========");
        for (Map.Entry<String, Integer> entry : objectNodeMap.entrySet()) {
            long percent = (int) (100 * entry.getValue() / totalCount);
            System.out.println("IP=" + entry.getKey() + ": RATE=" + percent + "%");
        }
    }

    public static void main(String[] args) {
        Set<String> physicalNodes = new TreeSet<>() {
            {
                add("192.168.1.101");
                add("192.168.1.102");
                add("192.168.1.103");
                add("192.168.1.104");
            }
        };

        ConsistentHash ch = new ConsistentHash(physicalNodes);

        // 初始情况
        ch.dumpObjectNodeMap("初始情况", 0, 65536);

        // 删除物理节点
        ch.removePhysicalNode("192.168.1.103");
        ch.dumpObjectNodeMap("删除物理节点", 0, 65536);

        // 添加物理节点
        ch.addPhysicalNode("192.168.1.108");
        ch.dumpObjectNodeMap("添加物理节点", 0, 65536);
    }

}
