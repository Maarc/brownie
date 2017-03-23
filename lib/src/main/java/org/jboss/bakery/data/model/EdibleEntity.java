package org.jboss.bakery.data.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@MappedSuperclass
public abstract class EdibleEntity implements Serializable, IEdible {

	private static final long serialVersionUID = 1L;

	@Column(name = "DATE", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	@Column(name = "NAME", nullable = false)
	private String name;

	public Date getDate() {
		Date date = null;
		if (this.date != null) {
			date = new Date(this.date.getTime());
		}
		return date;
	}

	public void setDate(Date date) {
		this.date = ((Date) date.clone());
	}

	public void setName(String name) {
		this.name = name;
	}

	public abstract Integer getId();

	public boolean hasBeenPersisted() {
		return getId() != null;
	}
}
