package de.hnu.eae.ai;

import opennlp.tools.tokenize.SimpleTokenizer;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TextTokenizer {
    private final SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;

    public String[] tokenize(String text) {
        return tokenizer.tokenize(text);
    }
}
