$("#formRegister").kendoForm({
    validatable: { validationSummary: true},
    orientation: "vertical",
    formData:{
        email: "",
        username: "",
        password: "",
        repeatPassword: "",
    },
    items: [
        {
            field: "email",
            label: "Email: ",
            validation: { email: true, required: true }
        },
        {
            field: "username",
            label: "Username: ",
            validation: { required: true }
        },
        {
            field: "password",
            label: "Password: ",
            validation: { required: true }
        },
        {
            field: "repeatPassword",
            label: "Repeat password: ",
            type: "password",
            validation: { required: true }
        }
    ],
    buttonsTemplate: `
    <button class="k-button k-primary k-form-submit" type="submit">Register</button>
    <a href="/login"> <button class="k-button" type="button">Cancel</button></a>
    `
})
$("#formRegister").kendoValidator({
    messages:{
        repeatPassword: "The passwords do not match."
    },
    rules:{
        repeatPassword: (input : JQuery) => {
            if (input.is("[id='repeatPassword']")){
                return input.val() === $("#password").val();
            }
            return true;
        }
    }
})

$("#repeatPassword").attr("type", "password");
$("#password").attr("type", "password");