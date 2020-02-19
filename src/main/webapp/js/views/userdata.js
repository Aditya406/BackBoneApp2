define([
  'jquery',
  'underscore',
  'backbone',
  'collection/UserdataCollection',
  'text!templates/userdata.html',
   'text!templates/edit.html'
],function($, _, Backbone,UserdataCollection,userdatatemplate,edittemplate){
 //View
	var UserView = Backbone.View.extend({
       el:'#suessdiv',
		initialize: function () {
			this.render();
		},
		
		 render: function() {
	            var that = this;
	            this.collection = new UserdataCollection();
	            template = _.template(userdatatemplate);
	            this.collection.save({},{
	                success: function(collection, response) {
	                	$("#suessdiv").empty();
	                     that.$el.append(template({data:that.collection.toJSON()}));
	                     return that;
	                 },
	                 error: function(collection, response) {
	                     alert("error");
	                 }
	             });
	            return this;
	        },

	        events : {
				"click #edit" : "edituser",
			},
			
			
			edituser : function(e) {
				event.preventDefault()
			var name=e.currentTarget.getAttribute("value");
				console.log("hiii");
				require(["views/edit"], function(edit) {
			          new edit({name:name})
					});
			
			}
			
	    });
	
	return UserView;
});