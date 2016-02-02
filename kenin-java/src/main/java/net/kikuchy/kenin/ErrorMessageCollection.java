package net.kikuchy.kenin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kikuchy on 16/02/02.
 */
public class ErrorMessageCollection extends ArrayList<ErrorMessage> {
    public List<String> getMessages() {
        List<String> ret = new ArrayList<>(this.size());
        for (ErrorMessage e: this) {
            ret.add(e.toString());
        }
        return ret;
    }
}
