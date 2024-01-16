package de.hnu.eae.ai;

import opennlp.tools.tokenize.SimpleTokenizer;
import javax.enterprise.context.ApplicationScoped;

/**
 * This class represents a text tokenizer that splits a given text into tokens.
 */
@ApplicationScoped
public class TextTokenizer {
    private final SimpleTokenizer tokenizer = SimpleTokenizer.INSTANCE;

    /**
     * Tokenizes the given text into an array of strings.
     *
     * @param text the text to be tokenized
     * @return an array of strings representing the tokens
     */
    public String[] tokenize(String text) {
        return tokenizer.tokenize(text);
    }
}
