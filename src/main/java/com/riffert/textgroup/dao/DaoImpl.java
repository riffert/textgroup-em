package com.riffert.textgroup.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.entity.Equivalence;
import com.riffert.textgroup.entity.Group;
import com.riffert.textgroup.entity.Language;
import com.riffert.textgroup.entity.Text;

@Transactional
public class DaoImpl
{
		  @PersistenceContext
		  private EntityManager em;
		  
		  /*_____________________________________________________________________________*/
		  
		  
		  public void createDomain(Domain domain)
		  {
				em.persist(domain);		  
		  }
	
		  public void updateDomain(Domain domain)
		  {
				em.merge(domain);		  
		  }
		  
		  public Domain getDomain(Long domainID)
		  {
			  	return em.find(Domain.class,domainID);
		  }
	
		  public Object getEntity(Object entity, Long id)
		  {
			  	return em.find(entity.getClass(),id);
		  }
		  
		  /*_____________________________________________________________________________*/
		  
		  
		  public void createGroup(Group group)
		  {
				em.persist(group);		  
		  }
	
		  public void updateGroup(Group group)
		  {
				em.merge(group);		  
		  }
	
		  public Group getGroup(Long groupID)
		  {
			  	return em.find(Group.class,groupID);
		  }
		  
		  /*_____________________________________________________________________________*/
		  
	
		  public void createEquivalence(Equivalence equivalence)
		  {
				em.persist(equivalence);		  
		  }
	
		  public void updateEquivalence(Equivalence equivalence)
		  {
				em.merge(equivalence);		  
		  }
		  
		  public Equivalence getEquivalence(Long equivalenceID)
		  {
			  	return em.find(Equivalence.class,equivalenceID);
		  }
	
		  /*_____________________________________________________________________________*/
		  
		  public void createText(Text text)
		  {
				em.persist(text);		  
		  }
	
		  public void updateText(Text text)
		  {
				em.merge(text);
		  }
		  
		  public void updateText(Long id,String text)
		  {
				em.createQuery("UPDATE Text t SET t.value = :text WHERE t.id = :id").setParameter("id", id).setParameter("text", text).executeUpdate();
		  }
		  
		  public void removeEquivalence(Long equivalenceId)
		  {
				em.createQuery("delete from Text t where t.equivalence.id = :equivalenceId").setParameter("equivalenceId", equivalenceId).executeUpdate();
				em.createQuery("delete from Equivalence e where e.id = :equivalenceId").setParameter("equivalenceId", equivalenceId).executeUpdate();
		  }
		  
		  /*_____________________________________________________________________________*/
		  
		  public Domain getDomain(Domain domain)
		  {
			  	return em.find(Domain.class,(long)domain.getId());
		  }
	
		  /*_____________________________________________________________________________*/
		  
		  public List<Text> searchText(String searchValue,Long groupID)
		  {
			   	return em.createQuery("select t from Text t where t.value like '"+searchValue+"' and t.group = "+groupID+" order by t.equivalence.userId").getResultList();
		  }
		  
		  public List<Domain> getDomains()
		  {
			  	return em.createQuery("Select d from Domain d").getResultList();
		  }
		  
		  public List<Language> findAll()
		  {
				List<Language> list = em.createQuery("Select l from Language l").getResultList();
				return list;
		  }
	
		  public List<Language> searchByEnglishKeyword(String searchValue)
		  {
				List<Language> list = em.createQuery("Select l from Language l where l.english like '"+searchValue+"'").getResultList();
				return list;
		  }
		  
		  /*_____________________________________________________________________________*/

}
