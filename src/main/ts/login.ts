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
            label: "Password: ",
            validation: { required: true }
        }
    ],
    buttonsTemplate: `
    <button class="k-button k-primary k-form-submit" type="submit">Login</button>
    <a href="/resetPassword"><button class="k-button" type="button">I forgot my password</button></a>
    <a href="/register"><button class="k-button" type="button">Register</button></a>
    `
});

$("#password").attr("type","password");