const groups = [
	{
		name: "Group1",
		members: [
			"User1",
			"User2",
			"User3",
		]
	},{
		name: "Group2",
		members: [
			"User4",
			"User5"
		]
	}
]

for (let index = 0; index < groups.length; index++) {

	const template = kendo.template($("#groupsMemberList").html(), {paramName: "members"})
	const newDivTemplate = template(groups[index].members);

	const newDiv = $(newDivTemplate);
	newDiv.appendTo("#groupsView");
	newDiv.kendoExpansionPanel({
		title: groups[index].name,
		expanded: false,
		
	})

}

