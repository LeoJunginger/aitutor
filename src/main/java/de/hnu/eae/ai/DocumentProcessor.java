package de.hnu.eae.ai;

import org.apache.tika.Tika;
import java.io.File;
import java.io.IOException;
import org.apache.tika.exception.TikaException;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DocumentProcessor {
    private final Tika tika = new Tika();

    public String processDocument(String filePath) {
        try {
            return tika.parseToString(new File(filePath));
        } catch (IOException | TikaException e) {
            e.printStackTrace();
            return null;
        }
    }
}
