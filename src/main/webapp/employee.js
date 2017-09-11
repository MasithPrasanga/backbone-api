var Employee = Backbone.Model.extend({
	idAttribute: "id",
	default:{
		firstName: '',
		lastName:'',
		salary:''
	},
	urlRoot:'http://localhost:8080/jersey-api/api/v1/employee'
});

var Employees = Backbone.Collection.extend({
	model:Employee,
	url:'http://localhost:8080/jersey-api/api/v1/employee'
});
var employees = new Employees();


var EmployeeView = Backbone.View.extend({
	model: new Employee(),
	tagName:'tr',
	initialize:function(){
		this.template = _.template($('.employee-list-template').html());
	},
	events:{
		'click .delete-employee':'delete',
		'click .edit-employee':'edit',
		'click .update-employee':'update',
		'click .cancel':'cancel'
	},
	edit:function(){

		this.$('.edit-employee').hide();
		this.$('.delete-employee').hide();
		this.$('.update-employee').show();
		this.$('.cancel').show();

		var firstName = this.$('.firstname').html();
		var lastName = this.$('.lastname').html();
		var salary = this.$('.salary').html();


		this.$('.firstname').html('<input type="text" class="form-control update-firstname" value="'+firstName+'"/>');
		this.$('.lastname').html('<input type="text" class="form-control update-lastname" value="'+lastName+'"/>');
		this.$('.salary').html('<input type="text" class="form-control update-salary" value="'+salary+'" />');

	},
	update:function(){

		this.model.set('firstName',$('.update-firstname').val());
		this.model.set('lastName',$('.update-lastname').val());
		this.model.set('salary',$('.update-salary').val());
		this.model.save();
	},
	delete:function(){
		this.model.destroy();
	},
	cancel:function(){
		employeesView.render();
	},
	render:function(){
		this.$el.html(this.template(this.model.toJSON()));
		return this;
	}

});

var EmployeesView = Backbone.View.extend({
	model:employees,
	el:$('.employee-list'),
	initialize:function(){
		var self = this;
		this.model.on('add',this.render,this);
		this.model.on('change',function(){
			setTimeout(function(){
				self.render();
			},30);
		},this);
		this.model.on('remove',this.render,this);

	},
	render:function(){
		var self = this;
		this.$el.html('');
		_.each(employees.toArray(),function(emp){
			self.$el.append((new EmployeeView({model:emp})).render().$el);
		});

		return this;
	}

});
var employeesView = new EmployeesView();


$(document).ready(function(){
	
	employees.fetch();
	
	$('.add-employee').on('click',function(){
		var employee = new Employee({
			firstName:$('.firstname-input').val(),
			lastName:$('.lastname-input').val(),
			salary:$('.salary-input').val()
		});
		
		employees.add(employee);
		employee.save();
		
		$('.firstname-input').val('');
		$('.lastname-input').val('');
		$('.salary-input').val('');

	});
	
});