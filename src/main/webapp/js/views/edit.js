define([ 'jquery', 'underscore', 'backbone','collection/EditCollection','collection/UpdateCollection',
	
		'text!templates/edit.html' ], function($, _, Backbone,EditCollection,UpdateCollection,
				edittemplate) {
	var UserView = Backbone.View.extend({
		el:'.userdiv',
		initialize: function (options) {
			this.a_id=options.name
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
				data : $.param({a_id : that.a_id}),
			});
		},
		updateuser : function(e) {
			var self = {};
			var that = this;
			e.preventDefault();
			var ulc = new UpdateCollection ()
			/*self.a_id = $('#a_id').val(),*/
			self.username = $('#username').val(),
			self.email = $('#email').val(),
			ulc.save({},{
				success : function(response) {
					if (response.attributes.success == true) {
						$('.editdata').html("update successfully done").css(
								"color", "green"); 
						/*$('#a_id').val("")*/
						$('#username').val("")
						$('#email').val("")
					}
				},
				
				data : $.param({a_id:that.a_id,username : self.username,email :self.email}),
			});
			
		

		}
	});
	return UserView;
});