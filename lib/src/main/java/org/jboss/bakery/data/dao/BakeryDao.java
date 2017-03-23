package org.jboss.bakery.data.dao;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.bakery.data.model.Pastry;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

@Component
public class BakeryDao extends AbstractDao<Pastry> {

	private Date earlyBirdPastry = null;

	public BakeryDao() {
		super(Pastry.class);
	}

	public final Pastry findRecipe(Date publicationDate) {
		Assert.notNull(publicationDate, "publicationDate must not be null");
		EntityManager em = getEntityManager();
		Query query = em.createNamedQuery("Pastry.findRecipe");
		query.setParameter("publicationDate", publicationDate);

		@SuppressWarnings("unchecked")
		List<Pastry> pastryList = query.getResultList();
		Pastry pastry;
		if (pastryList.isEmpty()) {
			pastry = null;
		} else {
			pastry = pastryList.get(0);
		}
		return pastry;
	}

	public final Pastry eatPastry(Pastry pastry, Date date) {
		if (pastry == null) {
			throw new IllegalArgumentException("pastry is null");
		}
		if (date == null) {
			throw new IllegalArgumentException("date is null");
		}
		EntityManager em = getEntityManager();
		Calendar bakingCalendar = new GregorianCalendar();
		bakingCalendar.setTime(date);

		pastry.setDate(bakingCalendar.getTime());
		em.flush();
		return pastry;
	}

	public final Date findEarlyBirdPastry() {
		if (this.earlyBirdPastry == null) {
			this.earlyBirdPastry = initEarlyBirdPastry();
		}
		return this.earlyBirdPastry;
	}

	private Date initEarlyBirdPastry() {
		EntityManager em = getEntityManager();
		Query query = em.createNamedQuery("Pastry.findEarlyBirdPastry");

		return (Date) query.getSingleResult();
	}
}
