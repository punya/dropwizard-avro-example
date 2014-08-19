package translate.service;

import org.apache.avro.ipc.Responder;
import org.apache.avro.ipc.ResponderServlet;
import org.apache.avro.ipc.reflect.ReflectResponder;

import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import translate.api.Translate;

public final class TranslateService extends Application<Configuration> {

  public static void main(String[] args) throws Exception {
    new TranslateService().run(args);
  }

  @Override
  public void initialize(Bootstrap<Configuration> bootstrap) {
    // nothing to do here
  }

  @Override
  public void run(Configuration configuration, Environment environment) throws Exception {
    environment.jersey().disable();

    Responder responder = new ReflectResponder(Translate.class, new TranslateImpl());
    environment.servlets().addServlet("avro", new ResponderServlet(responder)).addMapping("/*");
  }

}
