package lab6_zookeeper;

import akka.actor.ActorRef;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.util.List;

public class ZooServer implements Watcher {
    private ZooKeeper zoo;
    private ActorRef storage;

    public ZooServer(ZooKeeper zoo, ActorRef storage) throws KeeperException, InterruptedException {
        this.zoo = zoo;
        this.storage = storage;
        sendServers();
    }

    private void sendServers() throws KeeperException, InterruptedException {
        List<String> servers = zoo.getChildren("/servers", this);
        storage.tell(new StoreServer(servers), ActorRef.noSender());
    }

    public void process(WatchedEvent watchedEvent) {
        System.out.println(watchedEvent.toString());
        try {
            sendServers();
        } catch (KeeperException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void createServer(String localhost, String port) throws KeeperException, InterruptedException {
        
    }
}
