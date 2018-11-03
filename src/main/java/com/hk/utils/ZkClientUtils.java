package com.hk.utils;

import com.hk.constant.RPCConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.shaded.com.google.common.base.Charsets;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * zookeeper工具类
 */
@Slf4j
public class ZkClientUtils implements AutoCloseable {
    private CuratorFramework client; // 客户端

    /**
     * 连接zookeeper服务
     *
     * @param zkAddress 服务地址
     * @return 连接标志
     */
    public CuratorFramework connectServer(String zkAddress) {
        if (client == null) {
            synchronized (ZkClientUtils.class) {
                if (client == null) {
                    client = CuratorFrameworkFactory.builder().connectString(zkAddress).sessionTimeoutMs(RPCConstant.SESSION_TIMEOUT)
                            .retryPolicy(new RetryNTimes(RPCConstant.REGRY_TIMES, RPCConstant.REGRY_TIME))
                            .build();
                    client.start();
                }
            }
        }
        return client;
    }

    /**
     * 创建节点
     *
     * @param nodeName 节点路径
     */

    public void createNode(String nodeName) {
        try {
            Stat stat = client.checkExists().forPath(nodeName); // 检查节点是否存在
            if (stat == null) {
                client.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(nodeName);
            }
        } catch (Exception e) {
            log.error("zk创建节点失败", e);
        }
    }

    /**
     * 创建节点
     *
     * @param nodeName 节点路径
     * @param value    节点信息
     */

    public void createNode(String nodeName, String value) {
        try {
            Stat stat = client.checkExists().forPath(nodeName); // 检查节点是否存在
            if (stat == null) {
                if (StringUtils.isEmpty(value)) {
                    this.createNode(nodeName);
                } else {
                    client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)  // 节点类型
                            .forPath(nodeName, value.getBytes(Charsets.UTF_8));
                }
            }
        } catch (Exception e) {
            log.error("zk创建节点失败", e);
        }
    }

    /**
     * 更新节点信息
     *
     * @param nodeName 节点名称
     * @param value    节点信息
     */
    public void updateNode(String nodeName, String value) {
        try {
            Stat stat = client.checkExists().forPath(nodeName); // 检查节点是否存在
            if (stat == null) {
                this.createNode(nodeName, value);
            } else {
                this.client.setData().forPath(nodeName, value.getBytes(Charsets.UTF_8));
            }
        } catch (Exception e) {
            log.error("zk更新节点信息失败", e);
        }
    }

    /**
     * 读取节点信息
     *
     * @param nodeName 节点路径
     * @return 节点信息
     */
    public String readNode(String nodeName) {
        byte[] bytes = null;
        try {
            bytes = this.client.getData().forPath(nodeName);
        } catch (Exception e) {
            log.error("zk读取节点信息失败", e);
        }
        return new String(bytes != null ? bytes : new byte[0]);
    }


    /**
     * 读取节点列表
     *
     * @param nodeName 节点路径
     * @return 节点列表
     */
    public List<String> readClildrenList(String nodeName) {
        try {
            return this.client.getChildren().forPath(nodeName);
        } catch (Exception e) {
            log.error("zk更新节点列表失败", e);
        }
        return null;
    }

    /**
     * 创建临时节点
     *
     * @param nodeName 节点路径
     * @param value    节点信息
     */
    public void createEphemeralNode(String nodeName, String value) {
        byte[] bytes = (value == null ? "" : value).getBytes(Charsets.UTF_8);
        try {
            this.client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                    .forPath(nodeName, bytes);
        } catch (Exception e) {
            log.error("zk创建临时节点失败", e);
        }
    }

    /**
     * 删除节点
     *
     * @param nodeName 节点路径
     */
    public void deleteNode(String nodeName) {
        try {
            client.delete().deletingChildrenIfNeeded().forPath(nodeName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public InterProcessMutex tryLock(String nodeName){
        InterProcessMutex lock = new InterProcessMutex(client,nodeName);
        return lock;
    }


    @Override
    public void close() {
        if ((null != this.client)
                && (CuratorFrameworkState.STARTED
                .equals(this.client.getState()))) {
            this.client.close();
        }
        System.out.println("关闭连接");
    }

    static int count = 10;
    public static void genarNo(){
        try {
            count--;
            System.out.println(count);
        } finally {

        }
    }

    public static void main(String[] args) {
        try {
            ZkClientUtils zkClientUtils = new ZkClientUtils();
            CuratorFramework client = zkClientUtils.connectServer("localhost:2181");
            final InterProcessMutex lock = new InterProcessMutex(client, "/super");
            final CountDownLatch countdown = new CountDownLatch(1);
            for(int i = 0; i < 10; i++){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            countdown.await();
                            //加锁
                            lock.acquire();
                            //-------------业务处理开始
                            genarNo();
                            //-------------业务处理结束
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                //释放
                                lock.release();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                },"t" + i).start();
            }
            Thread.sleep(100);
            countdown.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
