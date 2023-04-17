package jdbc;

import java.util.List;
import java.util.Scanner;

import dto.ZaposleniDto;
import metode.ProgramerskaFirmaCrud;

public class GlavnaKlasa {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		/*
		 * System.out.println("Unesite platu: "); String plata = scanner.nextLine();
		 */
		/*
		 * System.out.println("Unesite id pozicije: "); String id = scanner.nextLine();
		 */
		
		//ProgramerskaFirmaCrud.insertUPozicija(pozicija);
		//ProgramerskaFirmaCrud.updatePlataZaposleni(id, plata);
		//ProgramerskaFirmaCrud.deleteZaposleniPoId(id);
		
		/*
		 * String pozicija = ProgramerskaFirmaCrud.vratiNazivPozicije(id);
		 * System.out.println("Naziv pozicije: " + pozicija);
		 */
		
		/*List<String> userNames = ProgramerskaFirmaCrud.vratiSveUserNameove();
		System.out.println("Svi usernameovi: ");
		for(String s: userNames) {
			System.out.println(s);
		}*/
		
		List<ZaposleniDto> sviZaposleni = ProgramerskaFirmaCrud.vratiPodatkeOzaposlenima();
		System.out.println("id *** ime *** prezime *** plata");
		for(ZaposleniDto zaposleni: sviZaposleni) {
			System.out.println(zaposleni.getId() + "   " + zaposleni.getIme()
			+ "   " + zaposleni.getPrezime() + "   " + zaposleni.getPlata());	
		}
		scanner.close();
		
		
		

	}

}
