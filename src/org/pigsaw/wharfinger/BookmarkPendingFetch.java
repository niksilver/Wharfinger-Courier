package org.pigsaw.wharfinger;

import com.google.appengine.api.datastore.Text;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class BookmarkPendingFetch {

    @PrimaryKey
    @Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
    public String url;

    @Persistent
    public String title;

    @Persistent
    private Text citation;

    @Persistent
    private int fetchAttempts = 0;

    public BookmarkPendingFetch(String url, String title, String citation) {
        this.url = url;
        this.title = title;
        setCitation(citation);
    }

    public void incrementFetchAttempts() {
        fetchAttempts += 1;
    }

    public int getFetchAttempts() {
        return fetchAttempts;
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
