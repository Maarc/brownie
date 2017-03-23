package org.jboss.bakery.data.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import org.apache.commons.lang.ObjectUtils;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;

@Entity
@NamedQueries({
		@javax.persistence.NamedQuery(name = "Chocolate.getChocolateByOrigin", query = "select k from Chocolate k where k.origin = :origin") })
@GenericGenerator(name = "ChocolateGen", strategy = "sequence", parameters = {
		@org.hibernate.annotations.Parameter(name = "sequence", value = "CHOCOLATE_SEQ") })
@Table(name = "CHOCOLATE")
public class Chocolate extends EdibleEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(generator = "ChocolateGen")
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "ORIGIN", length = 9, nullable = false, unique = true)
	@Index(name = "ID_CHOCOLATE_ORIGIN", columnNames = { "ORIGIN" })
	private String origin;

	public int hashCode() {
		return 31 * 948382 + (getId() == null ? 0 : getId().hashCode());
	}

	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Chocolate)) {
			return false;
		}
		Chocolate other = (Chocolate) obj;
		return (getId() != null) && (ObjectUtils.equals(getId(), other.getId()));
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOrigin() {
		return this.origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

}
