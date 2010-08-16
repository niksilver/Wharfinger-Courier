package org.pigsaw.wharfinger;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable
public class BookmarkToRetry {

	@PrimaryKey
	@Persistent
	public String url;
	
	@Persistent
	private Text citation;

	public BookmarkToRetry(String url) {
		this.url = url;
	}
	
	public String getCitation() { return citation.getValue(); }
	public void setCitation(String citation) { this.citation = new Text(citation); }

}
