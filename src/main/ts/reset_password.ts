$("#formResetPassword").kendoForm({
    validatable: { validationSummary: true},
    orientation: "vertical",
    formData:{
        email: "",
    },
    items: [
        {
            field: "email",
            label: "Email: ",
            validation: { email: true, required: true }
        },
    ],
    buttonsTemplate: `
    <button class="k-button k-primary k-form-submit" type="submit">Reset password</button>
    <a href="/login"> <button class="k-button" type="button">Cancel</button></a>
    `
});