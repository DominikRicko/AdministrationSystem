jQuery.ajax({
	url: "/e/api/v1/profile",
}).done((data: JSON, textStatus: string) =>{
	
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
			bodyTemplate: templatePersonal(data)
		}, {
			colSpan: 1,
			header: {
				text: "Security"
			},
			bodyTemplate: templateCredentials(data)
		},
		{
			colSpan: 1,
			header: {
				text: "Groups"
			},
			bodyTemplate: templateGroups(data)
		},
		{
			colSpan: 1,
			header: {
				text: "Privileges"
			},
			bodyTemplate: templatePrivileges(data)
		}],
		columns: 3,
		rows: 3,
		height: 'auto',
		reorderable: true,
		resizable: true,
	
	});
});