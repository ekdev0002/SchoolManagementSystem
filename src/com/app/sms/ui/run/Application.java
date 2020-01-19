package com.app.sms.ui.run;

import com.app.sms.ui.controllers.impl.UILoginController;
import com.app.sms.ui.impl.UILogin;

public class Application {

	public static void main(String[] args) {
		seConnecter ();
	}

	private static void seConnecter () {
		UILogin uILogin = new UILogin () ;
		UILoginController uILoginController = new UILoginController ( uILogin );	
		Thread setUpThread = new Thread ( new Runnable () {
			@Override
			public void run() {
				uILoginController.setUp () ;
			}
		});
		setUpThread.start();
		uILogin.setVisible (true);
	}
}
