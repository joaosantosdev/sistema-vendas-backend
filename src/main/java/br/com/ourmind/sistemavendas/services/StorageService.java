package br.com.ourmind.sistemavendas.services;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.ourmind.sistemavendas.utils.ImageBuilder;
import br.com.ourmind.sistemavendas.utils.ImageUtil;

@Service
public class StorageService {
	private final Path root = Paths.get("uploads");
	
	public void init() {
		try {
			Files.createDirectory(this.root);
		}catch(Exception e) {
			
		}
	}
	
	public String save(MultipartFile file, String name) throws IOException {
		this.init();
		String ext = ImageUtil.getExtension(file);
		String fileName = name+"."+ext;
		Files.delete(this.root.resolve(fileName));
		Files.copy(file.getInputStream(), this.root.resolve(fileName));
		return fileName;
	}
	
	public String saveImage(MultipartFile file, String name) throws IOException {
		this.init();
		String ext = "jpg";
		String fileName = name+"."+ext;
		Path fileDir = this.root.resolve(fileName);
		if(fileDir.toFile().exists()){
			Files.delete(fileDir);
		}
		Files.copy(ImageUtil.getInputStream(ImageUtil.pngToJpg(file), ext), fileDir);
		return fileName;
	}
	
	
	public String saveImage(InputStream is, String name) throws IOException {
		this.init();
		String ext = "jpg";
		String fileName = name+"."+ext;
		
		Path fileDir = this.root.resolve(fileName);
		if(fileDir.toFile().exists()){
			Files.delete(fileDir);
		}
		
		Files.copy(is, fileDir);
		return fileName;
	}
	
	
	public void delete(String fileName) throws IOException {
		Files.delete(this.root.resolve(fileName));
	}
	
	public Resource load(String fileName) throws MalformedURLException {
		Path file = root.resolve(fileName);
		Resource resource = new UrlResource(file.toUri());
		
		if(resource.exists() && resource.isReadable()) {
			return resource;
		}
		return null;
	}
}
