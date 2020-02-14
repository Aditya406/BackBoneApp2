define([
  'jquery',
  'underscore',
  'backbone',
  'user',
],
function($, _, Backbone, Router,user){
var AppRouter = Backbone.Router.extend({
	routes : {
		"register" : "registerView",
		"login"    : "indexView",
		"userdata" : "userdataViwe"
	},
	registerView : function() {
		require([ "views/register" ], function(register) {
			new register()
		});
	},
	indexView : function(e) {
		require([ "views/Login" ], function(login) {
          new login()
		});
	},
	
	userdataViwe : function(e) {
		require(["views/userdata"], function(userdata) {
          new userdata()
		});
	}
});

var appRouter = new AppRouter();
Backbone.history.start();
});