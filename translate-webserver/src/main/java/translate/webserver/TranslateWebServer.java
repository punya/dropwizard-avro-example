package translate.webserver;

import java.net.URI;

import org.apache.avro.ipc.Ipc;
import org.apache.avro.ipc.specific.SpecificRequestor;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import translate.api.Translate;

public final class TranslateWebServer extends Application<Configuration> {

  public static void main(String[] args) throws Exception {
    new TranslateWebServer().run(args);
  }

  @Override
  public void initialize(Bootstrap<Configuration> bootstrap) {
    bootstrap.addBundle(new AssetsBundle("/assets", "/", "index.html"));
  }

  @Override
  public void run(Configuration configuration, Environment environment) throws Exception {
    Translate translate = SpecificRequestor.getClient(Translate.class, Ipc.createTransceiver(URI.create("http://localhost:4080")));
    environment.jersey().setUrlPattern("/api/*");
    environment.jersey().register(new TranslateResource(translate));
  }

}
