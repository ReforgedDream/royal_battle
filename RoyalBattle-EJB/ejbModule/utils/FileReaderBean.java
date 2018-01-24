package utils;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class FileReaderBean
 */
@Stateless
@LocalBean
public class FileReaderBean {

	private String path;
	private Charset encoding = Charset.defaultCharset();

	//constructor
	public FileReaderBean(String path){this.path = path;}
	
	/**
     * Default constructor. 
     */
    public FileReaderBean() {
        // TODO Auto-generated constructor stub
    }

	public String ReadFile() {
		byte[] encoded = null;

		try {
			encoded = Files.readAllBytes(Paths.get(this.path));
		} catch (IOException e) {
	            e.printStackTrace();
		}

		return new String(encoded, this.encoding);
	}

}
