$("#grid").kendoGrid({
    columns:[
        {
            field: 'name',
            width: '14%',
            title: 'Name',
        },
        {
            field: 'surname',
            title: 'Surname',
            width: '14%',
        },
        {
            field: 'birthdate',
            title: 'Birthdate',
            width: '14%',
            format: "{0:dd.MM.yyyy}",
        },
        {
            field: 'groups',
            title: 'Groups',
            width: '14%',
        },
        {
            field: 'email',
            title: 'Email',
            width: '25%',
        },
        {
            field: 'enabled',
            title: 'Enable',
            width: '10%',
        },
        {
            field: 'account_expiration_date',
            title: 'Account Expires at',
            width: '14%',
            format: "{0:dd.MM.yyyy}",
        },
        {
            field: 'credentials_expiration_date',
            title: 'Credentials Expire at',
            width: '14%',
            format: "{0:dd.MM.yyyy}",
        },
        {
            title: 'Commands',
            width: '20%',
            //@ts-ignore
            command:["destroy"]
        }
    ],
    dataSource: {
       transport:{
           read: {
                url: "/getUsers",
                data: {
                    format: "json"
                }
            },
            create: {
                url: "/createUsers",
                type: "POST",
            },
            destroy: {
                url: "/removeUsers",
                type: "POST",
            },
            batch: true,
       },
       schema: {
           model: {
               id: 'id',
               fields: {
                   id: {type: 'string'},
                   name: {type: 'string'},
                   surname: {type: 'string'},
                   birthdate: {type: 'date'},
                   groups: {type: 'string'},
                   email: {type: 'string'},
                   enabled: {type: 'boolean'},
                   account_expiration_date: {type: 'date'},
                   credentials_expiration_date: {type: 'date'},
               }
           },
       }
    },
    scrollable: true,
    selectable: true,
    toolbar: ["create", "save", "cancel" ],
    editable: true,
});