package robinhood.api.endpoint.account.data;

import java.net.URL;

public class AccountHolderEmploymentElement {
	
	private String employer_address;
	private String employer_city;
	private String employer_name;
	private String employer_state;
	private String employer_zipcode;
	private String employment_status;
	private String occupation;
	
	//TODO: updated_at
	private URL user;
	private int years_employed;
	
	/**
	 * @return the employer_address
	 */
	public String getEmployerAddress() {
		return employer_address;
	}
	/**
	 * @return the employer_city
	 */
	public String getEmployerCity() {
		return employer_city;
	}
	/**
	 * @return the employer_name
	 */
	public String getEmployerName() {
		return employer_name;
	}
	/**
	 * @return the employer_state
	 */
	public String getEmployerState() {
		return employer_state;
	}
	/**
	 * @return the employer_zipcode
	 */
	public int getEmployerZipcode() {
		return Integer.valueOf(employer_zipcode);
	}
	/**
	 * @return the employment_status
	 */
	public String getEmploymentStatus() {
		return employment_status;
	}
	/**
	 * @return the occupation
	 */
	public String getOccupation() {
		return occupation;
	}
	/**
	 * @return the user
	 */
	public URL getUser() {
		return user;
	}
	/**
	 * @return the years_employed
	 */
	public int getYearsEmployed() {
		return years_employed;
	}

}
