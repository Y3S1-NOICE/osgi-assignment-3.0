package bill.service;

import java.sql.ResultSet;

public interface IBillService {
	public void payAllMedicines(String nic);
	public void payAllLabTests(String nic);
	public ResultSet seeUnpaidLabTests(String nic);
	public ResultSet seeUnpaidMedicines(String nic);
}
