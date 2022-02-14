package lab6_zookeeper;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;

import java.util.List;

public class StorageActor extends AbstractActor {
    private List<String> servers;

    public Receive createReceive() {
        return receiveBuilder()
                .match(StoreServer.class, mes -> this.servers = mes.getServers())
                .match(NextServer.class, mes -> {
                    this.servers.add(mes.getUrl());
                })
                .match(RandomServer.class, mes -> {
                    getSender().tell(this.getRandomServer(), ActorRef.noSender());
                }
    }
}
