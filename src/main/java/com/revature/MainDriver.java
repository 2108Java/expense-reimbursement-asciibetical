package com.revature;

import org.apache.log4j.Logger;

import com.revature.controller.RequestHandler;

import io.javalin.Javalin;

public class MainDriver {

	public static void main(String[] args) {
		final Logger reimbursementLog = Logger.getLogger(MainDriver.class);
		reimbursementLog.info("Application Started!");
		Javalin app = Javalin.create(config -> {
			config.addStaticFiles(staticFiles -> {
				staticFiles.directory = "/public";
			});
			config.enableCorsForAllOrigins();
		}).start(9000);

		RequestHandler.setUpEndpoints(app);
	}

}
