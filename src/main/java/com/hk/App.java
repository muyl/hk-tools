package com.hk;

import com.google.common.collect.Lists;
import com.hk.domain.URL;
import com.hk.utils.ZookeeperClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.leader.LeaderLatch;
import org.apache.curator.framework.recipes.leader.LeaderLatchListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

import java.util.List;

/**
 * Hello world!
 */
@Slf4j
public class App {
    protected static String PATH = "/francis/leader";
    private static final int CLIENT_QTY = 10;

    public static void main(String[] args) throws Exception {

        ZookeeperClient zookeeperClient = new ZookeeperClient(new URL("localhost:2181"));
        System.out.println("连接状态A：" + zookeeperClient.isConnected());
        zookeeperClient.createPersistent("/muyl");
        System.out.println("连接状态B：" + zookeeperClient.isConnected());
        zookeeperClient.close();
        System.out.println("连接状态C：" + zookeeperClient.isConnected());

//        List<CuratorFramework> clients = Lists.newArrayList();
//        List<LeaderLatch> examples = Lists.newArrayList();
//        try {
//            for (int i = 0; i < CLIENT_QTY; i++) {
//                CuratorFramework client
//                        = CuratorFrameworkFactory.newClient("localhost:2181", new ExponentialBackoffRetry(20000, 3));
//                clients.add(client);
//                LeaderLatch latch = new LeaderLatch(client, PATH, "Client #" + i);
//                latch.addListener(new LeaderLatchListener() {
//
//                    @Override
//                    public void isLeader() {
//                        // TODO Auto-generated method stub
//                        System.out.println("I am Leader");
//                    }
//
//                    @Override
//                    public void notLeader() {
//                        // TODO Auto-generated method stub
//                        System.out.println("I am not Leader");
//                    }
//                });
//                examples.add(latch);
//                client.start();
//                latch.start();
//            }
//            Thread.sleep(10000);
//            LeaderLatch currentLeader = null;
//            for (LeaderLatch latch : examples) {
//                if (latch.hasLeadership()) {
//                    currentLeader = latch;
//                }
//            }
//            System.out.println("current leader is " + currentLeader.getId());
//            System.out.println("release the leader " + currentLeader.getId());
//            currentLeader.close();
//
//            Thread.sleep(5000);
//
//            for (LeaderLatch latch : examples) {
//                if (latch.hasLeadership()) {
//                    currentLeader = latch;
//                }
//            }
//            System.out.println("current leader is " + currentLeader.getId());
//            System.out.println("release the leader " + currentLeader.getId());
//        } finally {
//            for (LeaderLatch latch : examples) {
//                if (null != latch.getState())
//                    CloseableUtils.closeQuietly(latch);
//            }
//            for (CuratorFramework client : clients) {
//                CloseableUtils.closeQuietly(client);
//            }
//        }
    }


}
