import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
public class GetRandom {
	
	public static void main(String[] args) throws Exception {
		int max = 127;
		int min = -128;
		int num = 20;
		String url = "https://www.random.org/integers/?num="+num+"&min="+min+"&max="+max+"&col=1&base=10&format=plain&rnd=new";

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		// optional default is GET
		con.setRequestMethod("GET");

		//add request header
		con.setRequestProperty("User-Agent", "Mozilla/5.0");

		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'GET' request to URL : " + url);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		
		int  i = 0 ;
		byte[] randbytes = new byte[num];
		while ((inputLine = in.readLine()) != null) {
			int inval = Integer.valueOf(inputLine);
			randbytes[i++] = (byte) inval;
		}
		in.close();

		//print result
		KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
		SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
		sr.setSeed(randbytes);
		kpg.initialize(1024, sr);
		KeyPair pair = kpg.generateKeyPair();
		PrivateKey priv = pair.getPrivate();
		PublicKey pub = pair.getPublic();
		System.out.println("Encoded private string is "+priv.getEncoded());
		System.out.println("Encoded public string is "+pub.getEncoded());
	}
}
