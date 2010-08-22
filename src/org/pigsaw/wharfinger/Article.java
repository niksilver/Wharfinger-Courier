package org.pigsaw.wharfinger;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Text;

@PersistenceCapable
public class Article {

	@PrimaryKey
	@Persistent
	public String url;

    @Persistent
    private Text content;

    @Persistent
    private Text citation;

	public Article(String url, String citation, String content) {
		this.url = url;
        this.citation = (citation == null) ? null : new Text(citation);
		this.content = (content == null) ? null : new Text(content);
	}
	
    public String getContent() {
        return (content == null) ? null : content.getValue();
    }
    
    public String getCitation() {
        return (citation == null) ? null : citation.getValue();
    }
}
