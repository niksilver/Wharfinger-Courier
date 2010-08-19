package org.pigsaw.wharfinger;

import com.google.appengine.api.datastore.Text;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class BookmarkPendingFetch {

    @PrimaryKey
    @Persistent
    public String url;

    @Persistent
    public int popularity;

    @Persistent
    public String username;

    @Persistent
    private Text citation;

    @Persistent
    public int fetchAttempts = 0;

    public BookmarkPendingFetch(String url, int popularity, String username, String citation) {
        this.url = url;
        this.popularity = popularity;
        this.username = username;
        setCitation(citation);
    }

    public void setCitation(String c) {
        if (c == null) {
            citation = null;
        }
        else {
            citation = new Text(c);
        }
    }

    public String getCitation() {
        if (citation == null) {
            return null;
        }
        else {
            return citation.getValue();
        }
    }

    public void saveForLaterFetching() {
      PersistenceManager pm = PMF.get().getPersistenceManager();
      try {
        pm.makePersistent(this);
      }
      finally {
        pm.close();
      }
    }

}
