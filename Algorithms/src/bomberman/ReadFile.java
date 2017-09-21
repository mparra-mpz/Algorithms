package bomberman;

import java.io.BufferedReader;
import java.io.FileReader;

public class ReadFile {
	
	private String content;
	private String fileName;
	
	public ReadFile(String _fileName) {
		this.content = "";
		this.fileName = _fileName;
	}
	
	public String getContent() throws Exception {
		BufferedReader br = null;
		FileReader fr = null;
		
		fr = new FileReader(this.fileName);
		br = new BufferedReader(fr);

		String aux = br.readLine();

		while(aux != null) {
			this.content = this.content + aux + "\n";
			aux = br.readLine();
		}
		
		br.close();
		fr.close();
		
		return this.content;
	}

}
