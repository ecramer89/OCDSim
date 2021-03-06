package ocdSim;

public class ConcernManager {
	public static final int ORGANIC= 0;
	public static final int BPA= 1;
	public static final int UNFILTERED= 2;
	public static final int MOULDY=3;
    public static final int HOT=4;
	
			
	private static Concern ORGANIC_=new Concern("Organic", .5f);
	private static Concern BPA_=new Concern("BPA", .5f);
	private static Concern HOT_=new Concern("Hot", .5f);
	private static Concern UNFILTERED_=new Concern("Unfiltered", .5f);
	private static Concern MOULDY_=new Concern("Mouldy", .5f); 

	private static Concern[] concerns=new Concern[5];
	
	
	public ConcernManager(){
		concerns[ORGANIC]=ORGANIC_;
		concerns[BPA]=BPA_;
		concerns[UNFILTERED]=UNFILTERED_;
		concerns[MOULDY]=MOULDY_;
		concerns[HOT]=HOT_;
		
	}
	
	public static int getNumConcerns(){
		return concerns.length;
	}
	
	
	
	public Concern getConcern(int concernIndex) throws IllegalArgumentException{
		if(concernIndex<concerns.length&&concernIndex>-1){
			return concerns[concernIndex];
		}
		throw new IllegalArgumentException("Error! Invalid Concern index.");
		
	}
	
	
	

}
