define([
  'jquery',
  'underscore',
  'backbone',
  'collection/UserdataCollection',
  'text!templates/userdata.html'
],function($, _, Backbone,UserdataCollection,userdatatemplate){
 //View
	var UserView = Backbone.View.extend({
		el:'#suessdiv',
		initialize: function () {
			this.render();
		},
		
		render : function() {
			var that = this;	
			template = _.template(userdatatemplate);
			
			var cls = new UserdataCollection()
			cls.save({
				success : function(response) {
			var data=JSON.parse(response);
			var finalData = data.data
						$("#suessdiv").empty();
						 that.$el.append(template({data:finalData}));
				},
			});
			
		},
		
		 events : {
	            "click #userdata" : "adddata"
	            	
	        },
	        adddata : function(e) {

				var self = this;
				e.preventDefault();
				var attrs = {
					'username' : $('#username').val(),
					'email' : $('#email').val(),
					'password' : $('#password').val()
				};
				var cls = new RegisterCollection()
				self.username = $('#username').val(),
				self.email = $('#email').val(),
				self.password = $('#password').val()

				cls.fetch({
					success : function(response) {
						
						if (response.attributes.success == true) {
							 that.$el.append(that.template({data:response.attributes}));
						}
					},
					data : $.param({username : self.username,email :self.email, password : self.password }),
				});

			}

	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	        
	      /*  adddata : function(event) {
	        	var self = this;
				e.preventDefault();
	        	$('#suessdiv').empty();
	        	this.undelegateEvents();
	        	var cls = new UserdataCollection()
	            this.cls.fetch({
	                success:function (response) {
	                	
	                	 that.$el.append(that.template({data:response.attributes}));
	                	 $(this.el).empty();
	                	 $("#rowTemplate").empty();
	                }
	            });
	            
	        },*/
	
	});
	return UserView;
});