package com.niit.Controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.niit.ServiceDAO.ProductServiceDAO;
import com.niit.model.Products;

@Controller
@RequestMapping("/products")
public class ProductController 
{
	
	@Autowired
	ProductServiceDAO productDAO;
	@RequestMapping("/add")
	public String addProducts(ModelMap map)
	{
		map.addAttribute("products", new Products());
		return "AddProduct";
	}
	@PostMapping("/add")
	public String addProductcmplt(@ModelAttribute("products")Products products,@RequestParam("productPhoto")MultipartFile productPhoto)
	{
		productDAO.addProduct(products);
		String path="E:\\Demo\\SpringFrontEndd\\src\\main\\webapp\\WEB-INF\\images\\";
		Path p=Paths.get(path+products.getProductname());
		if (!Files.exists(p))
		{    
			try
			{
				Files.createDirectory(p);
				System.out.println("Directory created");
			}
			catch (Exception e) 
			{
				System.out.println(e);
			}
        }
List<String> files=displayImage(products.getProductname());
		
		path=String.valueOf(p.toString()+"\\"+(files.size()+1)+".jpg");
		System.out.println(path);
		File imageFile=new File(path);
		
		if(!productPhoto.isEmpty())
		{
			try
			{
				byte buffer[]=productPhoto.getBytes();
				FileOutputStream fos=new FileOutputStream(imageFile);
				BufferedOutputStream bos=new BufferedOutputStream(fos);
				bos.write(buffer);
				bos.close();
			}
			catch (Exception e) 
			{
				System.out.println(e);
			}
		}
		return "redirect:/";
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
	@RequestMapping("/edit")
	public String displayProductById(@RequestParam("productid")int productid,ModelMap map)
	{
		map.addAttribute("p",productDAO.displayProductsById(productid));
		return "AddProduct";
	}
	@PostMapping("/update")
	public String updateProducts(@ModelAttribute("p")Products products,@RequestParam("productPhoto")MultipartFile productphoto)
	{
		productDAO.updateProduct(products);
		String path="E:\\Demo\\SpringFrontEndd\\src\\main\\webapp\\WEB-INF\\images\\";
		Path p=Paths.get(path+products.getProductname());
		if (!Files.exists(p))
		{    
			try
			{
				Files.createDirectory(p);
				System.out.println("Directory created");
			}
			catch (Exception e) 
			{
				System.out.println(e);
			}
        }
List<String> files=displayImage(products.getProductname());
		
		path=String.valueOf(p.toString()+"\\"+(files.size()+1)+".jpg");
		System.out.println(path);
		File imageFile=new File(path);
		
		if(!productphoto.isEmpty())
		{
			try
			{
				byte buffer[]=productphoto.getBytes();
				FileOutputStream fos=new FileOutputStream(imageFile);
				BufferedOutputStream bos=new BufferedOutputStream(fos);
				bos.write(buffer);
				bos.close();
			}
			catch (Exception e) 
			{
				System.out.println(e);
			}
		}
		return "redirect:/";
	}
}
