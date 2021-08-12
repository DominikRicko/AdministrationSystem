import CreateError from "./messages/errorbox";

function parseDate(input: any) {
	for (const key in input) {

		if (input[key] instanceof Date) {

			input[key] = (input[key] as Date).toISOString();
		}
	}
	return JSON.stringify(input);
}

function checkEmail(val: string) {

	var re = /(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|"(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21\x23-\x5b\x5d-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])*")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\x01-\x08\x0b\x0c\x0e-\x1f\x21-\x5a\x53-\x7f]|\\[\x01-\x09\x0b\x0c\x0e-\x7f])+)\])/;
	if (val == "") {
		return false;
	} else if (val.search(re) == -1) {
		return false;
	}
	return true;
}

const gridColumns : kendo.ui.GridColumn[] = [
	{
		field: 'name',
		width: '14%',
		title: 'Name',
		minResizableWidth: 70,
	},
	{
		field: 'surname',
		title: 'Surname',
		width: '14%',
		minResizableWidth: 70,
	},
	{
		field: 'birthdate',
		title: 'Birthdate',
		width: '14%',
		format: "{0:dd.MM.yyyy}",
		minResizableWidth: 70,
	},
	{
		field: 'groups',
		title: 'Groups',
		width: '14%',
		minResizableWidth: 70,
	},
	{
		field: 'email',
		title: 'Email',
		width: '25%',
		minResizableWidth: 150,
	},
	{
		field: 'username',
		title: 'Username',
		width: '25%',
		minResizableWidth: 150,
	},
	{
		field: 'enabled',
		title: 'Enabled',
		width: '10%',
		minResizableWidth: 60,
	},
	{
		field: 'account_expiration_date',
		title: 'Account Expires at',
		width: '14%',
		format: "{0:dd.MM.yyyy}",
		minResizableWidth: 70,
	},
	{
		field: 'credentials_expiration_date',
		title: 'Credentials Expire at',
		width: '14%',
		format: "{0:dd.MM.yyyy}",
		minResizableWidth: 70,
	}
];

const transport: kendo.data.DataSourceTransport = {};
transport.read = {
	url: "/i/api/v1/users/",
	data: {
		format: "json"
	},
	type: "GET"
};

const gridToolbar : string[] | kendo.ui.GridToolbarItem[] = ['search'];

const grid = $("#grid");
if (grid.attr("write") == "true") {

	gridColumns.push({
		title: 'Commands',
		width: '20%',
		//@ts-ignore
		command: ["destroy"],
		minResizableWidth: 120,
	});

	transport.create = {
		url: "/i/api/v1/users/",
		type: "POST",
		data: (input: any) => parseDate(input),
		dataType: "json"
	};

	transport.destroy = {
		url: (data: any) => "/i/api/v1/users/" + data.id,
		type: "DELETE"
	};

	transport.update = {
		url: (data: any) => "/i/api/v1/users/" + data.id,
		data: (input: any) => parseDate(input),
		dataType: "json",
		type: "PUT",
	};

	//@ts-ignore
	transport.batch = true;

	gridToolbar.push("create", "save", "cancel");

}

grid.kendoGrid({
	columns: gridColumns,
	dataSource: {
		transport: transport,
		schema: {
			model: {
				id: 'id',
				fields: {
					id: { type: 'string' },
					name: { type: 'string' },
					surname: { type: 'string' },
					birthdate: { type: 'date' },
					groups: { type: 'string' },
					email: {
						type: 'string',
						validation: {
							required: true,
							customRule: function (input: any) {
								return checkEmail(input.val());
							}
						}
					},
					username: { type: 'string' },
					enabled: { type: 'boolean' },
					account_expiration_date: { type: 'date' },
					credentials_expiration_date: { type: 'date' },
				}
			},
		},
		error: (e: kendo.data.DataSourceErrorEvent) => {
			CreateError(e.xhr.responseText);
		}
	},
	scrollable: true,
	selectable: true,
	toolbar: gridToolbar,
	editable: grid.attr("write") == "true",
	filterable: true,
	groupable: true,
	sortable: true,
})