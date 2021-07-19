$("#formLogin").kendoForm({
    validatable: { validationSummary: true},
    orientation: "vertical",
    formData:{
        username: "",
        password: "",
    },
    items: [
        {
            field: "username",
            label: "Username: ",
            validation: { required: true }
        },
        {
            field: "password",
            editor: "MaskedTextBox",
            label: "Password: ",
            validation: { required: true }
        }
    ],
    buttonsTemplate: `
    <button class="k-button k-primary k-form-submit" type="submit">Login</button>
    <button class="k-button">I forgot my password</button>
    <button class="k-button">Register</button>
    `
})