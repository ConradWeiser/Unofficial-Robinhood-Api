package conrad.weiser.robinhood.api.endpoint.oauth.methods;

import conrad.weiser.robinhood.api.endpoint.oauth.Oauth;
import conrad.weiser.robinhood.api.endpoint.oauth.data.OauthTokenMigrationElement;
import conrad.weiser.robinhood.api.request.RequestMethod;

public class MigrateAuthToOauth extends Oauth {

	public MigrateAuthToOauth()  {

		this.setUrlBase("https://api.robinhood.com/oauth2/migrate_token/");

		this.setMethod(RequestMethod.POST);

		//Declare what the response should look like
		this.setReturnType(OauthTokenMigrationElement.class);
	}
}
