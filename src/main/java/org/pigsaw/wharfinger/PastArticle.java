package org.pigsaw.wharfinger;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable
public class PastArticle {

	@PrimaryKey
	@Persistent
	public String url;

	public PastArticle(String url) {
		this.url = url;
	}
}
