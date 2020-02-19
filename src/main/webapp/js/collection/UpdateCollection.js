define([
  'jquery',
  'underscore',
  'backbone',
],function($, _, Backbone){	 
	 var User = Backbone.Model.extend({
			urlRoot : "saveupdate",
		}); 
	 return User
});