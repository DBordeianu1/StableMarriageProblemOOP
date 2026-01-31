// Project CSI2120/CSI2520
// Winter 2026
// Robert Laganiere, uottawa.ca

// this is the (incomplete) Resident class

// Completed by Roman Solomakha St. No. 300422752 and Daniela Bordeianu St. No. ?
public class Resident {
	
	private int residentID;
	private String firstname;
	private String lastname;
	private String[] rol;
	private Program matchedProgram; // null if the resident has not been matched
	private int matchedRank; // -1 if the resident has not been matched 
	
	// constructs a Resident
    public Resident(int id, String fname, String lname) {
		residentID = id;
		firstname = fname;
		lastname = lname;
		matchedProgram = null;
		matchedRank = -1;
	}

	//getters

	public int getID(){
		return residentID;
	}

	public int getRank(){
		return matchedRank;
	}

	public String[] getROL() {
		return rol;
	}

	//setters

    // the rol in order of preference
	public void setROL(String[] rol) {
		this.rol= rol;
	}

	public void setMatchedProgram(Program program){
		matchedProgram = program;
	}

	public void setMatchedRank(int i){
		matchedRank = i;
	}
	
	// string representation
	public String toString() {
		return "["+residentID+"]: "+firstname+" "+ lastname+" ("+rol.length+")";	  
	}
}