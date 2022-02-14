package lab6_zookeeper;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import org.apache.log4j.BasicConfigurator;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

public class Server {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        BasicConfigurator.configure();
        ActorSystem system = ActorSystem.create("routes");
        ActorRef storage = system.actorOf(Props.create(StorageActor.class));

        Watcher empty = new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
            }
        };
        ZooKeeper zoo = new ZooKeeper("127.0.0.1:2181", 2500, empty);
        final Http http = Http.get(system);
        ZooServer server = new ZooServer(zoo, storage);
        server.createServer("127.0.0.1:2181", "8080");

    }
}
