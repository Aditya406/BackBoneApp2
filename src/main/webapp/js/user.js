define([
  'jquery',
  'underscore',
  'backbone',
],
function($, _, Backbone) {
	var User = Backbone.Model.extend({
		
	});
	var UserView = Backbone.View.extend({
		el: $("#someElement"),
		
		events : {
			"click #login"    : "index",
			"click #register" : "register",
			"click #userdata" : "userdata",
			"click #edit"     : "edituser"
				
		},
		initialize : function(options) {
			this.render();
		},
		render : function() {
				
		},
		index : function(e) {
			 var href = "login";
		Backbone.history.navigate(href, true);
		},
		register : function(e) {
			 var href = "register";
		Backbone.history.navigate(href, true);
		},
		userdata : function(e) {
			 var href = "userdata";
		Backbone.history.navigate(href, true);
		},
		edituser : function(e) {
			 var href = "edit";
		Backbone.history.navigate(href, true);
		}
	
	});
	var userview = new UserView();
});
