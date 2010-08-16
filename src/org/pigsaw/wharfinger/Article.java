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

	public Article(String url, String content) {
		this.url = url;
		this.content = new Text(content);
	}
	
	public String getContent() {
		return content.getValue();
	}
}
