package lab6_zookeeper;

import akka.actor.AbstractActor;

public class StorageActor extends AbstractActor {
    
    public Receive createReceive() {
        return receiveBuilder()
                .match(StoreServer.class, mes -> this.servers = mes.getServers())
    }
}
