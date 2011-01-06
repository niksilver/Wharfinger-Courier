package org.pigsaw.wharfinger;

import com.google.appengine.api.datastore.Text;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import java.util.Date;

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

    /** Publication date set to be creation date.
     */
    @Persistent
    public Date publicationDate = new Date();

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

    public int getContentLength() {
        String content = getContent();
        return (content == null) ? 0 : content.length();
    }

    public void setPublicationDate(Date d) {
        publicationDate = d;
    }
}
