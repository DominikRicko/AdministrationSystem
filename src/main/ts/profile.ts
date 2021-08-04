const personalInfo = {
	name: "Dominik",
	surname: "Ričko",
	birthdate: "28.08.1998.",
	address: "whatever 123",
	email: "dominik.ricko@nth.ch"
}

const credentialsInfo = {
	username: "dricko",
	account_expiration_date: "whatever",
	credentials_expiration_date: "whatever",
	enabled: "yes"
}

const groupsInfo = {
	groups :[
		"Admins", "Managers", "Users", "Tester"
	]
}


const privilegesInfo = {
	privileges:[
		"READ_PROFILES", "READ_GROUPS", "READ_AUTHORITIES", "WRITE_PROFILES", "WRITE_GROUPS","WRITE_AUTHORITIES"
	]
}

const templatePersonal = kendo.template($("#personalInfo").html());
const templateCredentials = kendo.template($("#credentialsInfo").html());
const templateGroups = kendo.template($("#groupsInfo").html());
const templatePrivileges = kendo.template($("#privilegesInfo").html());

$("#tilelayout").kendoTileLayout({
	containers: [{
		colSpan: 1,
		rowSpan: 2,
		header: {
			text: "Personal Information"
		},
		bodyTemplate: templatePersonal(personalInfo)
	}, {
		colSpan: 1,
		header: {
			text: "Security"
		},
		bodyTemplate: templateCredentials(credentialsInfo)
	},
	{
		colSpan: 1,
		header: {
			text: "Groups"
		},
		bodyTemplate: templateGroups(groupsInfo)
	},
	{
		colSpan: 1,
		header: {
			text: "Privileges"
		},
		bodyTemplate: templatePrivileges(privilegesInfo)
	}],
	columns: 3,
	rows: 3,
	height: 'auto',
	reorderable: true,
	resizable: true,

});