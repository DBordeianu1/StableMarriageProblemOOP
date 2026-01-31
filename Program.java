// Project CSI2120/CSI2520
// Winter 2026
// Robert Laganiere, uottawa.ca

// this is the (incomplete) Program class

// Completed by Roman Solomakha St. No. 300422752 and Daniela Bordeianu St. No. 300435411
public class Program {
	
	private String programID;
	private String name;
	private int quota;
	private int[] rol; // important that it is in ascending order
	private Resident[] matchedResidents;
	private int pointer; // points at the first null cell in matchedResidents, cannot be greater than quota
	
	// constructs a Program
    public Program(String id, String n, int q) {
		programID = id;
		name = n;
		quota = q;
		matchedResidents = new Resident[q];
		pointer = 0;
	}

    // the rol in order of preference
	public void setROL(int[] rol) {
		this.rol= rol;
	}
	
	// string representation
	public String toString() {
		return "["+programID+"]: "+name+" {"+ quota+ "}" +" ("+rol.length+")";	  
	}

	public int avalible(){	// return number of avalible positions left
		return (quota - pointer);
	}

	public String getID(){
		return programID;
	}

	public String getName(){
		return name;
	}

	public boolean member(int residentID){
		for (int i=0;i<rol.length;i++){
			if (rol[i] == residentID){
				return true;
			}
		}
		return false;
	}

	public int rank(int residentID){
		for (int i=0;i<rol.length;i++){
			if (rol[i] == residentID){
				return i+1; //0 cannot be a rank
			}
		}
		return -1; //if resident not in the list
	}

	public Resident leastPreferred(){// returns a REFERENCE to leastPreferred resident in matchedResidents
		int worstRank = 0; // highier rank = worse, starting worst rank = 0
		int worstResident = -1; // remembering worst resident
		for (int i = (pointer - 1); i <= 0; i--){ // checking every matched resident 
			if (matchedResidents[i].getRank() > worstRank){ // only if the residents rank is worse than current worst
				worstRank = matchedResidents[i].getRank();
				worstResident = i;
			}
		}
		return matchedResidents[worstResident];
	}

	public int leastPreferredPos(){ // returns the POSITION of leastPreferred resident in matchedResidents
		int worstRank = 0; 
		int worstResident = -1; 
		for (int i = (pointer - 1); i >= 0; i--){ 
			if (matchedResidents[i].getRank() > worstRank){ 
				worstRank = matchedResidents[i].getRank();
				worstResident = i;
			}
		}
		return worstResident;
	}

	public Resident addResident(Resident resident){
		int r = rank(resident.getID());
		if (r != -1){ // chechking for member
			if (pointer != quota){ 	// if has not reached quota
				matchedResidents[pointer] = resident;
				resident.setMatchedProgram(this);
				resident.setMatchedRank(rank(resident.getID()));
				pointer++;
				return null;	// took free place
			}
			else {	// if has reached quota
				int l =  leastPreferredPos();
				Resident least = matchedResidents[l];
				if (r < least.getRank()) {
					matchedResidents[l] = resident;
					resident.setMatchedProgram(this);
					resident.setMatchedRank(r);
					least.setMatchedProgram(null);
					least.setMatchedRank(-1);
					return least;	// took place of another resident, return that resident
				}
			}
		}
		return null;	// isnt on the rol of this program, didn't get matched
	}
}