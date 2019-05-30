package org.eclipse.wg.jakartaee;

import org.tomitribe.crest.api.Command;
import org.tomitribe.crest.api.Default;
import org.tomitribe.crest.api.Option;
import org.tomitribe.crest.api.StreamingOutput;
import org.tomitribe.crest.val.Readable;
import org.tomitribe.util.Files;
import org.tomitribe.util.IO;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

/**
 *
 */
public class App {

    @Command
    public StreamingOutput cat(@IsFile @Readable final File file) {
        Files.exists(file);
        Files.readable(file);
        Files.file(file);

        return new StreamingOutput() {
            public void write(OutputStream os) throws IOException {
                IO.copy(file, os);
            }
        };
    }

    @Command
    public String hello(@Option("name") @Default("World") String name,
                        @Option("language") @Default("EN") Language language) {
        return String.format("%s, %s!", language.getSalutation(), name);
    }

    public static enum Language {
        EN("Hello"),
        ES("Hola"),
        FR("Bonjour");

        private final String salutation;

        private Language(String salutation) {
            this.salutation = salutation;
        }

        public String getSalutation() {
            return salutation;
        }
    }

}
