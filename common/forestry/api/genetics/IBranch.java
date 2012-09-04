package forestry.api.genetics;

public interface IBranch {
	/**
	 * @return Unique String identifier.
	 */
	String getUID();
	
	/**
	 * @return Localized branch name for user display.
	 */
	String getName();
	
	/**
	 * A branch approximates a "subgenus" in real life. Real life examples: "Micrapis", "Megapis"
	 * 
	 * @return flavour text (may be null)
	 */
	String getScientific();
	
	/**
	 * @return Localized description of this branch. (May be null.)
	 */
	String getDescription();
	
	/**
	 * @return Members of this branch.
	 */
	IAlleleSpecies[] getMembers();
	/**
	 * Used by the allele registry to populate internal collection of branch members on the fly.
	 * @param species 
	 */
	void addMember(IAlleleSpecies species);
}
