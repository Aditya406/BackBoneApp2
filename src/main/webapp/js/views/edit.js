define([ 'jquery', 'underscore', 'backbone','collection/EditCollection','collection/UpdateCollection',
	
		'text!templates/edit.html' ], function($, _, Backbone,EditCollection,UpdateCollection,
				edittemplate) {
	var UserView = Backbone.View.extend({
		el:'.userdiv',
		initialize: function (options) {
			this.username=options.name
			this.render();
		},
		events : {
			"click #update" : "updateuser",
		},
		render : function() {
			var that = this;
			var cls = new EditCollection();
			cls.save({},{
				success : function(response) {
					var template = _.template(edittemplate);
					that.$el.html(template({data:response.toJSON()}));
				},
				data : $.param({username : that.username}),
			});
		},
		updateuser : function(e) {
			var that = this;
			e.preventDefault();
			var ulc = new UpdateCollection ()
			self.username = $('#username').val(),
			self.email = $('#email').val(),
			ulc.save({},{
				success : function(response) {
					if (response.attributes.success == true) {
						$('.editdata').html("update successfully done").css(
								"color", "green"); 
						$('#username').val("")
						$('#email').val("")
					}
				},
				
			});

		}
	});
	return UserView;
});