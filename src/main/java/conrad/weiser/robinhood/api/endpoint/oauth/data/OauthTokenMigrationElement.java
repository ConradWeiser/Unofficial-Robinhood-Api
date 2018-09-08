package conrad.weiser.robinhood.api.endpoint.oauth.data;

public class OauthTokenMigrationElement {

	/**
	 * The type of token which the migration request returns. This almost always will be 'Bearer'
	 */
	private String token_type;

	/**
	 * The OAuth access token returned from the request
	 */
	private String access_token;

	/**
	 * The access token lifetime in seconds(?)
	 */
	private int expires_in;

	/**
	 * The token required to refresh the OAuth token once it's expired
	 */
	private String refresh_token;

	/**
	 * The scope of the token. This usually will be 'internal'
	 */
	private String scope;

	public String getToken_type() {
		return token_type;
	}

	public String getAccess_token() {
		return access_token;
	}

	public int getExpires_in() {
		return expires_in;
	}

	public String getRefresh_token() {
		return refresh_token;
	}

	public String getScope() {
		return scope;
	}
}
