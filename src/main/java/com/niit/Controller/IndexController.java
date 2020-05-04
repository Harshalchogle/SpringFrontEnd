package com.niit.Controller;

import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.niit.ServiceDAO.ProductServiceDAO;
import com.niit.model.Products;

@Controller
public class IndexController 
{
	@Autowired
	ProductServiceDAO productDAO;
	@RequestMapping(value = {"","/","home"})
	public String index(ModelMap map)
	{
		List<Products> products=new ArrayList<Products>();
		for (Products p:productDAO.displayAllProducts()) 
		{
			List<String> im=displayImage(p.getProductname());
			if(!im.isEmpty())
			p.setProductimage(im.get(0));	
			products.add(p);
		}
		map.addAttribute("products",products);
		return "index";
	}
	public List<String> displayImage(String productname)
	{

		List<String> images=new ArrayList();
		
		try
		{
			String path="E:\\Demo\\SpringFrontEndd\\src\\main\\webapp\\WEB-INF\\images\\";
			Path p=Paths.get(path+productname);
			DirectoryStream<Path> files=Files.newDirectoryStream(p,"*.*");
			for(Path file:files)
			{
//				FileInputStream fis=new FileInputStream(file.toString());
//				byte[] arr=new byte[fis.available()];
//				fis.read(arr);
				
				images.add(file.getFileName().toString());
			}
			
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
		return images;
	}

}
