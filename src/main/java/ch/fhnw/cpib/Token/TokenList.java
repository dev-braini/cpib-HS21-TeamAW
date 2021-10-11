package ch.fhnw.cpib.Token;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * The TokenList is implemented using a linked list.
 * These offer the advantage that, compared to an ArrayList,
 * the elements can be added and deleted more quickly.
 */
public class TokenList implements ITokenList {
    private LinkedList<IToken> list;
    private Iterator<IToken> iterator;

    /**
     * Constructor initializes the linked list
     */
    public TokenList() {
        list = new LinkedList<IToken>();
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
}