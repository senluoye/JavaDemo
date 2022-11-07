package com.atguigu.distributed.lock.zk;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * @ClassName ZkTest
 * @Description
 * @Author QKS
 * @Version v1.0
 * @Create 2022-10-20 10:22
 */
public class ZkTest {

    public static void main(String[] args) throws KeeperException, InterruptedException {

        // 闭锁
        CountDownLatch countDownLatch = new CountDownLatch(1);
        ZooKeeper zooKeeper = null;
        try {
            // 获取zookeeper链接
            // 地址 会话超时事件 监听事件
            zooKeeper = new ZooKeeper("127.0.0.1:2181", 30000, event -> {
                // 如果当前状态是获取链接的状态 &&
                if (Watcher.Event.KeeperState.SyncConnected.equals(event.getState())
                        && Watcher.Event.EventType.None.equals(event.getType())) {
                    System.out.println("获取链接成功。。。。。。" + event);
                    // 值减一
                    countDownLatch.countDown();
                } else if (Watcher.Event.KeeperState.Closed.equals(event.getState())) {
                    System.out.println("关闭连接：" + event);
                }
            });

            // 当值不为0的时候，一直等待
            countDownLatch.await();
            System.out.println("加载完毕");

            // 创建一个节点，1-节点路径 2-节点内容 3-节点的访问权限 4-节点类型
            // OPEN_ACL_UNSAFE：任何人可以操作该节点
            // CREATOR_ALL_ACL：创建者拥有所有访问权限
            // READ_ACL_UNSAFE: 任何人都可以读取该节点
            zooKeeper.create("/atguigu/aa", "haha~~".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
            zooKeeper.create("/test", "haha~~".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            zooKeeper.create("/atguigu/cc", "haha~~".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT_SEQUENTIAL);
            zooKeeper.create("/atguigu/dd", "haha~~".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            zooKeeper.create("/atguigu/dd", "haha~~".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
            zooKeeper.create("/atguigu/dd", "haha~~".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);

            // 判断节点是否存在
            Stat stat = zooKeeper.exists("/test", true);
            if (stat != null) {
                System.out.println("当前节点存在！" + stat.getVersion());
            } else {
                System.out.println("当前节点不存在！");
            }

            // 判断节点是否存在，同时添加监听
            zooKeeper.exists("/test", event -> {
                System.out.println("监听事件触发");
            });

            // 获取一个节点的数据
            byte[] data = zooKeeper.getData("/atguigu/ss0000000001", false, null);
            System.out.println(new String(data));

            // 查询一个节点的所有子节点
            List<String> children = zooKeeper.getChildren("/test", false);
            System.out.println(children);

            // 更新
            zooKeeper.setData("/test", "wawa...".getBytes(), stat.getVersion());

            // 删除一个节点
            //zooKeeper.delete("/test", -1);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zooKeeper.close();
        }
    }
}