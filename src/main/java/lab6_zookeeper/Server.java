package lab6_zookeeper;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.http.javadsl.server.Route;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
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
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        Watcher empty = new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
            }
        };
        ZooKeeper zoo = new ZooKeeper("127.0.0.1:2181", 2500, empty);
        final Http http = Http.get(system);
        ZooServer server = new ZooServer(zoo, storage);
        server.createServer("127.0.0.1:2181", "8080");
        final Flow<HttpRequest, HttpResponse, NotUsed> flow = createRoute(storage, http)
                .flow(system, materializer);

    }

    private static Route createRoute(ActorRef storage, final Http http) {

    }
}
