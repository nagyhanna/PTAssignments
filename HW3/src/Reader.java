import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

	private int[] inputs;
	private String[] stopAndStart;

	public Reader() {
		int i = 0;
		int j=0;
		inputs = new int[10];
		 stopAndStart= new String[2];
		try (BufferedReader br = new BufferedReader(new FileReader("input.txt"))) {

			String sCurrentLine;

			while ((sCurrentLine = br.readLine()) != null) {
				
				
				try{
					inputs[i] = Integer.parseInt(sCurrentLine);
					System.out.println(sCurrentLine);
				}catch(NumberFormatException e){
					System.out.println(sCurrentLine);
					stopAndStart[j]= sCurrentLine;
					j++;
				}
				
				i++;

			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int getMinProcTime() {
		return this.inputs[0];
	}

	public int getMaxProcTime() {
		return this.inputs[1];
	}

	public int getNrOfServers() {

		return this.inputs[2];
	}
	public String getStartTime() {

		return this.stopAndStart[0];
	}
	public String getStopTime() {

		return this.stopAndStart[1];
	}

}