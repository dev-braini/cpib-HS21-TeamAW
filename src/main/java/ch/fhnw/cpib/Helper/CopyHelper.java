package ch.fhnw.cpib.Helper;

import ch.fhnw.cpib.Parser.AbstractSyntaxTree.TypedIdent;

import java.util.HashMap;
import java.util.Map;

public class CopyHelper {
    public static HashMap<String, TypedIdent> deepCopy(HashMap<String, TypedIdent> map) {
        HashMap<String, TypedIdent> tmp = new HashMap<>();
        for(Map.Entry<String, TypedIdent> entry : map.entrySet()){
            try {
                tmp.put(entry.getKey(), (TypedIdent) entry.getValue().clone());
            } catch (CloneNotSupportedException e) {
                System.out.println("Clone error");
                e.printStackTrace();
            }
        }
        return tmp;
    }
}
