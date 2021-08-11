jQuery.ajax({
	url: "/e/api/v1/groups",
}).done((data: JSON, textStatus: string) => {

	console.log(data);

	//@ts-ignore
	let groups : {id : number, name: string, members: string[]}[] = data;

	for (let index = 0; index < groups.length; index++) {

		const template = kendo.template($("#groupsMemberList").html(), { paramName: "members" })
		const newDivTemplate = template(groups[index].members);

		const newDiv = $(newDivTemplate);
		newDiv.appendTo("#groupsView");
		newDiv.kendoExpansionPanel({
			title: groups[index].name,
			expanded: false,

		})

	}
});
