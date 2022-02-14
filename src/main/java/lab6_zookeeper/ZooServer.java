package lab6_zookeeper;

import akka.actor.ActorRef;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class ZooServer implements Watcher {
    private ZooKeeper zoo;
    private ActorRef storage;

    public ZooServer(ZooKeeper zoo, ActorRef storage) throws KeeperException, InterruptedException {
        this.zoo = zoo;
        this.storage = storage;
    }
}
