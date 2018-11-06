package com.riffert.bootstrap;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.riffert.node.Node;
import com.riffert.node.TextNode;


public class Treeview
{
		private List<Node> nodes ;

		public Treeview()
		{
			nodes = new ArrayList<Node>();
		}
		
		
		public void validate()
		{
			if ( nodes != null && nodes.size() > 0 )
			{
				if ( nodes.size() > 0 && nodes.get(0).getClass().getName().equals(TextNode.class.getName()) )
				{
					((TextNode)nodes.get(nodes.size()-1)).setC(' ');
					nodes.forEach(node->node.validate());
				}
			}
		}		
		
		public Treeview(List<Node> nodes)
		{
			this();
			setNodes(nodes);
			validate();
		}
		
		public Node addNode(Node node)
		{
			nodes.add(node);
			return node;
		}
		
		public void setNodes(List<Node> nodes)
		{
			this.nodes = nodes;
			validate();
		}
		
		public List<Node> getNodes() {
			return nodes;
		}		
		
}
