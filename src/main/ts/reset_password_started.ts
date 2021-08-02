$("#formResetPassword").kendoForm({
	validatable: {
		validateOnBlur: true,
		validationSummary: true,
		errorTemplate: "Error",
	},
	orientation: "vertical",
	formData: {
		email: "",
	},
	items: [
		{
			field: "password",
			label: "New password: ",
			validation: { required: true }
		},
		{
			field: "repeatPassword",
			label: "Repeat password: ",
		}
	],
	buttonsTemplate: `
	<button class="k-button k-primary k-form-submit" type="submit">Reset password</button>
	<a href="/login"> <button class="k-button" type="button">Cancel</button></a>
	`
});

$("#formRegister").kendoValidator({
	messages: {
		repeatPassword: "The passwords do not match."
	},
	rules: {
		repeatPassword: (input: JQuery) => {
			if (input.is("[id='repeatPassword']")) {
				return input.val() === $("#password").val();
			}
			return true;
		}
	}
})

$("#repeatPassword").attr("type", "password");
$("#password").attr("type", "password");