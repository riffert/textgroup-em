package com.riffert.web;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.riffert.config.Init;
import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.entity.Group;
import com.riffert.textgroup.service.DatabaseRequestService;

@Controller
public class GroupController
{

		private DatabaseRequestService databaseRequestService;
		
		public GroupController()
		{
				databaseRequestService = Init.databaseRequestService;
		}		

		@RequestMapping(value="/addGroup")
		public String addGroup(Model model,
				@RequestParam(name="domain",defaultValue="1")Long domainID,
				@RequestParam(defaultValue="")String groupname,
				@RequestParam(defaultValue="")String usergroupname)
		{		
			
				Domain domain = databaseRequestService.getDomain(domainID);
			
				// checking if groupname is already present
				for(Group group:domain.getGroups())
				{
					if (group.getName().equals(groupname))
					{
						groupname = groupname + "_" + randomString(4);
					}
				}
			
				databaseRequestService.addGroup(domain, groupname, usergroupname );
			
				return "redirect:/?domain="+domain.getId()+"&flag=1";
		}
		
		private String randomString(int nlen)
		{
			char[] chars = "abcdefghijklmnopqrstuvwxyz".toCharArray();
			StringBuilder sb = new StringBuilder();
			Random random = new Random();
			for (int i = 0; i < nlen; i++) {
			    char c = chars[random.nextInt(chars.length)];
			    sb.append(c);
			}
			return sb.toString();

		}
}
