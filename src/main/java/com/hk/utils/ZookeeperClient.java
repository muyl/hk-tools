package com.hk.utils;

import com.hk.constant.RPCConstant;
import com.hk.domain.URL;
import com.hk.service.ChildListener;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.CuratorWatcher;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryNTimes;
import org.apache.curator.shaded.com.google.common.base.Charsets;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.data.Stat;

import java.util.List;

/**
 * zookeeper工具类
 */
public class ZookeeperClient implements AutoCloseable {
    private CuratorFramework client; // 客户端

    public ZookeeperClient(URL url) {
        try {
//            CuratorFrameworkFactory.Builder builder = CuratorFrameworkFactory.builder()
//                    .connectString(url.getHost())
//                    .retryPolicy(new RetryNTimes(Integer.MAX_VALUE, 1000))
//                    .connectionTimeoutMs(5000);

//            client = builder.build();
//            client.start();

            client = CuratorFrameworkFactory.builder().connectString(url.getHost()).sessionTimeoutMs(RPCConstant.SESSION_TIMEOUT)
                    .retryPolicy(new RetryNTimes(RPCConstant.REGRY_TIMES, RPCConstant.REGRY_TIME))
                    .build();
            client.start();


        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * 获取是否连接到zookeeper
     *
     * @return 连接标志
     */
    public boolean isConnected() {
        return client.getZookeeperClient().isConnected();
    }

    /**
     * 创建节点
     *
     * @param path 节点路径
     */

    public void createPersistent(String path) {
        try {
            Stat stat = client.checkExists().forPath(path); // 检查节点是否存在
            if (stat == null) {
                client.create().creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .forPath(path);
            }
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * 创建节点
     *
     * @param nodeName 节点路径
     * @param value    节点信息
     */

    public void createPersistent(String nodeName, String value) {
        try {
            Stat stat = client.checkExists().forPath(nodeName); // 检查节点是否存在
            if (stat == null) {
                if (StringUtils.isEmpty(value)) {
                    this.createPersistent(nodeName);
                } else {
                    client.create().creatingParentsIfNeeded().withMode(CreateMode.PERSISTENT)  // 节点类型
                            .forPath(nodeName, value.getBytes(Charsets.UTF_8));
                }
            }
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * 更新节点信息
     *
     * @param nodeName 节点名称
     * @param value    节点信息
     */
    public void updatePersistent(String nodeName, String value) {
        try {
            Stat stat = client.checkExists().forPath(nodeName); // 检查节点是否存在
            if (stat == null) {
                this.createPersistent(nodeName, value);
            } else {
                this.client.setData().forPath(nodeName, value.getBytes(Charsets.UTF_8));
            }
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * 读取节点信息
     *
     * @param nodeName 节点路径
     * @return 节点信息
     */
    public String readPersistent(String nodeName) {
        byte[] bytes = null;
        try {
            bytes = this.client.getData().forPath(nodeName);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
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
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    /**
     * 创建临时节点
     *
     * @param nodeName 节点路径
     * @param value    节点信息
     */
    public void createEphemeral(String nodeName, String value) {
        byte[] bytes = (value == null ? "" : value).getBytes(Charsets.UTF_8);
        try {
            this.client.create().creatingParentsIfNeeded().withMode(CreateMode.EPHEMERAL)
                    .forPath(nodeName, bytes);
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }


    public List<String> addTargetChildListener(String path, CuratorWatcher listener) {
        try {
            return client.getChildren().usingWatcher(listener).forPath(path);
        } catch (KeeperException.NoNodeException e) {
            return null;
        } catch (Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
    }

    public void removeTargetChildListener(String path, CuratorWatcher listener) {
        ((CuratorWatcherImpl) listener).unwatch();
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

    public InterProcessMutex tryLock(String nodeName) {
        InterProcessMutex lock = new InterProcessMutex(client, nodeName);
        return lock;
    }


    @Override
    public void close() {
        if ((null != this.client)
                && (CuratorFrameworkState.STARTED
                .equals(this.client.getState()))) {
            this.client.close();
        }
    }

    private class CuratorWatcherImpl implements CuratorWatcher {

        private volatile ChildListener listener;

        public CuratorWatcherImpl(ChildListener listener) {
            this.listener = listener;
        }

        public void unwatch() {
            this.listener = null;
        }

        public void process(WatchedEvent event) throws Exception {
            if (listener != null) {
                listener.childChanged(event.getPath(), client.getChildren().usingWatcher(this).forPath(event.getPath()));
            }
        }
    }


}
