package dto;

public class ZaposleniDto {
	
	private int id;
	private String ime;
	private String prezime;
	private int tim;
	private double plata;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIme() {
		return ime;
	}
	public void setIme(String ime) {
		this.ime = ime;
	}
	public String getPrezime() {
		return prezime;
	}
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	public int getTim() {
		return tim;
	}
	public void setTim(int tim) {
		this.tim = tim;
	}
	public double getPlata() {
		return plata;
	}
	public void setPlata(double plata) {
		this.plata = plata;
	}
	
}
