define([
  'jquery',
  'underscore',
  'backbone',
  'collection/RegisterCollection',
  'text!templates/register.html'
],function($, _, Backbone,RegisterCollection,registertemplate){
 //View
	var UserView = Backbone.View.extend({
		el:'#suessdiv',
		initialize: function () {
			this.render();
		},
		events : {
			"click #submit" : "submit",
		},
	
		render : function() {
			var that = this;	
			template = _.template(registertemplate);
				$("#suessdiv").empty();
				that.$el.append(template);
				this.$el.html();
			  return that;
			
		},
		
		submit : function(e) {

			var self = this;
			e.preventDefault();
			var attrs = {
				'username' : $('#username').val(),
				'email' : $('#email').val(),
				'password' : $('#password').val()
			};
			var cls = new RegisterCollection()
//			cls.set({
			self.username = $('#username').val(),
			self.email = $('#email').val(),
			self.password = $('#password').val()
//			});

			cls.save({},{
				success : function(response) {
					
					if (response.attributes.success == true) {
						$('.reg').html(" Register successfully done ").css(
								"color", "green"); 
						$('#username').val("")
						$('#email').val("")
						$('#password').val("")
					}
					/*else{
						$('.reg').html("user alredy existed").css("color","red");
					} */
				},
				data : $.param({username : self.username,email :self.email, password : self.password }),
			});

		}
	});
	return UserView;
});