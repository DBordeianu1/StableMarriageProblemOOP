// Project CSI2120/CSI2520
// Winter 2026
// Robert Laganiere, uottawa.ca

// this is the (incomplete) Resident class
public class Resident {
	
	private int residentID;
	private String firstname;
	private String lastname;
	private String[] rol;
	
	// constructs a Resident
    public Resident(int id, String fname, String lname) {
	
		residentID= id;
		firstname= fname;
		lastname= lname;
	}

    // the rol in order of preference
	public void setROL(String[] rol) {
		
		this.rol= rol;
	}
	
	// string representation
	public String toString() {
      
       return "["+residentID+"]: "+firstname+" "+ lastname+" ("+rol.length+")";	  
	}
}