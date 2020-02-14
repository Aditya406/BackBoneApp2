define([ 'jquery', 'underscore', 'backbone', 'collection/LoginCollection',
		'text!templates/login.html' ], function($, _, Backbone,
		LoginCollection, logintemplate) {
	var UserView = Backbone.View.extend({
		el : '#suessdiv',
		initialize : function() {
			this.render();
		},
		events : {
			"click #login" : "onSubmit",
		},

		render : function() {
			var that = this;
			template = _.template(logintemplate);
			$("#suessdiv").empty();
			that.$el.append(template);
			this.$el.html();
			return that;
		},

		onSubmit : function(e) {

			var self = this;
			e.preventDefault();
			var attrs = {
				'username' : $('#username').val(),
				'password' : $('#password').val()
			};
			var cls = new LoginCollection()
			// cls.set({
			self.username = $('#username').val(),
					self.password = $('#password').val()
			// });

			cls.save({}, {
				success : function(response) {
					if (response.attributes.success == true) {
						$('.valid').html(" Login successfully done ").css(
								"color", "green");

					} else {
						$('.valid').html("Invalid UserName & Password").css(
								"color", "red");
					}
				},
				data : $.param({
					username : self.username,
					password : self.password
				}),
			});

		}

	});
	return UserView;
});