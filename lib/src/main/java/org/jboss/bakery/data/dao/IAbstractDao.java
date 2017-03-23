package org.jboss.bakery.data.dao;

import java.util.List;

public abstract interface IAbstractDao<T>
{
  public abstract void flush();
  
  public abstract T create(T paramT);
  
  public abstract T update(T paramT);
  
  public abstract void delete(T paramT);
  
  public abstract T findById(Object paramObject);
  
  public abstract List<T> findAll();
  
  public abstract void refresh(T paramT);
  
  public abstract T merge(T paramT);
}
