package doctorchannel.util;

public class DBQueries {

	public static final String CREATE_CHANNEL_TABLE_IF_NOT_EXSIST =
			"CREATE TABLE IF NOT EXISTS channel"
		            + "  (channelId 	INT NOT NULL AUTO_INCREMENT PRIMARY KEY,"
		            + "   dnic								VARCHAR(20),"
		            + "   pnic								VARCHAR(20),"
					+ "   date								VARCHAR(20));";
	
	public static final String DELETE_CHANNEL_RECORD = 
			 "DELETE FROM channel WHERE channelId = ? ";
	
	public static final String GET_ALL_CHANNELS = 
			"SELECT * FROM channel WHERE dnic = ?";
	
	public static final String ADD_CHANNEL_RECORD = 
			 "INSERT INTO channel (dnic, pnic, date) VALUES (?, ?, ?);";
}
