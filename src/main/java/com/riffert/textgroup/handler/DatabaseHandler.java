package com.riffert.textgroup.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.riffert.config.Init;
import com.riffert.textgroup.dao.DaoImpl;

//import com.riffert.textgroup.dao.DomainRepository;
//import com.riffert.textgroup.dao.EquivalenceRepository;
//import com.riffert.textgroup.dao.GroupRepository;
//import com.riffert.textgroup.dao.LanguageRepository;
//import com.riffert.textgroup.dao.SomethingRepository;
//import com.riffert.textgroup.dao.TextRepository;


import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.entity.Equivalence;
import com.riffert.textgroup.entity.Group;
import com.riffert.textgroup.entity.Language;
import com.riffert.textgroup.entity.Text;


/*
 * Persistence layer
 */

public class DatabaseHandler
{

		DaoImpl dao;
	
		public DatabaseHandler()
		{
			dao = (DaoImpl) Init.context.getBean("DaoImpl");
		}
	
		public Page<Language> getLanguagesByEnglishKeyword(String keyword,int currentpage,int pagesize)
		{
				List<Language> list = dao.searchByEnglishKeyword("%"+keyword+"%");
				Page<Language> page = new PageImpl<Language>(list);
				return page;
		}

		
		//TODO : 2 last parameters are not used
		public Page<Text> search(Long groupID,String keyword,int currentpage,int pagesize)
		{
				List<Text> list = dao.searchText("%"+keyword+"%", groupID);
				Page<Text> page = new PageImpl<Text>(list);
				return page;
		}
		
		public List<Equivalence> getEquivalences(Domain domain)
		{
				return null;
		}				
		
		public List<Long> getUserIds(Domain domain)
		{
				return null;
		}

		public void update(Equivalence equivalence, Domain domain)
		{
				updateEquivalence(equivalence);
				updateDomain(domain);
		}
		
		public Equivalence updateEquivalence(Equivalence equivalence)
		{
				dao.updateEquivalence(equivalence);
				return equivalence;
		}
		
		public void updateText(Long id,String text)
		{
				dao.updateText(id, text);
		}
		
		public void removeEquivalence(Long equivalenceId)
		{
				dao.removeEquivalence(equivalenceId);
		}

		public void removeGroup(Domain domain, Group group)
		{
		}
		
		public Domain updateDomain(Domain domain)
		{
				dao.updateDomain(domain);
				return domain;
		}

		public List<Domain> getDomains()
		{
				return dao.getDomains();
		}
		
		
		public Domain getDomain(Long domainID)
		{
				Domain domain = dao.getDomain(domainID);
				
				if (domain == null)
					return new Domain(domainID);
				else
					return domain;
					
		}		
		
		public Group getGroup(Long groupID)
		{
				Group group = dao.getGroup(groupID);
				
				if ( group == null )
					return new Group(groupID);
				else
					return group;
		}		

		public Equivalence getEquivalence(Long equivalenceID)
		{
				Equivalence equivalence = dao.getEquivalence(equivalenceID);
				
				if ( equivalence == null )
					return new Equivalence(equivalenceID);
				else
					return equivalence;
		}		
		
		public Equivalence getNewEquivalence(Group group)
		{
				return createEquivalence(group.getDomain());
		}
		
		public Equivalence createEquivalence(Domain domain)
		{
				Equivalence equivalence = new Equivalence();
				dao.createEquivalence(equivalence);
				domain.add(equivalence);  // next call
				dao.updateDomain(domain);
				dao.updateEquivalence(equivalence);
				return equivalence;
				
		}		
		
		public boolean addText(Group group,Text text)
		{
				Equivalence equivalence = createEquivalence(group.getDomain());
				addText(text, equivalence, group);
				return true;
		}

		public Text addText(Text text,Equivalence equivalence,Group group)
		{
				dao.createText(text);
				equivalence.add(text);
				group.add(text);
				dao.updateEquivalence(equivalence);
				dao.updateGroup(group);
				dao.updateText(text);
				return text;
		}
		
		public void addGroup(Domain domain,String groupName, String userGroupName)
		{
				addGroup(domain, new Group(groupName,userGroupName));
		}
		
		public Group addGroup(Domain domain,Group group)
		{
				dao.createGroup(group);	
				domain.add(group);
				dao.updateDomain(domain);
				dao.updateGroup(group);
				return group;
		}
		
		public void createDomain(String domainName)
		{
				createDomain(new Domain(domainName));
		}		
		
		public Domain createDomain(Domain domain)
		{
				dao.createDomain(domain);
				return domain;
		}

}
