package ita.P2Q2;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import ita.P2Q2.VulnerableClass;

public class AppTest{
	
	@BeforeClass
	public static void cleanTestFolder(){
		Path root = Paths.get(System.getProperty("user.dir"),"ArquivosDeTeste");
		File[] files = root.toFile().listFiles();
		if(files!=null) {
	        for(File f: files) {
	            f.delete();
	        }
	    }
	}
	
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	VulnerableClass vulnerableClass = new VulnerableClass();
	
	
	@Test
	public void testInvalidAnswer() throws Exception {
		//byte[] input = {"R".getBytes(),"W".getBytes(),"leonardo".getBytes()};
		exception.expect(Exception.class);
	    exception.expectMessage("Invalid answer.");
		ByteArrayInputStream in = new ByteArrayInputStream("R\ns\nW\nleonardo\ns\nR\na\n".getBytes());
		System.setIn(in);

		vulnerableClass.vulnerableMethod("teste.txt");
		System.setIn(System.in);
	}
	@Test
	public void testBadFileName() throws Exception {
		//byte[] input = {"R".getBytes(),"W".getBytes(),"leonardo".getBytes()};
		exception.expect(Exception.class);
	    exception.expectMessage("Bad file name.");

		vulnerableClass.vulnerableMethod("teste/leonardo.txt");
	}
	
	@Test
	public void testFileDoesntExist() throws Exception {
		//byte[] input = {"R".getBytes(),"W".getBytes(),"leonardo".getBytes()};
		exception.expect(FileNotFoundException.class);

		ByteArrayInputStream in = new ByteArrayInputStream("R\ns\nW\nleonardo\ns\nR\na\n".getBytes());
		System.setIn(in);

		vulnerableClass.vulnerableMethod("teste1.txt");
		System.setIn(System.in);
	}
	@Test
	public void test() throws Exception {
		//byte[] input = {"R".getBytes(),"W".getBytes(),"leonardo".getBytes()};

		ByteArrayInputStream in = new ByteArrayInputStream("W\nleonardo\ns\nR\nn\n".getBytes());
		System.setIn(in);

		vulnerableClass.vulnerableMethod("teste.txt");
		Path root = Paths.get(System.getProperty("user.dir"),"ArquivosDeTeste");
		Path file = root.resolve("teste.txt");
		BufferedReader br = new BufferedReader(new FileReader(file.toFile()));
		String result = "";
		String currentLine;

		while ((currentLine = br.readLine()) != null) {
			result = result + currentLine;
		}
		br.close();
		System.setIn(System.in);
		assertEquals(result,"leonardo");
	}

}
