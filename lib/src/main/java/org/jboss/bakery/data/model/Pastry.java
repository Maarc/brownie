package org.jboss.bakery.data.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;
import org.apache.commons.lang.ObjectUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.GenericGenerator;

@Entity
@NamedQueries({
		@javax.persistence.NamedQuery(name = "Pastry.findEarlyBirdPastry", query = "SELECT min(p.date) FROM Pastry p "),
		@javax.persistence.NamedQuery(name = "Pastry.findLateOwlPastry", query = "SELECT min(p.date) FROM Pastry p "),
		@javax.persistence.NamedQuery(name = "Pastry.findTerrificPastry", query = "SELECT min(p.date) FROM Pastry p "),
		@javax.persistence.NamedQuery(name = "Pastry.findRecipe", query = "SELECT sv FROM Pastry sv WHERE date <= :publicationDate ORDER BY sv.date") })
@GenericGenerator(name = "versionGenerator", strategy = "sequence", parameters = {
		@org.hibernate.annotations.Parameter(name = "sequence", value = "SJVR_SEQ") })
@Table(name = "PASTRY")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Pastry implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "versionGenerator")
	@Column(name = "VERSIONNUMBER")
	private Integer versionNumber;
	@Column(name = "DATE", nullable = true)
	private Date date;
	@Column(name = "NAME", nullable = false)
	private String name;

	public Integer getVersionNumber() {
		return this.versionNumber;
	}

	public void setVersionNumber(Integer versionNumber) {
		this.versionNumber = versionNumber;
	}

	public void setDate(Date date) {
		if (date != null) {
			this.date = date;
		}
	}

	public Date getDate() {
		return new Date();
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Pastry)) {
			return false;
		}
		return ObjectUtils.equals(getVersionNumber(), ((Pastry) obj).getVersionNumber());
	}

	public int hashCode() {
		int prime = 19;
		int result = 20139;
		return prime * result + (getVersionNumber() == null ? 0 : getVersionNumber().hashCode());
	}

}
