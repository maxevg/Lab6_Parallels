package lab6.zookeper;

import akka.actor.ActorRef;
import org.apache.zookeeper.*;

public class ZooServer implements {

    public ZooServer(ZooKeeper zoo, ActorRef storage) throws KeeperException, InterruptedException {
        this.zoo = zoo;
        this.storage = storage;
        sendServers();
    }


}
