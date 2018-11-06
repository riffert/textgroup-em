package com.riffert.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.riffert.config.Init;
import com.riffert.textgroup.entity.Domain;
import com.riffert.textgroup.service.DatabaseRequestService;


@Controller
public class DomainController
{
		private DatabaseRequestService databaseRequestService;
		
		public DomainController()
		{
				databaseRequestService = Init.databaseRequestService;
		}

		@RequestMapping(value="/addDomain")
		public String inputDomain()
		{		
				return "addDomain";
		}
		
		@RequestMapping(value="/createDomain")
		public String createDomain(@RequestParam(defaultValue="")String domain)
		{		
				if ( !domain.equals("") ) {
					databaseRequestService.createDomain(domain);
				}

				return "redirect:/";
		}
		
		@RequestMapping(value="/modifyDomain")
		public String modifyDomain(Model model,@RequestParam(name="domain",defaultValue="")Long domainID)
		{		
				Domain domain = databaseRequestService.getDomain(domainID);
				model.addAttribute("domain", domain);
				return "modifyDomain";
		}
		
		@RequestMapping(value="/updateDomain")
		public String updateDomain(@RequestParam(name="domain",defaultValue="")Long domainID,
								   @RequestParam(defaultValue="")String newName)
		{		
				Domain domain = new Domain(domainID);
			
				if ( !newName.equals("") )
				{
					System.out.println("update : "+domain.getName()+" to "+newName);
					
					domain.setName(newName);
					databaseRequestService.updateDomain(domain);
				}
				
				return "redirect:/?domain="+domain.getId();
		}

}
