package translate.service;

import org.apache.avro.AvroRemoteException;

import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.FluentIterable;

import translate.api.Translate;
import translate.api.Translations;

final class TranslateImpl implements Translate {

  public Translations translate(String source) throws AvroRemoteException {
    return this.translate((CharSequence) source);
  }

  @Override
  public Translations translate(CharSequence source) throws AvroRemoteException {
    Iterable<String> words = Splitter.on(CharMatcher.BREAKING_WHITESPACE).split(source);
    Iterable<String> latinWords = FluentIterable.from(words).transform((input) -> input + "ay");
    CharSequence pigLatin = Joiner.on(' ').join(latinWords);

    char[] results = new char[source.length()];
    for (int i = 0; i < source.length(); ++i) {
      results[i] = source.charAt(source.length() - i - 1);
    }
    CharSequence reverse = new String(results);

    return Translations.newBuilder().setPigLatin(pigLatin).setReverse(reverse).build();
  }

}
