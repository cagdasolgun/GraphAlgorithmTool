package logger;

import java.util.logging.Logger;

public class GraphLogger {

	private static Logger logger;

	public static Logger getLogger() {
		if (logger == null) {
			logger = Logger.getLogger("GraphLogger");
		}
		return logger;
	}

}
