// Project CSI2120/CSI2520
// Winter 2026
// Robert Laganiere, uottawa.ca

// this is the (incomplete) Program class
public class Program {
	
	private String programID;
	private String name;
	private int quota;
	private int[] rol; //important that it is in ascending order
	private Resident[] matchedResidents;
	int pointer; //points at the first null cell in matchedResidents
	
	// constructs a Program
    public Program(String id, String n, int q) {
	
		programID= id;
		name= n;
		quota= q;
		pointer=0;
	}

    // the rol in order of preference
	public void setROL(int[] rol) {
		
		this.rol= rol;
	}
	
	// string representation
	public String toString() {
      
       return "["+programID+"]: "+name+" {"+ quota+ "}" +" ("+rol.length+")";	  
	}

	public boolean member(int residentID){
		//do we have to optimize time-complexity
		for (int i=0;i<rol.length;i++){
			if (rol[i]==residentID){
				return true;
			}
		}
		return false;
	}

	public int rank(int residentID){
		for (int i=0;i<rol.length;i++){
			if (rol[i]==residentID){
				return i+1; //0 cannot be a rank
			}
		}
		return -1; //if resident not in the list
	}

	public Resident leastPreferred(){
		int leastPref=0;
		for (int i=0;i<matchedResidents.length;i++){
			if (member(matchedResidents[i].getID()) 
				&& matchedResidents[i].getRank()>leastPref){
				leastPref=i;//basically finding the maximum. A larger rank means they are less preferred
			}
		}
		return matchedResidents[leastPref];
	}

	public void addResident(Resident resident){
		//if has not reached quota
		if (pointer+1!=quota){
			matchedResidents[pointer]=resident;
			resident.setMatchedProgram(this);
			resident.setMatchedRank(rank(resident.getID()));
			pointer++;
			return;
		}
		Resident least=leastPreferred();
		if(rank(resident.getID())<rank(least.getID())){
			rol[least.getRank()]=resident.getID();
			least.setMatchedProgram(null);
			least.setMatchedRank(-1);
		}
	}
}