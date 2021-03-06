package ch.fhnw.cpib.Token;

import java.util.Iterator;
import java.util.LinkedList;

import static java.lang.String.format;

/**
 * The TokenList is implemented using a linked list.
 * These offer the advantage that, compared to an ArrayList,
 * the elements can be added and deleted more quickly.
 */
public class TokenList implements ITokenList {
    private final LinkedList<IToken> list;
    private Iterator<IToken> iterator;

    /**
     * Constructor initializes the linked list
     */
    public TokenList() {
        list = new LinkedList<>();
    }

    /**
     * Add the token only if it's not zero
     * @param token Token to add
     */
    @Override
    public void add(IToken token) {
        if(token != null) {
            list.add(token);
        }
    }

    /**
     * Reset only the iterator
     */
    @Override
    public void reset() {
        iterator = null;
    }

    /**
     * Get a listen iterator if it doesn't exist and return the next IToken from List
     * @return IToken
     */
    @Override
    public IToken nextToken() {
        if (iterator == null){
            iterator = list.iterator();
        }
        return iterator.next();
    }

    /**
     * Return a string that representing the ITokenList
     * @return String
     */
    @Override
    public String toString() {
        return list.toString();
    }

    /**
     * Print each token on a separate line
     */
    public void print() {
        int count = 0;
        String nrFormat = "%0" + String.valueOf(list.size()).length() + "d";

        for (IToken iToken : list) {
            System.out.printf("%s: %s%n", format(nrFormat, ++count), iToken);
        }
    }
}