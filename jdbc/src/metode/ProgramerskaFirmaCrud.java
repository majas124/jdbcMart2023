package metode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.ZaposleniDto;


public class ProgramerskaFirmaCrud {
	
	private static final String url = "jdbc:mysql://localhost:3306/programerska_firma";
	private static final String userName = "root";
	private static final String password = "root";
	
	private static Connection konektujSe() throws SQLException {
		
		return DriverManager.getConnection(url, userName, password);
			
	}
	
	private static void zatvoriKonekciju(Connection con) {
		if(con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void zatvoriPreparedStatement(PreparedStatement pst) {
		if(pst != null) {
			try {
				pst.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void zatvoriResultSet(ResultSet rs) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void insertUPozicija(String pozicija) {
		
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			// konekcija na bazu
			con = konektujSe();
			System.out.println("Konekcija uspesna...");
			// kreiramo query
			String query = "INSERT INTO pozicija VALUES (null, ?)";
			// kreiramo prepared statement (postar)
			pst = con.prepareStatement(query);
			// setovanje parametara (znakove pitanja)
			pst.setString(1, pozicija);
			// saljemo query ka bazi
			pst.execute();
			System.out.println("U bazu je upisana pozicija: " + pozicija);		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {	
			zatvoriPreparedStatement(pst);
			zatvoriKonekciju(con);		
		}
		
	}
	
	public static void updatePlataZaposleni(String id, String plata) {
		
		Connection con = null;
		PreparedStatement pst = null;
		
		try {
			con = konektujSe();
			String query = "UPDATE zaposleni SET plata = ? WHERE id = ?";
			pst = con.prepareStatement(query);
			pst.setDouble(1, Double.parseDouble(plata));
			pst.setInt(2, Integer.parseInt(id));
			pst.execute();
			System.out.println("Setovana je plata: " + plata + " zaposlenom sa id: " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			zatvoriPreparedStatement(pst);
			zatvoriKonekciju(con);		
		}	
		
	}
	
	
	public static void deleteZaposleniPoId(String id) {
		
		Connection con = null;
		PreparedStatement pst = null;
			
		try {
			con = konektujSe();
			String query = "DELETE FROM zaposleni WHERE id = ?";
			pst = con.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(id));
			pst.execute();
			System.out.println("Obrisan je zaposleni sa id: " + id);
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			zatvoriPreparedStatement(pst);	
			zatvoriKonekciju(con);			
		}	
		
	}
	
	
	public static String vratiNazivPozicije(String id) {
		
		String nazivPozicije = "";
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			con = konektujSe();
			String query = "SELECT naziv FROM pozicija WHERE id = ?";
			pst = con.prepareStatement(query);
			pst.setInt(1, Integer.parseInt(id));
			rs = pst.executeQuery();
			while(rs.next()) {
				nazivPozicije = rs.getString("naziv");
			}	
			return nazivPozicije;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			zatvoriResultSet(rs);
			zatvoriPreparedStatement(pst);
			zatvoriKonekciju(con);		
		}	
	}
	
	
	public static List<String> vratiSveUserNameove() {
		
		List<String> sviUserNameovi = new ArrayList<String>();
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			con = konektujSe();
			String query = "SELECT username FROM user";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			while(rs.next()) {
				String userNameIzBaze = rs.getString("username");
				sviUserNameovi.add(userNameIzBaze);
			}	
			return sviUserNameovi;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			zatvoriResultSet(rs);
			zatvoriPreparedStatement(pst);
			zatvoriKonekciju(con);		
		}	
	}
	
	
	public static List<ZaposleniDto> vratiPodatkeOzaposlenima() {
		
		List<ZaposleniDto> zaposleni = new ArrayList<ZaposleniDto>();
		
		Connection con = null;
		PreparedStatement pst = null;
		ResultSet rs = null;
		
		try {
			con = konektujSe();
			String query = "SELECT id, ime, prezime, plata FROM zaposleni";
			pst = con.prepareStatement(query);
			rs = pst.executeQuery();
			while(rs.next()) {
				ZaposleniDto zaposleniDto = new ZaposleniDto();
				// ovo je mapiranje - spajate klasu iz jave sa kolonama iz baze
				zaposleniDto.setId(rs.getInt("id"));
				zaposleniDto.setIme(rs.getString("ime"));
				zaposleniDto.setPrezime(rs.getString("prezime"));
				zaposleniDto.setPlata(rs.getDouble("plata"));
				
				zaposleni.add(zaposleniDto);
			}	
			return zaposleni;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}finally {
			zatvoriResultSet(rs);
			zatvoriPreparedStatement(pst);
			zatvoriKonekciju(con);		
		}	
	}
	
	
	

}
