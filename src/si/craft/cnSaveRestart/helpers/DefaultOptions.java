package si.craft.cnSaveRestart.helpers;

import si.craft.cnSaveRestart.main;


public class DefaultOptions {
	private main ec;

	public DefaultOptions(main ec) {
		this.ec = ec;
	}

	public Object getConfigOption(String s) {
		return ec.getMainConfig().get(s);
	}

	public Object getConfigOption(String s, Object def) {
		return ec.getMainConfig().get(s, def);
	}

	public void setDefaultValues(YAMLConfig mainConfig) {
		try {
			/************************************************************************************************
			 * MESSAGES
			 ***********************************************************************************************/
			mainConfig.set("messages.NoPermission", mainConfig.get("messages.NoPermission", "&aCheck out the commands available to donors @ &e/donate"), "Messages");
			/************************************************************************************************
			 * WRITE CONFIG FILE
			 ***********************************************************************************************/
			mainConfig.saveConfig();
		} catch (Exception e) {
			Helper.warning("Failed to generate default Configuration File.");
		}
	}
}
