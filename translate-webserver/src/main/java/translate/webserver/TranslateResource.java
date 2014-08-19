package translate.webserver;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.avro.AvroRemoteException;

import com.google.common.collect.ImmutableList;

import translate.api.Translate;
import translate.api.Translations;

@Path("/translate")
public final class TranslateResource {
  private final Translate translate;

  TranslateResource(Translate translate) {
    this.translate = translate;
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public Object translate(String source) throws AvroRemoteException {
    Translations translations = this.translate.translate(source);
    return ImmutableList.of(translations.getPigLatin().toString(), translations.getReverse().toString());
  }
}
