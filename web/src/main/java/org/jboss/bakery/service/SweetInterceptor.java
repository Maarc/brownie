package org.jboss.bakery.service;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.Logger;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;

/**
 * Self-explaining.
 */
public class SweetInterceptor extends EmptyInterceptor {
	private static final long serialVersionUID = 131839813498324L;

	private static final Logger LOG = Logger.getLogger(SweetInterceptor.class);

	public final boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
		LOG.debug(">>> onSave");
		setValue(state, propertyNames, "date", new Date());
		return super.onSave(entity, id, state, propertyNames, types);
	}

	public final boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState,
			String[] propertyNames, Type[] types) {
		LOG.debug(">>> onFlushDirty");
		setValue(currentState, propertyNames, "date", new Date());
		return super.onFlushDirty(entity, id, currentState, previousState, propertyNames, types);
	}

	private void setValue(Object[] state, String[] propertyNames, String propertyToSet, Object value) {
		LOG.debug(">>> setValue");
		int index = Arrays.asList(propertyNames).indexOf(propertyToSet);
		if (index >= 0) {
			state[index] = value;
		}
	}

}
