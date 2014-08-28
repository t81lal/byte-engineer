package eu.bibl.bio;

/**
 * Type of Jar Stored.
 * @author Bibl
 */
public enum JarType {
	
	/** Local file **/
	FILE(0),
	/** External URL **/
	WEB(1);
	
	JarType(int type) {
		
	}
	
	/** Gets the prefix for the JarURLConnection. **/
	public static String prefix(JarType type) {
		switch (type) {
			case FILE:
				return "file:";
			case WEB:
				return "";
			default:
				return "";
		}
	}
}