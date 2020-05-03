package com.niit.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String addProductcmplt(@ModelAttribute("products")Products products)
	{
		productDAO.addProduct(products);
		return "redirect:/";
	}

}
