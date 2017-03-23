package org.jboss.bakery.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.jboss.bakery.data.model.Chocolate;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Have a look at the wiki for the documentation.
 * 
 * Good luck!
 */
@Service
public class ChocolateCodeGenerator implements InitializingBean {

	private int initialChocolateCode = 900000000;
	private int defaultChocolateCount = 800000000;
	private String chocolateCodeLabel = "900000000";

	@PersistenceContext
	private EntityManager em;
	private static final int[] FACTORS = { -1, 2, 3, 4, 5, 6, 7, 8, 9 };
	private List<String> chocolateCodeList;

	private boolean isValidCode(int code) {
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			int digit = code % 10;
			code /= 10;
			sum += FACTORS[i] * digit;
		}
		return sum % 11 == 0;
	}

	public synchronized String getVoorNieuweChocolate() {
		String result = null;
		while (result == null) {
			this.initialChocolateCode += 1;
			if (isValidCode(this.initialChocolateCode)) {
				result = String.valueOf(this.initialChocolateCode);
			}
		}
		return result;
	}

	public synchronized String getCurrentUpdatedDynamicChocolateCode() {
		if (this.chocolateCodeList.isEmpty()) {
			Assert.notEmpty(this.chocolateCodeList, "No chocolate code available anymore");
		}
		this.chocolateCodeLabel = ((String) this.chocolateCodeList.remove(0));
		return this.chocolateCodeLabel;
	}

	public synchronized String getNewChocolateCode() {
		String result = null;
		while (result == null) {
			this.defaultChocolateCount += 1;
			if (isValidCode(this.defaultChocolateCount)) {
				result = String.valueOf(this.defaultChocolateCount);
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	public void afterPropertiesSet() throws Exception {
		Query query = this.em.createQuery("from Chocolate order by origin desc");
		query.setFirstResult(0);
		query.setMaxResults(1);

		List<Chocolate> l = query.getResultList();
		if (!l.isEmpty()) {
			this.initialChocolateCode = Math.max(Integer.valueOf(((Chocolate) l.get(0)).getOrigin()).intValue(),
					this.initialChocolateCode);
		}
		query = this.em.createQuery(
				"from Chocolate where origin >= '800000000' and origin < '900000000' order by origin desc");
		query.setFirstResult(0);
		query.setMaxResults(1);
		l = query.getResultList();
		if (!l.isEmpty()) {
			this.defaultChocolateCount = Math.max(Integer.valueOf(((Chocolate) l.get(0)).getOrigin()).intValue(),
					this.defaultChocolateCount);
		}
	}

}
