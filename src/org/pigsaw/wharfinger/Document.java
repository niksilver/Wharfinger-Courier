package org.pigsaw.wharfinger;

import com.google.appengine.api.datastore.Text;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

/**
 * A Wharfinger Courier document
 */

@PersistenceCapable
public class Document {

    @PrimaryKey
    @Persistent
    public String filename;

    @Persistent
    public String contentType;

    @Persistent
    private Text content;

    public Document(String filename, String contentType, String content) {
        this.filename = filename;
        this.contentType = contentType;
        this.content = (content == null) ? null : new Text(content);
    }

    public String getContent() {
        return (content == null) ? null : content.getValue();
    }
}
