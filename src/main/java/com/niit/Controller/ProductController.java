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
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.instamojo.wrapper.api.ApiContext;
import com.instamojo.wrapper.api.Instamojo;
import com.instamojo.wrapper.api.InstamojoImpl;
import com.instamojo.wrapper.model.PaymentOrder;
import com.instamojo.wrapper.model.PaymentOrderResponse;
import com.niit.ServiceDAO.ProductServiceDAO;
import com.niit.ServiceDAO.UserServiceDAO;
import com.niit.model.Products;
import com.niit.model.User;

@Controller
@RequestMapping("/products")
public class ProductController 
{
	
	@Autowired
	ProductServiceDAO productDAO;
	@Autowired
	UserServiceDAO userDAO;
	@RequestMapping("/add")
	public String addProducts(ModelMap map)
	{
		map.addAttribute("products", new Products());
		return "AddProduct";
	}
	@PostMapping("/add")
	public String addProductcmplt(@ModelAttribute("products")Products products,@RequestParam("productPhoto")MultipartFile productPhoto,ModelMap map)
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
		map.addAttribute("msg", "Product Added successfully");
		map.addAttribute("pagename","/home");
		map.addAttribute("type", "success");
		return "popup";
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
	public String updateProducts(ModelMap map,@ModelAttribute("p")Products products,@RequestParam("productPhoto")MultipartFile productphoto)
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
		map.addAttribute("msg", "Product Updated successfully");
		map.addAttribute("pagename","/home");
		map.addAttribute("type", "success");
		return "popup";
	}
	@RequestMapping("/delete")
	public String deleteProduct(ModelMap map,@RequestParam("productid")int productid)
	{
		productDAO.deleteProduct(productid);
		map.addAttribute("msg", "Product deleted successfully");
		map.addAttribute("pagename","/home");
		map.addAttribute("type", "success");
		return "popup";
		
	}
	@RequestMapping("/buy")
	public void buyProduct(@RequestParam("productid")int productid,HttpServletResponse resp)
	{
		
		Products pr=productDAO.displayProductsById(productid);
		Object principal=SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		User u=null;
		if(principal instanceof UserDetails)
		{
			UserDetails user=((UserDetails)principal);
			u=userDAO.displayUserByUsername(user.getUsername());
		}
		
		try 
        {
            //Enter your client id and client secret
            ApiContext context = ApiContext.create("test_0zjLXzsz8KEKeeu09dmIfWjeZkdvdMxyYm6", "test_JKCZGl73EY4hcA7ILkV4MkedRcDH684JzklL5RO7yQDZ0XTHXdzDyiqIZvXnYlWs4bzRwW9XFxibVwczjHO8dni2fbbnzideLsstBqJvYVPEGTrwwljnOPnOLuz", ApiContext.Mode.TEST);
            Instamojo api = new InstamojoImpl(context);

            PaymentOrder order = new PaymentOrder();
            order.setName(u.getUsername());
            order.setEmail(u.getEmail());
            order.setPhone("9004008298");
            order.setCurrency("INR");
            order.setAmount(pr.getPrice());
            order.setDescription(pr.getProductname());
            order.setRedirectUrl("https://www.google.com");
            order.setWebhookUrl("https://www.google.com");
            order.setTransactionId(UUID.randomUUID().toString());
            PaymentOrderResponse paymentOrderResponse = api.createPaymentOrder(order);
            resp.sendRedirect(paymentOrderResponse.getPaymentOptions().getPaymentUrl());
        }
        catch (Exception e) 
        {
            System.out.println(e);
        }
		
	}
}
