package org.jboss.bakery.service;

import org.jboss.bakery.data.dao.BakeryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * No idea why we created a separate service for this.
 * 
 * Don't try this at home! 
 */
@Service
public class BakingService {

	@Autowired
	private BakeryDao bakeryDao;

	public void bakeSomeDoe(String bakingAction) {
		this.bakeryDao.findEarlyBirdPastry();
	}

}
