
function parseDate(input: any) {
	for (const key in input) {

		if (input[key] instanceof Date) {

			const day = (input[key] as Date).getDate();
			(input[key] as Date).setUTCHours(0, 0, 0, 0);
			(input[key] as Date).setDate(day);
			input[key] = (input[key] as Date).toISOString();
		}
	}

	console.log(input);

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

$("#grid").kendoGrid({
	columns: [
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
			title: 'Enable',
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
		},
		{
			title: 'Commands',
			width: '20%',
			//@ts-ignore
			command: ["destroy"],
			minResizableWidth: 120,
		}
	],
	dataSource: {
		transport: {
			read: {
				url: "/i/api/v1/users/",
				data: {
					format: "json"
				},
				type: "GET"
			},
			create: {
				url: "/i/api/v1/users/",
				type: "POST",
				data: (input: any) => parseDate(input),
				dataType: "json",

			},
			destroy: {
				url: (data: any) => "/i/api/v1/users/" + data.id,
				type: "DELETE",

			},
			update: {
				url: (data: any) => "/i/api/v1/users/" + data.id,
				data: (input: any) => parseDate(input),
				dataType: "json",
				type: "PUT",
			},
			batch: true,
		},
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
		}
	},
	scrollable: true,
	selectable: true,
	toolbar: ["create", "save", "cancel", "search"],
	editable: true,
	filterable: true,
	groupable: true,
	sortable: true,
})