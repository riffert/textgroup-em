package com.riffert.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.riffert.config.Init;
import com.riffert.option.FreeId;
import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.entity.Group;
import com.riffert.textgroup.service.DatabaseRequestService;



@Controller
public class OptionsController
{

		private DatabaseRequestService databaseRequestService;
		
		public OptionsController()
		{
				databaseRequestService = Init.databaseRequestService;
		}
	
		@RequestMapping(value="/options")
		public String options(Model model,
				@RequestParam(name="domain",defaultValue="1")Long domainID,
				@RequestParam(name="group",defaultValue="1")Long groupID,
				@RequestParam(defaultValue="0")int currentpage)
		{
				
			
				Domain domain = databaseRequestService.getDomain(domainID);
				Group group = databaseRequestService.getGroup(groupID);
			
			
				List<FreeId> freeIds = new ArrayList<FreeId>();
			
				System.out.println("domain -> "+domain.getId());
				System.out.println("domain -> "+domain.getName());
				
				List<Long> userIds = databaseRequestService.getUserIds(domain);
				
				Long nextEquivalenceId = domain.getNextEquivalenceId();

				boolean[] aPresent = new boolean[nextEquivalenceId.intValue()+1]; // TODO long
				
				for (int i=0;i<aPresent.length;i++)
					aPresent[i] = false;

				for (int i=0;i<userIds.size();i++)
				{
					Long userId = userIds.get(i);
					
					if (userId == null)
						System.out.println("userIds.get("+i+") returned null ");
					else
						aPresent[userId.intValue()] = true;
				}
				
				for (int i=1;i<aPresent.length;i++)
					if (!aPresent[i])
							freeIds.add(new FreeId((long)i));
						
				model.addAttribute("domain", domain);
				model.addAttribute("group", group);
				
				model.addAttribute("freeIds",freeIds);
				model.addAttribute("currentpage", currentpage);
				
				return "options";
		}
}
