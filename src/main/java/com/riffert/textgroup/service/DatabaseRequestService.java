package com.riffert.textgroup.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.entity.Group;
import com.riffert.textgroup.entity.Text;
import com.riffert.textgroup.handler.DatabaseHandler;


/*
 * This class provides methods between user interface and the persistence layer
 */

public class DatabaseRequestService extends DatabaseHandler
{
	
		public DatabaseRequestService()
		{
			super();
		}

		public Page<Text> getPage(Long groupID,String keyword,int currentpage,int pagesize)
		{
				Page<Text> pageTexts = search(groupID, keyword,currentpage, pagesize);
				return pageTexts;
		}
		
		public List<Group> getGroups(Domain domain, Long groupID)
		{
				if ( domain == null) return null;
			
				List<Group> groups = domain.getGroups();
				
				if ( groups != null )
				{
						boolean  bFound = false;
						
						for (int i=0;i<groups.size();i++)
						{
							Group group = groups.get(i);
							
							if ( group.getId() == groupID )
							{
								bFound = true;
								group.setSelected("selected=\"selected\"");
							}
							else
								group.setSelected("");
						}
						
						if ( !bFound && groups.size() > 0 )
							groups.get(0).setSelected("selected=\"selected\"");
						
				}
				
				return groups;
		}
		
		public List<Domain> getDomains(Domain domain)
		{
				List<Domain> domains = getDomains();
				
				if ( domains != null )
				{
						boolean  bFound = false;
						
						for (int i=0;i<domains.size();i++)
						{
							Domain dom = domains.get(i);
							
							if ( dom.getId() == domain.getId() )
							{
								bFound = true;
								dom.setSelected("selected=\"selected\"");
							}
							else
								dom.setSelected("");
						}
						
						if ( !bFound && domains.size() > 0 )
							domains.get(0).setSelected("selected=\"selected\"");
						
				}
				
				return domains;
		}
		
}
