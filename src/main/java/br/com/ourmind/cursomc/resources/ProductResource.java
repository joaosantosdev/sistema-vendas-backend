package br.com.ourmind.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ourmind.cursomc.dto.ProductDTO;
import br.com.ourmind.cursomc.services.ProductService;
import br.com.ourmind.cursomc.utils.StringUtil;


@RestController
@RequestMapping(value="/products")
public class ProductResource {
	
	@Autowired 
	private ProductService productService;
	

	@RequestMapping(value="/page", method=RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> getPage(
			@RequestParam(required=false, defaultValue="") String name,
			@RequestParam(required=false, defaultValue="") String categories,
			@RequestParam(required=false, defaultValue="0")  Integer page, 
			@RequestParam(required=false, defaultValue="24")  Integer perPage, 
			@RequestParam(required=false, defaultValue="name")  String orderBy, 
			@RequestParam(required=false, defaultValue="ASC")  String direction) {
		
		Page<ProductDTO> products = this.productService.searchByNameAndCategories(
				StringUtil.decodeParam(name),
				StringUtil.splitInteger(categories), page, perPage, orderBy, direction)
				.map(c->new ProductDTO(c));
			
		
		return ResponseEntity.ok().body(products);
	}
}
