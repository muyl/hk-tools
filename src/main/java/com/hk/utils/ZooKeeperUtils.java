package com.hk.utils;

import com.hk.constant.RPCConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.shaded.com.google.common.base.Charsets;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * zookeeper工具类
 */
@Slf4j
public class ZooKeeperUtils implements AutoCloseable {
    private  CuratorFramework client; // 客户端

    /**
     * 连接zookeeper服务
     *
     * @param zkAddress 服务地址
     * @return 连接标志
     */
    public boolean connectServer(String zkAddress) {
        if (client == null) {
            synchronized (CuratorFramework.class) {
                if (client == null) {
                    client = CuratorFrameworkFactory.builder().connectString(zkAddress).sessionTimeoutMs(RPCConstant.SESSION_TIMEOUT)
                            .retryPolicy(new RetryNTimes(RPCConstant.REGRY_TIMES, RPCConstant.REGRY_TIME))
                            .build();
                    client.start();
                }
            }
        }
        return client.isStarted();
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


    public void deleteNode(String nodeName) {
        try {
            client.delete().deletingChildrenIfNeeded().forPath(nodeName);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    public static void main(String[] args) {
        try (ZooKeeperUtils zooKeeperUtils = new ZooKeeperUtils()) {
//            boolean isSucc = zooKeeperUtils.connectServer("localhost:2181");
////            zooKeeperUtils.deleteNode("/servers");
////            zooKeeperUtils.createNode("/servers/timer", "111");
////            System.out.println(zooKeeperUtils.readNode("/servers/timer"));
////            List list = zooKeeperUtils.readClildrenList("/servers");
////            System.out.println(list.size());
//            zooKeeperUtils.createEphemeralNode("/servers/timer/127.0.0.1:8080","1111");
            //System.in.read();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
